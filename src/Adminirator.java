public class Adminirator extends User {
    boolean ban;
    Serveur serveur;


    public Adminirator(boolean ban, Serveur serveur, Integer id, String first_name, String last_name, String username, String password, String email, int state, Type type) {
        super(id, first_name, last_name, username, password, email, state, type);
        this.ban = ban;
        this.serveur = serveur;
    }
}
