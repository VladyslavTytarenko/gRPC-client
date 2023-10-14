package org.grpc;

public class App {
    public static void main( String[] args ) {
        GreetingClient client = new GreetingClient();
        client.request("Vlad");
    }
}
