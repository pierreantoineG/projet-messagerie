package Accueil;

import java.sql.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.UnsupportedEncodingException;
import java.sql.*;
import java.security.*;
import java.security.MessageDigest;
import java.util.Base64;
import java.security.Provider;
import java.security.Security;
import java.util.Set;
import javax.swing.border.Border;
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

                    // Vérifier si l'utilisateur est valide et afficher ses informations s'il l'est
                    if (isValidUser(username, password)) {
                        displayUserInformation(username);
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

                    // Enregistre l'utilisateur dans la base de données
                    if (registerUser(firstName, lastName, email, username, password)) {
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
    private boolean isValidUser(String username, char[] password) {
        try {
            // Récupérer le mot de passe haché de l'utilisateur à partir de la base de données
            Connection connection = DriverManager.getConnection(url, login, passwd);
            String query = "SELECT password FROM users WHERE username = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, username);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                String hashedPasswordFromDB = result.getString("password");

                // Hacher le mot de passe entré par l'utilisateur avec SHA-1
                MessageDigest md = MessageDigest.getInstance("SHA-1");
                md.update(new String(password).getBytes("UTF-8"));
                byte[] digest = md.digest();
                String hashedInputPassword = bytesToHex(digest); // Utilisez bytesToHex ici

                // Comparer les deux mots de passe hachés
                if (hashedInputPassword.equalsIgnoreCase(hashedPasswordFromDB)) {
                    return true;
                } else {
                    return false;
                }
            }
            else {
                return false;
            }
        } catch (SQLException | NoSuchAlgorithmException | UnsupportedEncodingException e) {
            e.printStackTrace();
            return false;
        }
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

            // Préparer la requête SQL pour récupérer les informations de l'utilisateur dans la table "users"
            String query = "SELECT first_name, last_name, email FROM users WHERE username = ?";

            // Exécuter la requête SQL
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();

            // Créer une nouvelle fenêtre pour afficher les informations de l'utilisateur
            JFrame userFrame = new JFrame("Informations de l'utilisateur");

            // Définir la taille de la fenêtre
            userFrame.setSize(1920, 1080);

            // Centre la fenêtre au milieu de l'écran
            userFrame.setLocationRelativeTo(null);

            // Créer un panneau pour afficher les informations de l'utilisateur
            JPanel userPanel = new JPanel();
            userPanel.setLayout(new BoxLayout(userPanel, BoxLayout.Y_AXIS));

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

            // Fermer la connexion avec la base de données
            resultSet.close();
            statement.close();
            connection.close();

            // Ajouter le panneau dans la fenêtre
            userFrame.getContentPane().add(userPanel);

            // Afficher la fenêtre
            userFrame.setVisible(true);
/*
            userFrame.getContentPane().add(userPanel);
            displayAllMessages(userPanel);

// Créer le champ de saisie de message et le bouton "Envoyer"
            JTextField messageField = new JTextField(20);
            JButton sendButton = new JButton("Envoyer");

            userPanel = new JPanel(new BorderLayout());
            userPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

            messageField = new JTextField();
            messageField.setPreferredSize(new Dimension(300, 50));
            userPanel.add(messageField, BorderLayout.CENTER);

            sendButton = new JButton("Envoyer");
            sendButton.setPreferredSize(new Dimension(100, 50));
            userPanel.add(sendButton, BorderLayout.EAST);

            getContentPane().add(userPanel);

            pack();
            setLocationRelativeTo(null);
            setVisible(true);

            JPanel finalUserPanel = userPanel;
            JTextField finalMessageField = messageField;
            sendButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    String messageContent = finalMessageField.getText();
                    sendMessage(messageContent, username);

                    displayLastMessage(finalUserPanel);
                    // Actualiser l'affichage des messages
                    displayAllMessages(finalUserPanel);
                    finalMessageField.setText("");
                    userFrame.getContentPane().revalidate(); // Actualiser la fenêtre
                    userFrame.getContentPane().repaint();
                }

            });

*/

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private boolean registerUser(String firstName, String lastName, String email, String username, char[] password) {
        boolean success = false;
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            // Établir la connexion avec la base de données
            connection = DriverManager.getConnection(url, login, passwd);

            // Préparer la requête SQL pour insérer un nouvel utilisateur dans la table "users"
            String query = "INSERT INTO users (first_name, last_name, email, username, password) VALUES (?, ?, ?, ?, ?)";

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

    private void displayAllMessages(JPanel messagesPanel) {
        try {
            // Établir la connexion avec la base de données
            Connection connection = DriverManager.getConnection(url, login, passwd);

            // Préparer la requête SQL pour récupérer tous les messages
            String query = "SELECT users.username, MESSAGE.content "
                    + "FROM users "
                    + "JOIN message ON users.id = MESSAGE.user_id";

            // Exécuter la requête SQL
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            // Créer un nouveau JPanel pour contenir tous les messages
            JPanel allMessagesPanel = new JPanel();
            allMessagesPanel.setLayout(new BoxLayout(allMessagesPanel, BoxLayout.Y_AXIS));
            allMessagesPanel.setBackground(Color.BLUE);
            allMessagesPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

            // Parcourir le résultat de la requête et ajouter les messages dans des étiquettes
            while (resultSet.next()) {
                String username = resultSet.getString("username");
                String content = resultSet.getString("content");

                // Créer un nouveau JPanel pour chaque message
                JPanel messagePanel = new JPanel();
                messagePanel.setLayout(new BoxLayout(messagePanel, BoxLayout.Y_AXIS));
                messagePanel.setBackground(Color.WHITE);
                messagePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1, true));
                messagePanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE));

                // Ajouter le nom d'utilisateur au messagePanel
                JLabel usernameLabel = new JLabel("Nom d'utilisateur: " + username);
                usernameLabel.setForeground(Color.BLACK);
                usernameLabel.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
                messagePanel.add(usernameLabel);

                // Ajouter le contenu du message au messagePanel
                JLabel contentLabel = new JLabel(content);
                contentLabel.setForeground(Color.WHITE);
                contentLabel.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
                messagePanel.add(contentLabel);

                // Ajouter le messagePanel à allMessagesPanel
                allMessagesPanel.add(messagePanel);

                // Ajouter un espace de 100 pixels entre chaque message
                allMessagesPanel.add(Box.createRigidArea(new Dimension(0, 100)));
            }

            // Fermer la connexion avec la base de données
            resultSet.close();
            statement.close();
            connection.close();

            // Ajouter le panel contenant tous les messages à un JScrollPane
            JScrollPane scrollPane = new JScrollPane(allMessagesPanel);

            // Ajouter le JScrollPane au messagesPanel passé en paramètre
            messagesPanel.add(scrollPane);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private void displayLastMessage(JPanel messagesPanel) {
        try {
            // Établir la connexion avec la base de données
            Connection connection = DriverManager.getConnection(url, login, passwd);

            // Préparer la requête SQL pour récupérer le dernier message
            String query = "SELECT TOP 1 users.username, message.content "
                    + "FROM users "
                    + "JOIN message ON users.id = message.user_id "
                    + "ORDER BY message.id DESC";

            // Exécuter la requête SQL
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            // Vérifier si un résultat est retourné
            if (resultSet.next()) {
                String username = resultSet.getString("username");
                String content = resultSet.getString("content");
                JLabel usernameLabel = new JLabel("Nom d'utilisateur: " + username);
                JLabel contentLabel = new JLabel("Contenu: " + content);
                // Ajouter les étiquettes dans le panneau
                messagesPanel.add(usernameLabel);
                messagesPanel.add(contentLabel);
            }

            // Fermer la connexion avec la base de données
            resultSet.close();
            statement.close();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private void sendMessage(String messageContent, String username) {
        try {
            // Établir la connexion avec la base de données
            Connection connection = DriverManager.getConnection(url, login, passwd);

            // Préparer la requête SQL pour insérer le message dans la base de données
            String query = "INSERT INTO message (user_id, content) VALUES (?, ?)";
            // Préparer la requête SQL pour récupérer les informations de l'utilisateur dans la table "users"
            String query_id = "SELECT id FROM users WHERE username = ?";

            PreparedStatement statement = connection.prepareStatement(query);
            PreparedStatement statement_id = connection.prepareStatement(query_id);

            statement_id.setString(1, username);
            ResultSet resultSet = statement_id.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");

                statement.setInt(1, id);
            }

            statement.setString(2, messageContent);
            statement.executeUpdate();

            // Fermer la connexion avec la base de données
            statement.close();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}