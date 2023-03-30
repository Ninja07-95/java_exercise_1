import java.util.Scanner;

public class Launcher {
    public static void main(String[] args) {
        System.out.println("Bienvenue dans Launcher programme !");
        Scanner scanner = new Scanner(System.in);
        String userInput = "";

        while(!userInput.equals("quit")) {
            System.out.println("Veuillez entrer une commande (ou 'quit' pour quitter):");
            userInput = scanner.nextLine();
            
            switch (userInput) {
                // Add cases for valid commands here
                default:
                    System.out.println("Unknown command");
                    break;
            }
        }
        
        System.out.println("Au revoir !");
    }
}

