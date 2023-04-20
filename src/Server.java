import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Server {
    public Server() {
        this.url = "jdbc:sqlserver://projetmessagerie.database.windows.net:1433;database=projet_messagerie;user=pgloulou@projetmessagerie;password={your_password_here};encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;";
        this.login = "pgloulou";
        this.passwd = "Malouise17";
    }

    public String getUrl() {
        return url;
    }

    public String getLogin() {
        return login;
    }

    public String getPasswd() {
        return passwd;
    }

    //private UserDao userDao;
    private String url = "jdbc:sqlserver://projetmessagerie.database.windows.net:1433;database=projet_messagerie;user=pgloulou@projetmessagerie;password={your_password_here};encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;";
    private String login = "pgloulou";
    private String  passwd = "Malouise17";

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


    public void startServeur() throws RuntimeException {
        try {

            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            Connection connection = DriverManager.getConnection(url, login, passwd);

            System.out.println("Good connection!");





            while (!serverSocket.isClosed()) {
                Socket socket = serverSocket.accept();
                System.out.println("A new client is connected !");
                ClientHandler clientHandler = new ClientHandler(socket);

                Thread thread = new Thread(clientHandler);
                thread.start();
            }
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
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
