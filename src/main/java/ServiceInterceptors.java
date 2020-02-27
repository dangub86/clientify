
import org.apache.cxf.interceptor.Interceptor;
import org.apache.cxf.message.Message;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ServiceInterceptors {
    List<Interceptor<? extends Message>> outInterceptors = Collections.emptyList();
    List<Interceptor<? extends Message>> inInterceptors = Collections.emptyList();
    List<Interceptor<? extends Message>> outFaultInterceptors = Collections.emptyList();
    List<Interceptor<? extends Message>> inFaultInterceptors = Collections.emptyList();

    private ServiceInterceptors() {

    }

    public List<Interceptor<? extends Message>> getOutInterceptors() {
        return new ArrayList<>(outInterceptors);
    }

    public List<Interceptor<? extends Message>> getInInterceptors() {
        return new ArrayList<>(inInterceptors);
    }

    public List<Interceptor<? extends Message>> getOutFaultInterceptors() {
        return new ArrayList<>(outFaultInterceptors);
    }

    public List<Interceptor<? extends Message>> getInFaultInterceptors() {
        return new ArrayList<>(inFaultInterceptors);
    }


    public static final class ServiceInterceptorsBuilder {
        List<Interceptor<? extends Message>> outInterceptors = Collections.emptyList();
        List<Interceptor<? extends Message>> inInterceptors = Collections.emptyList();
        List<Interceptor<? extends Message>> outFaultInterceptors = Collections.emptyList();
        List<Interceptor<? extends Message>> inFaultInterceptors = Collections.emptyList();

        private ServiceInterceptorsBuilder() {
        }

        public static ServiceInterceptorsBuilder aServiceInterceptors() {
            return new ServiceInterceptorsBuilder();
        }

        public ServiceInterceptorsBuilder withOutInterceptors(List<Interceptor<? extends Message>> outInterceptors) {
            this.outInterceptors = outInterceptors;
            return this;
        }

        public ServiceInterceptorsBuilder withInInterceptors(List<Interceptor<? extends Message>> inInterceptors) {
            this.inInterceptors = inInterceptors;
            return this;
        }

        public ServiceInterceptorsBuilder withOutFaultInterceptors(List<Interceptor<? extends Message>> outFaultInterceptors) {
            this.outFaultInterceptors = outFaultInterceptors;
            return this;
        }

        public ServiceInterceptorsBuilder withInFaultInterceptors(List<Interceptor<? extends Message>> inFaultInterceptors) {
            this.inFaultInterceptors = inFaultInterceptors;
            return this;
        }

        public ServiceInterceptors build() {
            ServiceInterceptors serviceInterceptors = new ServiceInterceptors();
            serviceInterceptors.inFaultInterceptors = this.inFaultInterceptors;
            serviceInterceptors.inInterceptors = this.inInterceptors;
            serviceInterceptors.outFaultInterceptors = this.outFaultInterceptors;
            serviceInterceptors.outInterceptors = this.outInterceptors;
            return serviceInterceptors;
        }
    }
}
