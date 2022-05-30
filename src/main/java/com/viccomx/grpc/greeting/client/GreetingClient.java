package com.viccomx.grpc.greeting.client;

import com.proto.greet.GreetRequest;
import com.proto.greet.GreetServiceGrpc;
import com.proto.greet.Greeting;
import io.grpc.ManagedChannelBuilder;

/**
 * Basic client setup
 *
 * @author Nelson Victor Cruz Hernández
 */
public class GreetingClient {
  private static final int PORT = 50051;

  public static void main(String[] args) {
    System.out.println("Hello I'm a simple gRPC client");

    // Create transport channel
    final var channel = ManagedChannelBuilder.forAddress("localhost", PORT)
      // Force SSL to be deactivated
      .usePlaintext()
      .build();

    // Create synchronous stub
    System.out.println("Creating stub");
    final var greetBlockingStub = GreetServiceGrpc.newBlockingStub(channel);

    // Create request
    final var greeting = Greeting.newBuilder()
      .setFirstName("Vicco")
      .setLastName("Cush")
      .build();
    final var greetRequest = GreetRequest.newBuilder()
      .setGreeting(greeting)
      .build();

    // Call service and get response
    final var response = greetBlockingStub.greet(greetRequest);

    // Do something
    System.out.println(response.getResult());

    System.out.println("Shutting down channel");
    channel.shutdown();
  }
}
