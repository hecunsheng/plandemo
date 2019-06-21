package run.star.plan.tcp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * @Author: hecs
 * @Date: 2018/6/6 14:25
 * @Description:
 */
public class UdpClientDemo {

    public static void main(String[] args) throws IOException {
        InetAddress address = InetAddress.getByName("localhost");
        byte[] sendData = "hello,hecs".getBytes();
        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, address, 8080);
        DatagramSocket datagramSocket = new DatagramSocket();
        datagramSocket.send(sendPacket);
        if (datagramSocket != null) {
            datagramSocket.close();
        }
    }
}
