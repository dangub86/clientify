
public class Main {

    public static void main(String[] args) {
        callSomeService();

    }

    private static void callSomeService() {
        ClientBuilder.aWsClient()
                .givenRequest(request)
                .configuringPort().mutual()
                .call()
                .thenHandleResponse()
                .status();

    }

}
