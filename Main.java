import java.util.*;

public class Main {
        public static final String GREEN = "\u001B[32m";
        public static final String RESET = "\u001B[0m";
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        AuthService.loadUsers();

        boolean loggedIn = false;
        while (!loggedIn) {
            System.out.println("\n1. Register");
            System.out.println("2. Login");

            System.out.print("Choose: ");
            int choice = sc.nextInt();
            sc.nextLine();

            if (choice == 1) {
                AuthService.register(sc);
            } else if (choice == 2) {
                loggedIn = AuthService.login(sc);
            }
        }

        System.out.println("ChatBot started!\n");
        ChatBot bot = new SmartChatBot();

        while (true) {
           System.out.print(GREEN + "You: " + RESET);
           String input = sc.nextLine();

           String response = bot.getResponse(input);
           System.out.println(GREEN + "Bot: " + RESET + response);

           if (input.equalsIgnoreCase("bye")) break;
       }
    }
}

