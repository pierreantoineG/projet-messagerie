package Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import Model.*;
import Dao.*;

public class Settings extends JFrame {

    private Client clientUser;
    private boolean isAway = false;
    private boolean isOnline = false;

    public Settings(Client client) throws IOException, FontFormatException, SQLException {
        this.clientUser = client;
    }

    public void initializeSettings() throws IOException, FontFormatException, SQLException {
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

        //TODO Faire 5 autres JTextField pour afficher info dans la bulle (coordonnees ok)
        JTextField handle = new JTextField();
        handle.setFont(urbanist.deriveFont(Font.PLAIN, 20));
        handle.setBorder(null);
        handle.setEditable(false);
        handle.setOpaque(false);
        handle.setBackground(new Color(0, 0, 0, 0));
        handle.setText(clientUser.getUsername());

        JTextField fname = new JTextField();
        fname.setFont(urbanist.deriveFont(Font.PLAIN, 20));
        fname.setBorder(null);
        fname.setEditable(false);
        fname.setOpaque(false);
        fname.setBackground(new Color(0, 0, 0, 0));
        fname.setText(UserDaoImpl.getFirstName(clientUser.getUsername()));

        JTextField lname = new JTextField();
        lname.setFont(urbanist.deriveFont(Font.PLAIN, 20));
        lname.setBorder(null);
        lname.setEditable(false);
        lname.setOpaque(false);
        lname.setBackground(new Color(0, 0, 0, 0));
        lname.setText(UserDaoImpl.getLastName(clientUser.getUsername()));

        JTextField activeSince = new JTextField();
        activeSince.setFont(urbanist.deriveFont(Font.PLAIN, 20));
        activeSince.setBorder(null);
        activeSince.setEditable(false);
        activeSince.setOpaque(false);
        activeSince.setBackground(new Color(0, 0, 0, 0));
        activeSince.setText(UserDaoImpl.getLastTimeConnection(clientUser.getUsername()));

        JTextField messages = new JTextField();
        messages.setFont(urbanist.deriveFont(Font.PLAIN, 20));
        messages.setBorder(null);
        messages.setEditable(false);
        messages.setOpaque(false);
        messages.setBackground(new Color(0, 0, 0, 0));
        messages.setText(String.valueOf(UserDaoImpl.countUserMessages(clientUser.getUsername())));

        JTextField statut = new JTextField();
        statut.setFont(urbanist.deriveFont(Font.PLAIN, 20));
        statut.setBorder(null);
        statut.setEditable(false);
        statut.setOpaque(false);
        statut.setBackground(new Color(0, 0, 0, 0));
        statut.setText(UserDaoImpl.getRole(clientUser.getUsername()));

        //Image icon editer
        ImageIcon img_icon_edit = new ImageIcon("Icons/icon_modifier.png");
        ImageIcon img_icon_edit_white = new ImageIcon("Icons/icon_modifier (1).png");
        JLabel lbl_icon_edit = new JLabel(new ImageIcon(img_icon_edit.getImage()));
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
                fname.setEditable(true);
                fname.setPreferredSize(new Dimension(100, 30));
            }
        });

        ImageIcon img_icon_edit1 = new ImageIcon("Icons/icon_modifier.png");
        ImageIcon img_icon_edit_white_1 = new ImageIcon("Icons/icon_modifier (1).png");
        JLabel lbl_icon_edit1 = new JLabel(new ImageIcon(img_icon_edit1.getImage()));
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
                lname.setEditable(true);
                lname.setPreferredSize(new Dimension(100, 30));
            }
        });

        ImageIcon img_icon_edit2 = new ImageIcon("Icons/icon_modifier.png");
        ImageIcon img_icon_edit_white_2 = new ImageIcon("Icons/icon_modifier (1).png");
        JLabel lbl_icon_edit2 = new JLabel(new ImageIcon(img_icon_edit2.getImage()));
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
                handle.setEditable(true);
                handle.setPreferredSize(new Dimension(100, 30));
            }
        });

        //Image icon annuler
        ImageIcon img_icon_cancel = new ImageIcon("Icons/imgCancelRed (1).png");
        ImageIcon img_icon_cancel_white = new ImageIcon("Icons/imgCancelWhite (2).png");
        JLabel lbl_icon_cancel = new JLabel(new ImageIcon(img_icon_cancel.getImage()));
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
                fname.setEditable(false);
                try {
                    fname.setText(UserDaoImpl.getFirstName(clientUser.getUsername()));
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                fname.setPreferredSize(new Dimension(100, 30));
            }
        });

        ImageIcon img_icon_cancel1 = new ImageIcon("Icons/imgCancelRed (1).png");
        ImageIcon img_icon_cancel_white_1 = new ImageIcon("Icons/imgCancelWhite (2).png");
        JLabel lbl_icon_cancel1 = new JLabel(new ImageIcon(img_icon_cancel1.getImage()));
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
                lname.setEditable(false);
                try {
                    lname.setText(UserDaoImpl.getLastName(clientUser.getUsername()));
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                lname.setPreferredSize(new Dimension(100, 30));
            }
        });

        ImageIcon img_icon_cancel2 = new ImageIcon("Icons/imgCancelRed (1).png");
        ImageIcon img_icon_cancel_white_2 = new ImageIcon("Icons/imgCancelWhite (2).png");
        JLabel lbl_icon_cancel2 = new JLabel(new ImageIcon(img_icon_cancel2.getImage()));
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
                handle.setEditable(false);
                handle.setText(clientUser.getUsername());
                handle.setPreferredSize(new Dimension(100, 30));
            }
        });

        //Image icon validate
        ImageIcon img_icon_validate = new ImageIcon("Icons/imgConfirmGreen (1).png");
        ImageIcon img_icon_validate_white = new ImageIcon("Icons/imgConfirmWhite (1).png");
        JLabel lbl_icon_validate = new JLabel(new ImageIcon(img_icon_validate.getImage()));
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
                    UserDaoImpl.updateUserFirstName(clientUser.getUsername(), fname.getText());
                    fname.setEditable(false);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        ImageIcon img_icon_validate1 = new ImageIcon("Icons/imgConfirmGreen (1).png");
        ImageIcon img_icon_validate_white_1 = new ImageIcon("Icons/imgConfirmWhite (1).png");
        JLabel lbl_icon_validate1 = new JLabel(new ImageIcon(img_icon_validate1.getImage()));
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
                if (lname.getText().equals(" ")) {
                    try {
                        lname.setText(UserDaoImpl.getLastName(clientUser.getUsername()));
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                }
                lname.setEditable(false);
                try {
                    UserDaoImpl.updateUserLastName(clientUser.getUsername(), lname.getText());
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        ImageIcon img_icon_validate2 = new ImageIcon("Icons/imgConfirmGreen (1).png");
        ImageIcon img_icon_validate_white_2 = new ImageIcon("Icons/imgConfirmWhite (1).png");
        JLabel lbl_icon_validate2 = new JLabel(new ImageIcon(img_icon_validate2.getImage()));
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
                if (handle.getText().equals(" ")) {
                    handle.setText(clientUser.getUsername());
                }
                handle.setEditable(true);
                try {
                    UserDaoImpl.updateUserUserName(handle.getText(), UserDaoImpl.getFirstName(clientUser.getUsername()));
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        //Texte 1
        JLabel lbl_account = new JLabel("My Account");
        lbl_account.setFont(urbanist.deriveFont(Font.BOLD, 30));
        lbl_account.setHorizontalAlignment(SwingConstants.CENTER);

        //Texte 2
        JLabel lbl_fname = new JLabel("First name ");
        lbl_fname.setFont(urbanist.deriveFont(Font.BOLD, 20));
        lbl_fname.setHorizontalAlignment(SwingConstants.CENTER);

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
        JButton button_chat = new JButton("Chat");
        button_chat.setFont(urbanist.deriveFont(Font.BOLD, 19));
        button_chat.setBackground(new Color(0, 0, 0, 0));
        button_chat.setBorder(null);
        button_chat.setContentAreaFilled(false);
        button_chat.setOpaque(false);
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


        //Bouton users
        JButton button_users = new JButton("Users");
        button_users.setFont(urbanist.deriveFont(Font.BOLD, 19));
        button_users.setOpaque(false);
        button_users.setContentAreaFilled(false);
        button_users.setBackground(new Color(0, 0, 0, 0));
        button_users.setBorder(null);
        button_users.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Afficher view1 si User
                //Afficher view2 si Moderator
                //Afficher view3 si Admin
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

        //Bouton settings
        JButton button_settings = new JButton("Settings");
        button_settings.setFont(urbanist.deriveFont(Font.BOLD, 19));
        button_settings.setBackground(new Color(0, 0, 0, 0));
        button_settings.setBorder(null);
        button_settings.setOpaque(false);
        button_settings.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Rien ne se passe
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

        //Bouton log out
        JButton button_logout = new JButton("Log out");
        button_logout.setFont(urbanist.deriveFont(Font.BOLD, 19));
        button_logout.setBackground(new Color(0, 0, 0, 0));
        button_logout.setBorder(null);
        button_logout.setOpaque(false);
        button_logout.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                //Remettre page du log in
            }

            public void mouseEntered(MouseEvent e) {
                button_logout.setForeground(Color.WHITE);
            }

            public void mouseExited(MouseEvent e) {
                button_logout.setForeground(Color.BLACK);// Rétablir la couleur de fond par défaut du bouton
            }
        });

        //Bouton reporting
        JButton button_reporting = new JButton("Reporting");
        button_reporting.setFont(urbanist.deriveFont(Font.BOLD, 19));
        button_reporting.setBackground(new Color(0, 0, 0, 0));
        button_reporting.setBorder(null);
        button_reporting.setOpaque(false);
        button_reporting.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                try {
                    if(UserDaoImpl.getRole(clientUser.getUsername()).equals("Administrator")){
                        ReportView reportView = null;
                        reportView = new ReportView();
                        reportView.setVisible(true);
                    }
                    else {
                        JOptionPane.showMessageDialog(null, "NOT ALLOWED BECAUSE YOU ARE NOT AN ADMINISTRATOR ...");
                    }
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }

            public void mouseEntered(MouseEvent e) {
                button_reporting.setForeground(Color.WHITE);
            }

            public void mouseExited(MouseEvent e) {
                button_reporting.setForeground(Color.BLACK);
            }
        });

        //Bouton online
        JButton lbl_online = new JButton("Online ");
        lbl_online.setFont(urbanist.deriveFont(Font.BOLD, 20));
        lbl_online.setHorizontalAlignment(SwingConstants.CENTER);
        lbl_online.setBackground(new Color(0, 0, 0, 0));
        lbl_online.setOpaque(false);
        lbl_online.setBorder(null);
        lbl_online.setFocusPainted(false);

        //Bouton away
        JButton lbl_away = new JButton("Away");
        lbl_away.setFont(urbanist.deriveFont(Font.BOLD, 20));
        lbl_away.setHorizontalAlignment(SwingConstants.CENTER);
        lbl_away.setBackground(new Color(0, 0, 0, 0));
        lbl_away.setOpaque(false);
        lbl_away.setBorder(null);
        lbl_away.setFocusPainted(false);

        JPanel panel_chat = new JPanel() {
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
                lbl_away.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        System.out.println("ok");
                        if (!isAway) {
                            isAway = true;
                            isOnline = false;
                            try {
                                UserDaoImpl.updateUserAway(clientUser.getUsername());
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
                                UserDaoImpl.updateUserConnected(clientUser.getUsername());
                            } catch (SQLException ex) {
                                throw new RuntimeException(ex);
                            }
                            repaint();
                        }
                    }
                });

                //Dessiner le carrée pour bouton/fenetre actuelle
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
        panel_chat.add(lbl_icon_edit);
        panel_chat.add(lbl_icon_edit1);
        panel_chat.add(lbl_icon_edit2);
        panel_chat.add(lbl_icon_cancel);
        panel_chat.add(lbl_icon_cancel1);
        panel_chat.add(lbl_icon_cancel2);
        panel_chat.add(lbl_icon_validate);
        panel_chat.add(lbl_icon_validate1);
        panel_chat.add(lbl_icon_validate2);
        panel_chat.add(handle);
        panel_chat.add(fname);
        panel_chat.add(lname);
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


//TODO FAIRE COMME LA LIGNE D EN DESSOUS POUR RECUPERER LES INFOS SOIT DE LA BDD SOIT DU REGISTER
        //fname
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
        setVisible(true);
}




}
