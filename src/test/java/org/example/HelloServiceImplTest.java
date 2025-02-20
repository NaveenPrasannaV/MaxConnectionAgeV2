package org.example;

import io.grpc.stub.StreamObserver;
import org.example.grpc.HelloRequest;
import org.example.grpc.HelloResponse;
import org.junit.Test;

import static org.junit.Assert.*;

public class HelloServiceImplTest {

  @Test
  public void testSayHello() {
    HelloServiceImpl service = new HelloServiceImpl();
    HelloRequest request = HelloRequest.newBuilder().setName("Bazel").build();
    HelloResponse.Builder responseBuilder = HelloResponse.newBuilder();

    service.sayHello(request, new StreamObserver<HelloResponse>() {
      @Override
      public void onNext(HelloResponse response) {
        assertEquals("Hello, Bazel ", response.getMessage().substring(0, 13));
      }

      @Override
      public void onError(Throwable t) {
        fail("Unexpected error: " + t.getMessage());
      }

      @Override
      public void onCompleted() {
      }
    });
  }
}
