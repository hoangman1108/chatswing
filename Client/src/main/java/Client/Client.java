package Client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {
    final String serverHost = "localhost";
    Scanner scan;
    Socket socket = null;
    DataInputStream input = null;
    DataOutputStream output = null;
    InetAddress ip;
    String username;

    public Client(String user) {
        try {
            ip = InetAddress.getByName("localhost");
            socket = new Socket(serverHost,3000);
            input = new DataInputStream(socket.getInputStream());
            output = new DataOutputStream(socket.getOutputStream());
            scan = new Scanner(System.in);
            username = user;
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public DataInputStream getInput() {
        return input;
    }

    public void setInput(DataInputStream input) {
        this.input = input;
    }

    public DataOutputStream getOutput() {
        return output;
    }

    public void setOutput(DataOutputStream output) {
        this.output = output;
    }

    public void wirtetoserver(final String msg) {
        Thread sendMessage = new Thread(new Runnable() {
            public void run() {
                try {
                    output.writeUTF(msg);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        sendMessage.start();
    }
}