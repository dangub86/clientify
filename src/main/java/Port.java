
import org.apache.cxf.configuration.jsse.TLSClientParameters;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.endpoint.Endpoint;
import org.apache.cxf.frontend.ClientProxy;
import org.apache.cxf.interceptor.Interceptor;
import org.apache.cxf.message.Message;
import org.apache.cxf.transport.http.HTTPConduit;
import org.apache.cxf.transports.http.configuration.HTTPClientPolicy;

import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.xml.ws.BindingProvider;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.util.List;
import java.util.Objects;

public class Port<P> implements ServiceElement {
    private P port;
    private BindingProvider provider;
    protected Endpoint clientEndpoint;
    protected Client client;
    private char[] keyStorePwd;

    private Port(P port) {
        if (Objects.isNull(port)) {
            throw new IllegalArgumentException("Port parameter must not be null");
        }
        System.out.println("Initializing port object...");
        this.port = port;
    }

    public Port<P> withEndpoint(String endpoint) {
        System.out.println("Configuring Endpoint...");
        client = getClient(endpoint);
        return this;
    }

    private Client getClient(String endpoint) {
        provider = (BindingProvider) port;
        provider.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, endpoint);
        client = ClientProxy.getClient(port);
        clientEndpoint = client.getEndpoint();
        return client;
    }

    public Port<P> addingInterceptors(ServiceInterceptors serviceInterceptors) {
        System.out.println("Adding Interceptors...");
        addInterceptors(serviceInterceptors);
        return this;
    }

    private void addInterceptors(ServiceInterceptors serviceInterceptors) {
        List<Interceptor<? extends Message>> outInterceptors = serviceInterceptors.getOutInterceptors();
        List<Interceptor<? extends Message>> inInterceptors = serviceInterceptors.getInInterceptors();
        List<Interceptor<? extends Message>> outFaultInterceptors = serviceInterceptors.getOutFaultInterceptors();
        List<Interceptor<? extends Message>> inFaultInterceptors = serviceInterceptors.getInFaultInterceptors();
        outInterceptors.stream()
                .map(clientEndpoint.getOutInterceptors()::add);

        inInterceptors.stream()
                .map(clientEndpoint.getInInterceptors()::add);

        outFaultInterceptors.stream()
                .map(clientEndpoint.getOutFaultInterceptors()::add);

        inFaultInterceptors.stream()
                .map(clientEndpoint.getInFaultInterceptors()::add);
    }

    public Port<P> mutual(MutualProperties properties) throws NoSuchAlgorithmException, KeyStoreException, CertificateException, IOException, UnrecoverableKeyException {
        System.out.println("Configuring port as mutual...");
        HTTPConduit httpConduit = (HTTPConduit) client.getConduit();
        TLSClientParameters clientParameters = getClientParameters(httpConduit);

        TrustManager[] trustManagers = getTrustManagers(getTrustKeyStore(properties.getTrustedKeyStorePwd(), properties.getTrustedKeyStorePath()));
        KeyManager[] keyManagers = getKieManagers(getKeyStore(properties.getKeyStorePwd(), properties.getKeystorePath()), keyStorePwd);
//		SSLSocketFactory socketFactory = getSSLSocketFactory(trustManagers, keyManagers);

//		clientParameters.setSSLSocketFactory(socketFactory);
        clientParameters.setTrustManagers(trustManagers);
        clientParameters.setKeyManagers(keyManagers);
        clientParameters.setUseHttpsURLConnectionDefaultHostnameVerifier(false);
        clientParameters.setDisableCNCheck(true);

        httpConduit.setTlsClientParameters(clientParameters);
        keyStorePwd = null;

        return this;
    }

    private TLSClientParameters getClientParameters(HTTPConduit httpConduit) {
        HTTPClientPolicy httpClientPolicy = httpConduit.getClient();
        httpClientPolicy.setReceiveTimeout(60000);
        httpClientPolicy.setConnectionTimeout(0);
        TLSClientParameters tlsClientParameters = httpConduit.getTlsClientParameters();

        if (Objects.isNull(tlsClientParameters)) {
            tlsClientParameters = new TLSClientParameters();
            tlsClientParameters.setSecureSocketProtocol("TLSv1.2");
        }

        return tlsClientParameters;
    }

    private KeyStore getTrustKeyStore(String trustedKeystorePwd, String trustedKeystorePath) throws KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException {
        KeyStore trustedKeyStore = KeyStore.getInstance("JKS");
        File keystoreFile = new File(trustedKeystorePath);
        InputStream readStreamTrusted = new FileInputStream(keystoreFile);
        trustedKeyStore.load(readStreamTrusted, decrypt(trustedKeystorePwd));
        readStreamTrusted.close();

        return trustedKeyStore;
    }

    private KeyStore getKeyStore(String keystorePwd, String keystorePath) throws KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException {
        KeyStore trustedKeyStore = KeyStore.getInstance("JKS");
        File keystoreFile = new File(keystorePath);
        InputStream readStream = new FileInputStream(keystoreFile);
        keyStorePwd = decrypt(keystorePwd);
        trustedKeyStore.load(readStream, keyStorePwd);
        readStream.close();

        return trustedKeyStore;
    }

    private TrustManager[] getTrustManagers(KeyStore trustedKeyStore) throws NoSuchAlgorithmException, KeyStoreException {
        TrustManagerFactory trustFactory =
                TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
        trustFactory.init(trustedKeyStore);
        return trustFactory.getTrustManagers();
    }

    private KeyManager[] getKieManagers(KeyStore keyStore, char[] keyStorePwd) throws UnrecoverableKeyException, KeyStoreException, NoSuchAlgorithmException {
        KeyManagerFactory keyFactory =
                KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
        keyFactory.init(keyStore, keyStorePwd);
        return keyFactory.getKeyManagers();
    }

    private char[] decrypt(String keystorePwd) {
        if (DebugUtils.isDebug()) {
            return keystorePwd.toCharArray();
        }
        PasswordManager pwdMan= new PasswordManager(keystorePwd);
        return pwdMan.getPassword().toCharArray();
    }

    public P get() {
        System.out.println("Getting the port...");
        return port;
    }



    public static class PortBuilder  {

        public static <P> Port<P> port(P port) {
            return new Port<P>(port);
        }
    }



}


