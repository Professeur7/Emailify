// Main.java
import model.Employee;
import notification.ConsoleNotification;
import notification.EmailNotification;
import notification.Notification;
import service.EmployeeDirectory;
import service.NotificationService;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        EmployeeDirectory directory = new EmployeeDirectory();

        List<Notification> channels = Arrays.asList(new ConsoleNotification(), new EmailNotification());
        NotificationService notificationService = new NotificationService(channels);

        while (true) {
            System.out.println("\n==== Menu ====");
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
                    String name = scanner.nextLine();
                    directory.addEmployee(name);
                    System.out.println("Employé ajouté !");
                }
                case 2 -> {
                    System.out.println("Liste des employés : ");
                    directory.displayAllEmployees();
                }
                case 3 -> {
                    System.out.print("Nom de l'employé à abonner : ");
                    String name = scanner.nextLine();
                    Employee e = directory.getEmployee(name);
                    if (e != null) {
                        notificationService.subscribe(e);
                        System.out.println(name + " est maintenant abonné.");
                    } else {
                        System.out.println("Employé introuvable.");
                    }
                }
                case 4 -> {
                    System.out.print("Nom de l'employé à désabonner : ");
                    String name = scanner.nextLine();
                    Employee e = directory.getEmployee(name);
                    if (e != null) {
                        notificationService.unsubscribe(e);
                        System.out.println(name + " est maintenant désabonné.");
                    } else {
                        System.out.println("Employé introuvable.");
                    }
                }
                case 5 -> {
                    System.out.print("Nom de l'expéditeur : ");
                    String senderName = scanner.nextLine();
                    Employee sender = directory.getEmployee(senderName);
                    if (sender != null && notificationService.isSubscribed(sender)) {
                        System.out.print("Message à envoyer : ");
                        String msg = scanner.nextLine();
                        notificationService.sendMessage(sender, msg);
                    } else {
                        System.out.println("Expéditeur introuvable ou non abonné.");
                    }
                }
                case 6 -> {
                    System.out.print("Nom de l'employé : ");
                    String name = scanner.nextLine();
                    Employee e = directory.getEmployee(name);
                    if (e != null) {
                        System.out.println(notificationService.isSubscribed(e)
                                ? name + " est abonné."
                                : name + " n'est pas abonné.");
                    } else {
                        System.out.println("Employé introuvable.");
                    }
                }
                case 7 -> {
                    System.out.print("Nom de l'employé : ");
                    String name = scanner.nextLine();
                    Employee e = directory.getEmployee(name);
                    if (e != null) {
                        System.out.println("Messages reçus :");
                        e.getReceivedMessages().forEach(System.out::println);
                    } else {
                        System.out.println("Employé introuvable.");
                    }
                }
                case 0 -> {
                    System.out.println("Fermeture du programme.");
                    return;
                }
                default -> System.out.println("Option invalide.");
            }
        }
    }
}
