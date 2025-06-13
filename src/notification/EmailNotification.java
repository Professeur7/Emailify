package notification;

import jakarta.mail.MessagingException;
import model.Employee;
import service.EmailUtil;
import service.EmployeeDirectory;

public abstract class EmailNotification implements Notification {
    private final EmailUtil emailUtil;
    private final EmployeeDirectory directory;

    public EmailNotification(EmailUtil emailUtil, EmployeeDirectory directory) {
        this.emailUtil = emailUtil;
        this.directory = directory;
    }

    @Override
    public void send(String message, Employee sender, Employee receiver) {
        String recipientEmail = receiver.getEmail();
        System.out.println("🔄 Envoi email à : " + recipientEmail);

        if (recipientEmail == null || recipientEmail.isBlank() || !recipientEmail.contains("@")) {
            System.err.println("❌ Adresse email invalide : " + recipientEmail);
            return;
        }
        try {
            emailUtil.sendEmail(recipientEmail, "De " + sender.getName(), message);
            receiver.receiveNotification("[Email] De " + sender.getName() + " : " + message);
        } catch (MessagingException e) {
            System.err.println("❌ Erreur d’envoi à " + recipientEmail + " : " + e.getMessage());
        }
    }
}
