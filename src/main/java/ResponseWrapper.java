public class ResponseWrapper<E> {
    E response;

    public ResponseWrapper(E response) {
        this.response = response;
    }

    public E getResponse() {
        return response;
    }
}
