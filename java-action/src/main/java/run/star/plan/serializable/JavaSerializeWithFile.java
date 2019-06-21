package run.star.plan.serializable;

import java.io.*;

/**
 * @author hecs
 * @date 2019-06-21 14:13
 */
public class JavaSerializeWithFile implements ISerializer {
    @Override
    public byte[] serialize(Object obj) {
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(new File("user")));
            objectOutputStream.writeObject(obj);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new byte[0];
    }

    @Override
    public <c> c reSerialize(byte[] data, Class<c> clazz) {
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(new File("user")));
            return (c) objectInputStream.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
