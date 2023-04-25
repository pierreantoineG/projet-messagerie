package Controller;

import Model.Client;
import View.ChatView;
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

public class UsersControl {

    UsersView usersView;
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

    public UsersControl(UsersView usersView, Client client) {
        this.usersView = usersView;
        clientUser = client;
    }

    public void initializeUsersView(String cName) {

        JButton button_chat = usersView.getButton_chat();
        button_chat.addMouseListener(new MouseAdapter() {
            @Override
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

        JButton button_users = usersView.getButton_users();
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

        JButton button_settings = usersView.getButton_settings();
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

        JButton button_logout = usersView.getButton_logout();
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


        JButton button_reporting = usersView.getButton_reporting();
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

        usersView.setVisible(true);
    }
}



