package View;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import Dao.*;
import Model.*;
import Controller.*;

public class ChatView extends JFrame {
    static JScrollPane scrollPane_chat;
    private JTextArea chatArea;
    private JTextArea message_field;
    private JPanel panel_chat;
    private JButton button_chat = new JButton("Chat");
    private JButton button_send = new JButton("");
    private JButton button_users = new JButton("Users");
    private JButton button_settings = new JButton("Controller.Settings");
    private JButton button_logout = new JButton("Logout");
    private JButton button_reporting = new JButton("Reporting");
    private JLabel lbl_first_name;
    private JLabel lbl_last_name;
    private JLabel lbl_handle;
    private JLabel lbl_active;
    private JLabel lbl_msg;
    private JLabel lbl_statut;
    static File font = new File("Font/Urbanist (font)/static/Urbanist-Medium.ttf");
    static Font urbanist;

    static {
        try {
            urbanist = Font.createFont(Font.TRUETYPE_FONT, font);
        } catch (FontFormatException | IOException e) {
            throw new RuntimeException(e);
        }
    }


    public JTextArea getMessage_field() {
        return message_field;
    }

    public JScrollPane getScrollPane_chat(){
        return scrollPane_chat;
    }

    public Font getUrbanist(){
        return urbanist;
    }

    public JButton getButton_chat() {
        return button_chat;
    }

    public JButton getButton_send() {
        return button_send;
    }

    public JButton getButton_users() {
        return button_users;
    }

    public JButton getButton_settings() {
        return button_settings;
    }

    public JButton getButton_logout() {
        return button_logout;
    }
    public JButton getButton_reporting(){
        return button_reporting;
    }

    public JTextArea getChatArea(){
        return chatArea;
    }

    public JPanel getPanel_chat(){
        return panel_chat;
    }

