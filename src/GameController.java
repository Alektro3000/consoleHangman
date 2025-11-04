import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

public final class GameController {

    private final String word;
    private static final Scanner input = new Scanner(System.in);
    private final ArrayList<String> guesses = new ArrayList<>();
    private int errorCount = 0;

    public GameController() {
        this(getWord());
    }

    public GameController(String newInitWord) {
        word = newInitWord;
    }

    private String generateShownWord() {
        return word.codePoints()
                .mapToObj(Character::toChars)
                .map(x -> guesses.contains(new String(x)) ? new String(x) : "*")
                .collect(Collectors.joining());
    }

    private Boolean guess() {
        System.out.println("Enter guess letter");

        var letter = input.nextLine().toLowerCase();

        while (!GameDictionary.Alphabet.contains(letter) || guesses.contains(letter)) {
            if (!GameDictionary.Alphabet.contains(letter)) {
                System.out.println("Please enter a letter");
            } else {
                System.out.println("It's repetition of previous guess");
            }
            letter = input.nextLine();
        }

        guesses.add(letter);
        return word.contains(letter);
    }

    public void playGame() {
        System.out.println("Word is: " + word);
        while (true) {
            var isCorrect = guess();
            
            if (!isCorrect) {
                errorCount++;
                System.out.printf("Wrong, you made %d mistakes\n", errorCount);

                draw(errorCount);
                int maxErrors = 6;
                if (errorCount >= maxErrors) {
                    System.out.println("Sorry, you lost");
                    System.out.println("Word is: " + word);
                    return;
                }
            } else {
                System.out.print("Right, the word is: ");
                String shownWord = generateShownWord();
                System.out.println(shownWord);
                if (word.equals(shownWord)) {
                    System.out.print("You won!");
                    return;
                }
            }
        }
    }

    private static String getWord() {
        return GameDictionary.dictionary.get(ThreadLocalRandom.current().nextInt(0, GameDictionary.dictionary.size()));
    }

    private void draw(Integer errors) {
        List<String> Sprites = List.of(
                """
                        
                        
                        ____
                        """,
                """
                        |-
                        |
                        ____
                        """,
                """
                        |-O
                        |
                        ____
                        """,
                """
                        |-O
                        | |
                        ____
                        """,
                """
                        |-O
                        |/|\\
                        ____
                        """,
                """
                        |-O
                        |/|\\
                        _/_\\
                        """
        );
        System.out.println(Sprites.get(errors - 1));
    }

}
