package View;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.awt.event.*;
import java.util.Arrays;


public class LoginView extends JFrame {

    private JTextField handleField = new JTextField(14);
    private JPasswordField passwordField = new JPasswordField(14);
    private JTextField nameField = new JTextField(14);
    private JTextField surnameField = new JTextField(14);
    private JTextField emailField = new JTextField(14);
    private JTextField handleFieldRegister = new JTextField(14);
    private JPasswordField passwordFieldRegister = new JPasswordField(14);
    private JButton loginButton = new JButton("Login");
    private JButton signUpButton = new JButton("SIGN UP here");
    private JButton returnButton = new JButton("Back to login");
    private JLabel returnLabel;
    private JLabel handle = new JLabel("Handle");
    private JLabel password = new JLabel("Password");
    private JLabel name = new JLabel("Name");
    private JLabel surname = new JLabel("Surname");
    private JLabel email = new JLabel("Email");
    JLabel handleRegister = new JLabel("Handle");
    JLabel passwordRegister = new JLabel("Password");
    private JButton registerButton = new JButton("Register");
    private JPanel currentPanel;

    //    private String pseudo = handleField.getText();
//    private String mdp = Arrays.toString(passwordField.getPassword());
    private boolean stateSignUp = false;


    public static final int WINDOW_H = 650;
    public static final int WINDOW_W = 600;


    public LoginView() {
        //initialisation et création de la fenêtre
        super("Chat.Oeuf");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(WINDOW_W, WINDOW_H);
        this.setLocationRelativeTo(null);
        this.setBackground(new Color(0, 187, 249));


        //initialisation des zones de textes pour le pseudo et le mdp (pas de fond ni de bordure)

//        handleField.addMouseListener(new MouseAdapter() {
//            public void mouseClicked(MouseEvent e) {
//                handleField.requestFocus();
//            }
//        });

//        handleField.getDocument().addDocumentListener(new DocumentListener() {
//            @Override
//            public void insertUpdate(DocumentEvent e) {
//                updateText();
//            }
//
//            @Override
//            public void removeUpdate(DocumentEvent e) {
//                updateText();
//            }
//
//            @Override
//            public void changedUpdate(DocumentEvent e) {
//                updateText();
//            }
//
//            private void updateText() {
//                pseudo = handleField.getText(); // Mise à jour de la variable avec le nouveau contenu du champ de texte
//            }
//        });


//        signUpButton.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                stateSignUp = signUpButton.isSelected();
//            }
//        });

        setButton(signUpButton, 20);

        //création du panel qui va contenir tous les labels
        JPanel panelLog = new JPanel();
        panelLog.setLayout(new SpringLayout());
        panelLog.setBackground(new Color(0, 187, 249));

        //initialisation des labels contenant les images et les textes nécessaires
        ImageIcon imageIcon = new ImageIcon("images/LogoTexte2.png");
        Image image = imageIcon.getImage();
        JLabel labelLogo = new JLabel(new ImageIcon(image));

        ImageIcon fondLogin = new ImageIcon("images/Fond login.png");
        Image imgFondLog = fondLogin.getImage();
        JLabel labelFondLog1 = new JLabel(new ImageIcon(imgFondLog));
        JLabel labelFondLog2 = new JLabel(new ImageIcon(imgFondLog));

        setFieldWithText(handleField, handle);
        setFieldWithText(passwordField, password);

        ImageIcon imageIconBtn = new ImageIcon("images/Fond Bouton Login.png");
        ImageIcon imageIconBtnResize = new ImageIcon(imageIconBtn.getImage().getScaledInstance(465, 131, Image.SCALE_SMOOTH));
        Image fondBtn = imageIconBtnResize.getImage();
        JLabel labelBtn = new JLabel(new ImageIcon(fondBtn));

        loginButton.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                loginButton.setForeground(Color.WHITE);
            }

