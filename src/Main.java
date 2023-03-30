import java.util.*;

public class Main {

    // interface Command
    public interface Command {
        String name();
        boolean run(Scanner scanner);
    }

    // classe Quit
    public static class Quit implements Command {
        @Override
        public String name() {
            return "quit";
        }

        @Override
        public boolean run(Scanner scanner) {
            System.out.println("Bye!");
            return true;
        }
    }

    // classe Fibo
    public static class Fibo implements Command {
        @Override
        public String name() {
            return "fibo";
        }

        @Override
        public boolean run(Scanner scanner) {
            System.out.println("Enter a number:");
            int n = scanner.nextInt();
            int fibo = fibonacci(n);
            System.out.println("Fibo("+n+") = "+fibo);
            return false;
        }

        private int fibonacci(int n) {
            if (n <= 1) {
                return n;
            } else {
                return fibonacci(n-1) + fibonacci(n-2);
            }
        }
    }

    // classe Freq
    public static class Freq implements Command {
        @Override
        public String name() {
            return "freq";
        }

        @Override
        public boolean run(Scanner scanner) {
            System.out.println("Enter some text:");
            String input = scanner.nextLine();
            Map<Character,Integer> frequencies = new HashMap<>();
            for (char c : input.toCharArray()) {
                if (!Character.isWhitespace(c)) {
                    if (!frequencies.containsKey(c)) {
                        frequencies.put(c, 1);
                    } else {
                        int count = frequencies.get(c);
                        frequencies.put(c, count+1);
                    }
                }
            }
            System.out.println(frequencies);
            return false;
        }
    }

    public static void main(String[] args) {

        // initialisation de la liste des commandes
        List<Command> commands = new ArrayList<>();
        commands.add(new Quit());
        commands.add(new Fibo());
        commands.add(new Freq());

        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        while (loop) {
            System.out.print("> ");
            String input = scanner.nextLine();

            // recherche de la commande correspondante
            Command command = null;
            for (Command c : commands) {
                if (c.name().equals(input)) {
                    command = c;
                    break;
                }
            }

            // exécution de la commande ou affichage d'un message d'erreur
            if (command != null) {
                loop = !command.run(scanner);
            } else {
                System.out.println("Unknown command");
            }
        }

        scanner.close(); // close the scanner object when done
    }
}
