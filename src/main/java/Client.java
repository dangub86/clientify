
public interface Client {

    <R> Client givenRequest(R request);

    <R, E> ResponseWrapper<E> call(RequestWrapper<R> request);

    <E> boolean thenHandleResponse(ResponseWrapper<E> response);
}
