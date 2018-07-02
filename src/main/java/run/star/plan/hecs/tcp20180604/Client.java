package run.star.plan.hecs.tcp20180604;

import java.net.Socket;

/**
 * @Author: hecs
 * @Date: 2018/6/4 17:33
 * @Description:
 */
public class Client {

    public static final String IP   = "127.0.0.1"; //服务器地址
    public static final int    PORT = 8000;        //服务器端口号

    public static void main(String[] args) {
        handler();
    }

    private static void handler() {
        try {
            //实例化一个Socket，并指定服务器地址和端口
            Socket client = new Socket(IP, PORT);
            //开启两个线程，一个负责读，一个负责写
            new Thread(new ReadHandlerThread(client,"服务器端")).start();
            new Thread(new WriteHandlerThread(client)).start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
