package Dao;

import Dao.UserDao;
import Model.Client;

import javax.swing.*;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.*;

import java.awt.*;
import java.sql.*;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {
    private static String url = "jdbc:sqlserver://projetmessagerie.database.windows.net:1433;database=projet_messagerie;user=pgloulou@projetmessagerie;password={your_password_here};encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;";
    private static String login = "pgloulou";
    private static String passwd = "Malouise17";

    public static void saveMessage(String username, String content) {
        try {
            // Établir la connexion avec la base de données
            Connection connection = DriverManager.getConnection(url, login, passwd);
            // Récupérer l'ID de l'utilisateur
            String query = "SELECT id FROM users WHERE username=?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
            int userID = -1;
            if (resultSet.next()) {
                userID = resultSet.getInt("id");
            }
            // Si l'utilisateur existe, enregistrer le message dans la base de données
            if (userID != -1) {
                query = "INSERT INTO message (user_id, content) VALUES (?, ?)";
                statement = connection.prepareStatement(query);
                statement.setInt(1, userID);
                statement.setString( 2, content);
                statement.executeUpdate();
            }
            // Fermer la connexion avec la base de données
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void checkAndBanUser(String username, String content) throws SQLException {
        String[] forbiddenWords = {"putain", "merde", "enculé", "connard", "salope", "pédé", "gueule", "pute",
                "trou du cul", "foutre", "bite", "cul", "nique ta mère", "va te faire foutre", "sale race",
                "sale juif", "sale noir", "sale arabe", "sale chinois", "sale pédé", "sale gouine", "suce ma bite",
                "bougnoule", "tapette", "PD", "juif", "nègre", "enculer", "pétasse", "enfoiré", "putassier", "sa race",
                "sa mère", "ta mère la pute", "ta mère la salope", "ta mère la traînée", "traînée", "tarlouze", "fiotte",
                "fiot", "connasse", "bordel", "foutaise", "va chier", "chier dans ton froc", "chier dans ton slip",
                "fils de pute", "fils de chienne", "fils de pédale", "enculé de ta race", "enculé de ta mère", "baise ta mère",
                "baiser", "niquer", "merdique", "merdouille", "pouffiasse", "branleur", "branleuse", "sac à merde", "culé",
                "dégueulasse", "connard de ta race", "foutre en l'air", "foutre le bordel", "grosse pute", "batard", "batârd",
                "connard de merde", "pute de merde", "sale enculé", "sale fils de pute", "va niquer ta mère",
                "vas te faire enculer", "enculé de ta mère la pute", "va crever", "enculé de ta mère la salope",
                "sale fils de chienne", "suceur de bites", "sale enculé de ta mère", "sale grosse pute", "pute à juif",
                "pute à nègre", "pute à arabe", "pute à chinois", "pute à pédé", "pute à gouine", "enculé de ta mère le bâtard",
                "salaud", "salopard", "branleur de merde", "pisseuse", "suceuse", "enculé de ta mère le chien", "enculé de ta race le bâtard",
                "va te faire mettre", "va te faire voir", "connard de ta mère", "fils de pute de ta race", "sale pute de ta mère",
                "ta mère en string", "ta mère en bikini", "ta mère en maillot de bain", "ta mère la grenouille", "ta mère la truite",
                "ta mère la baleine", "ta mère la vache", "ta mère la dinde", "ta mère la poule", "ta mère la pintade", "ta mère la dinde aux marrons",
                "ta mère la chauve-souris", "ta mère la carpe", "ta mère la sardine", "ta mère la truie", "ta mère la cochonne"};
        for (String word : forbiddenWords) {
            if (content.contains(word)) {
                banUser(username, true);
                JOptionPane.showMessageDialog(null, "our are not allowed to say "+content);
                break;
            }
        }
    }

    public static Map<String, Integer> countMessagesSentByUser() {
        Map<String, Integer> messageCountByUser = new HashMap<>();
        String query = "SELECT u.username, COUNT(m.id) AS nb_messages_envoyes " +
                "FROM users u " +
                "LEFT JOIN MESSAGE m ON u.id = m.USER_ID " +
                "GROUP BY u.id, u.username";
        try (Connection con = DriverManager.getConnection(url, login, passwd);
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(query)) {
            while (rs.next()) {
                String username = rs.getString("username");
                int messageCount = rs.getInt("nb_messages_envoyes");
                messageCountByUser.put(username, messageCount);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return messageCountByUser;
    }
    public static int countDeconnectedUsers() {
        int count = 0;
        String query = "SELECT COUNT(*) AS count FROM users WHERE state = 'deconnected'";
        try (Connection con = DriverManager.getConnection(url, login, passwd);
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(query)) {
            if (rs.next()) {
                count = rs.getInt("count");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return count;
    }
    public static int countAwaydUsers() {
        int count = 0;
        String query = "SELECT COUNT(*) AS count FROM users WHERE state = 'away'";
        try (Connection con = DriverManager.getConnection(url, login, passwd);
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(query)) {
            if (rs.next()) {
                count = rs.getInt("count");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return count;
    }
    public static int countUsersWithRoleAdmin() {
        int count = 0;
        String query = "SELECT COUNT(*) AS count FROM users WHERE role = 1";
        try (Connection con = DriverManager.getConnection(url, login, passwd);
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(query)) {
            if (rs.next()) {
                count = rs.getInt("count");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return count;
    }
    public static int countUsersWithRoleModerateur() {
        int count = 0;
        String query = "SELECT COUNT(*) AS count FROM users WHERE role = 2";
        try (Connection con = DriverManager.getConnection(url, login, passwd);
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(query)) {
            if (rs.next()) {
                count = rs.getInt("count");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return count;
    }
    public static int countUsersWithRoleUser() {
        int count = 0;
        String query = "SELECT COUNT(*) AS count FROM users WHERE role = 3";
        try (Connection con = DriverManager.getConnection(url, login, passwd);
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(query)) {
            if (rs.next()) {
                count = rs.getInt("count");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return count;
    }
    public static int countBanned() {
        int count = 0;
        String query = "SELECT COUNT(*) AS count FROM users WHERE banned = 1";
        try (Connection con = DriverManager.getConnection(url, login, passwd);
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(query)) {
            if (rs.next()) {
                count = rs.getInt("count");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return count;
    }
    public static int countNoBanned() {
        int count = 0;
        String query = "SELECT COUNT(*) AS count FROM users WHERE banned = 0";
        try (Connection con = DriverManager.getConnection(url, login, passwd);
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(query)) {
            if (rs.next()) {
                count = rs.getInt("count");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return count;

    }


    public static void updateDeconnected(String username) throws SQLException {
        String query = "UPDATE users SET state = 'deconnected' WHERE username = ?";
        Connection connection = DriverManager.getConnection(url, login, passwd);
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, username);
        statement.executeUpdate();
        statement.close();
        connection.close();
    }

    public static int VerifLogin(String username, char[] password) throws SQLException, NoSuchAlgorithmException, UnsupportedEncodingException {
        int role;

        //try {
            // Récupérer le mot de passe haché de l'utilisateur à partir de la base de données
            Connection connection = DriverManager.getConnection(url, login, passwd);
            String query = "SELECT password, role, banned FROM users WHERE username = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, username);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                String hashedPasswordFromDB = result.getString("password");
                role = result.getInt("role");
                boolean isBanned = result.getBoolean("banned");

                // Hacher le mot de passe entré par l'utilisateur avec SHA-1
                MessageDigest md = MessageDigest.getInstance("SHA-1");
                md.update(new String(password).getBytes("UTF-8"));
                byte[] digest = md.digest();
                String hashedInputPassword = bytesToHex(digest); // Utilisez bytesToHex ici

                // Comparer les deux mots de passe hachés
                if (hashedInputPassword.equalsIgnoreCase(hashedPasswordFromDB)) {
                    if (isBanned) {
                        JOptionPane.showMessageDialog(null, "Votre compte a été banni.", "Erreur", JOptionPane.ERROR_MESSAGE);
                        return -1;
                    }
                    /*else {
                        // Mettre à jour lastTimeConnection dans la base de données
                        //PreparedStatement updateLastTimeConnection = connection.prepareStatement(
                               // "UPDATE users SET lastTimeConnection = CURRENT_TIMESTAMP WHERE username = ?");
                        //updateLastTimeConnection.setString(1, username);
                        //updateLastTimeConnection.executeUpdate();
                    }*/
                    return role;
                }
            }
            return -1;
        /*} catch (SQLException | NoSuchAlgorithmException | UnsupportedEncodingException e) {
            e.printStackTrace();
            return -1;
        }*/
    }

    private static final char[] HEX_ARRAY = "0123456789ABCDEF".toCharArray();

    public static String bytesToHex(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        for (int i = 0; i < bytes.length; i++) {
            int v = bytes[i] & 0xFF;
            hexChars[i * 2] = HEX_ARRAY[v >>> 4];
            hexChars[i * 2 + 1] = HEX_ARRAY[v & 0x0F];
        }
        return new String(hexChars);
    }
    /*public static void saveMessage(String username, String content) {
        try {
            // Établir la connexion avec la base de données
            Connection connection = DriverManager.getConnection(url, login, passwd);
            // Récupérer l'ID de l'utilisateur
            String query = "SELECT id FROM users WHERE username=?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
            int userID = -1;
            if (resultSet.next()) {
                userID = resultSet.getInt("id");
            }
            // Si l'utilisateur existe, enregistrer le message dans la base de données
            if (userID != -1) {
                query = "INSERT INTO message (user_id, content) VALUES (?, ?)";
                statement = connection.prepareStatement(query);
                statement.setInt(1, userID);
                statement.setString(2, content);
                statement.executeUpdate();
            }
            // Fermer la connexion avec la base de données
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }*/


    public static String[] getConnectedUsers() throws SQLException {
        String query = "SELECT first_name, last_name FROM users WHERE state = 'connected' ORDER BY first_name ASC";
        Connection connection = DriverManager.getConnection(url, login, passwd);
        PreparedStatement statement = connection.prepareStatement(query);
        ResultSet resultSet = statement.executeQuery();
        List<String> userList = new ArrayList<>();
        while (resultSet.next()) {
            String firstName = resultSet.getString("first_name");
            String lastName = resultSet.getString("last_name");
            String fullName = firstName + " " + lastName;
            userList.add(fullName);
        }
        String[] usersArray = new String[userList.size()];
        usersArray = userList.toArray(usersArray);
        return usersArray;
    }

    @Override
    public Client getById(int id) {
        return null;
    }

    @Override
    public void add(Client client) {

    }

    @Override
    public void save(Client client) {

    }

    @Override
    public void update(Client client) {

    }

    @Override
    public void delete(Client client) {

    }

    @Override
    public Client getByUsername(String username) {
        return null;
    }

    @Override
    public Client create(Client client) {
        return null;
    }

    @Override
    public List<Client> getUsers() {
        return null;
    }
    public static String getFirstName(String username) throws SQLException {
        String query = "SELECT first_name FROM users WHERE username = ?";
        Connection connection = DriverManager.getConnection(url, login, passwd);
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, username);
        ResultSet resultSet = statement.executeQuery();
        String firstName = null;
        while (resultSet.next()) {
            firstName = resultSet.getString("first_name");
        }
        return firstName;
    }

    public static String getLastName(String username) throws SQLException {
        String query = "SELECT last_name FROM users WHERE username = ?";
        Connection connection = DriverManager.getConnection(url, login, passwd);
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, username);
        ResultSet resultSet = statement.executeQuery();
        String lastname = null;
        while (resultSet.next()) {
            lastname = resultSet.getString("last_Name");
        }
        return lastname;
    }

    public static int countUserMessages(String username) throws SQLException {
        String query = "SELECT COUNT(*) AS message_count FROM MESSAGE m JOIN users u ON m.USER_ID = u.id WHERE u.username = ?";
        Connection connection = DriverManager.getConnection(url, login, passwd);
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, username);
        ResultSet resultSet = statement.executeQuery();
        int messageCount = 0; // initialisation à 0
        while (resultSet.next()) {
            messageCount = resultSet.getInt("message_count");
        }

        return messageCount;
    }

    public static String getLastTimeConnection(String username) throws SQLException {
        String query = "SELECT last_Connection FROM users WHERE username = ?";
        Connection connection = DriverManager.getConnection(url, login, passwd);
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, username);
        ResultSet resultSet = statement.executeQuery();
        Timestamp lastTimeConnection = null;
        while (resultSet.next()) {
            lastTimeConnection = resultSet.getTimestamp("last_Connection");
        }
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String strTimestamp = formatter.format(lastTimeConnection);
        return strTimestamp;
    }

    public static String getState(String username) throws SQLException {
        String query = "SELECT state FROM users WHERE username = ?";
        Connection connection = DriverManager.getConnection(url, login, passwd);
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, username);
        ResultSet resultSet = statement.executeQuery();
        String state = null;
        while (resultSet.next()) {
            state = resultSet.getString("state");
        }
        return state;
    }

    public static String getRole(String username) throws SQLException {
        String query = "SELECT role FROM users WHERE username = ?";
        Connection connection = DriverManager.getConnection(url, login, passwd);
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, username);
        ResultSet resultSet = statement.executeQuery();
        int role = -1;
        while (resultSet.next()) {
            role = resultSet.getInt("role");
        }
        String roleName = "";
        switch (role) {
            case 1:
                roleName = "Administrator";
                break;
            case 2:
                roleName = "Moderator";
                break;
            case 3:
                roleName = "User";
                break;
            default:
                roleName = "Unknown";
                break;
        }
        return roleName;
    }

    public static int getRoleInt(String username) throws SQLException {
        String query = "SELECT role FROM users WHERE username = ?";
        Connection connection = DriverManager.getConnection(url, login, passwd);
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, username);
        ResultSet resultSet = statement.executeQuery();
        int role = -1;
        while (resultSet.next()) {
            role = resultSet.getInt("role");
        }
        return role;
    }

    public static int[] getAllRoles() throws SQLException {
        String query = "SELECT role FROM users";
        Connection connection = DriverManager.getConnection(url, login, passwd);
        PreparedStatement statement = connection.prepareStatement(query);
        ResultSet resultSet = statement.executeQuery();
        List<Integer> roleList = new ArrayList<>();
        while (resultSet.next()) {
            int role = resultSet.getInt("role");
            int rolefinal = 0;
            switch (role) {
                case 1:
                    rolefinal = 0;
                    break;
                case 2:
                    rolefinal = 1;
                    break;
                case 3:
                    rolefinal = 2;
                    break;
                default:
                    rolefinal = 2;
                    break;
            }
            roleList.add(rolefinal);
        }
        int[] roleArray = roleList.stream().mapToInt(i -> i).toArray();
        return roleArray;
    }

    public static Object[][] getUserStatus() throws SQLException {
        String query = "SELECT username, first_name, last_name, state, role, banned FROM users";
        Connection connection = DriverManager.getConnection(url, login, passwd);
        PreparedStatement statement = connection.prepareStatement(query);
        ResultSet resultSet = statement.executeQuery();
        List<Object[]> userList = new ArrayList<>();
        while (resultSet.next()) {
            String username = resultSet.getString("username");
            String firstName = resultSet.getString("first_name");
            String lastName = resultSet.getString("last_name");
            String state = resultSet.getString("state");
            int role = resultSet.getInt("role");
            int banned = resultSet.getInt("banned");

            int rolefinal = 0;
            switch (role) {
                case 1:
                    rolefinal = 0;
                    break;
                case 2:
                    rolefinal = 1;
                    break;
                case 3:
                    rolefinal = 2;
                    break;
                default:
                    rolefinal = 2;
                    break;
            }

            Color color;
            if (state.equals("connected")) {
                color = Color.GREEN;
            } else if (state.equals("deconnected")) {
                color =new Color(239, 72, 72);
            } else if (state.equals("away")) {
                color = Color.ORANGE;
            } else {
                color = Color.GRAY;
            }

            Object[] userStatus = {firstName + " " + lastName, username, rolefinal,banned, color};
            userList.add(userStatus);
        }
        Object[][] usersArray = new Object[userList.size()][4];
        for (int i = 0; i < userList.size(); i++) {
            usersArray[i] = userList.get(i);
        }
        return usersArray;
    }

    public static String[] getRoleModeratorUsers() throws SQLException {
        String query = "SELECT first_name, last_name FROM users WHERE  role = 2 ORDER BY first_name ASC";
        Connection connection = DriverManager.getConnection(url, login, passwd);
        PreparedStatement statement = connection.prepareStatement(query);
        ResultSet resultSet = statement.executeQuery();
        List<String> userList = new ArrayList<>();
        while (resultSet.next()) {
            String firstName = resultSet.getString("first_name");
            String lastName = resultSet.getString("last_name");
            String fullName = firstName + " " + lastName;
            userList.add(fullName);
        }
        String[] usersArray = new String[userList.size()];
        usersArray = userList.toArray(usersArray);
        return usersArray;
    }

    public static void updateUserAway(String username) throws SQLException {
        String query = "UPDATE users SET state = 'away' WHERE username = ?";
        Connection connection = DriverManager.getConnection(url, login, passwd);
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, username);
        statement.executeUpdate();
        statement.close();
        connection.close();
    }

    public static void updateUserConnected(String username) throws SQLException {
        String query = "UPDATE users SET state = 'connected' WHERE username = ?";
        Connection connection = DriverManager.getConnection(url, login, passwd);
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, username);
        statement.executeUpdate();
        statement.close();
        connection.close();
    }

    public static String[] getAdmim() throws SQLException {
        String query = "SELECT first_name, last_name FROM users WHERE  role = 1 ORDER BY first_name ASC";
        Connection connection = DriverManager.getConnection(url, login, passwd);
        PreparedStatement statement = connection.prepareStatement(query);
        ResultSet resultSet = statement.executeQuery();
        List<String> userList = new ArrayList<>();
        while (resultSet.next()) {
            String firstName = resultSet.getString("first_name");
            String lastName = resultSet.getString("last_name");
            String fullName = firstName + " " + lastName;
            userList.add(fullName);
        }
        String[] usersArray = new String[userList.size()];
        usersArray = userList.toArray(usersArray);
        return usersArray;
    }

    public static int countConnectedUsers() {
        int count = 0;
        String query = "SELECT COUNT(*) AS count FROM users WHERE state = 'connected'";
        try (Connection con = DriverManager.getConnection(url, login, passwd);
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(query)) {
            if (rs.next()) {
                count = rs.getInt("count");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return count;
    }

    public static String[] getAllNicknames() throws SQLException {
        String query = "SELECT username FROM users";
        Connection connection = DriverManager.getConnection(url, login, passwd);
        PreparedStatement statement = connection.prepareStatement(query);
        ResultSet resultSet = statement.executeQuery();
        List<String> nicknameList = new ArrayList<>();
        while (resultSet.next()) {
            String nickname = resultSet.getString("username");
            nicknameList.add(nickname);
        }
        String[] nicknameArray = new String[nicknameList.size()];
        nicknameArray = nicknameList.toArray(nicknameArray);
        return nicknameArray;
    }

    public static String[] getAllUsers() throws SQLException {
        String query = "SELECT first_name, last_name FROM users";
        Connection connection = DriverManager.getConnection(url, login, passwd);
        PreparedStatement statement = connection.prepareStatement(query);
        ResultSet resultSet = statement.executeQuery();
        List<String> userList = new ArrayList<>();
        while (resultSet.next()) {
            String firstName = resultSet.getString("first_name");
            String lastName = resultSet.getString("last_name");
            String fullName = firstName + " " + lastName;
            userList.add(fullName);
        }
        String[] usersArray = new String[userList.size()];
        usersArray = userList.toArray(usersArray);
        return usersArray;
    }

    public static int countUsers() throws SQLException {
        String query = "SELECT COUNT(*) AS total FROM users";
        Connection connection = DriverManager.getConnection(url, login, passwd);
        PreparedStatement statement = connection.prepareStatement(query);
        ResultSet resultSet = statement.executeQuery();
        resultSet.next();
        int total = resultSet.getInt("total");
        return total;
    }

    public static void updateUserUserName(String username, String firstName) throws SQLException {
        String query = "UPDATE users SET username = ? WHERE first_name = ?";
        Connection connection = DriverManager.getConnection(url, login, passwd);
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, username);
        statement.setString(2, firstName);
        statement.executeUpdate();
        statement.close();
        connection.close();
    }

    public static void updateUserFirstName(String username, String newLastName) throws SQLException {
        String query = "UPDATE users SET first_name = ? WHERE username = ?";
        Connection connection = DriverManager.getConnection(url, login, passwd);
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, newLastName);
        statement.setString(2, username);
        statement.executeUpdate();
        statement.close();
        connection.close();
    }

    public static void updateUserLastName(String username, String newLastName) throws SQLException {
        String query = "UPDATE users SET last_name = ? WHERE username = ?";
        Connection connection = DriverManager.getConnection(url, login, passwd);
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, newLastName);
        statement.setString(2, username);
        statement.executeUpdate();
        statement.close();
        connection.close();
    }

    public static String[] getUnBannedUsers() throws SQLException {
        String query = "SELECT username FROM users WHERE banned = 0";
        Connection connection = DriverManager.getConnection(url, login, passwd);
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        List<String> bannedUsers = new ArrayList<>();
        while (resultSet.next()) {
            String username = resultSet.getString("username");
            bannedUsers.add(username);
        }
        return bannedUsers.toArray(new String[0]);
    }

    public static String[] getBannedUsers() throws SQLException {
        String query = "SELECT username FROM users WHERE banned = 1";
        Connection connection = DriverManager.getConnection(url, login, passwd);
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        List<String> bannedUsers = new ArrayList<>();
        while (resultSet.next()) {
            String username = resultSet.getString("username");
            bannedUsers.add(username);
        }
        return bannedUsers.toArray(new String[0]);
    }


    public static void banUser(String username, boolean execute) throws SQLException {
        if (execute) {
            String query = "UPDATE users SET banned = 1 WHERE username = ?";
            Connection connection = DriverManager.getConnection(url, login, passwd);
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, username);
            statement.executeUpdate();
            statement.close();
            connection.close();
        }
    }
    public static void UnbanUser(String username, boolean execute) throws SQLException {
        if (execute) {
            String query = "UPDATE users SET banned = 0 WHERE username = ?";
            Connection connection = DriverManager.getConnection(url, login, passwd);
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, username);
            statement.executeUpdate();
            statement.close();
            connection.close();
        }
    }

    public static void updateUserRole(String username, int newRole) throws SQLException {
        String query = "UPDATE users SET role = ? WHERE username = ?";
        Connection connection = DriverManager.getConnection(url, login, passwd);
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, newRole);
        statement.setString(2, username);
        statement.executeUpdate();
        statement.close();
        connection.close();
    }



}

    /*private Connection conn;

    public Dao.UserDaoImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public Model.Client getById(int id) {
        Model.Client client = null;
        try {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM users WHERE id = ?");
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                client = new Model.Client(client.getId(), client.getFirst_name(), client.getLast_name(), client.getUsername(), client.getPassword(), client.getEmail(), client.getState(), client.getType());
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
    public void save(Model.Client client) {
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
    public void update(Model.Client client) {
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
    public void delete(Model.Client client) {
        try {
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM users WHERE id = ?");
            stmt.setInt(1, client.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void add(Model.Client client) {
       save(client);
    }
    @Override
    public Model.Client getByUsername(String username) {
        Model.Client client = null;
        try {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM users WHERE username = ?");
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                client = new Model.Client(client.getId(), client.getFirst_name(), client.getLast_name(), client.getUsername(), client.getPassword(), client.getEmail(), client.getState(), client.getType());
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
    public Model.Client create(Model.Client client) {
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
    public List<Model.Client> getUsers() {
        List<Model.Client> clientList = new ArrayList<>();

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

                Model.Client client = new Model.Client(id, firstName, lastName, username, password, email, stateuser, type);
                clientList.add(client);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return clientList;
    }
}*/

