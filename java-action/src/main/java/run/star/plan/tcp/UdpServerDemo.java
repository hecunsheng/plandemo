package run.star.plan.tcp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 * @Author: hecs
 * @Date: 2018/6/6 14:19
 * @Description:
 */
public class UdpServerDemo {

    public static void main(String[] args) throws IOException {
        DatagramSocket datagramSocket = new DatagramSocket(8080);
        byte[] receiveData = new byte[1024];
        DatagramPacket datagramPacket = new DatagramPacket(receiveData, receiveData.length);
        datagramSocket.receive(datagramPacket);
        System.out.println(new String(receiveData, 0, datagramPacket.getLength()));
        if (datagramSocket != null) {
            datagramSocket.close();
        }
    }
}
