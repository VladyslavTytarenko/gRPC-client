package org.grpc;

import com.grpc.GreetingServiceGrpc.GreetingServiceBlockingStub;
import com.grpc.GreetingServiceOuterClass.HelloRequest;
import com.grpc.GreetingServiceOuterClass.HelloResponse;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import java.util.Iterator;
import java.util.stream.StreamSupport;

import static com.grpc.GreetingServiceGrpc.newBlockingStub;

public class GreetingClient {

    private final static String TARGET = "localhost:8080";

    public void request(String name) {
        ManagedChannel channel = ManagedChannelBuilder
                .forTarget(TARGET)
                .usePlaintext().build();

        GreetingServiceBlockingStub stub = newBlockingStub(channel);

        HelloRequest request = HelloRequest.newBuilder()
                .setName(name).build();

        Iterator<HelloResponse> response = stub.greeting(request);

        while (response.hasNext()) System.out.println(response.next().getGreeting());

        channel.shutdown();
    }
}
