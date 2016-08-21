package com.rhwayfun.examples.nio.channel;

import com.rhwayfun.examples.util.ConvertUtil;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;

/**
 * @author: rhwayfun
 * @version: v1.0.0 on 2016年04月26日
 */
public class ServerSocketChannelReader implements Runnable{

    private Selector selector;

    public ServerSocketChannelReader(Selector selector) {
        this.selector = selector;
        new Thread(this).start();
    }

    @Override
    public void run() {
        try {
            while (selector.select() > 0){
                for (SelectionKey sk : selector.selectedKeys()){
                    if (sk.isReadable()){
                        SocketChannel channel = (SocketChannel) sk.channel();
                        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                        byteBuffer.clear();
                        channel.read(byteBuffer);
                        byteBuffer.flip();

                        String msg = ConvertUtil.getString(byteBuffer);
                        System.out.println("respond: " + msg + " from " + channel.getRemoteAddress());

                        // prepare next read
                        sk.interestOps(SelectionKey.OP_READ);
                    }
                    // remove processing sk
                    selector.selectedKeys().remove(sk);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
