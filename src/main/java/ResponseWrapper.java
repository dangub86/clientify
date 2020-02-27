
public class ResponseWrapper<E> {
    E response;

    public ResponseWrapper(E response) {
        this.response = response;
    }

    public E get() {
        return response;
    }

    @Override
    public String toString() {
        return "ResponseWrapper{" +
                "response=" + response +
                '}';
    }
}
