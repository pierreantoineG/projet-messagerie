import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {
    //private UserDao userDao;
    private ServerSocket serverSocket;

    public Server(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    /*public boolean isUsernameTaken(String username, UserDao userDao) {
        List<Client> clients = userDao.getUsers();
        for (Client client : clients) {
            if (client.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }*/


    public void startServeur() {
        try {
            while (!serverSocket.isClosed()) {
                Socket socket = serverSocket.accept();
                System.out.println("A new client is connected !");
                ClientHandler clientHandler = new ClientHandler(socket);

                Thread thread = new Thread(clientHandler);
                thread.start();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void closeServerSocket(){
        try{
            if(serverSocket != null){
                serverSocket.close();
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }


    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket1 = new ServerSocket(4278);
        Server server = new Server(serverSocket1);
        server.startServeur();
    }
}
