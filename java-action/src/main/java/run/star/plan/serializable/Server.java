package run.star.plan.serializable;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author hecs
 * @date 2019-06-21 10:41
 */
public class Server {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        ServerSocket serverSocket = null;
        serverSocket = new ServerSocket(8099);
        Socket socket = serverSocket.accept();

        ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
        User user = (User) objectInputStream.readObject();
        System.out.println(user);

        serverSocket.close();
        objectInputStream.close();
    }
}
