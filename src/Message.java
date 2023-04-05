import java.time.LocalDate;
import java.time.LocalDateTime;

public class Message {
    private int state;
    private String content;
    private User user_id;
    private LocalDate date;
    private LocalDateTime heure;

    public Message(int state, String content, User user, LocalDate date, LocalDateTime heure){
        this.state = state;
        this.content = content;
        this.user_id = user;
        this.date = date;
        this.heure = heure;
    }

    public int getState() { return state; }

    public String getContent() { return content; }

    public LocalDate getDate() { return date; }

    public User getUser() { return user_id; }

    public LocalDateTime getHeure() { return heure; }

    public void setContent(String content) {
        this.content = content;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setHeure(LocalDateTime heure) {
        this.heure = heure;
    }

    public void setState(int state) {
        this.state = state;
    }

    public void setUser(User user) {
        this.user_id = user;
    }

    @Override
    public String toString() {
        return "Message{" +
                "state=" + state +
                ", content='" + content + '\'' +
                ", user=" + user_id +
                ", date=" + date +
                ", heure=" + heure +
                '}';
    }
}

