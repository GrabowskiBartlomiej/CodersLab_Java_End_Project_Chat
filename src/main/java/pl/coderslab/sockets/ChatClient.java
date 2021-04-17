//package pl.coderslab.sockets;
//
//import java.io.IOException;
//import java.net.Socket;
//import java.net.UnknownHostException;
//
//public class ChatClient {
//    private String hostname;
//    private int port;
//    private String userName;
//
//    public ChatClient(String hostname, int port) {
//        this.hostname = hostname;
//        this.port = port;
//    }
//
//
//
//    public void execute() {
//        try {
//            Socket socket = new Socket(hostname, port);
//
//            System.out.println("Connected to the chat server");
//
//        } catch (UnknownHostException ex) {
//            System.out.println("Server not found: " + ex.getMessage());
//        } catch (IOException ex) {
//            System.out.println("I/O Error: " + ex.getMessage());
//        }
//
//    }
//
//
//}
