
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
    public static void main(String[] args) throws ClassNotFoundException {
        LoginView loginView = new LoginView();
        LoginControl loginControl = new LoginControl(loginView);
        loginControl.initialize();
        //Model.ReportView reportView = new Model.ReportView();
        //fenetre fenetre = new fenetre();
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
