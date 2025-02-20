package org.example;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class HelloServer {

  public static void main(String[] args) throws IOException, InterruptedException {
    Server server = ServerBuilder.forPort(8080)
        .maxConnectionAge(1, TimeUnit.SECONDS) // Set maximum connection age to 1 second
        .maxConnectionAgeGrace(300,
            TimeUnit.SECONDS) // Set grace period for existing requests to finish
        .addService(new HelloServiceImpl())
        .build()
        .start();

    System.out.println("Server started on port 8080");
    server.awaitTermination();
  }
}