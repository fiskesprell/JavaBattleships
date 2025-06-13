public class Main {
    public static void main(String[] args) {
        // What has to happen?
        // Place 5 ships
        // 1x 5-long
        // 1x 4-long
        // 2x 3-long
        // 1x 2-long

        // board is 10x10 long

        // TODO's:
        // --PLAYER GUESSING--
        // 1. Create Premade Bot Layout (for testing, randomize later)
        // 2. Make sure Player cannot see Bot layout
        // 3. Take input from player to guess bot ship placement
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








        // ***ALL CODE AFTER THIS IS TESTING; GAME LOOP NOT YET IMPLEMENTED***
        Player player = new Player();
        player.board.PrintBoard();


        // Bot bot = new Bot();
        // bot.board.PrintBoard();

        Ship testShip1 = new Ship(4, 1, true);
        Ship testShip2 = new Ship(3, 55, false);

        player.AddShip(testShip1);
        player.AddShip(testShip2);

        for (int i = 0; i < player.playerShips.size(); i++){
            Ship ship = player.playerShips.get(i);
            for (int x = 0; x < ship.getPlacement().length; x++){
                player.board.UpdateBoard(ship.getPlacement()[x], "S");
            }
        }

        player.board.PrintBoard();



    }
}