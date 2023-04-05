public class UserDaoImpl implements UserDao {

    private Connection conn;

    public UserDaoImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public User getById(int id) {
        return getById(id);
        // Code pour récupérer un utilisateur par son identifiant
    }

    @Override
    public void save(User user) {
        // Code pour insérer un nouvel utilisateur dans la base de données
    }

    @Override
    public void update(User user) {
        // Code pour mettre à jour un utilisateur existant dans la base de données
    }

    @Override
    public void delete(User user) {
        // Code pour supprimer un utilisateur de la base de données
    }

    @Override
    public void add(User user) {
        //code pour ajouter un utilisateur
    }
}

