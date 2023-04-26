package Controller;
import Dao.UserDaoImpl;
import Model.*;
import View.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

public class SettingsControl extends JFrame{
    SettingsView settingsView;
    static Client clientUser;

    public SettingsControl(SettingsView settingsView, Client client){
        this.settingsView = settingsView;
        this.clientUser = client;
    }

    public void initializeSettingsView(){
        JLabel lbl_icon_edit = settingsView.getLbl_icon_edit();
        lbl_icon_edit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                lbl_icon_edit.setIcon(settingsView.getImg_icon_edit_white());
                lbl_icon_edit.repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                lbl_icon_edit.setIcon(settingsView.getImg_icon_edit());
                lbl_icon_edit.repaint();
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                settingsView.getFname().setEditable(true);
                settingsView.getFname().setPreferredSize(new Dimension(100, 30));
            }
        });

        JLabel lbl_icon_edit1 = settingsView.getLbl_icon_edit1();
        lbl_icon_edit1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                lbl_icon_edit1.setIcon(settingsView.getImg_icon_edit_white());
                lbl_icon_edit1.repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                lbl_icon_edit1.setIcon(settingsView.getImg_icon_edit1());
                lbl_icon_edit1.repaint();
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                settingsView.getLname().setEditable(true);
                settingsView.getLname().setPreferredSize(new Dimension(100, 30));
            }
        });

        JLabel lbl_icon_edit2 = settingsView.getLbl_icon_edit2();

        lbl_icon_edit2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                lbl_icon_edit2.setIcon(settingsView.getImg_icon_edit_white_2());
                lbl_icon_edit2.repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                lbl_icon_edit2.setIcon(settingsView.getImg_icon_edit2());
                lbl_icon_edit2.repaint();
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                settingsView.getHandle().setEditable(true);
                settingsView.getHandle().setPreferredSize(new Dimension(100, 30));
            }
        });

        JLabel lbl_icon_cancel = settingsView.getLbl_icon_cancel();

        lbl_icon_cancel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                lbl_icon_cancel.setIcon(settingsView.getImg_icon_cancel_white());
                lbl_icon_cancel.repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                lbl_icon_cancel.setIcon(settingsView.getImg_icon_cancel());
                lbl_icon_cancel.repaint();
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                settingsView.getFname().setEditable(false);
                try {
                    settingsView.getFname().setText(UserDaoImpl.getFirstName(clientUser.getUsername()));
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                settingsView.getFname().setPreferredSize(new Dimension(100, 30));
            }
        });

        JLabel lbl_icon_cancel1 = settingsView.getLbl_icon_cancel1();
        lbl_icon_cancel1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                lbl_icon_cancel1.setIcon(settingsView.getImg_icon_edit_white_1());
                lbl_icon_cancel1.repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                lbl_icon_cancel1.setIcon(settingsView.getImg_icon_cancel1());
                lbl_icon_cancel1.repaint();
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                settingsView.getLname().setEditable(false);
                try {
                    settingsView.getLname().setText(UserDaoImpl.getLastName(clientUser.getUsername()));
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                settingsView.getLname().setPreferredSize(new Dimension(100, 30));
            }
        });

        JLabel lbl_icon_cancel2 = settingsView.getLbl_icon_cancel2();

        lbl_icon_cancel2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                lbl_icon_cancel2.setIcon(settingsView.getImg_icon_cancel_white_2());
                lbl_icon_cancel2.repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                lbl_icon_cancel2.setIcon(settingsView.getImg_icon_cancel2());
                lbl_icon_cancel2.repaint();
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                settingsView.getHandle().setEditable(false);
                settingsView.getHandle().setText(clientUser.getUsername());
                settingsView.getHandle().setPreferredSize(new Dimension(100, 30));
            }
        });

        JLabel lbl_icon_validate = settingsView.getLbl_icon_validate();
        lbl_icon_validate.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                lbl_icon_validate.setIcon(settingsView.getImg_icon_validate_white());
                lbl_icon_validate.repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                lbl_icon_validate.setIcon(settingsView.getImg_icon_validate());
                lbl_icon_validate.repaint();
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    UserDaoImpl.updateUserFirstName(clientUser.getUsername(), settingsView.getFname().getText());
                    settingsView.getFname().setEditable(false);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        JLabel lbl_icon_validate1 = settingsView.getLbl_icon_validate1();
        lbl_icon_validate1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                lbl_icon_validate1.setIcon(settingsView.getImg_icon_validate_white_1());
                lbl_icon_validate1.repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                lbl_icon_validate1.setIcon(settingsView.getImg_icon_validate1());
                lbl_icon_validate1.repaint();
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                if (settingsView.getLname().getText().equals(" ")) {
                    try {
                        settingsView.getLname().setText(UserDaoImpl.getLastName(clientUser.getUsername()));
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                }
                settingsView.getLname().setEditable(false);
                try {
                    UserDaoImpl.updateUserLastName(clientUser.getUsername(), settingsView.getLname().getText());
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        JButton lbl_away = settingsView.getLbl_away();
        lbl_away.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("ok");
                if (!settingsView.isAway()) {
                    settingsView.setAway(true);
                    settingsView.setOnline(false);
                    try {
                        UserDaoImpl.updateUserAway(clientUser.getUsername());
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                    repaint();
                }
            }
        });

        JButton lbl_online = settingsView.getLbl_online();
        lbl_online.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("ok");
                if (!settingsView.isOnline()) {
                    settingsView.setOnline(true);
                    settingsView.setAway(false);
                    try {
                        UserDaoImpl.updateUserConnected(clientUser.getUsername());
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                    repaint();
                }
            }
        });

        JButton button_chat = settingsView.getButton_chat();
        button_chat.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                settingsView.setVisible(false);
                ChatView chatView;
                try {
                    chatView = new ChatView();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                chatView.setVisible(true);
            }

            public void mouseEntered(MouseEvent e) {
                button_chat.setForeground(Color.WHITE);
            }

            public void mouseExited(MouseEvent e) {
                button_chat.setForeground(Color.BLACK);// Rétablir la couleur de fond par défaut du bouton
            }
        });

        JButton button_users = settingsView.getButton_users();
        button_users.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Rien ne se passe
            }
        });
        button_users.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                button_users.setForeground(Color.WHITE);
            }

            public void mouseExited(MouseEvent e) {
                button_users.setForeground(Color.BLACK);
            }
        });

        JButton button_settings = settingsView.getButton_settings();

        button_settings.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                button_settings.setForeground(Color.WHITE);
            }

            public void mouseExited(MouseEvent e) {
                button_settings.setForeground(Color.BLACK);// Rétablir la couleur de fond par défaut du bouton
            }
        });

        JButton button_logout = settingsView.getButton_logout();
        button_logout.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                settingsView.setVisible(false);
                LoginView loginView = new LoginView();
                loginView.setVisible(true);
            }

            public void mouseEntered(MouseEvent e) {
                button_logout.setForeground(Color.WHITE);
            }

            public void mouseExited(MouseEvent e) {
                button_logout.setForeground(Color.BLACK);// Rétablir la couleur de fond par défaut du bouton
            }
        });


        JButton button_reporting = settingsView.getButton_reporting();
        button_reporting.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                ReportView reportView = new ReportView();
            }

            public void mouseEntered(MouseEvent e) {
                button_reporting.setForeground(Color.WHITE);
            }

            public void mouseExited(MouseEvent e) {
                button_reporting.setForeground(Color.BLACK);
            }
        });

        settingsView.setVisible(true);

    }
}
