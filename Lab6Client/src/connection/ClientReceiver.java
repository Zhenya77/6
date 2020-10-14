package connection;

import controller.DragonCollection;
import dragon.Dragon;
import utilities.Deserializator;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Scanner;

public class ClientReceiver {
    Deserializator deserializator = new Deserializator();
    static DatagramSocket clientSocket = null;
    byte[] data = new byte[100000];
    private int clientPort;

    public ClientReceiver(int clientPort) throws SocketException {
        this.clientSocket = new DatagramSocket(clientPort);
        this.clientPort = clientPort;
    }

    public static int getClientPortFromClient() {
        Scanner in = new Scanner(System.in);
        int port = -10;
        try {
            while (port == -10) {
                System.out.print("Укажите порт:\n$ ");
                String s = in.nextLine();
                port = Integer.parseInt(s);
                if (port > 0 || port < 65536) return port;
                else return getClientPortFromClient();
            }
            return port;
        } catch (Exception e) {
            return getClientPortFromClient();
        }
    }

    public int getClientPort() {
        return clientPort;
    }

    public String receive() throws IOException {
        DatagramPacket packet = new DatagramPacket(data, data.length);
        clientSocket.setSoTimeout(2000);
        clientSocket.receive(packet);
        clientSocket.setSoTimeout(0);
        String received = new String(packet.getData(), 0, packet.getLength());
        return received;
    }

    public void receiveCollection() throws IOException, ClassNotFoundException {
        DatagramPacket packet = new DatagramPacket(data, data.length);
        clientSocket.setSoTimeout(2000);
        clientSocket.receive(packet);
        clientSocket.setSoTimeout(0);
        DragonCollection.collection = (Hashtable<Integer, Dragon>) deserializator.toDeserialize(packet.getData());
    }
}
