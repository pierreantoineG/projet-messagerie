import java.io.*;
import java.net.Socket;
import java.time.LocalDateTime;
import java.util.Scanner;

public class Client {
    private Integer id;
    private String first_name;
    private String last_name;
    private String username;
    private String password;
    private String email;
    private State.StateUser state; //absent connecté deconnecté
    private Type.TypeUser type; // Admin Moderateur Normal
    private LocalDateTime last_connection_time;
    private Socket socket;
    private BufferedWriter bufferedWriter;
    private BufferedReader bufferedReader;

    public Client(Socket socket, Integer id, String first_name, String last_name, String username, String password, String email, State.StateUser state, Type.TypeUser type, LocalDateTime last_connection_time){
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.password = password;
        this.email = email;
        this.type = type;
        this.state = state;
        this.last_connection_time = last_connection_time;
        try {
            this.socket = socket;
            this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            this.username = username;
        } catch (IOException e){
            closeEverything(socket, bufferedWriter, bufferedReader);
        }
    }

    public Integer getId() { return id; }

    public String getLast_name() { return last_name; }

    public String getFirst_name() { return first_name; }

    public String getEmail() { return email; }

    public String getPassword() { return password; }

    public String getUsername() { return username; }

    public Type.TypeUser getType() { return type; }

    public State.StateUser getState() {
        return state;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setType(Type.TypeUser type) {
        this.type = type;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "User{" +
                "first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", type=" + type +
                '}';
    }

    public void sendMessage(){
        try {
            bufferedWriter.write(username);
            bufferedWriter.newLine();
            bufferedWriter.flush();

            Scanner scanner = new Scanner(System.in);
            while (socket.isConnected()){
                String messageToSend = scanner.nextLine();
                bufferedWriter.write(username+ ": " + messageToSend);
                bufferedWriter.newLine();
                bufferedWriter.flush();
            }
        } catch (IOException e ){
            closeEverything(socket, bufferedWriter, bufferedReader);
        }
    }

    public void listenForMessage(){
        new Thread(new Runnable() {

            @Override
            public void run() {
                String messageFromGroupeChat;

                while (socket.isConnected()){
                    try {
                        messageFromGroupeChat = bufferedReader.readLine();
                        System.out.println(messageFromGroupeChat);
                    } catch (IOException e){
                        closeEverything(socket, bufferedWriter, bufferedReader);
                    }
                }
            }
        }).start();
    }

    public void closeEverything(Socket socket, BufferedWriter bufferedWriter, BufferedReader bufferedReader){
        try {
            if(bufferedReader != null){
                bufferedReader.close();
            }
            if (bufferedWriter != null){
                bufferedWriter.close();
            }
            if (socket != null){
                socket.close();
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }
    public void updateUserList() {
        String userList = "/userlist";

        for (ClientHandler client : ClientHandler.clients) {
            userList += " " + client.getUsername();
        }

        for (ClientHandler client : ClientHandler.clients) {
            sendMessage();
        }
    }

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your username for the group chat");
        String username = scanner.nextLine();
        String password = scanner.nextLine();
        Socket socket = new Socket("localhost", 4278);
        Client client = new Client(socket, 1234, "Maeva", "Montagneux", username, password, "maeva.montagneux@edu.ece.fr", State.StateUser.MISSING, Type.TypeUser.NORMAL, null);
        client.listenForMessage();// on peut lancer que ces deux méthodes car elles contiennent toutes deux des while loop tant que le socket est connecté donc tant qu'un utilisateur ne se déconnecte pas ça continue
        client.sendMessage();// elle speuvent s'executer en même temps car on a différents thread qui peuvent s'executer en mm temps

    }
}



