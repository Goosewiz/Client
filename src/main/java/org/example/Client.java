package org.example;

import com.example.grpc.ServiceGrpc;
import com.example.grpc.ServiceOuterClass;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class Client
{
    public static void main( String[] args ){
        ManagedChannel channel = ManagedChannelBuilder.forTarget("localhost:8080").usePlaintext().build();
        ServiceGrpc.ServiceBlockingStub stub = ServiceGrpc.newBlockingStub(channel);
        System.out.println("Write your name");
        Scanner console = new Scanner(System.in);
        String name = console.nextLine();
        ServiceOuterClass.IncomingRequest request = ServiceOuterClass.IncomingRequest.newBuilder().setName(name).build();
        ServiceOuterClass.OutgoingResponse response = stub.exchange(request);
        System.out.println(response);
        channel.shutdown();
    }
}
