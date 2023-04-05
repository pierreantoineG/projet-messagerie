public class Type extends User {

    enum TypeUser {NORMAL, MODERATOR, ADMINISTRATOR;}
    private TypeUser typeUser;

    public Type(TypeUser typeUser, Integer id, String first_name, String last_name, String username, String password, String email, int state, Type type) {
        super(id, first_name, last_name, username, password, email, state, type);

        this.typeUser = typeUser;
    }

    public TypeUser getTypeUser() {
        return typeUser;
    }

    public void setTypeUser(TypeUser typeUser) {
        this.typeUser = typeUser;
    }
}



