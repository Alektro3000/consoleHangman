import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

public final class GameController {

    String word;
    static Scanner input = new Scanner(System.in);
    ArrayList<String> guesses = new ArrayList<>();
    int errorCount = 0;
    int maxErrors = 6;

    public GameController()
    {
        this(getWord());
    }
    public GameController(String newInitWord)
    {
        word = newInitWord;
    }
    String generateShownWord()
    {

        return word.codePoints()
                .mapToObj(Character::toChars)
                .map(x -> guesses.contains(new String(x)) ? new String(x) : "*")
                .collect(Collectors.joining());
    }
    Boolean guess()
    {
        System.out.println("Enter guess letter");

        var letter = input.nextLine().toLowerCase();

        while(!Alphabet.contains(letter) || guesses.contains(letter))
        {
            if(!Alphabet.contains(letter))
                System.out.println("Please enter a letter");
            else
                System.out.println("It's repetition of previous guess");
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
                System.out.printf("Wrong, you made %d mistakes\n",  errorCount);

                draw(errorCount);
                if(errorCount >= maxErrors)
                {
                    System.out.println("Sorry, you lost");
                    System.out.println("Word is: " + word);
                    return;
                }
            }
            else {
                System.out.print("Right, the word is: ");
                String shownWord = generateShownWord();
                System.out.println(shownWord);
                if(word.equals(shownWord))
                {
                    System.out.print("You won!");
                    return;
                }
            }
        }
    }

    public static String getWord() {
        String[] dictionary = {
                "apple",
                "banana",
                "orange",
                "elephant",
                "guitar",
                "mountain",
                "computer",
                "galaxy",
                "rainbow",
                "chocolate",
                "volcano",
                "diamond",
                "whisper",
                "thunder",
                "crystal",
                "lantern",
                "puzzle",
                "magnet",
                "ocean",
                "castle",
                "treasure",
                "dragon",
                "cactus",
                "horizon",
                "journey",
                "lantern",
                "village",
                "silver",
                "rocket",
                "pirate",
                "island",
                "comet",
                "planet",
                "mirror",
                "shadow",
                "secret",
                "candle",
                "desert",
                "forest",
                "jungle",
                "window",
                "ladder",
                "monster",
                "armor",
                "legend",
                "garden",
                "blossom",
                "crystal",
                "diamond",
                "thunderstorm",
                "waterfall",
                "universe",
                "galaxy",
                "telescope",
                "gravity",
                "molecule",
                "oxygen",
                "carbon",
                "satellite",
                "astronaut",
                "meteor",
                "nebula",
                "blackhole",
                "starlight",
                "quantum",
                "eclipse",
                "horizon",
                "aurora",
                "comet",
                "asteroid",
                "spaceship",
                "engine",
                "circuit",
                "battery",
                "magnet",
                "robot",
                "program",
                "virus",
                "pixel",
                "sensor",
                "laser",
                "crystal",
                "portal",
                "phantom",
                "spirit",
                "castle",
                "fortress",
                "kingdom",
                "soldier",
                "champion",
                "wizard",
                "potion",
                "sword",
                "armor",
                "spell",
                "crystal",
                "phoenix",
                "destiny",
                "freedom",
                "eternity"
        };

        return dictionary[ThreadLocalRandom.current().nextInt(0, dictionary.length)];
    }
    static ArrayList<String> Alphabet = new ArrayList<>(List.of("a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"));
    void draw(Integer errors)
    {
        ArrayList<String> Sprites = new ArrayList<>(List.of(
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
        ));
        System.out.println(Sprites.get(errors - 1));
    }

}
