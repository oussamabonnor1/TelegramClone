package ToolBox;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.function.Consumer;

public class NetworkConnection {
    public Consumer<Serializable> receiveCallBack;
    public String ip;
    public boolean isServer;
    public int port;


    public NetworkConnection(Consumer<Serializable> receiveCallBack) {
        this.receiveCallBack = receiveCallBack;
    }

    public void openConnection() {

    }

    public void sendData(Serializable data) {

    }

    public void closeConnection() {

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


            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
