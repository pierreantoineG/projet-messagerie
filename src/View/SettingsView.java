package View;

import Controller.SettingsControl;
import Dao.*;
import Model.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

public class SettingsView extends JFrame{


    private boolean isAway = false;
    private boolean isOnline = false;
    private static JPanel panel_chat;
    private static JLabel lbl_first_name;
    private static JLabel lbl_last_name;
    private static JLabel lbl_handle;
    private static JLabel lbl_active;
    private static JLabel lbl_msg;
    private static JLabel lbl_statut;

    private JButton lbl_away = new JButton("Away");
    private JButton lbl_online = new JButton("Online ");

    private static JLabel lbl_icon_edit;
    private static ImageIcon img_icon_edit;
    private static ImageIcon img_icon_edit_white;
    private static ImageIcon img_icon_edit1;
    private static ImageIcon img_icon_edit_white_1;
    private JLabel lbl_icon_edit1;
    private static ImageIcon img_icon_edit2;
    private static ImageIcon img_icon_edit_white_2;
    private static JLabel lbl_icon_edit2;
    private static ImageIcon img_icon_cancel;
    private static ImageIcon img_icon_cancel_white;
    private static JLabel lbl_icon_cancel;
    private static ImageIcon img_icon_cancel1;
    private static ImageIcon img_icon_cancel_white_1;
    private static JLabel lbl_icon_cancel1;
    private static ImageIcon img_icon_cancel2;
    private static ImageIcon img_icon_cancel_white_2;
    private static JLabel lbl_icon_cancel2;
    private static ImageIcon img_icon_validate;
    private static ImageIcon img_icon_validate_white;
    private static JLabel lbl_icon_validate;
    private static ImageIcon img_icon_validate1;
    private static ImageIcon img_icon_validate_white_1;
    private static JLabel lbl_icon_validate1;
    private static ImageIcon img_icon_validate2;
    private static ImageIcon img_icon_validate_white_2;
    private static JLabel lbl_icon_validate2;

    private JTextField handle;
    private JTextField fname;
    private JTextField lname;
    private JLabel activeSince;
    private JLabel statut;
    private JLabel messages;
    private JButton button_chat = new JButton("Chat");
    private JButton button_users = new JButton("Users");
    private JButton button_settings = new JButton("Settings");
    private JButton button_logout = new JButton("Log out");
    private JButton button_reporting = new JButton("Reporting");

    static File font = new File("Font/Urbanist (font)/static/Urbanist-Medium.ttf");
    static Font urbanist;

