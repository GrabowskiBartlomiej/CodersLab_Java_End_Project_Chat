//package pl.coderslab.sockets;
//
//import java.io.IOException;
//import java.net.ServerSocket;
//import java.net.Socket;
//import java.util.HashSet;
//
//import java.util.Set;
//
//public class ChatServer {
//    private final static int port = 8989;
//    private static Set<UserThread> userThreads = new HashSet<>();
//
//    public void execute() {
//        try (ServerSocket serverSocket = new ServerSocket(port)) {
//
//            System.out.println("Chat Server is listening on port " + port);
//
//            while (true) {
//                Socket client = serverSocket.accept();
//                System.out.println("New user connected");
//
//                UserThread newUser = new UserThread(client, this);
//                userThreads.add(newUser);
//                newUser.start();
//
//            }
//        } catch (IOException ex) {
//            System.out.println("Error in the server: " + ex.getMessage());
//            ex.printStackTrace();
//        }
//    }
//
//    public static void broadcast() {
//        for (UserThread aUser : userThreads) {
//            System.out.println(aUser);
//        }
//    }
//
//}
