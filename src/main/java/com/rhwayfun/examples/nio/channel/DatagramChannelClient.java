package com.rhwayfun.examples.nio.channel;

import com.rhwayfun.examples.util.ConvertUtil;

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
public class DatagramChannelClient {
    public static void main(String[] args) throws IOException {
        DatagramChannel channel = DatagramChannel.open();
        SocketAddress socketAddress = new InetSocketAddress(0);
        DatagramSocket socket = channel.socket();
        socket.setSoTimeout(5000);
        socket.bind(socketAddress);

        SocketAddress serverAddress = new InetSocketAddress("localhost",6666);
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        buffer.clear();
        String msg = "Hello, I am a client.";
        buffer.put(msg.getBytes());
        buffer.flip();
        channel.send(buffer,serverAddress);

        if (!channel.isConnected()) {
            channel.connect(serverAddress);
        }
        buffer.clear();
        channel.read(buffer);
        buffer.flip();
        System.out.println("respond: " + ConvertUtil.getString(buffer));
        channel.close();
    }
}
