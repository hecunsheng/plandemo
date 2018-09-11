package sun.star.plan.tcp;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * @Author: hecs
 * @Date: 2018/6/4 17:33
 * @Description:
 */
public class ClientSocketDemo {

    public static void main(String[] args) throws IOException {
        Socket socket = null;
        try {
            socket = new Socket("127.0.0.1", 8080);
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            out.println("hello");
            out.println("hello1");
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            if (socket != null) {
                socket.close();
            }
        }

    }
}
