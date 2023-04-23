package Accueil;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableCellEditor;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.UnsupportedEncodingException;
import java.sql.*;
import java.security.*;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Base64;
import java.security.Provider;
import java.security.Security;
import java.util.Set;
import javax.xml.*;


public class fenetre extends JFrame {

    // Les informations de connexion à la base de données
    private final String url = "jdbc:sqlserver://projetmessagerie.database.windows.net:1433;database=projet_messagerie;user=pgloulou@projetmessagerie;password={your_password_here};encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;";
    private final String login = "pgloulou";
    private final String  passwd = "Malouise17";

    public fenetre() {
        super("Application Messagerie");

        // Définit la taille de la fenêtre
        setSize(1920, 1080);

        // Centre la fenêtre au milieu de l'écran
        setLocationRelativeTo(null);

        // Crée un panneau pour les boutons
        JPanel panel = new JPanel();

        // Crée un bouton "login"
        JButton loginButton = new JButton("Login");

        // Définit le style du bouton "login"
        loginButton.setBackground(new Color(52, 152, 219));
        loginButton.setForeground(Color.WHITE);
        loginButton.setFocusPainted(false);
        loginButton.setFont(new Font("Arial", Font.BOLD, 14));
        loginButton.setBorder(BorderFactory.createEmptyBorder(10, 25, 10, 25));

        // Crée un bouton "register"
        JButton registerButton = new JButton("Register");

        // Définit le style du bouton "register"
        registerButton.setBackground(new Color(46, 204, 113));
        registerButton.setForeground(Color.WHITE);
        registerButton.setFocusPainted(false);
        registerButton.setFont(new Font("Arial", Font.BOLD, 14));
        registerButton.setBorder(BorderFactory.createEmptyBorder(10, 25, 10, 25));

        // Ajoute un ActionListener pour le bouton "login"
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Crée un formulaire de connexion
                JPanel formPanel = new JPanel();
                formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
                JLabel usernameLabel = new JLabel("Nom d'utilisateur:");
                JTextField usernameField = new JTextField(20);
                JLabel passwordLabel = new JLabel("Mot de passe:");
                JPasswordField passwordField = new JPasswordField(20);
                formPanel.add(usernameLabel);
                formPanel.add(usernameField);
                formPanel.add(passwordLabel);
                formPanel.add(passwordField);
                int result = JOptionPane.showConfirmDialog(null, formPanel, "Login",
                        JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
                if (result == JOptionPane.OK_OPTION) {
                    String username = usernameField.getText();
                    char[] password = passwordField.getPassword();
                    int userRole = isValidUser(username, password);
                    if (userRole != -1) {
                        if (userRole == 1 || userRole == 2) { // Si l'utilisateur est un admin (1) ou un modérateur (2)
                            showAdminModeratorWindow();
                        } else {
                            displayUserInformation(username);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Nom d'utilisateur ou mot de passe invalide.");
                    }
                }
            }
        });

        // Ajoute un ActionListener pour le bouton "register"
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Crée un formulaire d'inscription
                JPanel formPanel = new JPanel();
                formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
                JLabel firstNameLabel = new JLabel("Prénom:");
                JTextField firstNameField = new JTextField(20);
                formPanel.add(firstNameLabel);
                formPanel.add(firstNameField);

                JLabel lastNameLabel = new JLabel("Nom:");
                JTextField lastNameField = new JTextField(20);
                formPanel.add(lastNameLabel);
                formPanel.add(lastNameField);

                JLabel emailLabel = new JLabel("Email");
                JTextField emailField = new JTextField(20);
                formPanel.add(emailLabel);
                formPanel.add(emailField);

                JLabel usernameLabel = new JLabel("Nom d'utilisateur:");
                JTextField usernameField = new JTextField(20);
                formPanel.add(usernameLabel);
                formPanel.add(usernameField);

                JLabel passwordLabel = new JLabel("Mot de passe:");
                JPasswordField passwordField = new JPasswordField(20);
                formPanel.add(passwordLabel);
                formPanel.add(passwordField);