    static {
        try {
            urbanist = Font.createFont(Font.TRUETYPE_FONT, font);
        } catch (FontFormatException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    public JTextField getHandle() {
        return handle;
    }

    public JTextField getFname() {
        return fname;
    }

    public JTextField getLname() {
        return lname;
    }

    public JLabel getActiveSince() {
        return activeSince;
    }

    public JLabel getStatut() {
        return statut;
    }

    public JLabel getMessages() {
        return messages;
    }

    public JLabel getLbl_icon_edit() {
        return lbl_icon_edit;
    }

    public ImageIcon getImg_icon_edit() {
        return img_icon_edit;
    }

    public ImageIcon getImg_icon_edit_white() {
        return img_icon_edit_white;
    }

    public ImageIcon getImg_icon_edit1() {
        return img_icon_edit1;
    }

    public JLabel getLbl_icon_edit1() {
        return lbl_icon_edit1;
    }

    public ImageIcon getImg_icon_edit2() {
        return img_icon_edit2;
    }

    public JLabel getLbl_icon_edit2() {
        return lbl_icon_edit2;
    }

    public ImageIcon getImg_icon_cancel() {
        return img_icon_cancel;
    }

    public ImageIcon getImg_icon_cancel_white() {
        return img_icon_cancel_white;
    }

    public JLabel getLbl_icon_cancel() {
        return lbl_icon_cancel;
    }

    public ImageIcon getImg_icon_cancel1() {
        return img_icon_cancel1;
    }

    public ImageIcon getImg_icon_cancel_white_1() {
        return img_icon_cancel_white_1;
    }

    public JLabel getLbl_icon_cancel1() {
        return lbl_icon_cancel1;
    }

    public ImageIcon getImg_icon_cancel2() {
        return img_icon_cancel2;
    }

    public ImageIcon getImg_icon_cancel_white_2() {
        return img_icon_cancel_white_2;
    }

    public JLabel getLbl_icon_cancel2() {
        return lbl_icon_cancel2;
    }

    public ImageIcon getImg_icon_validate() {
        return img_icon_validate;
    }

    public ImageIcon getImg_icon_validate_white() {
        return img_icon_validate_white;
    }

    public JLabel getLbl_icon_validate() {
        return lbl_icon_validate;
    }

    public ImageIcon getImg_icon_validate1() {
        return img_icon_validate1;
    }

    public ImageIcon getImg_icon_validate_white_1() {
        return img_icon_validate_white_1;
    }

    public JLabel getLbl_icon_validate1() {
        return lbl_icon_validate1;
    }

    public ImageIcon getImg_icon_validate2() {
        return img_icon_validate2;
    }

    public ImageIcon getImg_icon_validate_white_2() {
        return img_icon_validate_white_2;
    }

    public JLabel getLbl_icon_validate2() {
        return lbl_icon_validate2;
    }
    public ImageIcon getImg_icon_edit_white_1(){
        return img_icon_edit_white_1;
    }
    public ImageIcon getImg_icon_edit_white_2(){
        return img_icon_edit_white_2;
    }

    public JButton getButton_chat() {
        return button_chat;
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

    public JButton getLbl_away() {
        return lbl_away;
    }

    public JButton getLbl_online() {
        return lbl_online;
    }

    public boolean isAway() {
        return isAway;
    }

    public boolean isOnline() {
        return isOnline;
    }

    public void setAway(boolean away) {
        isAway = away;
    }

    public void setOnline(boolean online) {
        isOnline = online;
    }

    public SettingsView(String firstName, String lastName, String pseudo, String date, String nbMsg, String userStatut) throws IOException, FontFormatException, SQLException {


        setSize(1000, 680);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        //Font
        File font = new File("Font/Urbanist (font)/static/Urbanist-Medium.ttf");
        Font urbanist = Font.createFont(Font.TRUETYPE_FONT, font);

        //Image logo
        ImageIcon logo = new ImageIcon("Icons/LogoChatOeuf.png");
        JLabel lbl_logo = new JLabel(new ImageIcon(logo.getImage()));

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



        //Image icon editer
        img_icon_edit = new ImageIcon("Icons/icon_modifier.png");
        img_icon_edit_white = new ImageIcon("Icons/icon_modifier (1).png");
        lbl_icon_edit = new JLabel(new ImageIcon(img_icon_edit.getImage()));

        img_icon_edit1 = new ImageIcon("Icons/icon_modifier.png");
        img_icon_edit_white_1 = new ImageIcon("Icons/icon_modifier (1).png");
        lbl_icon_edit1 = new JLabel(new ImageIcon(img_icon_edit1.getImage()));


        img_icon_edit2 = new ImageIcon("Icons/icon_modifier.png");
        img_icon_edit_white_2 = new ImageIcon("Icons/icon_modifier (1).png");
        lbl_icon_edit2 = new JLabel(new ImageIcon(img_icon_edit2.getImage()));

        //Image icon annuler
        img_icon_cancel = new ImageIcon("Icons/imgCancelRed (1).png");
        img_icon_cancel_white = new ImageIcon("Icons/imgCancelWhite (2).png");
        lbl_icon_cancel = new JLabel(new ImageIcon(img_icon_cancel.getImage()));


        img_icon_cancel1 = new ImageIcon("Icons/imgCancelRed (1).png");
        img_icon_cancel_white_1 = new ImageIcon("Icons/imgCancelWhite (2).png");
        lbl_icon_cancel1 = new JLabel(new ImageIcon(img_icon_cancel1.getImage()));


        img_icon_cancel2 = new ImageIcon("Icons/imgCancelRed (1).png");
        img_icon_cancel_white_2 = new ImageIcon("Icons/imgCancelWhite (2).png");
        lbl_icon_cancel2 = new JLabel(new ImageIcon(img_icon_cancel2.getImage()));


        //Image icon validate
        img_icon_validate = new ImageIcon("Icons/imgConfirmGreen (1).png");
        img_icon_validate_white = new ImageIcon("Icons/imgConfirmWhite (1).png");
        lbl_icon_validate = new JLabel(new ImageIcon(img_icon_validate.getImage()));


        img_icon_validate1 = new ImageIcon("Icons/imgConfirmGreen (1).png");
        img_icon_validate_white_1 = new ImageIcon("Icons/imgConfirmWhite (1).png");
        lbl_icon_validate1 = new JLabel(new ImageIcon(img_icon_validate1.getImage()));


        img_icon_validate2 = new ImageIcon("Icons/imgConfirmGreen (1).png");
        img_icon_validate_white_2 = new ImageIcon("Icons/imgConfirmWhite (1).png");
        lbl_icon_validate2 = new JLabel(new ImageIcon(img_icon_validate2.getImage()));


        //Texte 1
        JLabel lbl_account = new JLabel("My Account");
        lbl_account.setFont(urbanist.deriveFont(Font.BOLD, 30));
        lbl_account.setHorizontalAlignment(SwingConstants.CENTER);

        //Texte 2
        JLabel lbl_fname = new JLabel("First name ");
        lbl_fname.setFont(urbanist.deriveFont(Font.BOLD, 20));
        lbl_fname.setHorizontalAlignment(SwingConstants.CENTER);

        this.fname = new JTextField(firstName);
        fname.setFont(urbanist.deriveFont(Font.BOLD, 20));
        fname.setBorder(null);
        fname.setOpaque(false);
        fname.setBackground(new Color(0, 0, 0, 0));

        this.lname= new JTextField(lastName);
        lname.setFont(urbanist.deriveFont(Font.BOLD, 20));
        lname.setBorder(null);
        lname.setOpaque(false);
        lname.setBackground(new Color(0, 0, 0, 0));

        this.handle = new JTextField(pseudo);
        handle.setFont(urbanist.deriveFont(Font.BOLD, 20));
        handle.setBorder(null);
        handle.setOpaque(false);
        handle.setBackground(new Color(0, 0, 0, 0));

        this.activeSince = new JLabel(date);
        activeSince.setFont(urbanist.deriveFont(Font.BOLD, 20));
        activeSince.setBorder(null);
        activeSince.setOpaque(false);
        activeSince.setBackground(new Color(0, 0, 0, 0));

        this.messages = new JLabel(nbMsg);
        messages.setFont(urbanist.deriveFont(Font.BOLD, 20));
        messages.setBorder(null);
        messages.setOpaque(false);
        messages.setBackground(new Color(0, 0, 0, 0));

        this.statut = new JLabel(userStatut);
        statut.setFont(urbanist.deriveFont(Font.BOLD, 20));
        statut.setBorder(null);
        statut.setOpaque(false);
        statut.setBackground(new Color(0, 0, 0, 0));



        //Texte 3
        JLabel lbl_lname = new JLabel("Last name ");
        lbl_lname.setFont(urbanist.deriveFont(Font.BOLD, 20));
        lbl_lname.setHorizontalAlignment(SwingConstants.CENTER);

        //Texte 4
        JLabel lbl_handle1 = new JLabel("Handle ");
        lbl_handle1.setFont(urbanist.deriveFont(Font.BOLD, 20));
        lbl_handle1.setHorizontalAlignment(SwingConstants.CENTER);

        //Texte 5
        JLabel lbl_active_since = new JLabel("Active since ");
        lbl_active_since.setFont(urbanist.deriveFont(Font.BOLD, 20));
        lbl_active_since.setHorizontalAlignment(SwingConstants.CENTER);

        //Texte 6
        JLabel lbl_messages = new JLabel("Messages ");
        lbl_messages.setFont(urbanist.deriveFont(Font.BOLD, 20));
        lbl_messages.setHorizontalAlignment(SwingConstants.CENTER);

        //Texte 7
        JLabel lbl_status_1 = new JLabel("Status ");
        lbl_status_1.setFont(urbanist.deriveFont(Font.BOLD, 20));
        lbl_status_1.setHorizontalAlignment(SwingConstants.CENTER);

        //Texte 8
        JLabel lbl_state = new JLabel("State ");
        lbl_state.setFont(urbanist.deriveFont(Font.BOLD, 20));
        lbl_state.setHorizontalAlignment(SwingConstants.CENTER);

        //Boutons
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

        //Bouton reporting
        button_reporting.setFont(urbanist.deriveFont(Font.BOLD, 19));
        button_reporting.setBackground(new Color(0, 0, 0, 0));
        button_reporting.setBorder(null);
        button_reporting.setOpaque(false);

        //Bouton online
        lbl_online.setFont(urbanist.deriveFont(Font.BOLD, 20));
        lbl_online.setHorizontalAlignment(SwingConstants.CENTER);
        lbl_online.setBackground(new Color(0, 0, 0, 0));
        lbl_online.setOpaque(false);
        lbl_online.setBorder(null);
        lbl_online.setFocusPainted(false);

        //Bouton away
        lbl_away.setFont(urbanist.deriveFont(Font.BOLD, 20));
        lbl_away.setHorizontalAlignment(SwingConstants.CENTER);
        lbl_away.setBackground(new Color(0, 0, 0, 0));
        lbl_away.setOpaque(false);
        lbl_away.setBorder(null);
        lbl_away.setFocusPainted(false);

        panel_chat = new JPanel() {
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                //Chat
                g.setColor(Color.WHITE);
                g.fillRoundRect(150, 5, 820, 625, 30, 30);
                g.setColor(new Color(202, 214, 216));
                g.fillRect(150, 80, 820, 3);

                g.setColor(new Color(215, 246, 255));
                g.drawRoundRect(160, 120, 500, 65, 70, 70);
                g.setColor(new Color(215, 246, 255));
                g.drawRoundRect(160, 200, 500, 65, 70, 70);
                g.setColor(new Color(215, 246, 255));
                g.drawRoundRect(160, 280, 500, 65, 70, 70);

                g.setColor(new Color(215, 246, 255));
                g.drawRoundRect(160, 360, 500, 65, 70, 70);
                g.setColor(new Color(215, 246, 255));
                g.drawRoundRect(160, 440, 500, 65, 70, 70);
                g.setColor(new Color(215, 246, 255));
                g.drawRoundRect(160, 520, 500, 65, 70, 70);

                g.setColor(new Color(133, 229, 255));
                g.fillRoundRect(160, 120, 250, 65, 70, 70);
                g.setColor(new Color(133, 229, 255));
                g.fillRoundRect(160, 200, 250, 65, 70, 70);
                g.setColor(new Color(133, 229, 255));
                g.fillRoundRect(160, 280, 250, 65, 70, 70);

                g.setColor(new Color(133, 229, 255));
                g.fillRoundRect(160, 360, 250, 65, 70, 70);
                g.setColor(new Color(133, 229, 255));
                g.fillRoundRect(160, 440, 250, 65, 70, 70);
                g.setColor(new Color(133, 229, 255));
                g.fillRoundRect(160, 520, 250, 65, 70, 70);

                //rectangle state
                g.setColor(new Color(133, 229, 255));
                g.drawRoundRect(680, 400, 270, 150, 30, 30);
                g.setColor(Color.WHITE);
                g.fillRoundRect(680, 400, 270, 150, 30, 30);
                g.setColor(new Color(133, 229, 255));
                g.fillRect(680, 455, 270, 4);

                //Online
                if (!isOnline) {
                    g.setColor(new Color(133, 229, 255));
                    g.drawRoundRect(825, 485, 120, 40, 30, 30);
                    g.setColor(Color.WHITE);
                    g.fillRoundRect(825, 485, 120, 40, 30, 30);
                    g.setColor(new Color(133, 229, 255));
                    g.fillRoundRect(685, 485, 120, 40, 30, 30);
                }
                if (isOnline) {
                    g.setColor(new Color(133, 229, 255));
                    g.fillRoundRect(685, 485, 120, 40, 30, 30);
                    g.setColor(new Color(133, 229, 255));
                    g.drawRoundRect(825, 485, 120, 40, 30, 30);
                    g.setColor(Color.WHITE);
                    g.fillRoundRect(825, 485, 120, 40, 30, 30);
                }
                //Away
                if (!isAway) {
                    g.setColor(new Color(133, 229, 255));
                    g.drawRoundRect(825, 485, 120, 40, 30, 30);
                    g.setColor(Color.WHITE);
                    g.fillRoundRect(825, 485, 120, 40, 30, 30);
                    g.setColor(new Color(133, 229, 255));
                    g.fillRoundRect(685, 485, 120, 40, 30, 30);
                }
                if (isAway) {
                    g.setColor(new Color(133, 229, 255));
                    g.fillRoundRect(825, 485, 120, 40, 30, 30);
                    g.setColor(new Color(133, 229, 255));
                    g.drawRoundRect(685, 485, 120, 40, 30, 30);
                    g.setColor(Color.WHITE);
                    g.fillRoundRect(685, 485, 120, 40, 30, 30);
                }
                /*lbl_away.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        System.out.println("ok");
                        if (!isAway) {
                            isAway = true;
                            isOnline = false;
                            try {
                                UserDaoImpl.updateUserAway();
                            } catch (SQLException ex) {
                                throw new RuntimeException(ex);
                            }
                            repaint();
                        }
                    }
                });

                lbl_online.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        System.out.println("ok");
                        if (!isOnline) {
                            isOnline = true;
                            isAway = false;
                            try {
                                UserDaoImpl.updateUserConnected(ClientHandler.cName);
                            } catch (SQLException ex) {
                                throw new RuntimeException(ex);
                            }
                            repaint();
                        }
                    }
                });*/

                //Dessiner le carré pour bouton/fenetre actuelle
                g.setColor(new Color(0, 245, 212));
                g.fillRoundRect(5, 272, 140, 40, 20, 20);

            }
        };
        panel_chat.setBackground(new Color(0, 187, 249));
        panel_chat.setLayout(new SpringLayout());

        //Ajout de tous les composants au JPanel
        panel_chat.add(lbl_logo);
        panel_chat.add(lbl_account);
        panel_chat.add(lbl_fname);
        panel_chat.add(lbl_lname);
        panel_chat.add(lbl_handle1);
        panel_chat.add(lbl_active_since);
        panel_chat.add(lbl_messages);
        panel_chat.add(lbl_status_1);
        panel_chat.add(lbl_state);
        panel_chat.add(lbl_online);
        panel_chat.add(lbl_away);
        panel_chat.add(button_chat);
        panel_chat.add(button_users);
        panel_chat.add(button_settings);
        panel_chat.add(button_logout);
        panel_chat.add(lbl_icon_chat);
        panel_chat.add(lbl_icon_users);
        panel_chat.add(lbl_icon_settings);
        panel_chat.add(lbl_icon_logout);

        panel_chat.add(fname);
        panel_chat.add(lname);
        panel_chat.add(handle);
        panel_chat.add(activeSince);
        panel_chat.add(messages);
        panel_chat.add(statut);


        //TODO Définir les contraintes pour chaque composant pour le contentPanel uniquement
        SpringLayout contentLayout = (SpringLayout) panel_chat.getLayout();

        contentLayout.putConstraint(SpringLayout.NORTH, lbl_logo, 40, SpringLayout.NORTH, panel_chat);
        contentLayout.putConstraint(SpringLayout.WEST, lbl_logo, 40, SpringLayout.WEST, panel_chat);

        contentLayout.putConstraint(SpringLayout.NORTH, lbl_account, 25, SpringLayout.NORTH, panel_chat);
        contentLayout.putConstraint(SpringLayout.WEST, lbl_account, 440, SpringLayout.WEST, panel_chat);

        contentLayout.putConstraint(SpringLayout.NORTH, lbl_fname, 140, SpringLayout.NORTH, panel_chat);
        contentLayout.putConstraint(SpringLayout.WEST, lbl_fname, 235, SpringLayout.WEST, panel_chat);

        contentLayout.putConstraint(SpringLayout.NORTH, lbl_lname, 220, SpringLayout.NORTH, panel_chat);
        contentLayout.putConstraint(SpringLayout.WEST, lbl_lname, 235, SpringLayout.WEST, panel_chat);

        contentLayout.putConstraint(SpringLayout.NORTH, lbl_handle1, 300, SpringLayout.NORTH, panel_chat);
        contentLayout.putConstraint(SpringLayout.WEST, lbl_handle1, 250, SpringLayout.WEST, panel_chat);

        contentLayout.putConstraint(SpringLayout.NORTH, lbl_active_since, 380, SpringLayout.NORTH, panel_chat);
        contentLayout.putConstraint(SpringLayout.WEST, lbl_active_since, 230, SpringLayout.WEST, panel_chat);

        contentLayout.putConstraint(SpringLayout.NORTH, lbl_messages, 460, SpringLayout.NORTH, panel_chat);
        contentLayout.putConstraint(SpringLayout.WEST, lbl_messages, 237, SpringLayout.WEST, panel_chat);

        contentLayout.putConstraint(SpringLayout.NORTH, lbl_status_1, 540, SpringLayout.NORTH, panel_chat);
        contentLayout.putConstraint(SpringLayout.WEST, lbl_status_1, 255, SpringLayout.WEST, panel_chat);

        contentLayout.putConstraint(SpringLayout.NORTH, lbl_state, 420, SpringLayout.NORTH, panel_chat);
        contentLayout.putConstraint(SpringLayout.WEST, lbl_state, 785, SpringLayout.WEST, panel_chat);

        contentLayout.putConstraint(SpringLayout.NORTH, lbl_online, 71, SpringLayout.NORTH, lbl_state);
        contentLayout.putConstraint(SpringLayout.WEST, lbl_online, 710, SpringLayout.WEST, panel_chat);

        contentLayout.putConstraint(SpringLayout.NORTH, lbl_away, 71, SpringLayout.NORTH, lbl_state);
        contentLayout.putConstraint(SpringLayout.WEST, lbl_away, 145, SpringLayout.WEST, lbl_online);


        //TODO Ajout des JText Field et JText pour les infos du User --------------------------------------
        contentLayout.putConstraint(SpringLayout.NORTH, fname, 140, SpringLayout.NORTH, panel_chat);
        contentLayout.putConstraint(SpringLayout.WEST, fname, 267, SpringLayout.WEST, lbl_fname);
        //lname
        contentLayout.putConstraint(SpringLayout.NORTH, lname, 80, SpringLayout.NORTH, fname);
        contentLayout.putConstraint(SpringLayout.WEST, lname, 250, SpringLayout.WEST, lbl_lname);
        //Handle
        contentLayout.putConstraint(SpringLayout.NORTH, handle, 80, SpringLayout.NORTH, lname);
        contentLayout.putConstraint(SpringLayout.WEST, handle, 250, SpringLayout.WEST, lbl_handle1);
        //active since
        contentLayout.putConstraint(SpringLayout.NORTH, activeSince, 80, SpringLayout.NORTH, handle);
        contentLayout.putConstraint(SpringLayout.WEST, activeSince, 208, SpringLayout.WEST, lbl_active_since);
        //messages
        contentLayout.putConstraint(SpringLayout.NORTH, messages, 80, SpringLayout.NORTH, activeSince);
        contentLayout.putConstraint(SpringLayout.WEST, messages, 285, SpringLayout.WEST, lbl_messages);
        //statut
        contentLayout.putConstraint(SpringLayout.NORTH, statut, 80, SpringLayout.NORTH, messages);
        contentLayout.putConstraint(SpringLayout.WEST, statut, 223, SpringLayout.WEST, lbl_status_1);
        //TODO -------------------------------------------------------------------------------------------



//TODO FAIRE COMME LA LIGNE D EN DESSOUS POUR RECUPERER LES INFOS SOIT DE LA BDD SOIT DU REGISTER
        //fname


        contentLayout.putConstraint(SpringLayout.NORTH, button_chat, 120, SpringLayout.NORTH, lbl_logo);
        contentLayout.putConstraint(SpringLayout.WEST, button_chat, 60, SpringLayout.WEST, panel_chat);

        contentLayout.putConstraint(SpringLayout.NORTH, button_users, 60, SpringLayout.NORTH, button_chat);
        contentLayout.putConstraint(SpringLayout.WEST, button_users, 60, SpringLayout.WEST, panel_chat);

        contentLayout.putConstraint(SpringLayout.NORTH, button_settings, 60, SpringLayout.NORTH, button_users);
        contentLayout.putConstraint(SpringLayout.WEST, button_settings, 60, SpringLayout.WEST, panel_chat);

        contentLayout.putConstraint(SpringLayout.NORTH, button_logout, 60, SpringLayout.NORTH, button_settings);
        contentLayout.putConstraint(SpringLayout.WEST, button_logout, 60, SpringLayout.WEST, panel_chat);

        contentLayout.putConstraint(SpringLayout.NORTH, lbl_icon_chat, 120, SpringLayout.NORTH, lbl_logo);
        contentLayout.putConstraint(SpringLayout.WEST, lbl_icon_chat, 15, SpringLayout.WEST, panel_chat);

        contentLayout.putConstraint(SpringLayout.NORTH, lbl_icon_users, 55, SpringLayout.NORTH, lbl_icon_chat);
        contentLayout.putConstraint(SpringLayout.WEST, lbl_icon_users, 15, SpringLayout.WEST, panel_chat);

        contentLayout.putConstraint(SpringLayout.NORTH, lbl_icon_settings, 60, SpringLayout.NORTH, lbl_icon_users);
        contentLayout.putConstraint(SpringLayout.WEST, lbl_icon_settings, 15, SpringLayout.WEST, panel_chat);

        contentLayout.putConstraint(SpringLayout.NORTH, lbl_icon_logout, 60, SpringLayout.NORTH, lbl_icon_settings);
        contentLayout.putConstraint(SpringLayout.WEST, lbl_icon_logout, 15, SpringLayout.WEST, panel_chat);

        contentLayout.putConstraint(SpringLayout.NORTH, lbl_icon_edit, 125, SpringLayout.NORTH, panel_chat);
        contentLayout.putConstraint(SpringLayout.WEST, lbl_icon_edit, 665, SpringLayout.WEST, panel_chat);
        panel_chat.setComponentZOrder(lbl_icon_edit, 0);

        contentLayout.putConstraint(SpringLayout.NORTH, lbl_icon_edit1, 80, SpringLayout.NORTH, lbl_icon_edit);
        contentLayout.putConstraint(SpringLayout.WEST, lbl_icon_edit1, 665, SpringLayout.WEST, panel_chat);
        panel_chat.setComponentZOrder(lbl_icon_edit1, 0);

        contentLayout.putConstraint(SpringLayout.NORTH, lbl_icon_edit2, 80, SpringLayout.NORTH, lbl_icon_edit1);
        contentLayout.putConstraint(SpringLayout.WEST, lbl_icon_edit2, 665, SpringLayout.WEST, panel_chat);
        panel_chat.setComponentZOrder(lbl_icon_edit2, 0);

        contentLayout.putConstraint(SpringLayout.NORTH, lbl_icon_cancel, 118, SpringLayout.NORTH, panel_chat);
        contentLayout.putConstraint(SpringLayout.WEST, lbl_icon_cancel, 0, SpringLayout.EAST, lbl_icon_edit);
        panel_chat.setComponentZOrder(lbl_icon_cancel, 0);

        contentLayout.putConstraint(SpringLayout.NORTH, lbl_icon_cancel1, 80, SpringLayout.NORTH, lbl_icon_cancel);
        contentLayout.putConstraint(SpringLayout.WEST, lbl_icon_cancel1, 0, SpringLayout.EAST, lbl_icon_edit1);
        panel_chat.setComponentZOrder(lbl_icon_cancel1, 0);

        contentLayout.putConstraint(SpringLayout.NORTH, lbl_icon_cancel2, 80, SpringLayout.NORTH, lbl_icon_cancel1);
        contentLayout.putConstraint(SpringLayout.WEST, lbl_icon_cancel2, 0, SpringLayout.EAST, lbl_icon_edit2);
        panel_chat.setComponentZOrder(lbl_icon_cancel2, 0);

        contentLayout.putConstraint(SpringLayout.NORTH, lbl_icon_validate, 118, SpringLayout.NORTH, panel_chat);
        contentLayout.putConstraint(SpringLayout.WEST, lbl_icon_validate, 0, SpringLayout.EAST, lbl_icon_cancel);
        panel_chat.setComponentZOrder(lbl_icon_validate, 0);

        contentLayout.putConstraint(SpringLayout.NORTH, lbl_icon_validate1, 80, SpringLayout.NORTH, lbl_icon_validate);
        contentLayout.putConstraint(SpringLayout.WEST, lbl_icon_validate1, 0, SpringLayout.EAST, lbl_icon_cancel1);
        panel_chat.setComponentZOrder(lbl_icon_validate1, 0);

        contentLayout.putConstraint(SpringLayout.NORTH, lbl_icon_validate2, 80, SpringLayout.NORTH, lbl_icon_validate1);
        contentLayout.putConstraint(SpringLayout.WEST, lbl_icon_validate2, 0, SpringLayout.EAST, lbl_icon_cancel2);
        panel_chat.setComponentZOrder(lbl_icon_validate2, 0);

        add(panel_chat);
    }

    /*public static void displaySettingsInfo(String name, String lastname, String username, String date, String message, String status, Client client){
        JTextField labelName = new JTextField(name);
        JTextField labelSurname = new JTextField(lastname);
        JTextField labelUser = new JTextField(username);
        JTextField labelDate = new JTextField(date);
        JTextField labelMessage = new JTextField(message);
        JTextField labelStatus = new JTextField(status);

        lbl_icon_edit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                lbl_icon_edit.setIcon(img_icon_edit_white);
                lbl_icon_edit.repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                lbl_icon_edit.setIcon(img_icon_edit);
                lbl_icon_edit.repaint();
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                labelName.setEditable(true);
                labelName.setPreferredSize(new Dimension(100, 30));
            }
        });

        lbl_icon_edit1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                lbl_icon_edit1.setIcon(img_icon_edit_white_1);
                lbl_icon_edit1.repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                lbl_icon_edit1.setIcon(img_icon_edit1);
                lbl_icon_edit1.repaint();
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                labelSurname.setEditable(true);
                labelSurname.setPreferredSize(new Dimension(100, 30));
            }
        });


        lbl_icon_edit2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                lbl_icon_edit2.setIcon(img_icon_edit_white_2);
                lbl_icon_edit2.repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                lbl_icon_edit2.setIcon(img_icon_edit2);
                lbl_icon_edit2.repaint();
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                labelUser.setEditable(true);
                labelUser.setPreferredSize(new Dimension(100, 30));
            }
        });

        lbl_icon_cancel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                lbl_icon_cancel.setIcon(img_icon_cancel_white);
                lbl_icon_cancel.repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                lbl_icon_cancel.setIcon(img_icon_cancel);
                lbl_icon_cancel.repaint();
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                labelName.setEditable(false);
                try {
                    labelName.setText(UserDaoImpl.getFirstName(client.getUsername()));
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                labelName.setPreferredSize(new Dimension(100, 30));
            }
        });

        lbl_icon_cancel1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                lbl_icon_cancel1.setIcon(img_icon_cancel_white_1);
                lbl_icon_cancel1.repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                lbl_icon_cancel1.setIcon(img_icon_cancel1);
                lbl_icon_cancel1.repaint();
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                labelName.setEditable(false);
                try {
                    labelName.setText(UserDaoImpl.getLastName(client.getUsername()));
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                labelName.setPreferredSize(new Dimension(100, 30));
            }
        });

        lbl_icon_cancel2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                lbl_icon_cancel2.setIcon(img_icon_cancel_white_2);
                lbl_icon_cancel2.repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                lbl_icon_cancel2.setIcon(img_icon_cancel2);
                lbl_icon_cancel2.repaint();
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                labelUser.setEditable(false);
                labelUser.setText(client.getUsername());
                labelUser.setPreferredSize(new Dimension(100, 30));
            }
        });
        lbl_icon_validate.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                lbl_icon_validate.setIcon(img_icon_validate_white);
                lbl_icon_validate.repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                lbl_icon_validate.setIcon(img_icon_validate);
                lbl_icon_validate.repaint();
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    UserDaoImpl.updateUserFirstName(client.getUsername(), labelName.getText());
                    labelName.setEditable(false);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        lbl_icon_validate1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                lbl_icon_validate1.setIcon(img_icon_validate_white_1);
                lbl_icon_validate1.repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                lbl_icon_validate1.setIcon(img_icon_validate1);
                lbl_icon_validate1.repaint();
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                if (labelSurname.getText().equals(" ")) {
                    try {
                        labelSurname.setText(UserDaoImpl.getLastName(client.getUsername()));
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                }
                labelSurname.setEditable(false);
                try {
                    UserDaoImpl.updateUserLastName(client.getUsername(), labelSurname.getText());
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        lbl_icon_validate2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                lbl_icon_validate2.setIcon(img_icon_validate_white_2);
                lbl_icon_validate2.repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                lbl_icon_validate2.setIcon(img_icon_validate2);
                lbl_icon_validate2.repaint();
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                if (labelUser.getText().equals(" ")) {
                    labelUser.setText(client.getUsername());
                }
                labelUser.setEditable(true);
                try {
                    UserDaoImpl.updateUserUserName(labelUser.getText(), UserDaoImpl.getFirstName(client.getUsername()));
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        labelName.setFont(urbanist.deriveFont(Font.PLAIN, 20));
        labelName.setBorder(null);
        labelName.setOpaque(false);
        labelName.setBackground(new Color(0, 0, 0, 0));

        labelSurname.setFont(urbanist.deriveFont(Font.PLAIN, 20));
        labelSurname.setBorder(null);
        labelSurname.setOpaque(false);
        labelSurname.setBackground(new Color(0, 0, 0, 0));

        labelUser.setFont(urbanist.deriveFont(Font.PLAIN, 20));
        labelUser.setBorder(null);
        labelUser.setOpaque(false);
        labelUser.setBackground(new Color(0, 0, 0, 0));

        labelDate.setFont(urbanist.deriveFont(Font.PLAIN, 20));
        labelDate.setBorder(null);
        labelDate.setOpaque(false);
        labelDate.setBackground(new Color(0, 0, 0, 0));

        labelMessage.setFont(urbanist.deriveFont(Font.PLAIN, 20));
        labelMessage.setBorder(null);
        labelMessage.setOpaque(false);
        labelMessage.setBackground(new Color(0, 0, 0, 0));

        labelStatus.setFont(urbanist.deriveFont(Font.PLAIN, 20));
        labelStatus.setBorder(null);
        labelStatus.setOpaque(false);
        labelStatus.setBackground(new Color(0, 0, 0, 0));

        panel_chat.add(labelName);
        panel_chat.add(labelSurname);
        panel_chat.add(labelUser);
        panel_chat.add(labelDate);
        panel_chat.add(labelMessage);
        panel_chat.add(labelStatus);
        panel_chat.add(lbl_icon_edit);
        panel_chat.add(lbl_icon_edit1);
        panel_chat.add(lbl_icon_edit2);
        panel_chat.add(lbl_icon_cancel);
        panel_chat.add(lbl_icon_cancel1);
        panel_chat.add(lbl_icon_cancel2);
        panel_chat.add(lbl_icon_validate);
        panel_chat.add(lbl_icon_validate1);
        panel_chat.add(lbl_icon_validate2);

        SpringLayout layoutPanel = (SpringLayout) panel_chat.getLayout();

        //TODO FAIRE COMME LA LIGNE D EN DESSOUS POUR RECUPERER LES INFOS SOIT DE LA BDD SOIT DU REGISTER
        layoutPanel.putConstraint(SpringLayout.NORTH, labelName, 140, SpringLayout.NORTH, panel_chat);
        layoutPanel.putConstraint(SpringLayout.WEST, labelName, 267, SpringLayout.WEST, lbl_first_name);
        //lname
        layoutPanel.putConstraint(SpringLayout.NORTH, labelSurname, 80, SpringLayout.NORTH, labelName);
        layoutPanel.putConstraint(SpringLayout.WEST, labelSurname, 250, SpringLayout.WEST, lbl_last_name);
        //Handle
        layoutPanel.putConstraint(SpringLayout.NORTH, labelUser, 80, SpringLayout.NORTH, labelSurname);
        layoutPanel.putConstraint(SpringLayout.WEST, labelUser, 250, SpringLayout.WEST, lbl_handle);
        //active since
        layoutPanel.putConstraint(SpringLayout.NORTH, labelDate, 80, SpringLayout.NORTH, labelUser);
        layoutPanel.putConstraint(SpringLayout.WEST, labelDate, 208, SpringLayout.WEST, lbl_active);
        //messages
        layoutPanel.putConstraint(SpringLayout.NORTH, labelMessage, 80, SpringLayout.NORTH, labelDate);
        layoutPanel.putConstraint(SpringLayout.WEST, labelMessage, 285, SpringLayout.WEST, lbl_msg);
        //statut
        layoutPanel.putConstraint(SpringLayout.NORTH, labelStatus, 80, SpringLayout.NORTH, labelMessage);
        layoutPanel.putConstraint(SpringLayout.WEST, labelStatus, 223, SpringLayout.WEST, lbl_statut);
    }*/
}


