package run.star.plan.serializable;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * @author hecs
 * @date 2019-06-21 11:18
 */
public class Client {

    public static void main(String[] args) throws IOException {
        Socket socket = null;
        socket = new Socket("127.0.0.1", 8099);

        ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
        objectOutputStream.writeObject(User.builder().age(1).name("hecs").build());

        socket.close();
        objectOutputStream.close();
    }
}
