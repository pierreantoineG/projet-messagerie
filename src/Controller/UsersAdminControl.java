package Controller;

import Model.Client;
import View.UsersAdminView;
import View.UsersView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

public class UsersAdminControl {

    UsersAdminView usersAdminView;
    private Client clientUser;
    static File font = new File("Font/Urbanist (font)/static/Urbanist-Medium.ttf");

    static Font urbanist;

    static {
        try {
            urbanist = Font.createFont(Font.TRUETYPE_FONT, font);
        } catch (FontFormatException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    public UsersAdminControl(UsersAdminView usersAdminView, Client client) {
        this.usersAdminView = usersAdminView;
        clientUser = client;
    }

    public void initializeUsersView(String cName) {

        JButton button_chat = usersAdminView.getButton_chat();
        button_chat.addMouseListener(new MouseAdapter() {

            public void mousePressed(MouseEvent e) {
                //Afficher page chat
            }

            public void mouseEntered(MouseEvent e) {
                button_chat.setForeground(Color.WHITE);
            }

            public void mouseExited(MouseEvent e) {
                button_chat.setForeground(Color.BLACK);// Rétablir la couleur de fond par défaut du bouton
            }
        });

        JButton button_users = usersAdminView.getButton_users();
        button_users.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Rien ne se passe
            }
        });
        button_users.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                button_users.setForeground(Color.WHITE);
            }

            public void mouseExited(MouseEvent e) {
                button_users.setForeground(Color.BLACK);
            }
        });

        JButton button_settings = usersAdminView.getButton_settings();
        button_settings.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Settings settings = new Settings(clientUser);
                    settings.initializeSettings();
                } catch (SQLException | IOException | FontFormatException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        button_settings.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                button_settings.setForeground(Color.WHITE);
            }

            public void mouseExited(MouseEvent e) {
                button_settings.setForeground(Color.BLACK);// Rétablir la couleur de fond par défaut du bouton
            }
        });

        JButton button_logout = usersAdminView.getButton_logout();
        button_logout.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                //Remettre la page du log in
            }

            public void mouseEntered(MouseEvent e) {
                button_logout.setForeground(Color.WHITE);
            }

            public void mouseExited(MouseEvent e) {
                button_logout.setForeground(Color.BLACK);// Rétablir la couleur de fond par défaut du bouton
            }
        });


        JButton button_reporting = usersAdminView.getButton_reporting();
        button_reporting.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                //Afficher report
            }

            public void mouseEntered(MouseEvent e) {
                button_reporting.setForeground(Color.WHITE);
            }

            public void mouseExited(MouseEvent e) {
                button_reporting.setForeground(Color.BLACK);
            }
        });

        usersAdminView.setVisible(true);
    }
}



