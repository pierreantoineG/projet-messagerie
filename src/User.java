public class User {
    private Integer id;
    private String first_name;
    private String last_name;
    private String username;
    private String password;
    private String email;
    public enum stateUser {
        MISSING(1), CONNECTED(2), DISCONNECTED(3);

        private final int state;

        stateUser(int state) { this.state = state; }

        public int getState() { return state; }
    }
    private Type type; // Admin Moderateur Normal

    public User(Integer id, String first_name, String last_name, String username, String password, String email, int state, Type type){
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.username = username;
        this.password = password;
        this.email = email;
        this.type = type;
        //this.stateUser = state;
    }

    public Integer getId() { return id; }

    public String getLast_name() { return last_name; }

    public String getFirst_name() { return first_name; }

    public String getEmail() { return email; }

    public String getPassword() { return password; }

    public String getUsername() { return username; }

    public Type getType() { return type; }

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

    public void setType(Type type) {
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

    //public sendMessage();
}



