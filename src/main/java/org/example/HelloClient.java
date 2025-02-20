package org.example;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import java.time.LocalDateTime;
import java.util.UUID;
import org.example.grpc.GreeterGrpc;
import org.example.grpc.HelloRequest;
import org.example.grpc.HelloResponse;


public class HelloClient {

  public static void main(String[] args) {
    ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 8080)
        .usePlaintext()
        .build();

    GreeterGrpc.GreeterBlockingStub stub = GreeterGrpc.newBlockingStub(channel);

    System.out.println(LocalDateTime.now() + " STARTING");

    for (int i = 0; i < 10; i++) {
      for (int j = 0; j < 100; j++) {
        String message = "Hello".repeat(1024 * 1024 * 5).substring(0, 4194250);
        HelloRequest request = HelloRequest.newBuilder()
            .setName(UUID.randomUUID().toString())
            .setMessage(message)
            .build();
        HelloResponse response = stub.sayHello(request);
        System.out.println(
            LocalDateTime.now() + " i = " + i + " j = " + j + " " + response.getMessage().length());

      }
    }

    channel.shutdown();
    System.out.println(LocalDateTime.now() + " FINISHING");
  }
}