    public ChatView() throws SQLException{
        setSize(1000, 680);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        String[] connectedUsers;

        try {
            connectedUsers = UserDaoImpl.getConnectedUsers();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        JPanel panel_grid = new JPanel(new GridLayout(connectedUsers.length, 1));
        panel_grid.setOpaque(false);

        panel_chat = new JPanel() {
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                //Model.Chat
                g.setColor(Color.WHITE);
                g.fillRoundRect(150, 5, 520, 625, 30, 30);
                g.setColor(new Color(202, 214, 216));
                g.fillRect(150, 80, 520, 4);

                //Profile
                g.setColor(Color.WHITE);
                g.fillRoundRect(675, 5, 305, 240, 30, 30);

                g.setColor(new Color(89, 248, 227));
                g.fillOval(790, 15, 60, 60);
                g.setColor(new Color(89, 248, 227));
                g.fillOval(810, 15, 60, 60);

                g.setColor(new Color(215, 246, 255));
                g.drawRoundRect(680, 95, 145, 25, 30, 30);
                g.setColor(new Color(215, 246, 255));
                g.drawRoundRect(680, 135, 145, 25, 30, 30);
                g.setColor(new Color(215, 246, 255));
                g.drawRoundRect(680, 175, 145, 25, 30, 30);

                g.setColor(new Color(215, 246, 255));
                g.drawRoundRect(830, 95, 145, 25, 30, 30);
                g.setColor(new Color(215, 246, 255));
                g.drawRoundRect(830, 135, 145, 25, 30, 30);
                g.setColor(new Color(215, 246, 255));
                g.drawRoundRect(830, 175, 145, 25, 30, 30);

                g.setColor(new Color(133, 229, 255));
                g.fillRoundRect(680, 95, 80, 25, 30, 30);
                g.setColor(new Color(133, 229, 255));
                g.fillRoundRect(680, 135, 80, 25, 30, 30);
                g.setColor(new Color(133, 229, 255));
                g.fillRoundRect(680, 175, 80, 25, 30, 30);

                g.setColor(new Color(133, 229, 255));
                g.fillRoundRect(830, 95, 80, 25, 30, 30);
                g.setColor(new Color(133, 229, 255));
                g.fillRoundRect(830, 135, 80, 25, 30, 30);
                g.setColor(new Color(133, 229, 255));
                g.fillRoundRect(830, 175, 80, 25, 30, 30);

                //Membres connectés
                g.setColor(new Color(174, 240, 255));
                g.fillRoundRect(675, 250, 305, 380, 30, 30);
                g.setColor(Color.WHITE);
                g.fillRect(675, 320,305, 4);

                int x = 685;
                int y = 330;

                //TODO FAIRE UNE FONCTION DE TOUT CA
                for (int i = 0; i < connectedUsers.length; i++) {

                    g.setColor(Color.WHITE);
                    g.fillRoundRect(x, y, 270, 45, 30, 30);
                    y += 55;
                    JTextField fname_lname = new JTextField();
                    fname_lname.setFont(urbanist.deriveFont(Font.PLAIN, 16));
                    fname_lname.setBorder(null);
                    fname_lname.setText(connectedUsers[i]);
                    panel_grid.add(fname_lname);
                    fname_lname.setBorder(new EmptyBorder(0, 0, 35, 0));
                    fname_lname.setOpaque(false);
                    fname_lname.setBackground(new Color(0, 0, 0, 0));
                    fname_lname.setEditable(false);
                }

                //Dessiner le carrée pour le JTextField
                g.setColor(new Color(217, 217, 217));
                g.drawRoundRect(167, 540, 485, 75, 30, 30);

                //Dessiner le carrée pour fenetre actuelle
                g.setColor(new Color(0, 245, 212));
                g.fillRoundRect(5, 152, 140, 40, 20, 20);

            }
        };
        panel_chat.setBackground(new Color(0, 187, 249));
        panel_chat.setLayout(new SpringLayout());

        //Image logo
        ImageIcon logo = new ImageIcon("Icons/LogoChatOeuf.png");
        JLabel lbl_logo = new JLabel(new ImageIcon(logo.getImage()));

        //Image photo profil
        ImageIcon img_profile = new ImageIcon("Icons/3135715.png");
        JLabel lbl_profile = new JLabel(new ImageIcon(img_profile.getImage()));

        //Image icon chat
        ImageIcon img_icon_chat = new ImageIcon("Icons/icon_chat_conv.png");
        JLabel lbl_icon_chat = new JLabel(new ImageIcon(img_icon_chat.getImage()));

        //Image icon users
        ImageIcon img_icon_users = new ImageIcon("Icons/icon_users.png");
        JLabel lbl_icon_users = new JLabel(new ImageIcon(img_icon_users.getImage()));

        //Image icon settings
        ImageIcon img_icon_settings = new ImageIcon("Icons/icon_settings.png");
        JLabel lbl_icon_settings = new JLabel(new ImageIcon(img_icon_settings.getImage()));

        //Image icon log out
        ImageIcon img_icon_logout = new ImageIcon("Icons/icon_logout.png");
        JLabel lbl_icon_logout = new JLabel(new ImageIcon(img_icon_logout.getImage()));

        //Image icon send
        ImageIcon img_icon_send = new ImageIcon("Icons/icon_send.jpg");
        JLabel lbl_icon_send = new JLabel(new ImageIcon(img_icon_send.getImage()));

        //Image icon reporting
        ImageIcon img_icon_reporting = new ImageIcon("Icons/icon_reporting.png");
        JLabel lbl_icon_reporting = new JLabel(new ImageIcon(img_icon_reporting.getImage()));

        //Texte 1
        JLabel lbl_conv = new JLabel("Server 1");
        lbl_conv.setFont(urbanist.deriveFont(Font.BOLD, 30));
        lbl_conv.setHorizontalAlignment(SwingConstants.CENTER);

        //Texte 2
        JLabel lbl_users_conn = new JLabel("Members connected ");
        lbl_users_conn.setFont(urbanist.deriveFont(Font.BOLD, 25));
        lbl_users_conn.setHorizontalAlignment(SwingConstants.CENTER);

        //Texte 3
        lbl_first_name = new JLabel("First name ");
        lbl_first_name.setFont(urbanist.deriveFont(Font.PLAIN, 13));
        lbl_first_name.setHorizontalAlignment(SwingConstants.CENTER);

        //Texte 4
        lbl_last_name = new JLabel("Last name ");
        lbl_last_name.setFont(urbanist.deriveFont(Font.PLAIN, 13));
        lbl_last_name.setHorizontalAlignment(SwingConstants.CENTER);

        //Texte 5
        lbl_handle = new JLabel("Handle ");
        lbl_handle.setFont(urbanist.deriveFont(Font.PLAIN, 13));
        lbl_handle.setHorizontalAlignment(SwingConstants.CENTER);

        //Texte 6
        lbl_active = new JLabel("Active since  ");
        lbl_active.setFont(urbanist.deriveFont(Font.PLAIN, 13));
        lbl_active.setHorizontalAlignment(SwingConstants.CENTER);

        //Texte 7
        lbl_msg = new JLabel("Messages ");
        lbl_msg.setFont(urbanist.deriveFont(Font.PLAIN, 13));
        lbl_msg.setHorizontalAlignment(SwingConstants.CENTER);

        //Texte 8
        lbl_statut = new JLabel("Status ");
        lbl_statut.setFont(urbanist.deriveFont(Font.PLAIN, 13));
        lbl_statut.setHorizontalAlignment(SwingConstants.CENTER);

        //JTextFiel pour entrer le message
        message_field = new JTextArea("Send a message");
        message_field.setFont(urbanist.deriveFont(Font.PLAIN, 15));
        message_field.setPreferredSize(new Dimension(400, 65));

        message_field.setLineWrap(true);
        message_field.setWrapStyleWord(true);


        //Scroll Pane
        scrollPane_chat = new JScrollPane();
        scrollPane_chat.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane_chat.setPreferredSize(new Dimension(510, 450));
        scrollPane_chat.setBackground(Color.WHITE);
        scrollPane_chat.setBorder(null);

        chatArea = new JTextArea();
        chatArea.setEditable(false);
        chatArea.setBackground(Color.WHITE);
        chatArea.setFont(urbanist.deriveFont(Font.PLAIN, 15));
        chatArea.setPreferredSize(new Dimension(520, 450));
        chatArea.setLineWrap(true);
        chatArea.setWrapStyleWord(true);

        /*JScrollPane scrollPane_message = new JScrollPane(message_field);
        scrollPane_chat.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane_message.setPreferredSize(new Dimension(400, 65));
        scrollPane_message.setBorder(null);
        scrollPane_message.setBackground(Color.WHITE);*/

        //Boutons
        //Bouton envoyer

        button_send.setOpaque(false);
        button_send.setContentAreaFilled(false);
        button_send.setBackground(new Color(0, 0, 0, 0));
        button_send.setPreferredSize(new Dimension(25, 25));

        //Bouton chat
        button_chat.setFont(urbanist.deriveFont(Font.BOLD, 19));
        button_chat.setBackground(new Color(0, 0, 0, 0));
        button_chat.setBorder(null);
        button_chat.setContentAreaFilled(false);
        button_chat.setOpaque(false);

        //Bouton users
        button_users.setFont(urbanist.deriveFont(Font.BOLD, 19));
        button_users.setOpaque(false);
        button_users.setContentAreaFilled(false);
        button_users.setBackground(new Color(0, 0, 0, 0));
        button_users.setBorder(null);

        //Bouton settings
        button_settings.setFont(urbanist.deriveFont(Font.BOLD, 19));
        button_settings.setBackground(new Color(0, 0, 0, 0));
        button_settings.setBorder(null);
        button_settings.setOpaque(false);

        //Bouton log out
        button_logout.setFont(urbanist.deriveFont(Font.BOLD, 19));
        button_logout.setBackground(new Color(0, 0, 0, 0));
        button_logout.setBorder(null);
        button_logout.setOpaque(false);

        //Bouton Reporting
        button_reporting.setFont(urbanist.deriveFont(Font.BOLD, 19));
        button_reporting.setBackground(new Color(0, 0, 0, 0));
        button_reporting.setBorder(null);
        button_reporting.setOpaque(false);

        //Ajout de tous les composants au JPanel
        panel_chat.add(lbl_logo);
        panel_chat.add(lbl_conv);
        panel_chat.add(lbl_profile);
        panel_chat.add(lbl_users_conn);
        panel_chat.add(lbl_first_name);
        panel_chat.add(lbl_last_name);
        panel_chat.add(lbl_handle);
        panel_chat.add(lbl_active);
        panel_chat.add(lbl_msg);
        panel_chat.add(lbl_statut);
        panel_chat.add(scrollPane_chat);
        //panel_chat.add(scrollPane_message);
        panel_chat.add(message_field);
        //panel_chat.add(handle);
        panel_chat.add(button_chat);
        panel_chat.add(button_users);
        panel_chat.add(button_settings);
        panel_chat.add(button_logout);
        panel_chat.add(button_send);
        panel_chat.add(button_reporting);
        panel_chat.add(lbl_icon_chat);
        panel_chat.add(lbl_icon_users);
        panel_chat.add(lbl_icon_settings);
        panel_chat.add(lbl_icon_logout);
        panel_chat.add(lbl_icon_send);
        panel_chat.add(lbl_icon_reporting);
        panel_chat.add(panel_grid);
        panel_chat.setComponentZOrder(panel_grid, 0);

        //TODO Définir les contraintes pour chaque composant pour le contentPanel uniquement
        SpringLayout contentLayout = (SpringLayout) panel_chat.getLayout();

        //Logo
        contentLayout.putConstraint(SpringLayout.NORTH, lbl_logo, 40, SpringLayout.NORTH, panel_chat);
        contentLayout.putConstraint(SpringLayout.WEST, lbl_logo, 40, SpringLayout.WEST, panel_chat);

        //Texte 1
        contentLayout.putConstraint(SpringLayout.NORTH, lbl_conv, 30, SpringLayout.NORTH, panel_chat);
        contentLayout.putConstraint(SpringLayout.WEST, lbl_conv, 170, SpringLayout.WEST, panel_chat);

        //Image profil
        contentLayout.putConstraint(SpringLayout.NORTH, lbl_profile, 15, SpringLayout.NORTH, panel_chat);
        contentLayout.putConstraint(SpringLayout.WEST, lbl_profile, 800, SpringLayout.WEST, panel_chat);

        //Texte 2
        contentLayout.putConstraint(SpringLayout.NORTH, lbl_users_conn, 270, SpringLayout.NORTH, panel_chat);
        contentLayout.putConstraint(SpringLayout.WEST, lbl_users_conn, 705, SpringLayout.WEST, panel_chat);

        //ScrollPane
        contentLayout.putConstraint(SpringLayout.NORTH, scrollPane_chat, 85, SpringLayout.NORTH, panel_chat);
        contentLayout.putConstraint(SpringLayout.WEST, scrollPane_chat, 155, SpringLayout.WEST, panel_chat);

        //Model.Message
        contentLayout.putConstraint(SpringLayout.NORTH, message_field, 542, SpringLayout.NORTH, panel_chat);
        contentLayout.putConstraint(SpringLayout.WEST, message_field, 180, SpringLayout.WEST, panel_chat);

        //Icon send
        contentLayout.putConstraint(SpringLayout.NORTH, lbl_icon_send, 590, SpringLayout.NORTH, panel_chat);
        contentLayout.putConstraint(SpringLayout.WEST, lbl_icon_send, 620, SpringLayout.WEST, panel_chat);

        //Bouton send
        contentLayout.putConstraint(SpringLayout.NORTH, button_send, 586, SpringLayout.NORTH, panel_chat);
        contentLayout.putConstraint(SpringLayout.WEST, button_send, 620, SpringLayout.WEST, panel_chat);

        //Bouton chat
        contentLayout.putConstraint(SpringLayout.NORTH, button_chat, 120, SpringLayout.NORTH, lbl_logo);
        contentLayout.putConstraint(SpringLayout.WEST, button_chat, 60, SpringLayout.WEST, panel_chat);

        //Bouton users
        contentLayout.putConstraint(SpringLayout.NORTH, button_users, 60, SpringLayout.NORTH, button_chat);
        contentLayout.putConstraint(SpringLayout.WEST, button_users, 60, SpringLayout.WEST, panel_chat);

        //Bouton settings
        contentLayout.putConstraint(SpringLayout.NORTH, button_settings, 60, SpringLayout.NORTH, button_users);
        contentLayout.putConstraint(SpringLayout.WEST, button_settings, 60, SpringLayout.WEST, panel_chat);

        //Bouton logout
        contentLayout.putConstraint(SpringLayout.NORTH, button_logout, 60, SpringLayout.NORTH, button_settings);
        contentLayout.putConstraint(SpringLayout.WEST, button_logout, 60, SpringLayout.WEST, panel_chat);

        //Bouton reporting
        contentLayout.putConstraint(SpringLayout.NORTH, button_reporting, 60, SpringLayout.NORTH, button_logout);
        contentLayout.putConstraint(SpringLayout.WEST, button_reporting, 60, SpringLayout.WEST, panel_chat);

        //Icon chat
        contentLayout.putConstraint(SpringLayout.NORTH, lbl_icon_chat, 120, SpringLayout.NORTH, lbl_logo);
        contentLayout.putConstraint(SpringLayout.WEST, lbl_icon_chat, 15, SpringLayout.WEST, panel_chat);

        //Icon users
        contentLayout.putConstraint(SpringLayout.NORTH, lbl_icon_users, 55, SpringLayout.NORTH, lbl_icon_chat);
        contentLayout.putConstraint(SpringLayout.WEST, lbl_icon_users, 15, SpringLayout.WEST, panel_chat);

        //Icon settings
        contentLayout.putConstraint(SpringLayout.NORTH, lbl_icon_settings, 60, SpringLayout.NORTH, lbl_icon_users);
        contentLayout.putConstraint(SpringLayout.WEST, lbl_icon_settings, 15, SpringLayout.WEST, panel_chat);

        //Icon logout
        contentLayout.putConstraint(SpringLayout.NORTH, lbl_icon_logout, 60, SpringLayout.NORTH, lbl_icon_settings);
        contentLayout.putConstraint(SpringLayout.WEST, lbl_icon_logout, 15, SpringLayout.WEST, panel_chat);

        //Icon reporting
        contentLayout.putConstraint(SpringLayout.NORTH, lbl_icon_reporting, 60, SpringLayout.NORTH, lbl_icon_logout);
        contentLayout.putConstraint(SpringLayout.WEST, lbl_icon_reporting, 15, SpringLayout.WEST, panel_chat);

        //Texte 3
        contentLayout.putConstraint(SpringLayout.NORTH, lbl_first_name, 98, SpringLayout.NORTH, panel_chat);
        contentLayout.putConstraint(SpringLayout.WEST, lbl_first_name, 688, SpringLayout.WEST, panel_chat);

        //Texte 4
        contentLayout.putConstraint(SpringLayout.NORTH, lbl_last_name, 138, SpringLayout.NORTH, panel_chat);
        contentLayout.putConstraint(SpringLayout.WEST, lbl_last_name, 688, SpringLayout.WEST, panel_chat);

        //Texte 5
        contentLayout.putConstraint(SpringLayout.NORTH, lbl_handle, 178, SpringLayout.NORTH, panel_chat);
        contentLayout.putConstraint(SpringLayout.WEST, lbl_handle, 697, SpringLayout.WEST, panel_chat);


        //Texte 6
        contentLayout.putConstraint(SpringLayout.NORTH, lbl_active, 98, SpringLayout.NORTH, panel_chat);
        contentLayout.putConstraint(SpringLayout.WEST, lbl_active, 835, SpringLayout.WEST, panel_chat);

        //Texte 7
        contentLayout.putConstraint(SpringLayout.NORTH, lbl_msg, 138, SpringLayout.NORTH, panel_chat);
        contentLayout.putConstraint(SpringLayout.WEST, lbl_msg, 840, SpringLayout.WEST, panel_chat);

        //Texte 8
        contentLayout.putConstraint(SpringLayout.NORTH, lbl_statut, 178, SpringLayout.NORTH, panel_chat);
        contentLayout.putConstraint(SpringLayout.WEST, lbl_statut, 850, SpringLayout.WEST, panel_chat);

        contentLayout.putConstraint(SpringLayout.NORTH, panel_grid, 340, SpringLayout.NORTH, panel_chat);
        contentLayout.putConstraint(SpringLayout.WEST, panel_grid, 720, SpringLayout.WEST, panel_chat);

        panel_chat.revalidate();

        add(panel_chat);

    }

