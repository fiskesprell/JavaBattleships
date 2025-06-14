public class Bot extends Player {
    public Bot(){
        super.board = new Board("BOT");
        createStandardBoard();
    }

    private void createStandardBoard(){
        Ship ship1 = new Ship(4, 1, false);
        // Ship ship2 = new Ship(2, 10, true);
        addShip(ship1);
        // addShip(ship2);

    }

    @Override
    public void takeAHit(int position){
        if (combinedShipLocations.contains(position)){
            super.board.updateBoard(position, "H");
            this.health -= 1;
        } else {
            super.board.updateBoard(position, "X");
        }
    }

}
