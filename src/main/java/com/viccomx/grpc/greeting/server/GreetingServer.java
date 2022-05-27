package com.viccomx.grpc.greeting.server;

import io.grpc.ServerBuilder;

import java.io.IOException;

/**
 * Basic server setup
 *
 * @author Nelson Victor Cruz Hernández
 */
public class GreetingServer {
  private static final int PORT = 50051;

  public static void main(String[] args) throws IOException, InterruptedException {
    System.out.println("Hello gRPC");

    // Create the server
    final var server = ServerBuilder.forPort(PORT)
      .build();

    // Start the server
    server.start();

    // Handle correct shutdown
    Runtime.getRuntime().addShutdownHook(new Thread(() -> {
      System.out.println("Received shutdown request");
      server.shutdown();
      System.out.println("Successfully stopped the server");
    }));

    // Avoid server is instantly terminated
    server.awaitTermination();
  }
}
