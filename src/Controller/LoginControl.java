package Controller;

import View.*;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LoginControl {
    private LoginView loginView;

    public LoginControl(LoginView loginView){
        this.loginView = loginView;
    }

    public void initialize(){
        animationComponentField(loginView.getHandleField(), loginView.getHandle());
        animationComponentField(loginView.getPasswordField(), loginView.getPassword());
        animationComponentField(loginView.getNameField(), loginView.getNameRegister());
        animationComponentField(loginView.getSurnameField(), loginView.getSurnameRegister());
        animationComponentField(loginView.getEmailField(), loginView.getEmail());
        animationComponentField(loginView.getHandleFieldRegister(), loginView.getHandleRegister());
        animationComponentField(loginView.getPasswordFieldRegister(), loginView.getPasswordRegister());

        JButton signUpButton = loginView.getSignUpButton();
        signUpButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                signUpButton.setForeground(Color.WHITE);
            }
            @Override
            public void mouseExited(MouseEvent e) {
                signUpButton.setForeground(Color.BLACK);
            }
        });
//        signUpButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                loginView.changeView();
//            }
//        });
    }

    public void animationComponentField(JTextComponent myComponentField, JLabel myLabelComponent){
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


    public void changeView(){

    }





}