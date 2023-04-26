package View;

import Controller.ChatControl;
import Controller.LoginControl;
import Model.*;
import Controller.Settings;
import Dao.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

public class UsersModeratorView extends JFrame{
    private static boolean isBan = false;
    private static boolean isUban = false;
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
    private ChatView chatView;

    public UsersModeratorView() throws IOException, FontFormatException, SQLException {

        setSize(1000, 680);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

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

        //Image icon reporting
        ImageIcon img_icon_reporting = new ImageIcon("Icons/icon_reporting.png");
        JLabel lbl_icon_reporting = new JLabel(new ImageIcon(img_icon_reporting.getImage()));

        //Texte 1
        JLabel lbl_user_community = new JLabel("User community");
        lbl_user_community.setFont(urbanist.deriveFont(Font.BOLD, 30));
        lbl_user_community.setHorizontalAlignment(SwingConstants.CENTER);

        //Texte 2
        JLabel lbl_admin = new JLabel("Administrator ");
        lbl_admin.setFont(urbanist.deriveFont(Font.BOLD, 20));
        lbl_admin.setHorizontalAlignment(SwingConstants.CENTER);

        //Texte 3
        JLabel lbl_moderator = new JLabel("Moderator ");
        lbl_moderator.setFont(urbanist.deriveFont(Font.BOLD, 20));
        lbl_moderator.setHorizontalAlignment(SwingConstants.CENTER);

        //Texte 4
        JLabel lbl_nb_users = new JLabel("Number of users ");
        lbl_nb_users.setFont(urbanist.deriveFont(Font.BOLD, 20));
        lbl_nb_users.setHorizontalAlignment(SwingConstants.CENTER);

        //Texte 5
        JLabel lbl_nb_registered = new JLabel(); //creer variable qui recupere nb de users enregistres sur la bdd
        lbl_nb_registered.setFont(urbanist.deriveFont(Font.BOLD, 38));
        lbl_nb_registered.setForeground(new Color(0, 245, 212));
        lbl_nb_registered.setHorizontalAlignment(SwingConstants.CENTER);
        lbl_nb_registered.setBorder(null);
        lbl_nb_registered.setOpaque(false);
        lbl_nb_registered.setBackground(new Color(0, 0, 0, 0));
        try {
            lbl_nb_registered.setText(UserDaoImpl.countUsers() + " registered");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        JTextArea chatArea = new JTextArea();
        chatArea.setEditable(false);
        chatArea.setBackground(Color.WHITE);
        //Scroll Pane
        /*JScrollPane scrollPane_chat = new JScrollPane(chatArea);
        scrollPane_chat.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane_chat.setPreferredSize(new Dimension(510, 540));
        scrollPane_chat.setBorder(null);*/

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
                ChatView chatView = null;
                try {
                    chatView = new ChatView();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                ChatControl chatControl = new ChatControl(chatView, clientUser);
                chatControl.initializeChatView(clientUser.getUsername());
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
                Settings f1 = null;
                try {
                    f1 = new Settings(clientUser);
                } catch (IOException | FontFormatException | SQLException ex) {
                    throw new RuntimeException(ex);
                }
                f1.setVisible(true);
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
        button_logout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                chatView.setVisible(false);
                LoginControl loginControl = new LoginControl(new LoginView());
                loginControl.initialize();
            }
        });
        button_logout.addMouseListener(new MouseAdapter() {
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

        String[] moderatorUsers;
        String[] administratorUsers;
        Object[][] userInfo;
        String[] bannedUsers;
        String[] unbannedUsers;

        try {
            moderatorUsers = UserDaoImpl.getRoleModeratorUsers();
            administratorUsers = UserDaoImpl.getAdmim();
            userInfo = UserDaoImpl.getUserStatus();
            bannedUsers = UserDaoImpl.getBannedUsers();
            unbannedUsers = UserDaoImpl.getUnBannedUsers();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        final int MAX_MODERATORS = 6;
        final int MAX_ADMINISTRATORS = 6;
        int numModerators = Math.min(moderatorUsers.length, MAX_MODERATORS);
        int numAdministrators = Math.min(administratorUsers.length, MAX_ADMINISTRATORS);

        JPanel panel_grid = new JPanel(new GridLayout(moderatorUsers.length, 2));
        panel_grid.setOpaque(false);
        System.out.println(moderatorUsers.length);

        JPanel panel_grid1 = new JPanel(new GridLayout(administratorUsers.length, 2));
        panel_grid1.setOpaque(false);
        System.out.println(administratorUsers.length);

        JPanel panel_grid2 = new JPanel(new GridLayout(userInfo.length, 1));
        panel_grid2.setOpaque(false);
        System.out.println(userInfo.length);

        JPanel panel_grid3 = new JPanel(new GridLayout(userInfo.length, 1));
        panel_grid3.setOpaque(false);

        JPanel panel_grid4 = new JPanel(new GridLayout(userInfo.length, 1));
        panel_grid4.setOpaque(false);


        JPanel panel_chat = new JPanel() {
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                //Chat
                g.setColor(Color.WHITE);
                g.fillRoundRect(150, 5, 520, 625, 30, 30);
                g.setColor(new Color(202, 214, 216));
                g.fillRect(150, 80, 520, 3);

                int x = 160;
                int y = 90;
                int x_ban = 580;
                int y_ban = 100;
                int x1 = 170;
                int y1 = 105;

                for (Object[] user : userInfo) {
                    String name = (String) user[0];
                    String username = (String) user[1];
                    final int[] role = {(int) user[2]};
                    int banned = (int) user[3];
                    Color color = (Color) user[4];

                    JTextField name1 = new JTextField();
                    name1.setFont(urbanist.deriveFont(Font.PLAIN, 14));
                    name1.setBorder(null);
                    name1.setText(name);
                    panel_grid2.add(name1);
                    name1.setBorder(new EmptyBorder(0, 0, 37, 0));
                    name1.setOpaque(false);
                    name1.setBackground(new Color(0, 0, 0, 0));
                    name1.setEditable(false);

                    JTextField username1 = new JTextField();
                    username1.setFont(urbanist.deriveFont(Font.BOLD, 14));
                    username1.setBorder(null);
                    username1.setText(username);
                    panel_grid2.add(username1);
                    username1.setBorder(new EmptyBorder(0, 0, 37, 0));
                    username1.setOpaque(false);
                    username1.setBackground(new Color(0, 0, 0, 0));
                    username1.setForeground(new Color(0, 245, 212));
                    username1.setEditable(false);

                    if (banned == 0) {
                        JButton ban = new JButton("Ban");
                        ban.setFont(urbanist.deriveFont(Font.BOLD, 14));
                        ban.setBackground(new Color(0, 0, 0, 0));
                        ban.setForeground(Color.WHITE);
                        ban.setBorder(null);
                        ban.setOpaque(false);
                        ban.setBorder(new EmptyBorder(0, 0, 38, 0));
                        for (String banUser : unbannedUsers) {
                            ban.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    isBan = true;
                                    try {
                                        UserDaoImpl.banUser(banUser, true);
                                        System.out.println(banUser);
                                    } catch (SQLException ex) {
                                        throw new RuntimeException(ex);
                                    }
                                    //;
                                }
                            });
                        }
                        panel_grid4.add(ban);
                    }
                    else {
                        JButton unbanned = new JButton("Unban");
                        //ban.setActionCommand("a");
                        unbanned.setFont(urbanist.deriveFont(Font.BOLD, 14));
                        unbanned.setBackground(new Color(0, 0, 0, 0));
                        unbanned.setForeground(Color.WHITE);
                        unbanned.setBorder(null);
                        unbanned.setOpaque(false);
                        unbanned.setBorder(new EmptyBorder(0, 0, 38, 0));
                        for (String unbannedUser : bannedUsers) {
                            unbanned.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    isUban = true;
                                    try {
                                        UserDaoImpl.UnbanUser(unbannedUser, true);
                                        System.out.println(unbannedUser);
                                    } catch (SQLException ex) {
                                        throw new RuntimeException(ex);
                                    }
                                    //unbanned.setText("Ban");
                                }
                            });
                        }
                        panel_grid4.add(unbanned);
                    }
                    //Image ban
                    ImageIcon img_ban = new ImageIcon("Icons/icon_bannir.png");
                    JLabel lbl_ban = new JLabel(new ImageIcon(img_ban.getImage()));
                    lbl_ban.setBorder(new EmptyBorder(0, 0, 35, 0));
                    panel_grid3.add(lbl_ban);

                    g.setColor(new Color(133, 229, 255));
                    g.drawRoundRect(x, y, 500, 45, 30, 30);
                    g.setColor(Color.WHITE);
                    g.fillRoundRect(x, y, 500, 45, 30, 30);
                    g.setColor(new Color(239, 72, 72));
                    g.fillRoundRect(x_ban, y_ban, 75, 25, 20, 20);
                    y += 55;
                    y_ban += 55;

                    g.setColor(color);
                    g.fillOval(x1,y1,10,10);
                    y1 += 55;
                }

                //Administrateur
                g.setColor(new Color(174, 240, 255));
                g.fillRoundRect(675, 5, 305, 180, 30, 30);
                g.setColor(Color.WHITE);
                g.fillRect(675, 60, 305, 3);

                int x2 = 685;
                int y2 = 70;
                int administratorCount = 0;

                while (administratorCount < numAdministrators) {
                    g.setColor(Color.WHITE);
                    g.fillRoundRect(x2, y2, 135, 30, 30, 30);

                    JTextField administratorName = new JTextField();
                    administratorName.setFont(urbanist.deriveFont(Font.PLAIN, 13));
                    administratorName.setBorder(null);
                    administratorName.setText(administratorUsers[administratorCount]);
                    panel_grid1.add(administratorName);
                    administratorName.setBorder(new EmptyBorder(0, 0, 40, 0));
                    administratorName.setOpaque(false);
                    administratorName.setBackground(new Color(0, 0, 0, 0));
                    administratorName.setEditable(false);

                    administratorCount++;

                    if (administratorCount % 2 == 0) {
                        y2 += 55;
                        x2 -= 145;
                    } else {
                        x2 += 145;
                    }
                }

                //Moderateur
                g.setColor(new Color(174, 240, 255));
                g.fillRoundRect(675, 195, 305, 180, 30, 30);
                g.setColor(Color.WHITE);
                g.fillRect(675, 250, 305, 3);
                //Ajouter à chaque fois qu'un membre se connecte --> faire un nouveau rectangle blanc + mettre son nom

                int x3 = 685;
                int y3 = 270;
                int moderatorCount = 0;

                while (moderatorCount < numModerators) {
                    g.setColor(Color.WHITE);
                    g.fillRoundRect(x3, y3, 135, 30, 30, 30);

                    JTextField moderatorName = new JTextField();
                    moderatorName.setFont(urbanist.deriveFont(Font.PLAIN, 13));
                    moderatorName.setBorder(null);
                    moderatorName.setText(moderatorUsers[moderatorCount]);
                    panel_grid.add(moderatorName);
                    moderatorName.setBorder(new EmptyBorder(0, 0, 40, 0));
                    moderatorName.setOpaque(false);
                    moderatorName.setBackground(new Color(0, 0, 0, 0));
                    moderatorName.setEditable(false);

                    moderatorCount++;

                    if (moderatorCount % 2 == 0) {
                        y3 += 55;
                        x3 -= 145;
                    } else {
                        x3 += 145;
                    }
                }

                //Nb of users
                g.setColor(new Color(174, 240, 255));
                g.fillRoundRect(675, 385, 305, 150, 30, 30);
                g.setColor(Color.WHITE);
                g.fillRect(675, 440, 305, 3);

                g.setColor(Color.WHITE);
                g.fillRoundRect(700, 455, 250, 50, 30, 30);

                //Dessiner le carrée pour bouton/fenetre actuelle
                g.setColor(new Color(0, 245, 212));
                g.fillRoundRect(5, 213, 140, 40, 20, 20);

            }
        };
        panel_chat.setBackground(new Color(0, 187, 249));
        panel_chat.setLayout(new SpringLayout());

        //Ajout de tous les composants au JPanel
        panel_chat.add(lbl_logo);
        panel_chat.add(lbl_user_community);
        panel_chat.add(lbl_admin);
        panel_chat.add(lbl_moderator);
        panel_chat.add(lbl_nb_users);
        panel_chat.add(lbl_nb_registered);
        //panel_chat.add(scrollPane_chat);
        panel_chat.add(button_chat);
        panel_chat.add(button_users);
        panel_chat.add(button_settings);
        panel_chat.add(button_logout);
        panel_chat.add(button_reporting);
        panel_chat.add(lbl_icon_chat);
        panel_chat.add(lbl_icon_users);
        panel_chat.add(lbl_icon_settings);
        panel_chat.add(lbl_icon_logout);
        panel_chat.add(lbl_icon_reporting);
        panel_chat.add(panel_grid);
        panel_chat.add(panel_grid1);
        panel_chat.add(panel_grid2);
        panel_chat.add(panel_grid3);
        panel_chat.add(panel_grid4);
        panel_chat.setComponentZOrder(panel_grid, 0);
        panel_chat.setComponentZOrder(panel_grid1, 0);
        //panel_chat.setComponentZOrder(panel_grid3, 0);
        panel_chat.setComponentZOrder(panel_grid2, 0);

        //TODO Définir les contraintes pour chaque composant pour le contentPanel uniquement
        SpringLayout contentLayout = (SpringLayout) panel_chat.getLayout();

        contentLayout.putConstraint(SpringLayout.NORTH, lbl_logo, 40, SpringLayout.NORTH, panel_chat);
        contentLayout.putConstraint(SpringLayout.WEST, lbl_logo, 40, SpringLayout.WEST, panel_chat);

        contentLayout.putConstraint(SpringLayout.NORTH, lbl_user_community, 25, SpringLayout.NORTH, panel_chat);
        contentLayout.putConstraint(SpringLayout.WEST, lbl_user_community, 290, SpringLayout.WEST, panel_chat);

        contentLayout.putConstraint(SpringLayout.NORTH, lbl_admin, 20, SpringLayout.NORTH, panel_chat);
        contentLayout.putConstraint(SpringLayout.WEST, lbl_admin, 760, SpringLayout.WEST, panel_chat);

        contentLayout.putConstraint(SpringLayout.NORTH, lbl_moderator, 210, SpringLayout.NORTH, panel_chat);
        contentLayout.putConstraint(SpringLayout.WEST, lbl_moderator, 775, SpringLayout.WEST, panel_chat);

        contentLayout.putConstraint(SpringLayout.NORTH, lbl_nb_users, 400, SpringLayout.NORTH, panel_chat);
        contentLayout.putConstraint(SpringLayout.WEST, lbl_nb_users, 750, SpringLayout.WEST, panel_chat);

        contentLayout.putConstraint(SpringLayout.NORTH, lbl_nb_registered, 455, SpringLayout.NORTH, panel_chat);
        contentLayout.putConstraint(SpringLayout.WEST, lbl_nb_registered, 715, SpringLayout.WEST, panel_chat);

        //contentLayout.putConstraint(SpringLayout.NORTH, scrollPane_chat, 85, SpringLayout.NORTH, panel_chat);
        //contentLayout.putConstraint(SpringLayout.WEST, scrollPane_chat, 155, SpringLayout.WEST, panel_chat);

        contentLayout.putConstraint(SpringLayout.NORTH, button_chat, 120, SpringLayout.NORTH, lbl_logo);
        contentLayout.putConstraint(SpringLayout.WEST, button_chat, 60, SpringLayout.WEST, panel_chat);

        contentLayout.putConstraint(SpringLayout.NORTH, button_users, 60, SpringLayout.NORTH, button_chat);
        contentLayout.putConstraint(SpringLayout.WEST, button_users, 60, SpringLayout.WEST, panel_chat);

        contentLayout.putConstraint(SpringLayout.NORTH, button_settings, 60, SpringLayout.NORTH, button_users);
        contentLayout.putConstraint(SpringLayout.WEST, button_settings, 60, SpringLayout.WEST, panel_chat);

        //Bouton reporting
        contentLayout.putConstraint(SpringLayout.NORTH, button_reporting, 60, SpringLayout.NORTH, button_logout);
        contentLayout.putConstraint(SpringLayout.WEST, button_reporting, 60, SpringLayout.WEST, panel_chat);

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

        //Icon logout
        contentLayout.putConstraint(SpringLayout.NORTH, lbl_icon_reporting, 60, SpringLayout.NORTH, lbl_icon_logout);
        contentLayout.putConstraint(SpringLayout.WEST, lbl_icon_reporting, 15, SpringLayout.WEST, panel_chat);

        contentLayout.putConstraint(SpringLayout.NORTH, panel_grid, 275, SpringLayout.NORTH, panel_chat);
        contentLayout.putConstraint(SpringLayout.WEST, panel_grid, 695, SpringLayout.WEST, panel_chat);

        contentLayout.putConstraint(SpringLayout.NORTH, panel_grid1, 75, SpringLayout.NORTH, panel_chat);
        contentLayout.putConstraint(SpringLayout.WEST, panel_grid1, 695, SpringLayout.WEST, panel_chat);

        contentLayout.putConstraint(SpringLayout.NORTH, panel_grid2, 100, SpringLayout.NORTH, panel_chat);
        contentLayout.putConstraint(SpringLayout.WEST, panel_grid2, 190, SpringLayout.WEST, panel_chat);

        contentLayout.putConstraint(SpringLayout.NORTH, panel_grid3, 102, SpringLayout.NORTH, panel_chat);
        contentLayout.putConstraint(SpringLayout.WEST, panel_grid3, 583, SpringLayout.WEST, panel_chat);

        contentLayout.putConstraint(SpringLayout.NORTH, panel_grid4, 100, SpringLayout.NORTH, panel_chat);
        contentLayout.putConstraint(SpringLayout.WEST, panel_grid4, 608, SpringLayout.WEST, panel_chat);

        add(panel_chat);
        revalidate();
    }

    /*private boolean toggleBanUser(int userId, boolean isBanned) {
        try {
            Connection connection = DriverManager.getConnection(url, login, passwd);
            String updateQuery = "UPDATE users SET banned = ? WHERE id = ?";
            PreparedStatement updateStatement = connection.prepareStatement(updateQuery);
            updateStatement.setBoolean(1, !isBanned);
            updateStatement.setInt(2, userId);
            int updatedRows = updateStatement.executeUpdate();

            return updatedRows > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }*/
}

