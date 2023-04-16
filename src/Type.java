import java.net.Socket;
import java.time.LocalDateTime;

public class Type extends Client {

    enum TypeUser {NORMAL, MODERATOR, ADMINISTRATOR;}
    private TypeUser typeUser;

    public Type(TypeUser typeUser, Socket socket, Integer id, String first_name, String last_name, String username, String password, String email, Type.TypeUser type, State.StateUser state, LocalDateTime last_connection_time) {
        super(socket, id, first_name, last_name, username, password, email, state, type, last_connection_time);

        this.typeUser = typeUser;
    }

    public TypeUser getTypeUser() {
        return typeUser;
    }

    public void setTypeUser(TypeUser typeUser) {
        this.typeUser = typeUser;
    }
}



