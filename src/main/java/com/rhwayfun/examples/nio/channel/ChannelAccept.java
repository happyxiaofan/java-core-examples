package com.rhwayfun.examples.nio.channel;

import com.rhwayfun.examples.util.ConvertUtil;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * @author: rhwayfun
 * @version: v1.0.0 on 2016年04月26日
 */
public class ChannelAccept {

    private static final String GREETING = "Welcome to rhwayfun's server.";

    public static void main(String[] args) {
        int port = 1234;
        if (args.length == 1) {
            port = Integer.valueOf(args[0]);
        }
        try {
            //Selector selector = Selector.open();
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
            ServerSocketChannel ssc = ServerSocketChannel.open();
            //listening on target port
            ssc.bind(new InetSocketAddress(port));
            //config none blocking mode
            ssc.configureBlocking(false);
            //ssc.register(selector, SelectionKey.OP_ACCEPT);
            while (true) {
                System.out.println("Waiting for connection ...");
                SocketChannel sc = ssc.accept();
                if (sc == null) {
                    Thread.sleep(200);
                } else {
                    System.out.println("Incoming connection from " + sc.getRemoteAddress());
                    byteBuffer.clear();
                    byteBuffer.put(GREETING.getBytes());
                    byteBuffer.flip();
                    sc.write(byteBuffer);

                    byteBuffer.clear();
                    sc.read(byteBuffer);
                    byteBuffer.flip();
                    System.out.println("\nreceive message: " + ConvertUtil.getString(byteBuffer));
                   // sc.close();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
