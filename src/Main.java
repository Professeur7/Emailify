import jakarta.mail.MessagingException;
import model.Employee;
import notification.ConsoleNotification;
import notification.EmailNotification;
import notification.Notification;
import service.EmailUtil;
import service.EmployeeDirectory;
import service.NotificationService;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        EmployeeDirectory directory = new EmployeeDirectory();

        NotificationService notificationService = getNotificationService(directory);

        while (true) {
            System.out.println("\n==== Bienvenue dans Emailify ====");
            System.out.println("1. Ajouter un employé");
            System.out.println("2. Afficher les employés");
            System.out.println("3. S'abonner à la notification");
            System.out.println("4. Se désabonner");
            System.out.println("5. Envoyer un message");
            System.out.println("6. Vérifier abonnement");
            System.out.println("7. Afficher les messages reçus");
            System.out.println("0. Quitter");

            System.out.print("Choix : ");
            int choix = scanner.nextInt();
            scanner.nextLine();

            switch (choix) {
                case 1 -> {
                    System.out.print("Nom de l'employé : ");
                    String name = scanner.nextLine().trim();
                    System.out.print("Email de l'employé : ");
                    String email = scanner.nextLine().trim();
                    directory.addEmployee(name, email);
                    System.out.println("✅ Employé ajouté !");
                }
                case 2 -> directory.displayAllEmployees();
                case 3 -> subscribeEmployee(scanner, directory, notificationService);
                case 4 -> unsubscribeEmployee(scanner, directory, notificationService);
                case 5 -> sendMessage(scanner, directory, notificationService);
                case 6 -> checkSubscription(scanner, directory, notificationService);
                case 7 -> showReceivedMessages(scanner, directory);
                case 0 -> { System.out.println("Fermeture du programme."); return; }
                default -> System.out.println("Option invalide.");
            }
        }
    }

    private static NotificationService getNotificationService(EmployeeDirectory directory) {
        EmailUtil emailUtil = new EmailUtil(
                "smtp.gmail.com", 587,
                "professeurhamidoudjire@gmail.com",
                "xrih sqvk mefa poxi",
                "professeurhamidoudjire@gmail.com"
        );

        EmailNotification emailNotif = new EmailNotification(emailUtil, directory) {
            @Override
            public void send(String message, String sender, String receiver) throws MessagingException {

            }
        };
        ConsoleNotification consoleNotif = new ConsoleNotification() {
            @Override
            public void send(String message, String sender, String receiver) throws MessagingException {

            }
        };

        return new NotificationService(Arrays.asList(emailNotif, consoleNotif));
    }

    private static void subscribeEmployee(Scanner sc, EmployeeDirectory dir, NotificationService ns) {
        System.out.print("Nom à abonner : ");
        String name = sc.nextLine().trim();
        Employee e = dir.getEmployee(name);

        if (e != null) {
            ns.subscribe(e);
            System.out.println(name + " est maintenant abonné.");
        } else {
            System.out.println("Employé introuvable.");
        }
    }

    private static void unsubscribeEmployee(Scanner sc, EmployeeDirectory dir, NotificationService ns) {
        System.out.print("Nom à désabonner : ");
        String name = sc.nextLine().trim();
        Employee e = dir.getEmployee(name);

        if (e != null) {
            ns.unsubscribe(e);
            System.out.println(name + " est maintenant désabonné.");
        } else {
            System.out.println("Employé introuvable.");
        }
    }


    private static void sendMessage(Scanner sc, EmployeeDirectory dir, NotificationService ns) {
        System.out.print("Nom de l'expéditeur : ");
        Employee sender = dir.getEmployee(sc.nextLine().trim());
        if (sender == null || !ns.isSubscribed(sender)) {
            System.out.println(sender == null ? "Expéditeur introuvable." : "Expéditeur non abonné."); return;
        }
        System.out.print("Message à envoyer : ");
        try {
            ns.sendMessage(sender, sc.nextLine().trim());
        } catch (MessagingException e) {
            System.out.println("Erreur lors de l'envoi de l'e-mail : " + e.getMessage());
        }
    }

    private static void checkSubscription(Scanner sc, EmployeeDirectory dir, NotificationService ns) {
        System.out.print("Nom à vérifier : ");
        Employee e = dir.getEmployee(sc.nextLine().trim());
        System.out.println(e != null
                ? (ns.isSubscribed(e) ? "est abonné." : "n'est pas abonné.")
                : "Employé introuvable.");
    }

    private static void showReceivedMessages(Scanner sc, EmployeeDirectory dir) {
        System.out.print("Nom : ");
        Employee e = dir.getEmployee(sc.nextLine().trim());
        if (e != null) {
            System.out.println("📨 Messages reçus :");
            e.getReceivedMessages().forEach(System.out::println);
        } else System.out.println("Employé introuvable.");
    }
}
