import java.util.Scanner;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        // TODO's:
        // --PLAYER GUESSING--
        // [x] 1. Create Premade Bot Layout (for testing, randomize later)
        // [x] 2. Make sure Player cannot see Bot layout
        // 3. Take input from player to guess bot ship placement
        //    - Make a list of valid guesses & validate answers
        //    - Create a 'clean bot board' where you can display the players hits and misses
        // 4. Make player see where they have guessed
        // 5. Make player see where they have hit
        // 6. Make player win when all ships have been sunk

        // --BOT PLAYING BACK--
        // 1. Create Premade Player Layout (for testing, player input later)
        // 2. Let bot make random guess (1 to 101)
        // 3. Prevent bot from making same guess twice
        // 4. Make bot remember where they hit
        // 5. Make bot guess below, right, under, left of first hits
        // 6. Make bot remember direction after first hit, keep guessing that way
        // 7. Make bot remember first hit, then guess opposite direction to see if it continues that way

        // --GAME LOGIC--
        // 1. Make a game loop
        // 2. Ask the player for input in determining placement of ships (all ships, 1x5 1x4 2x3 1x2)
        // 3. Make the BOT place its ships randomly (semi-randomly?)
        // 4. Alternate turns between player and bot
        // 5. Add win/lose text and "do you want to play again?"
        // 6. Shoutouts to simpleflips




        // ***ALL CODE AFTER THIS IS TESTING; GAME LOOP NOT YET IMPLEMENTED***
        // Player Setup
        Player player = new Player();
        Ship testShip1 = new Ship(4, 1, true);
        Ship testShip2 = new Ship(3, 55, false);
        player.AddShip(testShip1);
        player.AddShip(testShip2);
        player.MakePlayersBattleshipsVisible();

        // Bot Setup
        Bot bot = new Bot();
        // bot.MakePlayersBattleshipsVisible();

        // Print the boards
        bot.board.PrintBoard();
        player.board.PrintBoard();

        // Game Variables
        boolean gameOngoing = true;
        Scanner scanner = new Scanner(System.in);

        while (gameOngoing) {
            System.out.println("So... Where do ya think they are?: ");
            String currentGuess = scanner.nextLine();
            System.out.println("Your guess is: " + currentGuess);

            // TODO: Delete this - testing purposes only
            System.out.println(player.IsGuessValid(currentGuess));

            

            if (currentGuess.equalsIgnoreCase("q")){
                gameOngoing = false;
            }
        }















    }
}