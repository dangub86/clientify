public class RestClient implements Client {
    public <R> RequestWrapper<R> givenRequest(R request) {
        return null;
    }

    public <R, E> ResponseWrapper<E> call(RequestWrapper<R> request) {
        return null;
    }

    public <E> boolean thenHandleResponse(ResponseWrapper<E> response) {
        return false;
    }
}
