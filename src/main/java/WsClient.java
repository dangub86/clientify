public class WsClient implements Client {
    RequestWrapper requestWrapper = null;

    public <R> WsClient givenRequest(R request) {
        requestWrapper = new RequestWrapper<R>(request);
        return this;
    }

    public <R, E> ResponseWrapper<E> call(RequestWrapper<R> request) {
        return null;
    }

    public <E> boolean thenHandleResponse(ResponseWrapper<E> response) {
        return false;
    }

    public void configuringPort() {

    }
}
