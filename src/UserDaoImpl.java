import java.sql.*;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

/*public class UserDaoImpl implements UserDao {

    private Connection conn;

    public UserDaoImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public Client getById(int id) {
        Client client = null;
        try {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM users WHERE id = ?");
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                client = new Client(client.getId(), client.getFirst_name(), client.getLast_name(), client.getUsername(), client.getPassword(), client.getEmail(), client.getState(), client.getType());
                client.setId(rs.getInt("id"));
                client.setUsername(rs.getString("username"));
                client.setPassword(rs.getString("password"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return client;
    }

    @Override
    public void save(Client client) {
        try {
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO users (username, password) VALUES (?, ?)");
            stmt.setString(1, client.getUsername());
            stmt.setString(2, client.getPassword());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Client client) {
        try {
            PreparedStatement stmt = conn.prepareStatement("UPDATE users SET username = ?, password = ? WHERE id = ?");
            stmt.setString(1, client.getUsername());
            stmt.setString(2, client.getPassword());
            stmt.setInt(3, client.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void delete(Client client) {
        try {
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM users WHERE id = ?");
            stmt.setInt(1, client.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void add(Client client) {
       save(client);
    }
    @Override
    public Client getByUsername(String username) {
        Client client = null;
        try {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM users WHERE username = ?");
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                client = new Client(client.getId(), client.getFirst_name(), client.getLast_name(), client.getUsername(), client.getPassword(), client.getEmail(), client.getState(), client.getType());
                client.setId(rs.getInt("id"));
                client.setUsername(rs.getString("username"));
                client.setPassword(rs.getString("password"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return client;
    }

    @Override
    public Client create(Client client) {
        try {
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO users (first_name, last_name, username, password, email, type) VALUES (?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, client.getFirst_name());
            stmt.setString(2, client.getLast_name());
            stmt.setString(3, client.getUsername());
            stmt.setString(4, client.getPassword());
            stmt.setString(5, client.getEmail());
            stmt.setString(6, client.getType().toString());
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                int id = rs.getInt(1);
                client.setId(id);
            }
            return client;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    @Override
    public List<Client> getUsers() {
        List<Client> clientList = new ArrayList<>();

        try {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM users");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Integer id = rs.getInt("id");
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                String username = rs.getString("username");
                String password = rs.getString("password");
                String email = rs.getString("email");
                String state = rs.getString("state");
                State stateuser = State.(int)(state);
                String typeString = rs.getString("type");
                Type type = Type.(int)(typeString);

                Client client = new Client(id, firstName, lastName, username, password, email, stateuser, type);
                clientList.add(client);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return clientList;
    }
}*/

