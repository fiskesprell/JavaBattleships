public class Bot extends Player {
    public Bot(){
        super.board = new Board("BOT");
        createStandardBoard();
    }

    private void createStandardBoard(){
        Ship ship1 = new Ship(4, 1, false);
        Ship ship2 = new Ship(2, 10, true);
        AddShip(ship1);
        AddShip(ship2);

    }

}
