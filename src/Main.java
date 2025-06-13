public class Main {
    public static void main(String[] args) {
        // What has to happen?
        // Place 5 ships
        // 1x 5-long
        // 1x 4-long
        // 2x 3-long
        // 1x 2-long

        // board is 10x10 long


        Player player = new Player();
        // Bot bot = new Bot();

        player.board.PrintBoard();
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