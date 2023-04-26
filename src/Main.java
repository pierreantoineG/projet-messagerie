
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
    }
}
