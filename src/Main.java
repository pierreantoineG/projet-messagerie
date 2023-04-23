import Accueil.fenetre;
import View.*;
import Controller.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Main {

    public static JPanel createRoundedPanel(String out, Color bgColor, int radius) {
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g;
                g2.setColor(bgColor);
                g2.fill(new RoundRectangle2D.Double(0, 0, getWidth() - 1, getHeight() - 1, radius, radius));
            }
        };
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel output = formatLabel(out);
        output.setBorder(new EmptyBorder(15, 15, 15, 50));
        panel.add(output);

        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");

        JLabel time = new JLabel();
        time.setText(sdf.format(cal.getTime()));
        panel.add(time);

        return panel;
    }
    private static JLabel formatLabel(String out) {
        JLabel output = new JLabel("<html><p style=\"width: 150px\">" + out + "</p></html>");
        output.setFont(new Font("Tahoma", Font.PLAIN, 16));
        output.setBackground(new Color(37, 211, 102));
        output.setOpaque(true);
        return output;
    }
    private static void displayAllMessages(JPanel messagefinal) {
        try {
            Server server = new Server();
            // Établir la connexion avec la base de données
            Connection connection = DriverManager.getConnection(server.getUrl(), server.getLogin(), server.getPasswd());
            // Préparer la requête SQL pour récupérer tous les messages
            String query = "SELECT users.username, MESSAGE.content "
                    + "FROM users "
                    + "JOIN message ON users.id = MESSAGE.user_id";

            // Exécuter la requête SQL
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            // Parcourir le résultat de la requête et ajouter les messages dans des étiquettes
            while (resultSet.next()) {
                String username = resultSet.getString("username");
                String content = resultSet.getString("content");
                JPanel messagesPanel;
                // Créer un JPanel pour contenir les messages
                messagesPanel = new JPanel();
                messagesPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

                // Créer un nouveau JPanel pour chaque message
                JPanel messagePanel = new JPanel();
                messagePanel.setLayout(new BoxLayout(messagePanel, BoxLayout.Y_AXIS));
                messagePanel.setPreferredSize(new Dimension(200, 100));
                messagePanel.setBorder(BorderFactory.createEtchedBorder());
                // Ajouter le nom d'utilisateur au messagePanel
                JLabel usernameLabel = new JLabel("Nom d'utilisateur: " + username);
                usernameLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
                messagePanel.add(usernameLabel);

                // Ajouter le contenu du message au messagePanel
                JPanel contentLabel = createRoundedPanel(content, new Color(37, 211, 102),20);
                messagePanel.add(contentLabel);

                // Ajouter le messagePanel à allMessagesPanel
                messagesPanel.add(messagePanel);

                // Ajouter un espace de 10 pixels entre chaque message
                messagesPanel.add(Box.createRigidArea(new Dimension(0, 10)));

                // Ajouter le panel contenant tous les messages à un JScrollPane
                JScrollPane scrollPane = new JScrollPane(messagesPanel);
                scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
                scrollPane.setPreferredSize(new Dimension(400, 300));
                messagefinal.add(scrollPane);

            }
            // Fermer la connexion avec la base de données

            // Ajouter le JScrollPane au messagefinal passé en paramètre

            resultSet.close();
            statement.close();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) throws ClassNotFoundException {
        System.out.println("Good good");
        fenetre fenetre = new fenetre();
        /*
        // Créer un JPanel
        JPanel myPanel = new JPanel();


        // Créer une fenêtre JFrame et y ajouter le JPanel principal
        JFrame frame = new JFrame("Mon JPanel");
        frame.getContentPane().add(myPanel);
        // Appeler la méthode displayAllMessages
        displayAllMessages(myPanel);

        // Définir la taille de la fenêtre et l'afficher
        frame.pack();
        frame.setSize(500, 400);
        frame.setVisible(true);
*/
    }
}
