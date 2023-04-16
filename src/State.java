import java.net.Socket;
import java.time.LocalDateTime;

public class State extends Client {

    enum StateUser { MISSING, CONNECTED, DISCONNECTED}
    private StateUser stateUser;

    public State(StateUser stateUser, Socket socket, Integer id, String first_name, String last_name, String username, String password, String email, Type.TypeUser type, State.StateUser state, LocalDateTime last_connection_time) {
        super(socket, id, first_name, last_name, username, password, email, state, type, last_connection_time);

        this.stateUser = stateUser;
    }

    public StateUser getStateUser() {
        return stateUser;
    }

    public void setStateUser(StateUser stateUser) {
        this.stateUser = stateUser;
    }
}



