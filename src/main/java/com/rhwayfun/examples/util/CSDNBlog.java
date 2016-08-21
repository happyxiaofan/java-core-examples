package com.rhwayfun.examples.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URLConnection;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by rhwayfun on 16-4-14.
 * 访问csdn博客，增加访问量
 */
public class CSDNBlog {

    //日志记录
    private static final Logger logger = Logger.getLogger(RequestBlogRun.class.getName());
    //日期
    private static final DateFormat format = new SimpleDateFormat("HH:mm:ss");

    public static void main(String[] args) throws Exception {
        for (int i = 0; i < 10; i++) {
            RequestBlogRun conn = new RequestBlogRun();
            Thread t = new Thread(conn);
            t.start();
        }
    }

    /**
     * 构造对博客的请求
     */
    public static class RequestBlogRun implements Runnable {
        //读取配置文件的文章头
        private String ariticleHeader;
        //文章id
        private List<String> articleIds;
        //访问客户端
        private String userAgent;
        //请求的阻塞队列
        private BlockingQueue<HttpURLConnection> bq = new ArrayBlockingQueue<HttpURLConnection>(5);
        //请求的线程池
        private ExecutorService service = Executors.newFixedThreadPool(5);

        public RequestBlogRun(){
            try {
                init();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        private void init() throws IOException {
            InputStream in = RequestBlogRun.class.getResourceAsStream("/blog.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            ariticleHeader = br.readLine();
            userAgent = br.readLine();
            articleIds = new ArrayList<>();
            String blogUrl = null;
            while ((blogUrl = br.readLine()) != null){
                articleIds.add(ariticleHeader + blogUrl);
            }
            logger.log(Level.INFO,"successfully init config! " + format.format(new Date()));
        }

        @Override
        public void run() {
            while (true) {
                try {
                    for (String blogUrl : articleIds) {
                        URLConnection conn = URI.create(blogUrl).toURL().openConnection();
                        conn.setRequestProperty("User-Agent", userAgent);
                        conn.setConnectTimeout(20000);
                        HttpURLConnection httpConn = (HttpURLConnection) conn;
                        bq.put(httpConn);
                        service.execute(new ConnBlog(bq));
                    }
                    /*URLConnection conn = URI.create("http://rhwayfun.com").toURL().openConnection();
                    conn.setRequestProperty("User-Agent", userAgent);
                    conn.setConnectTimeout(20000);
                    HttpURLConnection httpConn = (HttpURLConnection) conn;
                    bq.put(httpConn);
                    service.execute(new ConnBlog(bq));*/
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 实际访问blog
     */
    public static class ConnBlog implements Runnable {
        private static int i = 0;
        private BlockingQueue<HttpURLConnection> bq;

        public ConnBlog(BlockingQueue<HttpURLConnection> bq) {
            this.bq = bq;
        }

        public void run() {
            try {
                HttpURLConnection conn = bq.take();
                logger.log(Level.INFO,"response code is " + conn.getResponseCode() + " " + format.format(new Date()));
                i++;
                logger.info("request times is " + i + " " + format.format(new Date()));
                if (i > 50000) {
                    //为了提高效率，没有加锁，次数有出入
                    logger.info("program will exit! " + format.format(new Date()));
                    System.exit(0);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
