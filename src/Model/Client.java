package Model;

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
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

public class Client extends JFrame {
    private Integer id;
    private String first_name;
    private String last_name;
    private String username;
    private char[] password;
    private String email;
    enum StateUser { MISSING, CONNECTED, DISCONNECTED}
    private StateUser state;//absent connecté deconnecté
    enum TypeUser {NORMAL, MODERATOR, ADMINISTRATOR;}
    private int typeUser; // Admin Moderateur Normal
    private LocalDateTime last_connection_time;

    static File font = new File("Font/Urbanist (font)/static/Urbanist-Medium.ttf");
    static Font urbanist;

    static {
        try {
            urbanist = Font.createFont(Font.TRUETYPE_FONT, font);
        } catch (FontFormatException | IOException e) {
            throw new RuntimeException(e);
        }
    }


//    public Client(String firstName, String lastName, String email, String handle, char[] password){
//        this.first_name = firstName;
//        this.last_name = lastName;
//        this.email = email;
//        this.username = handle;
//        this.password = password;
//    }

    public Client(){

    }


    public Integer getId() { return id; }

    public String getLast_name() { return last_name; }

    public String getFirst_name() { return first_name; }

    public String getEmail() { return email; }

    public char[] getPassword() { return password; }

    public String getUsername() { return username; }

    public int getTypeUser() { return typeUser;}

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

    public void setPassword(char[] password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setTypeUser(int typeUser) { this.typeUser = typeUser;}
}





