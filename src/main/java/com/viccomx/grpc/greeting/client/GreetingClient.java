package com.viccomx.grpc.greeting.client;

import com.proto.dummy.DummyServiceGrpc;
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
    final var syncClient = DummyServiceGrpc.newBlockingStub(channel);

    // Create an asynchronous stub
    // final var asyncClient = DummyServiceGrpc.newFutureStub(channel);

    // Do something

    System.out.println("Shutting down channel");
    channel.shutdown();
  }
}
