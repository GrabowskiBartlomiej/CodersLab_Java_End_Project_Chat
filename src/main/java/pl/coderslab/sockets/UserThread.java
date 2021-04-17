//package pl.coderslab.sockets;
//
//import org.springframework.ui.Model;
//import org.springframework.web.servlet.ModelAndView;
//import pl.coderslab.repository.MessageRepository;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpSession;
//import java.io.*;
//import java.net.Socket;
//
//public class UserThread extends Thread {
//    private Socket socket;
//    private ChatServer server;
//    private PrintWriter writer;
//    public static boolean newMessage = false;
//
//    public UserThread(Socket socket, ChatServer server) {
//        this.socket = socket;
//        this.server = server;
//    }
//
//    public void run() {
//
//        InputStream input = null;
//        try {
//            input = socket.getInputStream();
//
//            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
//
//
//            OutputStream output = socket.getOutputStream();
//            writer = new PrintWriter(output, true);
//
//            while (true) {
//
//                if (newMessage) {
//
//                    newMessage = false;
//                }
//
//            }
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//
//    }
//
//}