            public void mouseExited(MouseEvent e) {
                loginButton.setForeground(Color.BLACK);
            }
        });
        setButton(loginButton, 25);
        loginButton.setPreferredSize(new Dimension(402, 70));

        JLabel signUpInfo = new JLabel("Don't have an account?");
        signUpInfo.setFont(new Font("Poppins", Font.PLAIN, 20));

        // Ajout des labels au panel principal
        panelLog.add(labelLogo);
        panelLog.add(handle);
        panelLog.add(labelFondLog1);
        panelLog.add(password);
        panelLog.add(labelFondLog2);
        panelLog.add(handleField);
        panelLog.add(passwordField);
        panelLog.add(signUpButton);
        panelLog.add(signUpInfo);
        panelLog.add(labelBtn);
        panelLog.add(loginButton);

        // Définir les contraintes pour chaque composant
        SpringLayout layout = (SpringLayout) panelLog.getLayout();

        layout.putConstraint(SpringLayout.NORTH, labelLogo, 60, SpringLayout.NORTH, panelLog);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, labelLogo, 0, SpringLayout.HORIZONTAL_CENTER, panelLog);

        layout.putConstraint(SpringLayout.NORTH, labelFondLog1, 30, SpringLayout.SOUTH, labelLogo);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, labelFondLog1, 0, SpringLayout.HORIZONTAL_CENTER, panelLog);

        layout.putConstraint(SpringLayout.WEST, handle, 35, SpringLayout.WEST, labelFondLog1);
        layout.putConstraint(SpringLayout.VERTICAL_CENTER, handle, 0, SpringLayout.VERTICAL_CENTER, labelFondLog1);

        layout.putConstraint(SpringLayout.WEST, handleField, 35, SpringLayout.WEST, labelFondLog1);
        layout.putConstraint(SpringLayout.VERTICAL_CENTER, handleField, 0, SpringLayout.VERTICAL_CENTER, labelFondLog1);
        panelLog.setComponentZOrder(handleField, 0); //Permet de placer au dessus de tous les autres composants

        layout.putConstraint(SpringLayout.NORTH, labelFondLog2, -20, SpringLayout.SOUTH, labelFondLog1);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, labelFondLog2, 0, SpringLayout.HORIZONTAL_CENTER, panelLog);

        layout.putConstraint(SpringLayout.WEST, password, 35, SpringLayout.WEST, labelFondLog2);
        layout.putConstraint(SpringLayout.VERTICAL_CENTER, password, 0, SpringLayout.VERTICAL_CENTER, labelFondLog2);

        layout.putConstraint(SpringLayout.WEST, passwordField, 35, SpringLayout.WEST, labelFondLog2);
        layout.putConstraint(SpringLayout.VERTICAL_CENTER, passwordField, 0, SpringLayout.VERTICAL_CENTER, labelFondLog2);
        panelLog.setComponentZOrder(passwordField, 0);

        layout.putConstraint(SpringLayout.NORTH, labelBtn, -30, SpringLayout.SOUTH, labelFondLog2);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, labelBtn, 0, SpringLayout.HORIZONTAL_CENTER, panelLog);

        layout.putConstraint(SpringLayout.WEST, loginButton, 31, SpringLayout.WEST, labelBtn);
        layout.putConstraint(SpringLayout.VERTICAL_CENTER, loginButton, 0, SpringLayout.VERTICAL_CENTER, labelBtn);
        panelLog.setComponentZOrder(loginButton, 0);

        layout.putConstraint(SpringLayout.NORTH, signUpInfo, 20, SpringLayout.SOUTH, labelBtn);
        layout.putConstraint(SpringLayout.WEST, signUpInfo, 25, SpringLayout.WEST, labelFondLog2);

        layout.putConstraint(SpringLayout.WEST, signUpButton, -6, SpringLayout.EAST, signUpInfo);
        layout.putConstraint(SpringLayout.VERTICAL_CENTER, signUpButton, 1, SpringLayout.VERTICAL_CENTER, signUpInfo);


        // TODO ---------------------------------------- 2e panel ------------------------------------------------//

        JPanel panelRegister = new JPanel();
        panelRegister.setLayout(new SpringLayout());
        panelRegister.setBackground(new Color(0, 187, 249));

        ImageIcon imageIconRegisterResize = new ImageIcon(imageIcon.getImage().getScaledInstance(200, 65, Image.SCALE_SMOOTH));
        Image logoRegister = imageIconRegisterResize.getImage();
        JLabel labelLogoRegister = new JLabel(new ImageIcon(logoRegister));

        JLabel labelFondName = new JLabel(new ImageIcon(imgFondLog));
        JLabel labelFondSurname = new JLabel(new ImageIcon(imgFondLog));
        JLabel labelFondMail = new JLabel(new ImageIcon(imgFondLog));
        JLabel labelFondHandle = new JLabel(new ImageIcon(imgFondLog));
        JLabel labelFondPassword = new JLabel(new ImageIcon(imgFondLog));

        JLabel labelRegisterBtn = new JLabel(new ImageIcon(fondBtn));

        setFieldWithText(nameField, name);
        setFieldWithText(surnameField, surname);
        setFieldWithText(emailField, email);
        setFieldWithText(handleFieldRegister, handleRegister);
        setFieldWithText(passwordFieldRegister, passwordRegister);

        registerButton.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                registerButton.setForeground(Color.WHITE);
            }

            public void mouseExited(MouseEvent e) {
                registerButton.setForeground(Color.BLACK);
            }
        });
        setButton(registerButton, 25);
        registerButton.setPreferredSize(new Dimension(402, 70));

        ImageIcon imageIconReturnBlack = new ImageIcon("images/fleche return black.png");
        ImageIcon imageIconReturnBlackResize = new ImageIcon(imageIconReturnBlack.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH));
        Image imageReturnBlack = imageIconReturnBlackResize.getImage();

        ImageIcon imageIconReturnWhite = new ImageIcon("images/fleche return white.png");
        ImageIcon imageIconReturnWhiteResize = new ImageIcon(imageIconReturnWhite.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH));

        returnLabel = new JLabel(new ImageIcon(imageReturnBlack));

        panelRegister.add(labelLogoRegister);
        panelRegister.add(labelFondName);
        panelRegister.add(name);
        panelRegister.add(nameField);
        panelRegister.add(labelFondSurname);
        panelRegister.add(surname);
        panelRegister.add(surnameField);
        panelRegister.add(labelFondMail);
        panelRegister.add(email);
        panelRegister.add(emailField);
        panelRegister.add(labelFondHandle);
        panelRegister.add(handleRegister);
        panelRegister.add(handleFieldRegister);
        panelRegister.add(labelFondPassword);
        panelRegister.add(passwordRegister);
        panelRegister.add(passwordFieldRegister);
        panelRegister.add(labelRegisterBtn);
        panelRegister.add(registerButton);
        panelRegister.add(returnLabel);

        // Définir les contraintes pour chaque composant
        SpringLayout layoutRegister = (SpringLayout) panelRegister.getLayout();

        layoutRegister.putConstraint(SpringLayout.NORTH, labelLogoRegister, 10, SpringLayout.NORTH, panelRegister);
        layoutRegister.putConstraint(SpringLayout.HORIZONTAL_CENTER, labelLogoRegister, 0, SpringLayout.HORIZONTAL_CENTER, panelRegister);

        layoutRegister.putConstraint(SpringLayout.NORTH, labelFondName, -10, SpringLayout.SOUTH, labelLogoRegister);
        layoutRegister.putConstraint(SpringLayout.HORIZONTAL_CENTER, labelFondName, 0, SpringLayout.HORIZONTAL_CENTER, panelRegister);

        layoutRegister.putConstraint(SpringLayout.WEST, name, 35, SpringLayout.WEST, labelFondName);
        layoutRegister.putConstraint(SpringLayout.VERTICAL_CENTER, name, 0, SpringLayout.VERTICAL_CENTER, labelFondName);
        panelRegister.setComponentZOrder(name, 0);

        layoutRegister.putConstraint(SpringLayout.WEST, nameField, 35, SpringLayout.WEST, labelFondName);
        layoutRegister.putConstraint(SpringLayout.VERTICAL_CENTER, nameField, 0, SpringLayout.VERTICAL_CENTER, labelFondName);
        panelRegister.setComponentZOrder(nameField, 0); //Permet de placer au dessus de tous les autres composants

        layoutRegister.putConstraint(SpringLayout.NORTH, labelFondSurname, -20, SpringLayout.SOUTH, labelFondName);
        layoutRegister.putConstraint(SpringLayout.HORIZONTAL_CENTER, labelFondSurname, 0, SpringLayout.HORIZONTAL_CENTER, panelRegister);

        layoutRegister.putConstraint(SpringLayout.WEST, surname, 35, SpringLayout.WEST, labelFondSurname);
        layoutRegister.putConstraint(SpringLayout.VERTICAL_CENTER, surname, 0, SpringLayout.VERTICAL_CENTER, labelFondSurname);
        panelRegister.setComponentZOrder(surname, 0);

        layoutRegister.putConstraint(SpringLayout.WEST, surnameField, 35, SpringLayout.WEST, labelFondSurname);
        layoutRegister.putConstraint(SpringLayout.VERTICAL_CENTER, surnameField, 0, SpringLayout.VERTICAL_CENTER, labelFondSurname);
        panelRegister.setComponentZOrder(surnameField, 0);

        layoutRegister.putConstraint(SpringLayout.NORTH, labelFondMail, -20, SpringLayout.SOUTH, labelFondSurname);
        layoutRegister.putConstraint(SpringLayout.HORIZONTAL_CENTER, labelFondMail, 0, SpringLayout.HORIZONTAL_CENTER, panelRegister);

        layoutRegister.putConstraint(SpringLayout.WEST, email, 35, SpringLayout.WEST, labelFondMail);
        layoutRegister.putConstraint(SpringLayout.VERTICAL_CENTER, email, 0, SpringLayout.VERTICAL_CENTER, labelFondMail);
        panelRegister.setComponentZOrder(email, 0);

        layoutRegister.putConstraint(SpringLayout.WEST, emailField, 35, SpringLayout.WEST, labelFondMail);
        layoutRegister.putConstraint(SpringLayout.VERTICAL_CENTER, emailField, 0, SpringLayout.VERTICAL_CENTER, labelFondMail);
        panelRegister.setComponentZOrder(emailField, 0);

        layoutRegister.putConstraint(SpringLayout.NORTH, labelFondHandle, -20, SpringLayout.SOUTH, labelFondMail);
        layoutRegister.putConstraint(SpringLayout.HORIZONTAL_CENTER, labelFondHandle, 0, SpringLayout.HORIZONTAL_CENTER, panelRegister);

        layoutRegister.putConstraint(SpringLayout.WEST, handleRegister, 35, SpringLayout.WEST, labelFondHandle);
        layoutRegister.putConstraint(SpringLayout.VERTICAL_CENTER, handleRegister, 0, SpringLayout.VERTICAL_CENTER, labelFondHandle);
        panelRegister.setComponentZOrder(handleRegister, 0);

        layoutRegister.putConstraint(SpringLayout.WEST, handleFieldRegister, 35, SpringLayout.WEST, labelFondHandle);
        layoutRegister.putConstraint(SpringLayout.VERTICAL_CENTER, handleFieldRegister, 0, SpringLayout.VERTICAL_CENTER, labelFondHandle);
        panelRegister.setComponentZOrder(handleFieldRegister, 0);

        layoutRegister.putConstraint(SpringLayout.NORTH, labelFondPassword, -20, SpringLayout.SOUTH, labelFondHandle);
        layoutRegister.putConstraint(SpringLayout.HORIZONTAL_CENTER, labelFondPassword, 0, SpringLayout.HORIZONTAL_CENTER, panelRegister);

        layoutRegister.putConstraint(SpringLayout.WEST, passwordRegister, 35, SpringLayout.WEST, labelFondPassword);
        layoutRegister.putConstraint(SpringLayout.VERTICAL_CENTER, passwordRegister, 0, SpringLayout.VERTICAL_CENTER, labelFondPassword);
        panelRegister.setComponentZOrder(passwordRegister, 0);

        layoutRegister.putConstraint(SpringLayout.WEST, passwordFieldRegister, 35, SpringLayout.WEST, labelFondPassword);
        layoutRegister.putConstraint(SpringLayout.VERTICAL_CENTER, passwordFieldRegister, 0, SpringLayout.VERTICAL_CENTER, labelFondPassword);
        panelRegister.setComponentZOrder(passwordFieldRegister, 0);

        layoutRegister.putConstraint(SpringLayout.NORTH, labelRegisterBtn, -30, SpringLayout.SOUTH, labelFondPassword);
        layoutRegister.putConstraint(SpringLayout.HORIZONTAL_CENTER, labelRegisterBtn, 0, SpringLayout.HORIZONTAL_CENTER, panelRegister);

        layoutRegister.putConstraint(SpringLayout.WEST, registerButton, 31, SpringLayout.WEST, labelRegisterBtn);
        layoutRegister.putConstraint(SpringLayout.VERTICAL_CENTER, registerButton, 0, SpringLayout.VERTICAL_CENTER, labelRegisterBtn);
        panelRegister.setComponentZOrder(registerButton, 0);

        layoutRegister.putConstraint(SpringLayout.WEST, registerButton, 31, SpringLayout.WEST, labelRegisterBtn);
        layoutRegister.putConstraint(SpringLayout.VERTICAL_CENTER, registerButton, 0, SpringLayout.VERTICAL_CENTER, labelRegisterBtn);
        panelRegister.setComponentZOrder(registerButton, 0);

        layoutRegister.putConstraint(SpringLayout.WEST, returnLabel, 24, SpringLayout.WEST, panelRegister);
        layoutRegister.putConstraint(SpringLayout.NORTH, returnLabel, 15, SpringLayout.NORTH, panelRegister);
