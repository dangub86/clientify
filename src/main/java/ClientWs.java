/*

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

import it.aubay.posteitaliane.concepts.FlussoBPM;
import it.aubay.posteitaliane.concepts.PraticaBPM;
import it.aubay.posteitaliane.internal.config.MutualProperties;

public interface ClientService_WS {

    default boolean callService(PraticaBPM pratica) throws Exception {
        boolean toRetry = true;
        FlussoBPM flusso = pratica.getFlussoBPM();
        try {
            toRetry = handleResponse(sendRequest(flusso, createRequest(pratica)));
        } catch (Exception e) {
            esitoERROR();
            throw(e);
        }
        return toRetry;
    };

    <T> T createRequest(PraticaBPM pratica);

    default <T, E, P> E sendRequest(FlussoBPM flusso, T request) throws PortConfigurationException {
        P port = getPort();
        configureWsPort(port, flusso);
        E response = call(port, request);
        return response;
    }

    <P, T, E> E call(P port, T request);

    default <P> void configureWsPort(P port, FlussoBPM flusso) throws PortConfigurationException {
        WsPort<P> wsPort = new WsPortMutual<P>(port, getMutualProperties());
        try {
            wsPort.configurePort(getEndpoint(), flusso);
        } catch (UnrecoverableKeyException | KeyManagementException | NoSuchAlgorithmException | KeyStoreException
                | CertificateException | IOException e) {
            // Translating exception
            throw new PortConfigurationException("Errore nella configurazione della porta del servizio", e);
        }
    }

    <P> P getPort();

    MutualProperties getMutualProperties();

    String getEndpoint();

    <E> boolean handleResponse(E response) throws Exception;

    void esitoERROR();


}*/
