package Model;

import View.*;
import View.ClientHandler;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

public class Client extends JFrame {
    private Integer id;
    private String first_name;
    private String last_name;
    private String username;
    private String password;
    private String email;
    enum StateUser { MISSING, CONNECTED, DISCONNECTED}
    private StateUser state;//absent connecté deconnecté
    enum TypeUser {NORMAL, MODERATOR, ADMINISTRATOR;}
    private TypeUser typeUser; // Admin Moderateur Normal
    private LocalDateTime last_connection_time;
    private static Socket client;
    private static PrintWriter out;
    private static BufferedReader in;
    static File font = new File("Font/Urbanist (font)/static/Urbanist-Medium.ttf");
    static Font urbanist;

    static {
        try {
            urbanist = Font.createFont(Font.TRUETYPE_FONT, font);
        } catch (FontFormatException | IOException e) {
            throw new RuntimeException(e);
        }
    }
    static JScrollPane scrollPane_chat;
    private static JTextArea chatArea;
    private JTextArea message_field;
    private JPanel panel_chat;


    public Client(){

        setSize(1000, 680);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        panel_chat = new JPanel() {
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                //Chat
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
                //Ajouter à chaque fois qu'un membre se connecte --> faire un nouveau rectangle blanc + mettre son nom

                //Dessiner le carrée pour le JTextField
                g.setColor(new Color(217, 217, 217));
                g.drawRoundRect(167, 540, 485, 75, 30, 30);

                //Dessiner le carrée pour View.fenetre actuelle
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

        //Texte 1
        JLabel lbl_conv = new JLabel("#Server 1");
        lbl_conv.setFont(urbanist.deriveFont(Font.BOLD, 30));
        lbl_conv.setHorizontalAlignment(SwingConstants.CENTER);

        //Texte 2
        JLabel lbl_users_conn = new JLabel("Members connected ");
        lbl_users_conn.setFont(urbanist.deriveFont(Font.BOLD, 25));
        lbl_users_conn.setHorizontalAlignment(SwingConstants.CENTER);

        //Texte 3
        JLabel lbl_first_name = new JLabel("First name ");
        lbl_first_name.setFont(urbanist.deriveFont(Font.PLAIN, 13));
        lbl_first_name.setHorizontalAlignment(SwingConstants.CENTER);

        //Texte 4
        JLabel lbl_last_name = new JLabel("Last name ");
        lbl_last_name.setFont(urbanist.deriveFont(Font.PLAIN, 13));
        lbl_last_name.setHorizontalAlignment(SwingConstants.CENTER);

        //Texte 5
        JLabel lbl_handle = new JLabel("Handle ");
        lbl_handle.setFont(urbanist.deriveFont(Font.PLAIN, 13));
        lbl_handle.setHorizontalAlignment(SwingConstants.CENTER);

        //Texte 6
        JLabel lbl_active = new JLabel("Active since  ");
        lbl_active.setFont(urbanist.deriveFont(Font.PLAIN, 13));
        lbl_active.setHorizontalAlignment(SwingConstants.CENTER);

        //Texte 7
        JLabel lbl_msg = new JLabel("Messages ");
        lbl_msg.setFont(urbanist.deriveFont(Font.PLAIN, 13));
        lbl_msg.setHorizontalAlignment(SwingConstants.CENTER);

        //Texte 8
        JLabel lbl_statut = new JLabel("Status ");
        lbl_statut.setFont(urbanist.deriveFont(Font.PLAIN, 13));
        lbl_statut.setHorizontalAlignment(SwingConstants.CENTER);

        //JTextFiel pour entrer le message
        message_field = new JTextArea("Send a message");
        message_field.setFont(urbanist.deriveFont(Font.PLAIN, 15));
        message_field.setPreferredSize(new Dimension(400, 65));
        message_field.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                message_field.setText("");
            }
        });
        message_field.setLineWrap(true);
        message_field.setWrapStyleWord(true);

        //TODO Faire 5 autres JTextField pour afficher info dans la bulle (coordonnees ok)
        JTextField handle = new JTextField();
        handle.setFont(urbanist.deriveFont(Font.PLAIN, 13));
        handle.setBorder(null);
        handle.setText(ClientHandler.getcName());

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
        JButton button_send = new JButton("");
        button_send.setOpaque(false);
        button_send.setContentAreaFilled(false);
        button_send.setBackground(new Color(0, 0, 0, 0));
        button_send.setPreferredSize(new Dimension(25, 25));
        button_send.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startWriting();

            }
        });
        button_send.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                button_send.setBackground(UIManager.getColor("Button.background"));// Rétablir la couleur de fond par défaut du bouton
            }
        });

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
                //Mettre la couleur du bouton en bleu pour dire qu'on es dessus
            }

            /*public void mouseEntered(MouseEvent e) {
                //g.setColor(new Color(0, 245, 212));
                //g.fillRoundRect(5, 100, 140, 50, 30, 30);
                button_chat.setBackground(new Color(0, 245, 212));
                button_chat.setOpaque(true);
                button_chat.setBorder(null); // Changer la couleur de fond du bouton en rouge
            }*/

            public void mouseExited(MouseEvent e) {
                button_chat.setBackground(UIManager.getColor("Button.background"));// Rétablir la couleur de fond par défaut du bouton
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
            @Override
            public void mousePressed(MouseEvent e) {
                //Afficher page chat
                //Mettre la couleur du bouton en bleu pour dire qu'on es dessus
            }

            public void mouseEntered(MouseEvent e) {
                button_users.setBackground(new Color(0, 245, 212));
                button_users.setOpaque(true);
                button_users.setBorder(null);
                button_users.setContentAreaFilled(false);

            }

            public void mouseExited(MouseEvent e) {
                button_users.setBackground(UIManager.getColor("Button.background"));// Rétablir la couleur de fond par défaut du bouton
                button_users.setOpaque(true);
                button_users.setBorder(null);
                button_users.setContentAreaFilled(false);
            }
        });

        //Bouton settings
        JButton button_settings = new JButton("Settings");
        button_settings.setFont(urbanist.deriveFont(Font.BOLD, 19));
        button_settings.setBackground(new Color(0, 0, 0, 0));
        button_settings.setBorder(null);
        button_settings.setOpaque(false);
        button_settings.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                //Afficher page chat
                //Mettre la couleur du bouton en bleu pour dire qu'on es dessus
            }

            public void mouseEntered(MouseEvent e) {
                button_settings.setBackground(new Color(0, 245, 212));
                button_settings.setOpaque(true);
                button_settings.setBorder(null); // Changer la couleur de fond du bouton en rouge
            }

            public void mouseExited(MouseEvent e) {
                button_settings.setBackground(UIManager.getColor("Button.background"));// Rétablir la couleur de fond par défaut du bouton
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
                //Afficher page chat
                //Mettre la couleur du bouton en bleu pour dire qu'on es dessus
            }

            public void mouseEntered(MouseEvent e) {
                button_logout.setBackground(new Color(0, 245, 212));
                button_logout.setOpaque(true);
                button_logout.setBorder(null); // Changer la couleur de fond du bouton en rouge
            }

            public void mouseExited(MouseEvent e) {
                button_logout.setBackground(UIManager.getColor("Button.background"));// Rétablir la couleur de fond par défaut du bouton
            }
        });

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
        panel_chat.add(handle);
        panel_chat.add(button_chat);
        panel_chat.add(button_users);
        panel_chat.add(button_settings);
        panel_chat.add(button_logout);
        panel_chat.add(button_send);
        panel_chat.add(lbl_icon_chat);
        panel_chat.add(lbl_icon_users);
        panel_chat.add(lbl_icon_settings);
        panel_chat.add(lbl_icon_logout);
        panel_chat.add(lbl_icon_send);

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

        //Texte 3
        contentLayout.putConstraint(SpringLayout.NORTH, lbl_first_name, 98, SpringLayout.NORTH, panel_chat);
        contentLayout.putConstraint(SpringLayout.WEST, lbl_first_name, 688, SpringLayout.WEST, panel_chat);

        //Texte 4
        contentLayout.putConstraint(SpringLayout.NORTH, lbl_last_name, 138, SpringLayout.NORTH, panel_chat);
        contentLayout.putConstraint(SpringLayout.WEST, lbl_last_name, 688, SpringLayout.WEST, panel_chat);

        //Texte 5
        contentLayout.putConstraint(SpringLayout.NORTH, lbl_handle, 178, SpringLayout.NORTH, panel_chat);
        contentLayout.putConstraint(SpringLayout.WEST, lbl_handle, 697, SpringLayout.WEST, panel_chat);

        //TODO FAIRE COMME LA LIGNE D EN DESSOUS POUR RECUPERER LES INFOS SOIT DE LA BDD SOIT DU REGISTER
        //fname
        contentLayout.putConstraint(SpringLayout.NORTH, handle, 98, SpringLayout.NORTH, panel_chat);
        contentLayout.putConstraint(SpringLayout.WEST, handle, 80, SpringLayout.WEST, lbl_first_name);
        //lname
        contentLayout.putConstraint(SpringLayout.NORTH, handle, 138, SpringLayout.NORTH, panel_chat);
        contentLayout.putConstraint(SpringLayout.WEST, handle, 80, SpringLayout.WEST, lbl_last_name);
        //Handle
        contentLayout.putConstraint(SpringLayout.NORTH, handle, 178, SpringLayout.NORTH, panel_chat);
        contentLayout.putConstraint(SpringLayout.WEST, handle, 70, SpringLayout.WEST, lbl_handle);
        //active since
        contentLayout.putConstraint(SpringLayout.NORTH, handle, 98, SpringLayout.NORTH, panel_chat);
        contentLayout.putConstraint(SpringLayout.WEST, handle, 87, SpringLayout.WEST, lbl_active);
        //messages
        contentLayout.putConstraint(SpringLayout.NORTH, handle, 138, SpringLayout.NORTH, panel_chat);
        contentLayout.putConstraint(SpringLayout.WEST, handle, 85, SpringLayout.WEST, lbl_msg);
        //statut
        contentLayout.putConstraint(SpringLayout.NORTH, handle, 178, SpringLayout.NORTH, panel_chat);
        contentLayout.putConstraint(SpringLayout.WEST, handle, 70, SpringLayout.WEST, lbl_statut);

        //Texte 6
        contentLayout.putConstraint(SpringLayout.NORTH, lbl_active, 98, SpringLayout.NORTH, panel_chat);
        contentLayout.putConstraint(SpringLayout.WEST, lbl_active, 835, SpringLayout.WEST, panel_chat);

        //Texte 7
        contentLayout.putConstraint(SpringLayout.NORTH, lbl_msg, 138, SpringLayout.NORTH, panel_chat);
        contentLayout.putConstraint(SpringLayout.WEST, lbl_msg, 840, SpringLayout.WEST, panel_chat);

        //Texte 8
        contentLayout.putConstraint(SpringLayout.NORTH, lbl_statut, 178, SpringLayout.NORTH, panel_chat);
        contentLayout.putConstraint(SpringLayout.WEST, lbl_statut, 850, SpringLayout.WEST, panel_chat);

        add(panel_chat);

        startClient();
        startReading();
    }

    public static void startClient() {
        try {
            client = new Socket(ClientHandler.getIp(), ClientHandler.getCpNo());
            out = new PrintWriter(client.getOutputStream());
            in = new BufferedReader(new InputStreamReader(client.getInputStream()));


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Integer getId() { return id; }

    public String getLast_name() { return last_name; }

    public String getFirst_name() { return first_name; }

    public String getEmail() { return email; }

    public String getPassword() { return password; }

    public String getUsername() { return username; }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    /*@Override
    public String toString() {
        return "User{" +
                "first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                '}';
    }*/

    public void startReading() {
        Runnable r1 = () -> {
            try {
                while (true) {
                    String msg = in.readLine();
                    if (!msg.isEmpty()) {
                        SwingUtilities.invokeLater(() -> {
                            String[] parts = msg.split(" : ");
                            if (!ClientHandler.getcName().equals(parts[0])) {
                                JPanel panel = formatLabel(msg, false); // pass false to indicate that this message is not from the user
                                chatArea.setCaretPosition(chatArea.getDocument().getLength());
                                chatArea.add(panel);
                                chatArea.revalidate();
                                chatArea.repaint();
                            }
                        });
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        };

        new Thread(r1).start();
        scrollPane_chat.setViewportView(chatArea);
    }

    public void startWriting() {

        try {
            String msg = message_field.getText();
            if (!msg.isEmpty()) {
                if (chatArea.getText().isEmpty()) {
                    JPanel messagePanel = formatLabel(ClientHandler.getcName() + " : " + msg, true);
                    chatArea.setLayout(new BoxLayout(chatArea, BoxLayout.Y_AXIS));
                    chatArea.add(messagePanel);
                    chatArea.add(Box.createRigidArea(new Dimension(0, 20))); // espace entre les messages
                    chatArea.revalidate();
                    chatArea.repaint();
                    out.println(ClientHandler.getcName() + " : " + msg);
                    out.flush();
                    message_field.setText("Send a message");
                    scrollPane_chat.setViewportView(chatArea);

                }
                else {
                    JPanel messagePanel = formatLabel(ClientHandler.getcName() + " : " + msg, true);
                    chatArea.setLayout(new BoxLayout(chatArea, BoxLayout.Y_AXIS));
                    chatArea.add(messagePanel);
                    chatArea.add(Box.createRigidArea(new Dimension(0, 20))); // espace entre les messages
                    chatArea.setBackground(Color.WHITE);
                    chatArea.revalidate();
                    chatArea.repaint();
                    out.println(ClientHandler.getcName() + " : " + msg);
                    out.flush();
                    message_field.setText("Send a message");
                    scrollPane_chat.setViewportView(chatArea);

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static JPanel formatLabel(String out, boolean isCurrentUser) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel output = new JLabel(out);
        output.setFont(urbanist.deriveFont(Font.PLAIN, 15));
        output.setBackground((isCurrentUser ? new Color(169, 255, 240) : Color.WHITE));
        output.setOpaque(true);
        output.setBorder(new EmptyBorder(15, 15, 15, 50));

        panel.add(output);

        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        String dateString = formatter.format(date);
        JLabel dateLabel = new JLabel(dateString);
        dateLabel.setFont(urbanist.deriveFont(Font.PLAIN, 10));

        panel.add(dateLabel);

        JPanel messagePanel = new JPanel(new BorderLayout());
        messagePanel.add(panel, isCurrentUser ? BorderLayout.LINE_END : BorderLayout.LINE_START); // align message to the right for messages from the user


        return messagePanel;
    }
}
//"<html><p style=\"width: 150px\">" + out + "</p></html>"





