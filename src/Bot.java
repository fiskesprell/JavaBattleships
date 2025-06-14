import java.util.ArrayList;
import java.util.Random;
public class Bot extends Player {
    ArrayList<Integer> usedGuesses = new ArrayList<>();
    int lastHit = 0;
    int rotation = 0;

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

    public void attackTheBot(int position){
        if (combinedShipLocations.contains(position)){
            super.board.updateBoard(position, "H");
            this.health -= 1;
        } else {
            super.board.updateBoard(position, "X");
        }
    }

    public void botGuesses(Player player){
        Random r = new Random();
        int guess = 0;

        if (lastHit != 0){
            switch (rotation){
                case 0: guess = lastHit + 1; this.rotation++; break;
                case 1: guess = lastHit + 10; this.rotation++; break;
                case 2: guess = lastHit - 1; this.rotation++; break;
                case 3: guess = lastHit - 10; this.rotation = 0; break;
            }
        }


        while (true && lastHit == 0) {
            guess = r.nextInt(101);
            if (!usedGuesses.contains(guess)){
                break;
            }
        }
        usedGuesses.add(guess);

        this.lastHit = player.takeAHit(guess);

    }



}