//        panelRegister.setComponentZOrder(returnButton, 0);

        currentPanel = panelLog;
        add(currentPanel);

        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changeView(panelRegister);
            }
        });

//        returnButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                changeView(panelLog);
//            }
//        });

        returnLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                returnLabel.setIcon(imageIconReturnWhiteResize);
                returnLabel.repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                returnLabel.setIcon(imageIconReturnBlackResize);
                returnLabel.repaint();
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                changeView(panelLog);
            }
        });

        this.setVisible(true);

    }


    public void changeView(JPanel panel) {
        currentPanel.setVisible(false);
        currentPanel = panel;
        add(currentPanel);
        currentPanel.setVisible(true);
        revalidate();
        repaint();

    }

    public void setFieldWithText(JTextField textField, JLabel text){
        textField.setBackground(new Color(0, 0, 0, 0));
        textField.setBorder(null);
        textField.setFont(new Font("Poppins", Font.PLAIN, 25));

        text.setFont(new Font("Poppins", Font.PLAIN, 25));
        text.setForeground(new Color(202, 214, 216));
    }

    public void setButton(JButton button, int policeSize){
        button.setBackground(new Color(0, 0, 0, 0));
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setFont(new Font("Poppins", Font.BOLD, policeSize));
    }


    public JTextField getHandleField() {
        return handleField;
    }

    public JButton getLoginButton() {
        return loginButton;
    }

    public JPasswordField getPasswordField() {
        return passwordField;
    }

    public JTextField getNameField() {
        return nameField;
    }

    public JTextField getSurnameField() {
        return surnameField;
    }

    public JTextField getEmailField() {
        return emailField;
    }

    public JTextField getHandleFieldRegister() {
        return handleFieldRegister;
    }

    public JPasswordField getPasswordFieldRegister() {
        return passwordFieldRegister;
    }

    public JButton getSignUpButton() {
        return signUpButton;
    }

    public JButton getReturnButton() { return returnButton; }

    public JLabel getReturnLabel() {
        return returnLabel;
    }

    public JLabel getHandle() { return handle; }

    public JLabel getPassword() { return password; }

    public JLabel getNameRegister() { return name; }

    public JLabel getSurnameRegister() { return surname; }

    public JLabel getEmail() { return email; }

    public JLabel getHandleRegister(){ return handleRegister; }

    public JLabel getPasswordRegister(){ return passwordRegister; }

    public JButton getRegisterButton() { return registerButton; }

    public boolean getStateSignUpBtn() {
        return stateSignUp;
    }


}

