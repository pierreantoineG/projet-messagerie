package Controller;

import Dao.UserDaoImpl;
import Model.*;
import View.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;
import java.net.Socket;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ChatControl extends IOException {
    private ChatView chatView;
    private LoginView loginView;
    private Client clientUser;
    private Message message;
    private static Socket client;
    private static PrintWriter out;
    private static BufferedReader in;
    private static String addIp = "192.168.1.14";
    private static int numPort = 1000;
    static File font = new File("Font/Urbanist (font)/static/Urbanist-Medium.ttf");
    static Font urbanist;

    static {
        try {
            urbanist = Font.createFont(Font.TRUETYPE_FONT, font);
        } catch (FontFormatException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    public ChatControl(ChatView chatView, Client client){
        this.chatView = chatView;
        clientUser = client;
    }

    public void initializeChatView(String cName){
        JTextArea message_field = chatView.getMessage_field();
        message_field.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e){
                message_field.setText("");
            }
        });

        //Bouton send
        JButton button_send = chatView.getButton_send();
        button_send.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                startWriting(cName);
            }
        });
        button_send.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                button_send.setBackground(UIManager.getColor("Button.background"));// Rétablir la couleur de fond par défaut du bouton
            }
        });

        //Bouton chat
        JButton button_chat = chatView.getButton_chat();
        UseMouse1(button_chat);

        //Bouton user
        JButton button_users = chatView.getButton_users();
        UseMouse1(button_users);
        button_users.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String role = UserDaoImpl.getRole(clientUser.getUsername());
                    if(role.equals("Administrator")){
                        /*UsersView usersView = new UsersView();
                        UsersControl usersControl = new UsersControl(usersView, clientUser);
                        usersControl.initializeUsersView(clientUser.getUsername());*/
                        UsersAdminView usersAdminView = null;
                        usersAdminView = new UsersAdminView();
                        usersAdminView.setVisible(true);

                    }
                    else if(role.equals("Moderator")){
                        /*UsersModeratorView usersModeratorView = new UsersModeratorView();
                        UsersModeratorControl usersModeratorControl = new UsersModeratorControl(usersModeratorView, clientUser);
                        usersModeratorControl.initializeUsersView(clientUser.getUsername());*/
                        UsersModeratorView usersModeratorView = null;
                        usersModeratorView = new UsersModeratorView();
                        usersModeratorView.setVisible(true);
                    }
                    else if(role.equals("User")){
                        /*UsersView usersView = new UsersView();
                        UsersControl usersControl = new UsersControl(usersView, clientUser);
                        usersControl.initializeUsersView(clientUser.getUsername());*/
                        UsersView usersView = null;
                        usersView = new UsersView();
                        usersView.setVisible(true);
                    }
                } catch (SQLException | IOException | FontFormatException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        //Bouton settings
        JButton button_settings = chatView.getButton_settings();
        UseMouse1(button_settings);
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

        //Bouton log out
        JButton button_logout = chatView.getButton_logout();
        UseMouse1(button_logout);
        button_logout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                chatView.setVisible(false);
                LoginControl loginControl = new LoginControl(new LoginView());
                loginControl.initialize();
            }
        });

        //Bouton reporting
        JButton button_reporting = chatView.getButton_reporting();
        UseMouse1(button_reporting);
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
        });

        chatView.setVisible(true);

        startClient();
        startReading(cName);
    }

    public static void startClient() {
        try {
            client = new Socket(addIp, numPort);
            out = new PrintWriter(client.getOutputStream());
            in = new BufferedReader(new InputStreamReader(client.getInputStream()));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void startReading(String cName) {
        Runnable r1 = () -> {
            try {
                while (true) {
                    String msg = in.readLine();
                    if (!msg.isEmpty()) {
                        SwingUtilities.invokeLater(() -> {
                            String[] parts = msg.split(" : ");
                            if (!cName.equals(parts[0])) {
                                JPanel panel = formatLabel(msg, false); // pass false to indicate that this message is not from the user
                                chatView.getChatArea().setCaretPosition(chatView.getChatArea().getDocument().getLength());
                                chatView.getChatArea().add(panel);
                                chatView.getChatArea().revalidate();
                                chatView.getChatArea().repaint();
                            }
                        });
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        };

        new Thread(r1).start();
        chatView.getScrollPane_chat().setViewportView(chatView.getChatArea());
    }

    public void startWriting(String cName) {

        try {
            String msg = chatView.getMessage_field().getText();
            if (!msg.isEmpty()) {
                if (chatView.getChatArea().getText().isEmpty()) {
                    JPanel messagePanel = formatLabel(cName + " : " + msg, true);
                    chatView.getChatArea().setLayout(new BoxLayout(chatView.getChatArea(), BoxLayout.Y_AXIS));
                    chatView.getChatArea().add(messagePanel);
                    UserDaoImpl.saveMessage(cName,msg);
                    UserDaoImpl.checkAndBanUser(cName,msg);
                    chatView.getChatArea().add(Box.createRigidArea(new Dimension(0, 20))); // espace entre les messages
                    chatView.getChatArea().revalidate();
                    chatView.getChatArea().repaint();
                    out.println(cName + " : " + msg);
                    out.flush();
                    chatView.getMessage_field().setText("Send a message");
                    chatView.getScrollPane_chat().setViewportView(chatView.getChatArea());

                }
                else {
                    JPanel messagePanel = formatLabel(cName + " : " + msg, true);
                    chatView.getChatArea().setLayout(new BoxLayout(chatView.getChatArea(), BoxLayout.Y_AXIS));
                    chatView.getChatArea().add(messagePanel);
                    chatView.getChatArea().add(Box.createRigidArea(new Dimension(0, 20))); // espace entre les messages
                    chatView.getChatArea().setBackground(Color.WHITE);
                    chatView.getChatArea().revalidate();
                    chatView.getChatArea().repaint();
                    out.println(cName + " : " + msg);
                    out.flush();
                    chatView.getMessage_field().setText("Send a message");
                    chatView.getScrollPane_chat().setViewportView(chatView.getChatArea());

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


    public void UseMouse1(JButton button) {
        button.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                button.setForeground(Color.WHITE);
            }
            public void mouseExited(MouseEvent e) {
                button.setForeground(Color.BLACK);
            }
        });
    }

    public void afficher() throws SQLException {
        chatView.displayUserInfo(UserDaoImpl.getFirstName(clientUser.getUsername()), UserDaoImpl.getLastName(clientUser.getUsername()), clientUser.getUsername(), String.valueOf(UserDaoImpl.getLastTimeConnection(clientUser.getUsername())), String.valueOf(UserDaoImpl.countUserMessages(clientUser.getUsername())), String.valueOf(UserDaoImpl.getRole(clientUser.getUsername())));
    }
}
