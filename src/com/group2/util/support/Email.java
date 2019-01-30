package com.group2.util.support;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Email {
    private String username;
    private String password;
    private Properties config;
    Session session;

    private String to;
    private String from;
    private String subject;
    private String text;

    public Email(String to){
        this.mailConfig();
        this.from = this.config.getProperty("MAIL_FROM");
        this.to = to;
    }
    public Email(String from, String to){
        this.mailConfig();
        this.from = from;
        this.to = to;
    }
    public Email(String from, String subject, String text){
        this.mailConfig();
        this.from = from;
        this.subject = subject;
        this.text = text;
    }

    private void mailConfig(){
        this.config = new Properties();
        try {
            this.config.load(new FileInputStream("/root/IdeaProjects/Coffe-project/resource/db_config.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(this.config.getProperty("SMTP_USERNAME"));
        this.username = this.config.getProperty("SMTP_USERNAME");
        this.password = this.config.getProperty("SMTP_PASSWORD");

        Properties props = new Properties();
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.host", this.config.getProperty("SMTP_HOST"));
        props.put("mail.smtp.port", this.config.getProperty("SMTP_PORT"));

        Email that = this;
        this.session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(that.username, that.password);
                    }
                });
    }
    public Email send(String subject, String text){
        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(this.from));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(this.to));
            message.setSubject(subject);
            message.setText(text);

            Transport.send(message);

            System.out.println("Done");
            return this;

        } catch (MessagingException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Email to (String to){
        this.to = to;
        this.send(this.subject, this.text);
        return this;
    }
}
