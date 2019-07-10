package ToolBox;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.function.Consumer;

public class NetworkConnection {
    private ConnectionThread connection = new ConnectionThread();
    public Consumer<Serializable> receiveCallBack;
    public String ip;
    public boolean isServer;
    public int port;


    public NetworkConnection(Consumer<Serializable> receiveCallBack, String ip, boolean isServer, int port) {
        this.receiveCallBack = receiveCallBack;
        this.ip = ip;
        this.isServer = isServer;
        this.port = port;
    }

    public void openConnection() {
        connection.start();
    }

    public void sendData(Serializable data) throws IOException {
        connection.outputStream.writeObject(data);
    }

    public void closeConnection() throws IOException {
        connection.socket.close();
    }

    private class ConnectionThread extends Thread {
        private Socket socket;
        private ObjectOutputStream outputStream;

        @Override
        public void run() {
            super.run();
            try {
                ServerSocket server = isServer ? new ServerSocket(port) : null;
                Socket socket = isServer ? server.accept() : new Socket(ip, port);
                ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
                ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
                socket.setTcpNoDelay(true);
                this.socket = socket;
                this.outputStream = outputStream;

                while (true) {
                    Serializable data = (Serializable) inputStream.readObject();
                    receiveCallBack.accept(data);
                }

            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

}
