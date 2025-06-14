import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // TODO's:
        // --PLAYER GUESSING--
        // [x] 1. Create Premade Bot Layout (for testing, randomize later)
        // [x] 2. Make sure Player cannot see Bot layout
        // [x] 3. Take input from player to guess bot ship placement
        //    - [x] Make a list of valid guesses & validate answers
        //    - [x] Create a 'clean bot board' where you can display the players hits and misses
        // [x] 4. Make player see where they have guessed
        // [x] 5. Make player see where they have hit
        // [x] 6. Make player win when all ships have been sunk

        // --BOT PLAYING BACK--
        // [x] 1. Create Premade Player Layout (for testing, player input later)
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
        Ship testShip3 = new Ship(5, player.convertGuessToInteger("E3"), false);
        player.addShip(testShip1);
        player.addShip(testShip2);
        player.addShip(testShip3);
        player.makePlayersBattleshipsVisible();

        // Bot Setup
        Bot bot = new Bot();
        // bot.makePlayersBattleshipsVisible();


        // Game Variables
        boolean gameOngoing = true;
        boolean playerWin = false;
        Scanner scanner = new Scanner(System.in);

        while (gameOngoing) {

            bot.board.printBoard();
            System.out.println("So... Where do ya think they are?: ");
            String currentGuess = scanner.nextLine();
            System.out.println("Your guess is: " + currentGuess);


            if (player.isGuessValid(currentGuess)){
                bot.attackTheBot(player.convertGuessToInteger(currentGuess));
            } else {
                System.out.println("Try that shit again homeboy. And input some valid shit ya hurd?");
            }


            if (currentGuess.equalsIgnoreCase("q")) {
                gameOngoing = false;
            }

            if (bot.isDead()) {
                gameOngoing = false;
                playerWin = true;
            }

            bot.botGuesses(player);
            player.board.printBoard();

        }
        if (playerWin) {
            System.out.println("GZ bro");
        } else {
            System.out.println("lol u bad bro");
        }
    }
}