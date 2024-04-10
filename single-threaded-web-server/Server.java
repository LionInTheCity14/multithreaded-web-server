import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class Server {

    public void run() throws IOException, UnknownHostException {
        int port = 8010;
        ServerSocket socket = new ServerSocket(port);
        socket.setSoTimeout(20000);
        while (true) {
            try {
                System.out.println("Server is listening on port: " + port);
                
                // when connection is established
                Socket acceptedConnection = socket.accept();
                System.out.println("Connected accepted from client " + acceptedConnection.getRemoteSocketAddress());

                // get client request in text and send it to server in form of bytes.
                PrintWriter toClient = new PrintWriter(acceptedConnection.getOutputStream(), true); 

                // send response in form of bytes to text to client
                BufferedReader fromClient = new BufferedReader(
                        new InputStreamReader(acceptedConnection.getInputStream()));    
                toClient.println("Hey, I am server, and I am running...");
                toClient.close();
                fromClient.close();
                acceptedConnection.close();
            } catch (IOException e) {
                // TODO: handle exception
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        Server server = new Server();
        try {
            server.run();
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }
}