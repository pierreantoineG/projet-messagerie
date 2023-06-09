package Controller;

import Dao.UserDaoImpl;
import View.*;
import Model.*;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;

public class LoginControl {
    private LoginView loginView;

    private ChatControl chatControl;

    private Client clientUser;

    private static String url = "jdbc:sqlserver://projetmessagerie.database.windows.net:1433;database=projet_messagerie;user=pgloulou@projetmessagerie;password={your_password_here};encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;";
    private static String login = "pgloulou";
    private static String passwd = "Malouise17";

    public LoginControl(LoginView loginView) {
        this.loginView = loginView;
    }

    public void initialize() {
        animationComponentField(loginView.getHandleField(), loginView.getHandle());
        animationComponentField(loginView.getPasswordField(), loginView.getPassword());
        animationComponentField(loginView.getNameField(), loginView.getNameRegister());
        animationComponentField(loginView.getSurnameField(), loginView.getSurnameRegister());
        animationComponentField(loginView.getEmailField(), loginView.getEmail());
        animationComponentField(loginView.getHandleFieldRegister(), loginView.getHandleRegister());
        animationComponentField(loginView.getPasswordFieldRegister(), loginView.getPasswordRegister());

        JButton signUpButton = loginView.getSignUpButton();
        changeColor(signUpButton);
        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changeView(loginView.getPanelRegister());
            }
        });

        JButton loginButton = loginView.getLoginButton();
        changeColor(loginButton);
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clientUser = new Client();
                clientUser.setUsername(loginView.getHandleField().getText());
                clientUser.setPassword(loginView.getPasswordField().getPassword());

//                String handle = loginView.getHandleField().getText();
//                char[] password = loginView.getPasswordField().getPassword();

                try {
                    clientUser.setTypeUser(UserDaoImpl.VerifLogin(clientUser.getUsername(), clientUser.getPassword()));
                } catch (SQLException | NoSuchAlgorithmException | UnsupportedEncodingException ex) {
                    throw new RuntimeException(ex);
                }

                if (clientUser.getTypeUser() != -1) {
                    if (clientUser.getTypeUser() == 1) {
                        //displayAdminView();
                        try {
                            displayUserView(clientUser.getUsername());
                        } catch (SQLException ex) {
                            throw new RuntimeException(ex);
                        }
                        loginView.setVisible(false);
                    } else if (clientUser.getTypeUser() == 2) {
                        //displayModeratorView();
                        try {
                            displayUserView(clientUser.getUsername());
                        } catch (SQLException ex) {
                            throw new RuntimeException(ex);
                        }
                        loginView.setVisible(false);
                    } else {
                        try {
                            displayUserView(clientUser.getUsername());
                        } catch (SQLException ex) {
                            throw new RuntimeException(ex);
                        }
                        loginView.setVisible(false);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Nom d'utilisateur ou mot de passe invalide.");
                }
                System.out.println(clientUser.getUsername());
            }
        });

        JButton registerButton = loginView.getRegisterButton();
        changeColor(registerButton);
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clientUser = new Client();
                clientUser.setFirst_name(loginView.getNameField().getText());
                clientUser.setLast_name(loginView.getSurnameField().getText());
                clientUser.setEmail(loginView.getEmailField().getText());
                clientUser.setUsername(loginView.getHandleFieldRegister().getText());
                clientUser.setPassword(loginView.getPasswordFieldRegister().getPassword());
//                String name = loginView.getNameRegister().getText();
//                String surname = loginView.getSurnameRegister().getText();
//                String email = loginView.getEmail().getText();
//                String handle = loginView.getHandleFieldRegister().getText();
//                char[] password = loginView.getPasswordFieldRegister().getPassword();
                //int role = 3;
                //clientUser.setRole(role);

                if (registerUser(clientUser.getFirst_name(), clientUser.getLast_name(), clientUser.getEmail(), clientUser.getUsername(), clientUser.getPassword())) {
                    JOptionPane.showMessageDialog(null, "Utilisateur enregistré avec succès.");
                    try {
                        displayUserView(clientUser.getUsername());
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                    loginView.setVisible(false);
                } else {
                    JOptionPane.showMessageDialog(null, "Erreur lors de l'enregistrement de l'utilisateur.");
                }
                System.out.println(clientUser.getUsername());

            }
        });


        JLabel returnLabel = loginView.getReturnLabel();
        returnLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                returnLabel.setIcon(loginView.getImageIconReturnWhiteResize());
                returnLabel.repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                returnLabel.setIcon(loginView.getImageIconReturnBlackResize());
                returnLabel.repaint();
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                changeView(loginView.getPanelLog());
            }
        });

//        signUpButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                loginView.changeView();
//            }
//        });
    }


    public void animationComponentField(JTextComponent myComponentField, JLabel myLabelComponent) {
        myComponentField.getDocument().addDocumentListener(new DocumentListener() {
            public void changedUpdate(DocumentEvent e) {
                if (myComponentField.getText().isEmpty()) {
                    myLabelComponent.setVisible(true);
                } else {
                    myLabelComponent.setVisible(false);
                }
            }

            public void removeUpdate(DocumentEvent e) {
                if (myComponentField.getText().isEmpty()) {
                    myLabelComponent.setVisible(true);
                } else {
                    myLabelComponent.setVisible(false);
                }
            }

            public void insertUpdate(DocumentEvent e) {
                myLabelComponent.setVisible(false);
            }
        });
        if (myComponentField.getText().isEmpty()) {
            myLabelComponent.setVisible(true);
        } else {
            myLabelComponent.setVisible(false);
        }
    }


    public void changeView(JPanel panel) {
        loginView.getCurrentPanel().setVisible(false);
        loginView.setCurrentPanel(panel);
        loginView.add(loginView.getCurrentPanel());
        loginView.getCurrentPanel().setVisible(true);
        loginView.revalidate();
        loginView.repaint();

    }

    public void changeColor(JButton jbutton) {
        jbutton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                jbutton.setForeground(Color.WHITE);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                jbutton.setForeground(Color.BLACK);
            }
        });
    }

    /*public int VerifLogin(String username, char[] password) {
        int role;

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

                    return role;
                }
            }
            return -1;
        } catch (SQLException | NoSuchAlgorithmException | UnsupportedEncodingException e) {
            e.printStackTrace();
            return -1;
        }
    }*/

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

    private boolean registerUser(String firstName, String lastName, String email, String username, char[] password) {

        boolean success = false;
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            // Établir la connexion avec la base de données
            connection = DriverManager.getConnection(url, login, passwd);

            boolean verif = VerifEmail(clientUser.getEmail());
            if(!verif){
                JOptionPane.showMessageDialog(null, "Adresse mail invalide");
                return false;
            }

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
            //statement.setInt(6, role);
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

    public void displayUserView(String cName) throws SQLException{
        ChatView chatView = new ChatView();
        ChatControl chatControl = new ChatControl(chatView, clientUser);
        UserDaoImpl.updateUserConnected(clientUser.getUsername());
        chatControl.initializeChatView(cName);
        chatControl.afficher();
        UserDaoImpl.updateDeconnected(clientUser.getUsername());
    }

    public boolean containsAtSymbol(String str) {
        return str.contains("@");
    }

    public boolean VerifEmail(String email){
        boolean verif;
        if (containsAtSymbol(email)) {
            verif = true;
        }
        else {
            verif = false;
        }
        return verif;
    }


}