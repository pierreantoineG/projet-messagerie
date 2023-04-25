package Controller;
import Model.*;
import View.*;
public class SettingsControl {
    SettingsView settingsView;
    Client clientUser;

    public SettingsControl(SettingsView settingsView, Client client){
        this.settingsView = settingsView;
        this.clientUser = client;
    }

}
