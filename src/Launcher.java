import java.util.Scanner;

public class Launcher {
    public static void main(String[] args) {
        System.out.println("Bienvenue dans le programme !");
        Scanner scanner = new Scanner(System.in);
        String input;
        do {
            System.out.print("Entrez une commande : ");
            input = scanner.nextLine();
            if (input.equals("quit")) {
                break;
            } else if (input.equals("fibo")) {
                System.out.print("Entrez un nombre n : ");
                int n = scanner.nextInt();
                scanner.nextLine(); 
                int fibo = fibonacci(n);
                System.out.println("La suite de Fibonacci à l'index " + n + " est : " + fibo);
            } else {
                System.out.println("Unknown command");
            }
        } while (true);
        System.out.println("Fin du programme");
    }

    private static int fibonacci(int n) {
        if (n == 0) {
            return 0;
        } else if (n == 1) {
            return 1;
        } else {
            return fibonacci(n - 1) + fibonacci(n - 2);
        }
    }
}
