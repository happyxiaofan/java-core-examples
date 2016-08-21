package com.rhwayfun.examples.nio.selector;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;

/**
 * Created with IntelliJ IDEA.
 *
 * @description:
 * @author: rhwayfun
 * @since: 2016-04-27 下午10:02
 */
public class SelectorThread extends  Thread{

    private Selector selector;

    public SelectorThread(Selector selector) {
        this.selector = selector;
    }

    @Override
    public void run() {
        try {
            int n = selector.select();
            while (n > 0){
                Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                while (iterator.hasNext()){
                    SelectionKey key = iterator.next();
                    if (key.isReadable()){
                        SocketChannel channel = (SocketChannel) key.channel();
                        ByteBuffer buffer = ByteBuffer.allocate(1024);
                        channel.read(buffer);
                        buffer.flip();
                        String receiveMsg = Charset.forName("UTF-8").newDecoder().decode(buffer).toString();
                        System.out.println("receive server message: " + receiveMsg + " from " + channel.getRemoteAddress());
                        key.interestOps(SelectionKey.OP_READ);
                    }

                    iterator.remove();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
