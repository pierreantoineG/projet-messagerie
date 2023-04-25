package Model;

import javax.swing.*;
import java.util.Date;

public class Message {
    private Integer id;
    private String allContent;
    private String sender;
    private String msgContent;
    private Client client;
    private Date timestamp;//Si marche pas mettre LocalDateTime
    private JPanel panelMessage = new JPanel(); //Conteneur du groupe imgUser + msgBulleWithHandle
    private JPanel imgUser = new JPanel(); //Conteneur de l'image de profil
    private JPanel msgBulleWithHandle = new JPanel(); //Conteneur du groupe bulleText + handleTimestamp
    private JPanel bulleText = new JPanel();
    private JPanel handleTimestamp = new JPanel(); //Conteneur du username (handle) et du timeStamp
    private JTextArea msgTextArea;//Text area dans lequel on va mettre le msgContent


//    public Message(Integer id, String content, Client client, LocalDateTime timestamp){
//        this.id = id;
//        this.content = content;
//        this.client_id = client;
//        this.timestamp = timestamp;
//    }

    public Message() {

    }

    public JPanel initPanelMessage(JPanel panel1, JPanel panel2) {
        panelMessage.add(panel1);
        panelMessage.add(panel2);
        return panelMessage;
    }

    public Integer getId() {
        return id;
    }

    public String getAllContent() {
        return allContent;
    }

    public Client getUser() {
        return client;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public JPanel getPanelMessage() {
        return panelMessage;
    }

    public void setPanelMessage(JPanel panelMsg) {
        this.panelMessage = panelMsg;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setAllContent(String content) {
        this.allContent = content;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public void setUser(Client client) {
        this.client = client;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getMsgContent() {
        return msgContent;
    }

    public void setMsgContent(String msgContent) {
        this.msgContent = msgContent;
    }

    public JPanel getImgUser() {
        return imgUser;
    }

    public void setImgUser(JPanel imgUser) {
        this.imgUser = imgUser;
    }

    public JPanel getMsgBulleWithHandle() {
        return msgBulleWithHandle;
    }

    public void setMsgBulleWithHandle(JPanel msgBulleWithHandle) {
        this.msgBulleWithHandle = msgBulleWithHandle;
    }

    public JPanel getBulleText() {
        return bulleText;
    }

    public void setBulleText(JPanel bulleText) {
        this.bulleText = bulleText;
    }

    public JPanel getHandleTimestamp() {
        return handleTimestamp;
    }

    public void setHandleTimestamp(JPanel handleTimestamp) {
        this.handleTimestamp = handleTimestamp;
    }

    public JTextArea getMsgTextArea() {
        return msgTextArea;
    }

    public void setMsgTextArea(JTextArea msgTextArea) {
        this.msgTextArea = msgTextArea;
    }

    @Override
    public String toString() {
        return "Model.Message{" +
                ", content='" + allContent + '\'' +
                ", user=" + client +
                ", timestamp =" + timestamp +
                '}';
    }
}

