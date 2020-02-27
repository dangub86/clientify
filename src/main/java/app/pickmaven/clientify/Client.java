package app.pickmaven.clientify;

import app.pickmaven.clientify.rest.RestClient;
import app.pickmaven.clientify.soap.WsClient;

import java.util.function.BiFunction;
import java.util.function.Function;

public abstract class Client {
    protected Object request;
    protected Object response;
    protected RequestWrapper requestWrapper;
    protected ResponseWrapper responseWrapper;

    public static WsClient<Object, Object> ws() {
        return new WsClient<>();
    }

    public static RestClient rest() {
        return new RestClient();
    }

//    public <R> app.pickmaven.clientify.Client givenRequest(R request) {
//        System.out.println("Given request..." + request);
//        requestWrapper = new app.pickmaven.clientify.RequestWrapper<R>(request);
//        System.out.println("Created requestWrapper..." + requestWrapper);
//        return this;
//    }

    public <R> Client givenRequest(Function<Object, R> createRequestFunction) {
        System.out.println("Creating request...");
        request = createRequestFunction.apply(requestWrapper);
        requestWrapper = new RequestWrapper<R>((R) request);
        System.out.println("Created requestWrapper..." + requestWrapper);
        return this;
    }

    public abstract <P, R, E> Client call(BiFunction<RequestWrapper<E>, P, R> function);

    public <E> ResponseStatus thenHandleResponse(Function<ResponseWrapper<E>, ResponseStatus> fun) {
        System.out.println("Handling response executin custom function...");

        return fun.apply(responseWrapper);
    }

    public abstract Client configuring(ServiceElement serviceElement);

}

