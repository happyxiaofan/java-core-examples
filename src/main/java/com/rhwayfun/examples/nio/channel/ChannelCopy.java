package com.rhwayfun.examples.nio.channel;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;

/**
 * @author: rhwayfun
 * @version: v1.0.0 on 2016年04月26日
 */
public class ChannelCopy {
    public static void main(String[] args) throws IOException {
        ReadableByteChannel source = Channels.newChannel(System.in);
        WritableByteChannel dest = Channels.newChannel(System.out);
        channelCopy(source,dest);
        source.close();
        dest.close();
    }

    private static void channelCopy(ReadableByteChannel source, WritableByteChannel dest) throws IOException {
        ByteBuffer buffer = ByteBuffer.allocate(16 * 1024);
        while (source.read(buffer) != -1){
            buffer.flip();
            while (buffer.hasRemaining()){
                dest.write(buffer);
            }
            buffer.clear();
        }
    }

    private static void channelCopy2(ReadableByteChannel source, WritableByteChannel dest) throws IOException {
        ByteBuffer buffer = ByteBuffer.allocate(16 * 1024);
        while (source.read(buffer) != -1){
            // reverse buffer
            buffer.flip();
            // drain buffer data to dest channel
            dest.write(buffer);
            // If write partly, then compact buffer so as to reuse space
            buffer.compact();
        }
        // this will leave buffer in full state
        buffer.flip();
        // drain rest buffer data
        while (buffer.hasRemaining()){
            dest.write(buffer);
        }
    }
}
