package sun.star.plan.tcp;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @Author: hecs
 * @Date: 2018/6/4 17:33
 * @Description:
 */
public class Server {

    public static final int PORT = 8000; //监听的端口号

    public static void main(String[] args) {
        Server server = new Server();
        server.init();
    }

    public void init() {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(PORT);
            while (true) {
                Socket client = serverSocket.accept();
                //一个客户端连接就开户两个线程处理读写
                new Thread(new ReadHandlerThread(client,"客户端")).start();
                new Thread(new WriteHandlerThread(client)).start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (serverSocket != null) {
                    serverSocket.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

/*
 *处理读操作的线程
 */
class ReadHandlerThread implements Runnable {
    private Socket client;
    private String source;

    public ReadHandlerThread(Socket client,String source) {
        this.client = client;
        this.source = source;
    }

    @Override
    public void run() {
        DataInputStream dis = null;
        try {
            while (true) {
                //读取客户端数据
                dis = new DataInputStream(client.getInputStream());
                String reciver = dis.readUTF();
                System.out.println(source+"发过来的内容:" + reciver);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (dis != null) {
                    dis.close();
                }
                if (client != null) {
                    client = null;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

/*
 * 处理写操作的线程
 */
class WriteHandlerThread implements Runnable {
    private Socket client;

    public WriteHandlerThread(Socket client) {
        this.client = client;
    }

    @Override
    public void run() {
        DataOutputStream dos = null;
        BufferedReader br = null;
        try {
            while (true) {
                //向客户端回复信息
                dos = new DataOutputStream(client.getOutputStream());
                System.out.print("请输入:\t");
                // 键盘录入
                br = new BufferedReader(new InputStreamReader(System.in));
                String send = br.readLine();
                //发送数据
                dos.writeUTF(send);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (dos != null) {
                    dos.close();
                }
                if (br != null) {
                    br.close();
                }
                if (client != null) {
                    client = null;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}