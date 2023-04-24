package Model;

import Model.Client;

import java.time.LocalDateTime;

public class Message {
    private Integer id;
    private String content;
    private Client client_id;
    private LocalDateTime timestamp;

    public Message(Integer id, String content, Client client, LocalDateTime timestamp){
        this.id = id;
        this.content = content;
        this.client_id = client;
        this.timestamp = timestamp;
    }

    public Integer getId() { return id; }

    public String getContent() { return content; }

    public Client getUser() { return client_id; }

    public LocalDateTime getTimestamp() { return timestamp; }


    public void setId(Integer id) { this.id = id; }

    public void setContent(String content) {
        this.content = content;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public void setUser(Client client) {
        this.client_id = client;
    }

    @Override
    public String toString() {
        return "Model.Message{" +
                ", content='" + content + '\'' +
                ", user=" + client_id +
                ", timestamp =" + timestamp +
                '}';
    }
}

