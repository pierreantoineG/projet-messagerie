//package View;
//
//import Model.Client;
//
//import java.io.*;
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//
//public class ClientHandler extends JFrame {
//    private JPanel contentPane;
//    private JTextField name;
//    private JTextField port;
//    private JTextField i;
//    static String cName;
//    static String ip = "192.168.1.40";
//    static int cpNo = 1000;
//    static File font = new File("Font/Urbanist (font)/static/Urbanist-Medium.ttf");
//    static Font urbanist;
//
//    static {
//        try {
//            urbanist = Font.createFont(Font.TRUETYPE_FONT, font);
//        } catch (FontFormatException | IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    public static void main(String[] args) {
//        EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                try {
//                    ClientHandler frame = new ClientHandler();
//                    frame.setVisible(true);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//    }
//
//    public ClientHandler () {
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        setLocationRelativeTo(null);
//        setSize(800, 600);
//
//        contentPane = new JPanel() {
//            public void paintComponent(Graphics g) {
//                super.paintComponent(g);
//                //Model.Chat
//                g.setColor(Color.WHITE);
//                g.fillRoundRect(250,170, 320, 45, 30, 30);
//                g.setColor(Color.WHITE);
//                g.fillRoundRect(250, 260, 320, 45, 30, 30);
//                g.setColor(Color.WHITE);
//                g.fillRoundRect(250, 350, 320, 45, 30, 30);
//
//                g.setColor(new Color(0, 245, 212));
//                g.fillRoundRect(312, 430, 200, 42, 30, 30);
//
//            }
//        };
//        contentPane.setLayout(new SpringLayout());
//        contentPane.setBackground(new Color(0, 187, 249));
//
//
//        //Image logo
//        ImageIcon logo_titre = new ImageIcon("Icons/LogoTexte.png");
//        JLabel lbl_logo = new JLabel(new ImageIcon(logo_titre.getImage()));
//
//        //Texte 1
//        JLabel lblChatAs = new JLabel("Model.Chat as :");
//        lblChatAs.setFont(urbanist.deriveFont(Font.BOLD, 18));
//        lblChatAs.setHorizontalAlignment(SwingConstants.CENTER);
//
//        //JTextField 1
//        name = new JTextField();
//        name.setBorder(null);
//        name.setFont(urbanist.deriveFont(Font.PLAIN, 16));
//        name.setColumns(10);
//
//        JLabel v1 = new JLabel("*");
//        v1.setForeground(new Color(255, 0, 0));
//        v1.setFont(urbanist.deriveFont(Font.ITALIC, 12));
//        v1.setHorizontalAlignment(SwingConstants.CENTER);
//
//        //Texte 2
//        JLabel lblPortNo = new JLabel("Port No. :");
//        lblPortNo.setFont(urbanist.deriveFont(Font.BOLD, 18));
//        lblPortNo.setHorizontalAlignment(SwingConstants.CENTER);
//
//        //JTextField 2
//        port = new JTextField();
//        port.setColumns(10);
//        port.setFont(urbanist.deriveFont(Font.PLAIN, 16));
//        port.setBorder(null);
//
//        //Texte 3
//        JLabel v2 = new JLabel("*");
//        v2.setForeground(Color.RED);
//        v2.setFont(urbanist.deriveFont(Font.ITALIC, 12));
//        v2.setHorizontalAlignment(SwingConstants.CENTER);
//
//        //Texte 4
//        JLabel lblIpAdress = new JLabel("IP Adress :");
//        lblIpAdress.setFont(urbanist.deriveFont(Font.BOLD, 18));
//        lblIpAdress.setHorizontalAlignment(SwingConstants.CENTER);
//
//        //JTextField 3
//        i = new JTextField();
//        i.setColumns(10);
//        i.setFont(urbanist.deriveFont(Font.PLAIN, 16));
//        i.setBorder(null);
//
//        //Texte 4
//        JLabel v3 = new JLabel("*");
//        v3.setFont(urbanist.deriveFont(Font.ITALIC, 12));
//        v3.setForeground(Color.RED);
//        v3.setHorizontalAlignment(SwingConstants.CENTER);
//
//        //Bouton Start
//        JButton button = new JButton("START");
//        button.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                String n = name.getText();
//                String p = port.getText();
//                String ipadress = i.getText();
//
//                if (n.isEmpty()) {
//                    v1.setText("This field is required");
//                } else if (p.isEmpty()) {
//                    v2.setText("This field is required");
//                } else if (ipadress.isEmpty()) {
//                    v3.setText("This field is required");
//                } else {
//                    int po = Integer.parseInt(p);
//                    cName = n;
//                    cpNo = po;
//                    ip = ipadress;
//
//                    Client c = null;
//                    c = new Client();
//                    dispose();
//                    c.setVisible(true);
//                }
//            }
//
//        });
//        button.setBackground(new Color(0, 0, 0, 0));
//        button.setBorder(null);
//        button.setOpaque(false);
//        button.setContentAreaFilled(false);
//        button.setFont(urbanist.deriveFont(Font.BOLD, 22));
//
//        contentPane.add(lbl_logo);
//        contentPane.add(lblChatAs);
//        contentPane.add(name);
//        contentPane.add(v1);
//        contentPane.add(lblPortNo);
//        contentPane.add(port);
//        contentPane.add(v2);
//        contentPane.add(lblIpAdress);
//        contentPane.add(i);
//        contentPane.add(v3);
//        contentPane.add(button);
//
//        //TODO Définir les contraintes pour chaque composant pour le contentPanel uniquement
//        SpringLayout contentLayout = (SpringLayout) contentPane.getLayout();
//
//        //Logo
//        contentLayout.putConstraint(SpringLayout.NORTH, lbl_logo, 30, SpringLayout.NORTH, contentPane);
//        contentLayout.putConstraint(SpringLayout.WEST, lbl_logo, 250, SpringLayout.WEST, contentPane);
//
//        //Texte 1
//        contentLayout.putConstraint(SpringLayout.NORTH, lblChatAs, 180, SpringLayout.NORTH, contentPane);
//        contentLayout.putConstraint(SpringLayout.WEST, lblChatAs, 140, SpringLayout.WEST, contentPane);
//
//        //JTextField 1
//        contentLayout.putConstraint(SpringLayout.NORTH, name, 183, SpringLayout.NORTH, contentPane);
//        contentLayout.putConstraint(SpringLayout.WEST, name, 260, SpringLayout.WEST, contentPane);
//
//        //Texte 2
//        contentLayout.putConstraint(SpringLayout.NORTH, lblPortNo, 270, SpringLayout.NORTH, contentPane);
//        contentLayout.putConstraint(SpringLayout.WEST, lblPortNo, 140, SpringLayout.WEST, contentPane);
//
//        //JTextField 2
//        contentLayout.putConstraint(SpringLayout.NORTH, port, 273, SpringLayout.NORTH, contentPane);
//        contentLayout.putConstraint(SpringLayout.WEST, port, 260, SpringLayout.WEST, contentPane);
//
//        //Texte 3
//        contentLayout.putConstraint(SpringLayout.NORTH, lblIpAdress, 360, SpringLayout.NORTH, contentPane);
//        contentLayout.putConstraint(SpringLayout.WEST, lblIpAdress, 140, SpringLayout.WEST, contentPane);
//
//        //JTextField 3
//        contentLayout.putConstraint(SpringLayout.NORTH, i, 363, SpringLayout.NORTH, contentPane);
//        contentLayout.putConstraint(SpringLayout.WEST, i, 260, SpringLayout.WEST, contentPane);
//
//        //Texte 4
//        contentLayout.putConstraint(SpringLayout.NORTH, v1, 220, SpringLayout.NORTH, contentPane);
//        contentLayout.putConstraint(SpringLayout.WEST, v1, 405, SpringLayout.WEST, contentPane);
//
//        //Texte 5
//        contentLayout.putConstraint(SpringLayout.NORTH, v2, 310, SpringLayout.NORTH, contentPane);
//        contentLayout.putConstraint(SpringLayout.WEST, v2, 405, SpringLayout.WEST, contentPane);
//
//        //Texte 6
//        contentLayout.putConstraint(SpringLayout.NORTH, v3, 400, SpringLayout.NORTH, contentPane);
//        contentLayout.putConstraint(SpringLayout.WEST, v3, 405, SpringLayout.WEST, contentPane);
//
//        //Bouton Start
//        contentLayout.putConstraint(SpringLayout.NORTH, button, 439, SpringLayout.NORTH, contentPane);
//        contentLayout.putConstraint(SpringLayout.WEST, button, 375, SpringLayout.WEST, contentPane);
//
//        add(contentPane);
//
//    }
//
//    public static String getcName(){
//        return cName;
//    }
//
//    public static String getIp(){
//        return ip;
//    }
//
//    public static int getCpNo(){
//        return cpNo;
//    }
//
//}
