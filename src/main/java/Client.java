
public interface Client {

    Request createRequest();

    Response sendRequest(Request request);

    boolean handleResponse();
}
