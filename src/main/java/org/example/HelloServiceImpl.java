package org.example;

import io.grpc.stub.StreamObserver;
import org.example.grpc.GreeterGrpc;
import org.example.grpc.HelloRequest;
import org.example.grpc.HelloResponse;

public class HelloServiceImpl extends GreeterGrpc.GreeterImplBase {

  @Override
  public void sayHello(HelloRequest request, StreamObserver<HelloResponse> responseObserver) {

    String name = request.getName();
    String message = "Hello, " + name + " ".repeat(1024 * 1024 * 5).substring(0, 4194250);
    //Thread.sleep(500);
    HelloResponse response = HelloResponse.newBuilder().setMessage(message).build();
    //Thread.sleep(2000);
    responseObserver.onNext(response);
    responseObserver.onCompleted();
  }
}