                int result = JOptionPane.showConfirmDialog(null, formPanel, "Register",
                        JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

                if (result == JOptionPane.OK_OPTION) {
                    String firstName = firstNameField.getText();
                    String lastName = lastNameField.getText();
                    String email = emailField.getText();
                    String username = usernameField.getText();
                    char[] password = passwordField.getPassword();
                    int role = 3;

                    // Enregistre l'utilisateur dans la base de données
                    if (registerUser(firstName, lastName, email, username, password, role)) {
                        JOptionPane.showMessageDialog(null, "Utilisateur enregistré avec succès.");
                    } else {
                        JOptionPane.showMessageDialog(null, "Erreur lors de l'enregistrement de l'utilisateur.");
                    }
                }
            }
        });

        // Ajoute les boutons au panneau
        panel.add(loginButton);
        panel.add(registerButton);

        // Ajoute le panneau à la fenêtre
        getContentPane().add(panel);

        // Affiche la fenêtre
        setVisible(true);

        // Ferme l'application lorsqu'on clique sur la croix rouge
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    ///CHANGER LES VALEURS PAR DEFAUT PAR LES VALEURS DU REGISTER
    private boolean banUser(String username) {
        try {
            Connection connection = DriverManager.getConnection(url, login, passwd);
            String query = "UPDATE users SET banned = TRUE WHERE username = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, username);
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    private int getUserRole(String username) {
        int role = 0;
        try {
            Connection connection = DriverManager.getConnection(url, login, passwd);
            String query = "SELECT role FROM users WHERE username = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, username);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                role = result.getInt("role");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return role;
    }//
    private int isValidUser(String username, char[] password) {
        int role = -1;
        try {
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
                    else {
                        // Mettre à jour lastTimeConnection dans la base de données
                        PreparedStatement updateLastTimeConnection = connection.prepareStatement(
                                "UPDATE users SET lastTimeConnection = CURRENT_TIMESTAMP WHERE username = ?");
                        updateLastTimeConnection.setString(1, username);
                        updateLastTimeConnection.executeUpdate();
                    }
                    return role;
                }
            }
            return -1;
        } catch (SQLException | NoSuchAlgorithmException | UnsupportedEncodingException e) {
            e.printStackTrace();
            return -1;
        }
    }
    private void showAdminModeratorWindow() {
        JFrame adminModeratorWindow = new JFrame("Gestion des utilisateurs");
        adminModeratorWindow.setSize(800, 600);
        adminModeratorWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        adminModeratorWindow.setLayout(new BorderLayout());

        String[] columnNames = {"ID", "Nom d'utilisateur", "Rôle", "Est banni ?", "Bannir"};
        Object[][] data = getUsersData(); // Charger les données des utilisateurs depuis la base de données
        DefaultTableModel model = new DefaultTableModel(data, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 4;
            }
        };
        JTable usersTable = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(usersTable);
        adminModeratorWindow.add(scrollPane, BorderLayout.CENTER);

        // TableCellRenderer pour le bouton
        TableCellRenderer buttonRenderer = new TableCellRenderer() {
            JButton banButton = new JButton();

            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                banButton.setText("Bannir");
                return banButton;
            }
        };
        usersTable.getColumn("Bannir").setCellRenderer(buttonRenderer);

        // TableCellEditor pour le bouton
        TableCellEditor buttonEditor = new DefaultCellEditor(new JCheckBox()) {
            JButton banButton = new JButton("Bannir");

            @Override
            public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
                banButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        int userId = (int) table.getValueAt(row, 0);
                        boolean isBanned = (boolean) table.getValueAt(row, 3);

                        if (toggleBanUser(userId, isBanned)) {
                            // Mettre à jour les données du tableau
                            Object[][] newData = getUsersData();
                            model.setDataVector(newData, columnNames);
                            model.fireTableDataChanged();
                        } else {
                            JOptionPane.showMessageDialog(null, "Erreur lors de la mise à jour du statut de bannissement de l'utilisateur.");
                        }
                    }
                });
                return banButton;
            }
        };
        usersTable.getColumn("Bannir").setCellEditor(buttonEditor);

        adminModeratorWindow.setVisible(true);
    }
    private boolean toggleBanUser(int userId, boolean isBanned) {
        try {
            Connection connection = DriverManager.getConnection(url, login, passwd);
            String updateQuery = "UPDATE users SET banned = ? WHERE id = ?";
            PreparedStatement updateStatement = connection.prepareStatement(updateQuery);
            updateStatement.setBoolean(1, !isBanned);
            updateStatement.setInt(2, userId);
            int updatedRows = updateStatement.executeUpdate();

            return updatedRows > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }


    private Object[][] getUsersData() {
        Object[][] data = null;

        try {
            Connection connection = DriverManager.getConnection(url, login, passwd);
            String query = "SELECT id, username, role, banned FROM users";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            ArrayList<Object[]> usersList = new ArrayList<>();
            while (resultSet.next()) {
                Object[] userData = new Object[4];
                userData[0] = resultSet.getInt("id");
                userData[1] = resultSet.getString("username");
                userData[2] = resultSet.getInt("role") == 1 ? "Admin" : resultSet.getInt("role") == 2 ? "Modérateur" : "Utilisateur";
                userData[3] = resultSet.getBoolean("banned");
                usersList.add(userData);
            }
            data = usersList.toArray(new Object[0][]);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return data;
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


    private void displayUserInformation(String username) {
        try {
            // Établir la connexion avec la base de données
            Connection connection = DriverManager.getConnection(url, login, passwd);

            // Créer une nouvelle fenêtre pour afficher les informations de l'utilisateur
            JFrame userFrame = new JFrame("Informations de l'utilisateur");

            // Définir la taille de la fenêtre
            userFrame.setSize(1920, 1080);

            // Centre la fenêtre au milieu de l'écran
            userFrame.setLocationRelativeTo(null);

            // Créer un panneau principal et définir son layout
            JPanel mainPanel = new JPanel();
            JPanel settingsPanel = new JPanel();
            JPanel infoPanel = new JPanel();
            infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
            mainPanel.setLayout(new BorderLayout());
            settingsPanel.setLayout(new BoxLayout(settingsPanel, BoxLayout.Y_AXIS));
            mainPanel.add(infoPanel, BorderLayout.NORTH);
            mainPanel.add(settingsPanel, BorderLayout.CENTER);


            // Créer un panneau pour afficher les informations de l'utilisateur
            JPanel userPanel = new JPanel();
            userPanel.setLayout(new BoxLayout(userPanel, BoxLayout.Y_AXIS));

            // Préparer la requête SQL pour récupérer les informations de l'utilisateur dans la table "users"
            String query = "SELECT first_name, last_name, email, state FROM users WHERE username = ?";


            // Ajouter les éléments pour sélectionner le statut
            settingsPanel.add(new JLabel("Sélectionner le statut:"));
            String[] statusOptions = {"Disponible", "Away", "Déconnecté"};
            JComboBox<String> statusComboBox = new JComboBox<>(statusOptions);
            settingsPanel.add(statusComboBox);
            JButton updateStatus = new JButton("Mettre à jour le statut");
            updateStatus.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        Connection connection = DriverManager.getConnection(url, login, passwd);
                        // Mettre à jour le statut dans la base de données
                        String selectedStatus = (String) statusComboBox.getSelectedItem();
                        System.out.println(selectedStatus +":"+ username);
                        // Requête SQL pour mettre à jour le statut dans la base de données
                        PreparedStatement updateStatus = connection.prepareStatement(
                                "UPDATE users SET state = ? WHERE username = ?");
                        updateStatus.setString(1, selectedStatus);
                        updateStatus.setString(2, username);
                        int rowsAffected = updateStatus.executeUpdate();

                        if (rowsAffected > 0) {
                            JOptionPane.showMessageDialog(null, "Statut mis à jour avec succès.");
                        } else {
                            JOptionPane.showMessageDialog(null, "Erreur lors de la mise à jour du statut.");
                        }
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
            });


            settingsPanel.add(updateStatus);

            // Ajouter le panneau des paramètres au panneau principal
            mainPanel.add(settingsPanel, BorderLayout.SOUTH);

            // ...

            // Ajouter le panneau principal dans la fenêtre
            userFrame.getContentPane().add(mainPanel);

            // Exécuter la requête SQL
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();

            // Parcourir le résultat de la requête et ajouter les informations de l'utilisateur dans des étiquettes
            while (resultSet.next()) {
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                String email = resultSet.getString("email");
                JLabel usernameLabel = new JLabel("Nom d'utilisateur: " + username);
                JLabel firstNameLabel = new JLabel("Prénom: " + firstName);
                JLabel lastNameLabel = new JLabel("Nom: " + lastName);
                JLabel emailLabel = new JLabel("Email: " + email);

                // Ajouter les étiquettes dans le panneau
                userPanel.add(usernameLabel);
                userPanel.add(firstNameLabel);
                userPanel.add(lastNameLabel);
                userPanel.add(emailLabel);
            }

            // Ajouter le panneau utilisateur à gauche du panneau principal
            mainPanel.add(userPanel, BorderLayout.WEST);

            // Créer un panneau pour afficher la liste de tous les noms d'utilisateur
            JPanel userListPanel = new JPanel();
            userListPanel.setLayout(new BoxLayout(userListPanel, BoxLayout.Y_AXIS));

            // Préparer la requête SQL pour récupérer tous les noms d'utilisateur dans la table "users"
            String userQuery = "SELECT username FROM users";

            // Exécuter la requête SQL
            PreparedStatement userStatement = connection.prepareStatement(userQuery);
            ResultSet userResultSet = userStatement.executeQuery();

            // Parcourir le résultat de la requête et ajouter les noms d'utilisateur dans des étiquettes
            JLabel userTitle = new JLabel("Utilisateurs :");
            userListPanel.add(userTitle);
            while (userResultSet.next()) {
                String user = userResultSet.getString("username");
                JLabel userLabel = new JLabel(" " + user);

                // Ajouter les étiquettes dans le panneau
                userListPanel.add(userLabel);
            }

            // Ajouter le panneau de la liste des utilisateurs à droite du panneau principal
            mainPanel.add(userListPanel, BorderLayout.EAST);

            // Fermer la connexion avec la base de données
            resultSet.close();
            statement.close();
            userResultSet.close();
            userStatement.close();
            connection.close();

            // Ajouter le panneau
            // Ajouter le panneau principal dans la fenêtre
            userFrame.getContentPane().add(mainPanel);

            // Afficher la fenêtre
            userFrame.setVisible(true);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }





    private boolean registerUser(String firstName, String lastName, String email, String username, char[] password, int role) {

        boolean success = false;
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            // Établir la connexion avec la base de données
            connection = DriverManager.getConnection(url, login, passwd);

            // Préparer la requête SQL pour insérer un nouvel utilisateur dans la table "users"
            String query = "INSERT INTO users (first_name, last_name, email, username, password, role) VALUES (?, ?, ?, ?, ?, ?)";

            // Hacher le mot de passe avec SHA-1
            MessageDigest digest = null;
            try {
                digest = MessageDigest.getInstance("SHA-1");
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
            byte[] hashedPassword = digest.digest(String.valueOf(password).getBytes("UTF-8"));
            String hashedPasswordHex = bytesToHex(hashedPassword);
            // Exécuter la requête SQL
            statement = connection.prepareStatement(query);
            statement.setString(1, firstName);
            statement.setString(2, lastName);
            statement.setString(3, email);
            statement.setString(4, username);
            statement.setString(5, hashedPasswordHex);
            statement.setInt(6, role);
            int rows = statement.executeUpdate();

            // Vérifier si l'insertion a réussi
            if (rows > 0) {
                success = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        } finally {
            // Fermer la connexion avec la base de données
            try {
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return success;
    }


}
