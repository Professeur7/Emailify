package service;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import java.util.Properties;

public class EmailUtil {
    private final Session session;
    private final String fromEmail;

    public EmailUtil(String host, int port, String username, String password, String fromEmail) {
        this.fromEmail = fromEmail;
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", String.valueOf(port));
        props.put("mail.smtp.ssl.trust", host);
        session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
    }

    public void sendEmail(String to, String subject, String body) throws MessagingException {
        InternetAddress emailAddress = new InternetAddress(to);
        emailAddress.validate();
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(fromEmail));
        message.setRecipient(Message.RecipientType.TO, emailAddress);
        message.setSubject(subject);
        message.setText(body);
        Transport.send(message);
        System.out.println("📩 Email envoyé à : " + to);
    }
}