    public void displayUserInfo(String name, String lastname, String username, String date, String message, String status){
        JLabel labelName = new JLabel(name);
        JLabel labelSurname = new JLabel(lastname);
        JLabel labelUser = new JLabel(username);
        JLabel labelDate = new JLabel(date);
        JLabel labelMessage = new JLabel(message);
        JLabel labelStatus = new JLabel(status);

        labelName.setFont(urbanist.deriveFont(Font.PLAIN, 13));
        labelName.setBorder(null);
        labelName.setOpaque(false);
        labelName.setBackground(new Color(0, 0, 0, 0));

        labelSurname.setFont(urbanist.deriveFont(Font.PLAIN, 10));
        labelSurname.setBorder(null);
        labelSurname.setOpaque(false);
        labelSurname.setBackground(new Color(0, 0, 0, 0));

        labelUser.setFont(urbanist.deriveFont(Font.PLAIN, 13));
        labelUser.setBorder(null);
        labelUser.setOpaque(false);
        labelUser.setBackground(new Color(0, 0, 0, 0));

        labelDate.setFont(urbanist.deriveFont(Font.PLAIN, 9));
        labelDate.setBorder(null);
        labelDate.setOpaque(false);
        labelDate.setBackground(new Color(0, 0, 0, 0));

        labelMessage.setFont(urbanist.deriveFont(Font.PLAIN, 13));
        labelMessage.setBorder(null);
        labelMessage.setOpaque(false);
        labelMessage.setBackground(new Color(0, 0, 0, 0));

        labelStatus.setFont(urbanist.deriveFont(Font.PLAIN, 10));
        labelStatus.setBorder(null);
        labelStatus.setOpaque(false);
        labelStatus.setBackground(new Color(0, 0, 0, 0));

        panel_chat.add(labelName);
        panel_chat.add(labelSurname);
        panel_chat.add(labelUser);
        panel_chat.add(labelDate);
        panel_chat.add(labelMessage);
        panel_chat.add(labelStatus);

        SpringLayout layoutPanel = (SpringLayout) panel_chat.getLayout();

        //TODO FAIRE COMME LA LIGNE D EN DESSOUS POUR RECUPERER LES INFOS SOIT DE LA BDD SOIT DU REGISTER
        //fname
        layoutPanel.putConstraint(SpringLayout.NORTH, labelName, 98, SpringLayout.NORTH, panel_chat);
        layoutPanel.putConstraint(SpringLayout.WEST, labelName, 80, SpringLayout.WEST, lbl_first_name);
        //lname
        layoutPanel.putConstraint(SpringLayout.NORTH, labelSurname, 140, SpringLayout.NORTH, panel_chat);
        layoutPanel.putConstraint(SpringLayout.WEST, labelSurname, 75, SpringLayout.WEST, lbl_last_name);
        //Handle
        layoutPanel.putConstraint(SpringLayout.NORTH, labelUser, 178, SpringLayout.NORTH, panel_chat);
        layoutPanel.putConstraint(SpringLayout.WEST, labelUser, 70, SpringLayout.WEST, lbl_handle);
        //active since
        layoutPanel.putConstraint(SpringLayout.NORTH, labelDate, 100, SpringLayout.NORTH, panel_chat);
        layoutPanel.putConstraint(SpringLayout.WEST, labelDate, 78, SpringLayout.WEST, lbl_active);
        //messages
        layoutPanel.putConstraint(SpringLayout.NORTH, labelMessage, 138, SpringLayout.NORTH, panel_chat);
        layoutPanel.putConstraint(SpringLayout.WEST, labelMessage, 88, SpringLayout.WEST, lbl_msg);
        //statut
        layoutPanel.putConstraint(SpringLayout.NORTH, labelStatus, 180, SpringLayout.NORTH, panel_chat);
        layoutPanel.putConstraint(SpringLayout.WEST, labelStatus, 61, SpringLayout.WEST, lbl_statut);
    }
}
