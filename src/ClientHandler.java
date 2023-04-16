import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class ClientHandler implements Runnable {
    public static ArrayList<ClientHandler> clients = new ArrayList<>(); //static car nous voulons qu'il appartienne à la classe et non à chaque objet
    private Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    private String username;

    public ClientHandler (Socket socket) {
        try{
            this.socket = socket;
            this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.username = bufferedReader.readLine(); //attend que le client rentre son nom d'utilisateur
            clients.add(this);
            broadcastMessage("SERVER : "+ username + " has entered the chat");
        } catch (IOException e){
            closeEverything(socket, bufferedReader, bufferedWriter);
        }
    }

    public String getUsername(Socket socket) {
        for (ClientHandler client : clients) {
            if (client.getClientSocket().equals(socket)) {
                return client.getUsername();
            }
        }
        return null;
    }

    public Socket getClientSocket() {
        return socket;
    }

    public void broadcastMessage (String messageToSend){
        for (ClientHandler clientHandler : clients) {
            try {
                if(!clientHandler.username.equals(username)){
                    clientHandler.bufferedWriter.write(messageToSend);
                    clientHandler.bufferedWriter.newLine();
                    clientHandler.bufferedWriter.flush();

                }
            } catch (IOException e){
                closeEverything(socket, bufferedReader, bufferedWriter);
            }
        }
    }

    public void removeClientHandler() {
        clients.remove(this);
        broadcastMessage("SERVER :" + username + "has left the chat");
    }



    /*public void removeClient(ClientHandler client, UserDao userDao) {
        clients.remove(client);
        Client user = userDao.getByUsername(client.getUsername());
        userDao.delete(user);
        System.out.println("Client " + client.getUsername() + " déconnecté");
        updateUserList();
    }*/

    /*public String getUsername() {
        return server.getUsername(socket); //on recupère le username pour pouvoir le ban ou changer son type
    }*/

    public String getUsername() {
        return username;
    }

    public void run() {
        String messageFromClient;

        while (socket.isConnected()) {
            try {
                messageFromClient = bufferedReader.readLine();
                broadcastMessage(messageFromClient);
            } catch (IOException e) {
                closeEverything(socket, bufferedReader, bufferedWriter);
                break;
            }

            /*String inputLine;
            while ((inputLine = in.readLine()) != null) {
                // Traitement du message reçu grâce à readLine qui bloque jusqu'à ce qu'elle recoive une ligne de texte commplète avant de continuer
                String outputLine = server.processInput(inputLine, out);

                if (inputLine.startsWith("/user")) {
                    // Mettre à jour la liste des utilisateurs connectés
                    server.updateUserList();

                } else if (inputLine.startsWith("/msg")) {
                    // Envoyer le message à tous les clients connectés
                    server.broadcastMessage(inputLine.substring(5), this);

                } else {
                    // Envoyer un message d'erreur au client
                    out.println("Commande invalide");
                }

                // Envoi de la réponse au client
                out.println(outputLine);


                if (outputLine.equals("Bye.")) {
                    break;
                }
            }
            in.close();*/
        }
    }

    public void closeEverything (Socket socket, BufferedReader bufferedReader, BufferedWriter bufferedWriter){
        removeClientHandler();
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
}
