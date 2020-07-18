package com.company;

import java.io.*;
import java.net.Socket;
import java.util.StringTokenizer;

public class ClientHandler implements Runnable {
    final Socket socket;
    String name;
    boolean isLosggedIn;

    private DataInputStream input;

    private DataOutputStream output;


    public ClientHandler(Socket socket) {
        this.socket = socket;
        isLosggedIn = true;
        try {
            input = new DataInputStream(socket.getInputStream());
            output = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void run() {
        String received;
        String CMD;
        while (true) {
            try {
                received = input.readUTF();
                System.out.println("Recieved: " + received);
                if (received.equals("LOGOUT")) {
                    isLosggedIn = false;
                    break;
                }

                StringTokenizer tokenizer = new StringTokenizer(received, "%&");
                if (tokenizer.countTokens() > 1) {
                    CMD = tokenizer.nextToken().trim();
                    switch (CMD) {
                        case "REGISTER":
                            CMD = tokenizer.nextToken().trim();
                            FileCSV.writeCSV(CMD);
                            System.out.println("REGISTER ACCOUNT SUCCESS!");
                            break;
                        case "CHECKLOGIN":
                            CMD = tokenizer.nextToken().trim();
                            String fullname = FileCSV.findAccount(CMD);
                            if (fullname.isEmpty()) {
                                write(output, "CHECKLOGIN%&FALSE");
                            } else {
                                write(output, "CHECKLOGIN%&TRUE%&" + fullname);
                            }
                            break;
                        case "LOGINSUCCESS":
                            CMD = tokenizer.nextToken().trim();
                            Server.listname.add(CMD);
                            name = CMD;
                            for (String str : Server.listname) {
                                output.writeUTF("USERONLINE%&" + str);
                            }
                            for (ClientHandler c : Server.clients) {
                                if (c.isLosggedIn) {
                                    write(c.output, "USERONLINE%&" + CMD);
                                }
                            }
                            break;
                        case "LOGOUTSUCCESS":
                            CMD = tokenizer.nextToken().trim();
                            Server.listname.remove(CMD);
                            isLosggedIn = false;
                            for (ClientHandler c : Server.clients) {
                                if (c.isLosggedIn) {
                                    write(c.output, "USEROFFLINE%&" + CMD);
                                }
                            }
                            break;
                        case "CONNECTCHATSINGLE":
                            CMD = tokenizer.nextToken().trim();
                            for (ClientHandler c : Server.clients) {
                                if (c.isLosggedIn && c.name.equals(CMD)) {
                                    write(c.output, "CONNECTEDCHATSINGLE%&" + name);
                                    break;
                                }
                            }
                            break;
                        case "CHATSINGLE":
                            CMD = tokenizer.nextToken().trim();
                            for (ClientHandler c : Server.clients) {
                                if (c.isLosggedIn && c.name.equals(CMD)) {
                                    System.out.println("CHAT SINGLE ACCEPT");
                                    String msg = tokenizer.nextToken().trim();
                                    write(c.output,"CHATSINGLE%&"+name+"%&"+msg);
                                    break;
                                }
                            }
                            break;
                        default:
                            break;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    private void forwardToClient(String received) {
//        username # message
        StringTokenizer tokenizer = new StringTokenizer(received, "#");
        if (tokenizer.countTokens() == 2) {
            System.out.println("vo 1");
            String recipient = tokenizer.nextToken().trim();
            String message = tokenizer.nextToken().trim();
            for (ClientHandler c : Server.getClients()) {
                if (c.isLosggedIn && c.name.equals(recipient)) {
                    write(c.output, name + " : " + message);
                    log(name + " --> " + recipient + " : " + message);
                    break;
                }
            }
        } else if (tokenizer.countTokens() == 4) {
            System.out.println("vo 2 file");
            System.out.println(received);
            String usersend = tokenizer.nextToken().trim();
            String file = tokenizer.nextToken().trim();
            String userrecieved = tokenizer.nextToken().trim();
            String path = tokenizer.nextToken().trim();
            log(usersend + " to " + userrecieved + " ------- " + path);
            try {
                output.writeUTF(received);
            } catch (IOException e) {
                e.printStackTrace();
            }

        } else {
            System.out.println("vo 3");
            this.name = received;
            Server.listname.add(this.name);

            for (ClientHandler c : Server.getClients()) {
                if (c.isLosggedIn) {
                    for (String name : Server.listname) {
                        write(c.output, name);
                    }
                }
            }
        }
    }

    private String read() {
        String line = "";
        try {
            line = input.readUTF();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return line;
    }

    private void write(DataOutputStream output, String message) {
        try {
            output.writeUTF(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void sendfile(DataOutputStream output, String path) {
        try {
            FileInputStream fr = new FileInputStream("D:\\cmd\\man.jpg");
            byte b[] = new byte[202400];
            fr.read(b, 0, b.length);
            output.write(b, 0, b.length);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void readfile(DataInputStream is, String path) {
        try {
            byte b[] = new byte[202400];
            FileOutputStream fr = new FileOutputStream(path);
            is.read(b, 0, b.length);
            fr.write(b, 0, b.length);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void log(String msg) {
        System.out.println(msg);
    }
}
