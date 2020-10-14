package connection;


import dragon.Dragon;
import utilities.Deserializator;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.util.ArrayList;


public class ServerReceiver {
    Deserializator deserializator = new Deserializator();
    ByteBuffer buffer = ByteBuffer.allocate(10000);
    DatagramChannel channel;
    DatagramSocket socket;
    InetSocketAddress address;
    static int clientPort;

    public ServerReceiver(int serverPort) throws IOException {
        address = new InetSocketAddress("localhost", serverPort);
        channel = DatagramChannel.open();
        socket = channel.socket();
        channel.configureBlocking(false);
        channel.bind(address);
    }

    public int getPort() {
        return clientPort;
    }

    public String receive() throws IOException {
        String s = "";
        while (true) {
            InetSocketAddress remoteAdress = (InetSocketAddress) channel.receive(buffer);
            if (remoteAdress != null) {
                buffer.flip();
                int limit = buffer.limit();
                byte bytes[] = new byte[limit];
                buffer.get(bytes, 0, limit);
                s = new String(bytes);
                buffer.clear();
                return s;
            }
        }
    }
    public Dragon receiveDragon() throws IOException, ClassNotFoundException {
        while (true) {
            buffer.clear();
            InetSocketAddress remoteAdress = (InetSocketAddress) channel.receive(buffer);
            if (remoteAdress != null) {
                buffer.flip();
                int limit = buffer.limit();
                byte bytes[] = new byte[limit];
                buffer.get(bytes, 0, limit);
                try {
                    Object dragon =  Deserializator.toDeserializeWithExc(bytes);
                    buffer.clear();
                    return (Dragon) dragon;
                }catch (Exception e){
                    buffer.clear();
                    return receiveDragon();
                }

            }
        }
    }
    public Object receiveObject() throws IOException, ClassNotFoundException {
        while (true) {
            InetSocketAddress remoteAdress = (InetSocketAddress) channel.receive(buffer);
            if (remoteAdress != null) {
                buffer.flip();
                int limit = buffer.limit();
                byte bytes[] = new byte[limit];
                buffer.get(bytes, 0, limit);
                Object object = Deserializator.toDeserialize(bytes);
                buffer.clear();
                return object;
            }
        }
    }

    public ArrayList receiveCommand() throws IOException, ClassNotFoundException {
        int tumb = 0;
        while (true) {
            if (tumb == 5000000) System.out.println("Ожидание команды от клиента...");
            buffer.clear();
            InetSocketAddress remoteAdress = (InetSocketAddress) channel.receive(buffer);
            if (remoteAdress != null) {
                buffer.flip();
                int limit = buffer.limit();
                byte bytes[] = new byte[limit];
                buffer.get(bytes, 0, limit);
                ArrayList commandArgumentObject = (ArrayList) Deserializator.toDeserialize(bytes);
                buffer.clear();
                return commandArgumentObject;
            } else tumb++;
        }
    }

}