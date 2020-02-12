public class RequestWrapper<R> {
    R request;

    public RequestWrapper(R request) {
        this.request = request;
    }

    public R getRequest() {
        return request;
    }
}
