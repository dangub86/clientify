
public class MutualProperties {

    private String trustedKeyStorePath;
    private String trustedKeyStorePwd;
    private String keystorePath;
    private String keyStorePwd;

    public MutualProperties(String trustedKeyStorePath, String trustedKeyStorePwd, String keystorePath, String keyStorePwd) {
        this.trustedKeyStorePath = trustedKeyStorePath;
        this.trustedKeyStorePwd = trustedKeyStorePwd;
        this.keystorePath = keystorePath;
        this.keyStorePwd = keyStorePwd;
    }

    public String getTrustedKeyStorePath() {
        return trustedKeyStorePath;
    }

    public String getTrustedKeyStorePwd() {
        return trustedKeyStorePwd;
    }

    public String getKeystorePath() {
        return keystorePath;
    }

    public String getKeyStorePwd() {
        return keyStorePwd;
    }



}
