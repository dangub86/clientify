
public class RequestWrapper<R> {
    R request;

    public RequestWrapper(R request) {
        this.request = request;
    }

    public R get() {
        return request;
    }

    @Override
    public String toString() {
        return "RequestWrapper{" +
                "request=" + request +
                '}';
    }
}
