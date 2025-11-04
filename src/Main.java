import java.util.concurrent.ThreadLocalRandom;
import java.util.*;


//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        System.out.println("Do you want to play? (y/n)");
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();

        if ("n".equalsIgnoreCase(s)) {
            return;
        }

        while (true) {
            GameController game = new GameController();
            game.playGame();
            System.out.println("Do you want to play again? (y/n)");
            s = sc.nextLine();
            if ("n".equalsIgnoreCase(s)) {
                return;
            }
        }

    }
}