package app.pickmaven.clientify.soap;

public class MutualProperties {

    private String trustedKeyStorePath;
    private String trustedKeyStorePwd;
    private String keystorePath;
    private String keyStorePwd;

    private MutualProperties() {}

    private String getTrustedKeyStorePath() {
        return trustedKeyStorePath;
    }

    private String getTrustedKeyStorePwd() {
        return trustedKeyStorePwd;
    }

    private String getKeystorePath() {
        return keystorePath;
    }

    private String getKeyStorePwd() {
        return keyStorePwd;
    }


    private void setTrustedKeyStorePath(String trustedKeyStorePath) {
        this.trustedKeyStorePath = trustedKeyStorePath;
    }

    private void setTrustedKeyStorePwd(String trustedKeyStorePwd) {
        this.trustedKeyStorePwd = trustedKeyStorePwd;
    }

    private void setKeystorePath(String keystorePath) {
        this.keystorePath = keystorePath;
    }

    private void setKeyStorePwd(String keyStorePwd) {
        this.keyStorePwd = keyStorePwd;
    }


    public static final class MutualPropertiesBuilder {
        private String trustedKeyStorePath;
        private String trustedKeyStorePwd;
        private String keystorePath;
        private String keyStorePwd;

        private MutualPropertiesBuilder() {
        }

        public static MutualPropertiesBuilder aMutualProperties() {
            return new MutualPropertiesBuilder();
        }

        public MutualPropertiesBuilder withTrustedKeyStorePath(String trustedKeyStorePath) {
            this.trustedKeyStorePath = trustedKeyStorePath;
            return this;
        }

        public MutualPropertiesBuilder withTrustedKeyStorePwd(String trustedKeyStorePwd) {
            this.trustedKeyStorePwd = trustedKeyStorePwd;
            return this;
        }

        public MutualPropertiesBuilder withKeystorePath(String keystorePath) {
            this.keystorePath = keystorePath;
            return this;
        }

        public MutualPropertiesBuilder withKeyStorePwd(String keyStorePwd) {
            this.keyStorePwd = keyStorePwd;
            return this;
        }

        public MutualProperties build() {
            MutualProperties mutualProperties = new MutualProperties();
            mutualProperties.setTrustedKeyStorePath(trustedKeyStorePath);
            mutualProperties.setTrustedKeyStorePwd(trustedKeyStorePwd);
            mutualProperties.setKeystorePath(keystorePath);
            mutualProperties.setKeyStorePwd(keyStorePwd);
            return mutualProperties;
        }
    }
}
