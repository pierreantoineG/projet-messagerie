package Dao;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.*;
import java.util.Vector;

public class Server extends JFrame implements Runnable{

    private static String url = "jdbc:sqlserver://projetmessagerie.database.windows.net:1433;database=projet_messagerie;user=pgloulou@projetmessagerie;password={your_password_here};encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;";
    private static String login = "pgloulou";
    private static String  passwd = "Malouise17";
    private ServerSocket serverSocket;

    //private Dao.UserDao userDao;

    private JPanel contentPane;
    private JTextField port;
    static int pNo;
    static Socket socket;
    static File font = new File("Font/Urbanist (font)/static/Urbanist-Medium.ttf");
    static Font urbanist;

    static {
        try {
            urbanist = Font.createFont(Font.TRUETYPE_FONT, font);
        } catch (FontFormatException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Vector clients = new Vector<>();

    public Server(Socket socket) {
        try {
            Server.socket = socket;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Server() {
        this.url = "jdbc:sqlserver://projetmessagerie.database.windows.net:1433;database=projet_messagerie;user=pgloulou@projetmessagerie;password={your_password_here};encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;";
        this.login = "pgloulou";
        this.passwd = "Malouise17";


        setLocationRelativeTo(null);
        setType(Window.Type.POPUP);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);

        contentPane = new JPanel() {
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                //Model.Chat
                g.setColor(Color.WHITE);
                g.fillRoundRect(250, 270, 320, 45, 30, 30);
                g.setColor(new Color(0, 245, 212));
                g.fillRoundRect(312, 370, 200, 42, 30, 30);

            }
        };
        contentPane.setLayout(new SpringLayout());
        contentPane.setBackground(new Color(0, 187, 249));

        //Image logo
        ImageIcon logo_titre = new ImageIcon("Icons/LogoTexte.png");
        JLabel lbl_logo = new JLabel(new ImageIcon(logo_titre.getImage()));

        //Texte 1
        JLabel lbl = new JLabel("Enter a Port No. to start the server");
        lbl.setFont(urbanist.deriveFont(Font.PLAIN, 18));
        lbl.setHorizontalAlignment(SwingConstants.CENTER);

        //Texte 2
        JLabel lblPortNo = new JLabel("Port No. :");
        lblPortNo.setFont(urbanist.deriveFont(Font.BOLD, 18));
        lblPortNo.setHorizontalAlignment(SwingConstants.CENTER);

        //JTextField 1
        port = new JTextField();
        port.setFont(urbanist.deriveFont(Font.PLAIN, 16));
        port.setColumns(10);
        port.setBorder(null);

        //Texte 3
        JLabel v2 = new JLabel("*");
        v2.setForeground(Color.RED);
        v2.setFont(urbanist.deriveFont(Font.ITALIC, 12));
        v2.setHorizontalAlignment(SwingConstants.CENTER);

        //Bouton Start
        JButton button = new JButton("START");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String p = port.getText();

                if (p.isEmpty()) {
                    v2.setText("This field is required");
                } else {
                    int port1 = Integer.parseInt(p);
                    pNo = port1;
                    dispose();

                    ServerSocket serverSocket;
                    try {
                        serverSocket = new ServerSocket(port1);
                        JOptionPane.showMessageDialog(null, "SERVER STARTED ...");
                        while (true) {
                            Socket socket = serverSocket.accept();

                            /*
                            // Création du rectangle blanc pour le nouveau client
                            JPanel panel = new JPanel();
                            panel.setBackground(Color.WHITE);
                            panel.setPreferredSize(new Dimension(200, 50));
                            JLabel label = new JLabel("New client connected.");
                            label.setFont(new Font("Arial", Font.BOLD, 16));
                            panel.add(label);
                            Model.Client.chatArea.add(panel);
                            Model.Client.chatArea.add(Box.createRigidArea(new Dimension(0, 20)));*/


                            Server server = new Server(socket);
                            Thread thread = new Thread(server);
                            thread.start();
                        }
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        });
        button.setBackground(new Color(0, 0, 0, 0));
        button.setBorder(null);
        button.setOpaque(false);
        button.setContentAreaFilled(false);
        button.setFont(urbanist.deriveFont(Font.BOLD, 22));

        contentPane.add(lbl_logo);
        contentPane.add(lbl);
        contentPane.add(lblPortNo);
        contentPane.add(port);
        contentPane.add(v2);
        contentPane.add(button);

        //TODO Définir les contraintes pour chaque composant pour le contentPanel uniquement
        SpringLayout contentLayout = (SpringLayout) contentPane.getLayout();

        //Logo
        contentLayout.putConstraint(SpringLayout.NORTH, lbl_logo, 30, SpringLayout.NORTH, contentPane);
        contentLayout.putConstraint(SpringLayout.WEST, lbl_logo, 250, SpringLayout.WEST, contentPane);

        //Texte 1
        contentLayout.putConstraint(SpringLayout.NORTH, lbl, 230, SpringLayout.NORTH, contentPane);
        contentLayout.putConstraint(SpringLayout.WEST, lbl, 275, SpringLayout.WEST, contentPane);

        contentLayout.putConstraint(SpringLayout.NORTH, lblPortNo, 280, SpringLayout.NORTH, contentPane);
        contentLayout.putConstraint(SpringLayout.WEST, lblPortNo, 140, SpringLayout.WEST, contentPane);

        contentLayout.putConstraint(SpringLayout.NORTH, port, 283, SpringLayout.NORTH, contentPane);
        contentLayout.putConstraint(SpringLayout.WEST, port, 260, SpringLayout.WEST, contentPane);

        contentLayout.putConstraint(SpringLayout.NORTH, v2, 330, SpringLayout.NORTH, contentPane);
        contentLayout.putConstraint(SpringLayout.WEST, v2, 405, SpringLayout.WEST, contentPane);

        contentLayout.putConstraint(SpringLayout.NORTH, button, 378, SpringLayout.NORTH, contentPane);
        contentLayout.putConstraint(SpringLayout.WEST, button, 375, SpringLayout.WEST, contentPane);

        add(contentPane);
    }

    public String getUrl() {
        return url;
    }

    public String getLogin() {
        return login;
    }

    public String getPasswd() {
        return passwd;
    }


    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

        Connection connection = DriverManager.getConnection(url, login, passwd);

        System.out.println("Good connection!");
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    Server frame = new Server();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void run() {// Méthode d'exécution pour chaque thread de client qui se connecte au serveur
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream())); //lire les données
            PrintWriter writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream())); //envoyer les données

            clients.add(writer);

            while (true) { //écoute continuellement les données envoyées
                String data = reader.readLine().trim();

                for (int i = 0; i < clients.size(); i++) {
                    try {
                        PrintWriter out = (PrintWriter) clients.get(i);
                        out.write(data);
                        out.write("\r\n");
                        out.flush();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
