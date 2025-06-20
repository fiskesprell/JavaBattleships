import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

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
        Ship testShip4 = new Ship(4, 10, true);
        Ship testShip5 = new Ship(4, 8, true);
        Ship testShip6 = new Ship(4, 72, true);
        player.addShip(testShip1);
        player.addShip(testShip2);
        player.addShip(testShip3);
        player.addShip(testShip4);
        player.addShip(testShip5);
        player.addShip(testShip6);
        player.makePlayersBattleshipsVisible();

        // Bot Setup
        Bot bot = new Bot();
        // bot.makePlayersBattleshipsVisible();


        // Game Variables
        boolean gameOngoing = true;
        boolean playerWin = false;
        Scanner scanner = new Scanner(System.in);

        while (gameOngoing) {

            // bot.board.printBoard();
            System.out.println("So... Where do ya think they are?: ");
            String currentGuess = scanner.nextLine();
            currentGuess.toUpperCase();
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
