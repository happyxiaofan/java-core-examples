package com.rhwayfun.examples.nio.channel;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

/**
 * @author: rhwayfun
 * @version: v1.0.0 on 2016年04月26日
 */
public class DatagramChannelServer {
    public static void main(String[] args) throws IOException {
        int port = 6666;
        if (args.length == 1){
            port = Integer.valueOf(args[1]);
        }
        // allocate two byteBuffer
        ByteBuffer in = ByteBuffer.allocate(8192);
        ByteBuffer out = ByteBuffer.allocate(1024);
        // open a channel on server
        DatagramChannel channel = DatagramChannel.open();
        DatagramSocket serverSocket = channel.socket();
        // bind to a address
        serverSocket.bind(new InetSocketAddress(port));
        System.out.println("Server bind to " + port);
        while (true) {
            in.clear();
            SocketAddress client = channel.receive(in);
            System.out.println("request: " + new String(in.array()));
            out.clear();
            out.put("Welcome to server.".getBytes());
            out.flip();
            channel.send(out,client);
        }
    }
}
