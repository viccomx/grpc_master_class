package com.viccomx.grpc.greeting.server;

import com.proto.greet.GreetRequest;
import com.proto.greet.GreetResponse;
import com.proto.greet.GreetServiceGrpc;
import io.grpc.stub.StreamObserver;

/**
 * Greet server implementation
 *
 * @author Nelson Victor Cruz Hernández
 */
public class GreetServerImpl extends GreetServiceGrpc.GreetServiceImplBase {
  @Override
  public void greet(GreetRequest request, StreamObserver<GreetResponse> responseObserver) {
    final var greeting = request.getGreeting();
    final var result = "Hello " + greeting.getFirstName();
    // Create the response
    final var response = GreetResponse.newBuilder()
      .setResult(result)
      .build();

    // Send the response
    responseObserver.onNext(response);

    // Complete the RPC call
    responseObserver.onCompleted();
  }
}
