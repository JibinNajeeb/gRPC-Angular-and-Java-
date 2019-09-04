package org.baeldung.grpc.server;

import org.baeldung.grpc.HelloRequest;
import org.baeldung.grpc.HelloResponse;
import org.baeldung.grpc.HelloServiceGrpc.HelloServiceImplBase;
import io.grpc.stub.StreamObserver;

public class HelloServiceImpl2 extends HelloServiceImplBase {
 
    @Override
    public void hello(
      HelloRequest request, StreamObserver<HelloResponse> responseObserver) {
 
        String greeting = new StringBuilder()
          .append("Hello, ")
          .append(request.getFirstName())
          .append(". ")
          .append(request.getLastName())
          .toString();
 
        HelloResponse response = HelloResponse.newBuilder()
          .setGreeting(greeting)
          .build();
 
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}