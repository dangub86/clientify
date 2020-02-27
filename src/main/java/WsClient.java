
import java.util.function.BiFunction;

public class WsClient<P, R> extends Client {
    private P port;

    public WsClient() {
    }


    @Override
    public <P, R, E> Client call(BiFunction<RequestWrapper<E>, P, R> function) {

        System.out.println("Calling the service...");
        response = function.apply(requestWrapper, (P) port);

        responseWrapper = new ResponseWrapper<>(response);
        System.out.println("Wrapping the response in ResponseWrapper...");
        return this;
    }



    @Override
    public WsClient<P, R> configuring(ServiceElement serviceElement) {
        this.port = (P) serviceElement.get();
        return this;
    }



}
