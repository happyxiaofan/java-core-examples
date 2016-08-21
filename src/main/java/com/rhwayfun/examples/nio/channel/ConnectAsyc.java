package com.rhwayfun.examples.nio.channel;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;

/**
 * @author: rhwayfun
 * @version: v1.0.0 on 2016年04月26日
 */
public class ConnectAsyc {

    public static void main(String[] args) throws IOException, InterruptedException {
        String host = "localhost";
        int port = 1234;
        //Selector selector = Selector.open();
        if (args.length == 2) {
            host = args[0];
            port = Integer.valueOf(args[1]);
        }
        InetSocketAddress address = new InetSocketAddress(host, port);
        SocketChannel sc = SocketChannel.open();
        sc.configureBlocking(false);
        System.out.println("Initiating connection");
        sc.connect(address);
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        while (!sc.finishConnect()){ }
        System.out.println("Finish connection");
        if (sc.isConnected()) {
            //new ServerSocketChannelReader(selector);
            buffer.clear();
            sc.read(buffer);
            buffer.flip();
            System.out.println("respond: " + Charset.forName("UTF-8").newDecoder().decode(buffer).toString()
                    + " from " + sc.getRemoteAddress());
            // send message to remote server
            String msg = "Hello, I am a client.";
            buffer.clear();
            buffer.put(msg.getBytes());
            buffer.flip();
            sc.write(buffer);
        }
        Thread.sleep(500);
        sc.close();
    }
}
