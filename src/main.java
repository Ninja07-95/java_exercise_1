import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class main {

    public interface Command {
        String name();
        boolean run(Scanner scanner);
    }

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

    public static class Predict implements Command {
        @Override
        public String name() {
            return "predict";
        }

        @Override
        public boolean run(Scanner scanner) {
            System.out.println("Enter the path of the text file to be analyzed:");
            String filePath = scanner.nextLine();
            try {
                File file = new File(filePath);
                Scanner fileScanner = new Scanner(file);

                Map<String, List<String>> wordMap = new HashMap<>();
                List<String> words = new ArrayList<>();

                while (fileScanner.hasNext()) {
                    String line = fileScanner.nextLine();
                    String[] lineWords = line.split("\\s+");
                    for (String word : lineWords) {
                        words.add(word.toLowerCase());
                    }
                }

                for (int i = 0; i < words.size() - 1; i++) {
                    String currentWord = words.get(i);
                    String nextWord = words.get(i + 1);
                    if (!wordMap.containsKey(currentWord)) {
                        wordMap.put(currentWord, new ArrayList<>());
                    }
                    wordMap.get(currentWord).add(nextWord);
                }

                System.out.println("Analysis complete.");
                boolean loop = true;
                while (loop) {
                    System.out.println("Enter a word (or 'quit' to exit):");
                    String input = scanner.nextLine();
                    if (input.equals("quit")) {
                        loop = false;
                        break;
                    }
                    if (!wordMap.containsKey(input.toLowerCase())) {
                        System.out.println("Unknown word.");
                        continue;
                    }
                    List<String> possibleNextWords = wordMap.get(input.toLowerCase());
                    int maxWords = Math.min(20, possibleNextWords.size());
                    StringBuilder sb = new StringBuilder(input);
                    for (int i = 0; i < maxWords; i++) {
                        String nextWord = possibleNextWords.get(i);
                        sb.append(" ").append(nextWord);
                        if (!wordMap.containsKey(nextWord)) {
                            break;
                        }
                        possibleNextWords = wordMap.get(nextWord);
                    }
                    System.out.println(sb.toString());
                }

            } catch (FileNotFoundException e) {
                System.out.println("Unreadable file: " + e.getClass().getName() + ": " + e.getMessage());
            }
            return false;
        }
    }

    public static void main(String[] args) {

        List<Command> commands = new ArrayList<>();
        commands.add(new Quit());
        commands.add(new Fibo());
        commands.add(new Freq());
        commands.add(new Predict());

        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        while (loop) {
            System.out.print("> ");
            String input = scanner.nextLine();

            Command command = null;
            for (Command c : commands) {
                if (c.name().equals(input)) {
                    command = c;
                    break;
                }
            }

            if (command != null) {
                loop = !command.run(scanner);
            } else {
                System.out.println("Unknown command");
            }
        }
    }
}

