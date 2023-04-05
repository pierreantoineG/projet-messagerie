package Accueil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;


public class fenetre extends JFrame {

    // Les informations de connexion à la base de données
    private final String url = "jdbc:mysql://localhost/testprojet";
    private final String login = "root";
    private final String  passwd = "";

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
        boolean valid = false;

        try {
            // Établir la connexion avec la base de données
            Connection connection = DriverManager.getConnection(url, login, passwd);

            // Préparer la requête SQL pour vérifier si l'utilisateur existe dans la table "users"
            String query = "SELECT * FROM users WHERE username = ? AND password = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, username);
            statement.setString(2, String.valueOf(password));
            ResultSet result = statement.executeQuery();

            // Vérifier si l'utilisateur existe dans la table "users"
            if (result.next()) {
                valid = true;
            }

            // Fermer la connexion avec la base de données
            result.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return valid;
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
            userFrame.setSize(1920 , 1080);

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

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    private boolean registerUser(String firstName, String lastName, String email, String username, char[] password) {
        boolean success = false;

        try {
            // Établir la connexion avec la base de données
            Connection connection = DriverManager.getConnection(url, login, passwd);

            // Préparer la requête SQL pour insérer un nouvel utilisateur dans la table "users"
            String query = "INSERT INTO users (first_name, last_name, email, username, password) VALUES (?, ?, ?, ?, ?)";

            // Exécuter la requête SQL
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, firstName);
            statement.setString(2, lastName);
            statement.setString(3, email);
            statement.setString(4, username);
            statement.setString(5, String.valueOf(password));
            int rows = statement.executeUpdate();

            // Vérifier si l'insertion a réussi
            if (rows > 0) {
                success = true;
            }

            // Fermer la connexion avec la base de données
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return success;
    }

}