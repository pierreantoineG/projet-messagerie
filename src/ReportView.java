import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.StandardBarPainter;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.io.*;
import javax.swing.*;
import java.sql.*;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import Dao.*;

public class ReportView {
    static File font = new File("Font/Urbanist (font)/static/Urbanist-Medium.ttf");
    static Font urbanist;

    static {
        try {
            urbanist = Font.createFont(Font.TRUETYPE_FONT, font);
        } catch (FontFormatException | IOException e) {
            throw new RuntimeException(e);
        }
    }
    public ReportView() {




        // Création des données pour le premier graphique (camembert)
        // DATA SUR LE  ROLE DES USERS
        DefaultPieDataset dataset = new DefaultPieDataset();
        dataset.setValue("Admin", UserDaoImpl.countUsersWithRoleAdmin());
        dataset.setValue("User", UserDaoImpl.countUsersWithRoleUser());
        dataset.setValue("Moderateur", UserDaoImpl.countUsersWithRoleModerateur());
        // Création du premier graphique (camembert)
        JFreeChart chart1 = ChartFactory.createPieChart (
                "Nombre des differents role des utilisateurs",
                dataset,
                true,
                true,
                false
        );
        PiePlot plot1 = (PiePlot) chart1.getPlot();
        plot1.setBackgroundPaint(new Color(255, 255, 255,250)); // Fond blanc
        plot1.setLabelFont(urbanist.deriveFont(Font.BOLD, 18)); // Police Arial, taille 12 pour les étiquettes
        plot1.setLabelLinkMargin(0.02); // Ajouter un petit espace entre les étiquettes et le bord du graphique
        plot1.setSectionPaint("Admin", new Color(176, 236, 255,250));
        plot1.setSectionPaint("Moderateur", new Color(59, 142, 144, 255));
        plot1.setSectionPaint("User", new Color(5, 39, 46,250));
        plot1.setLabelGenerator(new StandardPieSectionLabelGenerator("{0} : {1} ({2})", NumberFormat.getNumberInstance(), NumberFormat.getPercentInstance()));
        plot1.setLabelBackgroundPaint(new Color(255, 255, 255, 255)); // Couleur légèrement grise pour les étiquettes
        plot1.setLabelOutlinePaint(null);
        plot1.setLabelShadowPaint(null);
        plot1.setStartAngle(50);
        // Affichage du graphique
        ChartFrame frame1 = new ChartFrame("Nombre des differents role des utilisateurs", chart1);
        frame1.pack();


        // Création des données pour le deuxième graphique (à barres)
        // DATA SUR L ETAT DES USERS
        DefaultCategoryDataset dataset2 = new DefaultCategoryDataset();
        dataset2.addValue(UserDaoImpl.countAwaydUsers(), "Série 1", "away user");
        dataset2.addValue(UserDaoImpl.countDeconnectedUsers(), "Série 1", "deconnected user");
        dataset2.addValue(UserDaoImpl.countConnectedUsers(), "Série 1", "connected user");
        // Création du deuxième graphique (à barres)
        JFreeChart chart2 = ChartFactory.createBarChart(
                "Etat des utilisateurs",
                "Etat",
                "Number",
                dataset2,
                PlotOrientation.HORIZONTAL,
                false,
                true,
                false
        );
        chart2.setBackgroundPaint(new Color(250, 250, 250)); // gris clair
        CategoryPlot plot2 = (CategoryPlot) chart2.getPlot();
        plot2.setBackgroundPaint(new Color(250, 250, 250));
        BarRenderer renderer = (BarRenderer) plot2.getRenderer();
        renderer.setSeriesPaint(0, new Color(000, 187, 249));
        renderer.setMaximumBarWidth(0.5);
        plot2.setOutlinePaint(new Color(0x484747));
        plot2.setOutlineStroke(new BasicStroke(2.0f));
        renderer.setBarPainter(new StandardBarPainter());
        renderer.setShadowVisible(true);
        // Création d'une fenêtre pour le deuxième graphique
        ChartFrame frame2 = new ChartFrame("Etat des utilisateurs", chart2);
        frame2.pack();


        // Création du graphique
        // DATA SUR LES BANNED DES USERS
        DefaultCategoryDataset dataset3 = new DefaultCategoryDataset();
        dataset3.addValue(UserDaoImpl.countBanned(), "Utilisateurs bannis", "");
        dataset3.addValue(UserDaoImpl.countNoBanned(), "Utilisateurs non bannis", "");
        JFreeChart chart3 = ChartFactory.createBarChart(
                "Nombre d'utilisateurs bannis",
                "",
                "Nombre d'utilisateurs",
                dataset3,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );
        chart3.setBackgroundPaint(new Color(250, 250, 250)); // gris clair
        CategoryPlot plot3 = (CategoryPlot) chart3.getPlot();
        plot3.setBackgroundPaint(new Color(250, 250, 250));
        BarRenderer renderer2 = (BarRenderer) plot3.getRenderer();
        renderer2.setSeriesPaint(0, new Color(16, 45, 141));
        renderer2.setSeriesPaint(1, new Color(91, 122, 190));
        renderer2.setMaximumBarWidth(0.5);
        plot3.setOutlinePaint(new Color(0x484747));
        plot3.setOutlineStroke(new BasicStroke(2.0f));
        renderer2.setBarPainter(new StandardBarPainter());
        renderer2.setShadowVisible(true);
        ChartFrame frame3 = new ChartFrame("Etat des utilisateurs", chart3);
        frame3.pack();


        // Créer un graphique à barres
        // DATA SUR NB DE MESSAGES
        DefaultCategoryDataset dataset4 = new DefaultCategoryDataset();
        Map<String, Integer> messageCountByUser = UserDaoImpl.countMessagesSentByUser();
        // Ajouter chaque utilisateur et son nombre de messages envoyés dans le dataset
        for (Map.Entry<String, Integer> entry : messageCountByUser.entrySet()) {
            String username = entry.getKey();
            int messageCount = entry.getValue();
            dataset4.addValue(messageCount, "Nombre de messages envoyés", username);
        }
        JFreeChart chart = ChartFactory.createBarChart(
                "Nombre de messages envoyés par utilisateur",
                "Utilisateur",
                "Nombre de messages",
                dataset4,
                PlotOrientation.VERTICAL,
                false,
                true,
                false
        );
        // Personnaliser l'apparence du graphique

        chart.setBackgroundPaint(new Color(250, 250, 250)); // gris clair
        CategoryPlot plot = (CategoryPlot) chart.getPlot();
        plot.setBackgroundPaint(new Color(250, 250, 250));
        BarRenderer renderer4 = (BarRenderer) plot.getRenderer();
        renderer4.setSeriesPaint(0, new Color(0, 245, 212));
        renderer4.setMaximumBarWidth(0.5);
        renderer4.setBarPainter(new StandardBarPainter());
        renderer4.setShadowVisible(true);
        ChartFrame frame4 = new ChartFrame("Top utilisateur", chart);
        frame4.pack();
        // Trouver l'utilisateur ayant envoyé le plus de messages
        String topUser = "";
        int topMessageCount = 0;
        for (Map.Entry<String, Integer> entry : messageCountByUser.entrySet()) {
            String username = entry.getKey();
            int messageCount = entry.getValue();
            if (messageCount > topMessageCount) {
                topUser = username;
                topMessageCount = messageCount;
            }
        }
        // Ajouter un titre supplémentaire indiquant l'utilisateur ayant envoyé le plus de messages
        TextTitle subtitle = new TextTitle("L'utilisateur ayant envoyé le plus de messages est " + topUser + " (" + topMessageCount + " messages)");
        subtitle.setFont(new Font("Dialog", Font.BOLD, 14));
        chart.addSubtitle(subtitle);



// titre de la feuillen de reporting
        JPanel chartsPanel = new JPanel();
        JPanel titleBarPanel = new JPanel();
        titleBarPanel.setBackground(new Color(250, 250, 250));
        JLabel titleLabel = new JLabel("Reporting");
        titleLabel.setFont(new Font("urbanist", Font.BOLD, 18));
        titleBarPanel.add(titleLabel);
        titleBarPanel.setPreferredSize(new Dimension(1400,30));
        chartsPanel.add(titleBarPanel, BorderLayout.NORTH);

        // Création d'un conteneur pour les  graphiques
        JPanel panel = new JPanel(new GridLayout(2, 2));
        panel.setBackground(new Color(250,250,250));
        panel.add(frame1.getChartPanel());
        panel.add(frame4.getChartPanel());
        panel.add(frame2.getChartPanel());
        panel.add(frame3.getChartPanel());


        // Création de la vue pour la JScrollPane
        JViewport viewPort = new JViewport();
        viewPort.add(panel);
        // Création du JScrollPane avec la vue
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setViewportView(viewPort);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        chartsPanel.add(scrollPane, BorderLayout.CENTER);

        // Création de la fenêtre principale et ajout du conteneur des graphiques
        JFrame mainFrame = new JFrame("Information graphique");
        Container contentPane = mainFrame.getContentPane();
        contentPane.add(chartsPanel);
        mainFrame.setPreferredSize(new Dimension(1400, 1000));
        mainFrame.setBackground(new Color(250,250,250));
        mainFrame.pack();
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setVisible(true);

    };

    }
