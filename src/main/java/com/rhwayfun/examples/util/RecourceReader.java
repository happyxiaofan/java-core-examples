package com.rhwayfun.examples.util;

import java.io.*;

/**
 * Created by rhwayfun on 16-4-15.
 */
public class RecourceReader {
    public static void main(String[] args) throws IOException {
        InputStream inputStream = RecourceReader.class.getResourceAsStream("/blog.txt");
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
        String read = null;
        String header = br.readLine();
        System.out.println("article_header:" + header);
        while ((read = br.readLine()) !=null){
            System.out.print("article_id:" + read + "\t");
        }
        System.out.println();
        ClassLoader classLoader = RecourceReader.class.getClassLoader();
        File f = new File(classLoader.getResource("blog.txt").getFile());
        System.out.println(f.getAbsolutePath());
    }
}
