
public class Main {

    public static void main(String[] args) {
        callSomeService();

    }

    private static void callSomeService() {
        Request request = new Request();
        request.myField = "hello";

        ClientBuilder.aWsClient()
                .givenRequest(request)
                .configuringPort().mutual()
                .call()
                .thenHandleResponse()
                .status();

    }

}
