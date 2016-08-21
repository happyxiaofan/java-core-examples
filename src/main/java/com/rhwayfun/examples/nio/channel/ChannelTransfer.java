package com.rhwayfun.examples.nio.channel;

import java.io.*;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.WritableByteChannel;

/**
 * Created on 2016年04月26日
 *
 * @decription:
 * @author: rhwayfun
 * @version: v1.0.0
 */
public class ChannelTransfer {
    public static void main(String[] args) throws IOException {
        if (args.length == 0) {
            System.err.println("Usage: filename...");
            return;
        }
        String file = null;
        file = args[0];
        InputStream in = ChannelTransfer.class.getResourceAsStream("/" + file);
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        String line = null;
        while ((line = br.readLine()) != null) {
            catFile(Channels.newChannel(System.out), line);
        }
    }

    /**
     * @param target
     * @param file
     */
    private static void catFile(WritableByteChannel target, String file) throws IOException {
        ClassLoader classLoader = ChannelTransfer.class.getClassLoader();
        FileInputStream fis = new FileInputStream(new File(classLoader.getResource(file).getFile()).getAbsolutePath());
        FileChannel channel = fis.getChannel();
        // transfer content to target outputStream
        channel.transferTo(0,channel.size(),target);
        channel.close();
        fis.close();
    }
}
