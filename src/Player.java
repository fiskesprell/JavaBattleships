import java.util.ArrayList;
import java.util.Set;

public class Player {
    public Board board = new Board("Player");

    public ArrayList<Ship> playerShips = new ArrayList<Ship>();
    public ArrayList<Integer> combinedShipLocations = new ArrayList<>();
    public int health;

    private static final Set<String> VALID_GUESSES = Set.of(
            "A1", "A2", "A3", "A4", "A5", "A6", "A7", "A8", "A9", "A10",
            "B1", "B2", "B3", "B4", "B5", "B6", "B7", "B8", "B9", "B10",
            "C1", "C2", "C3", "C4", "C5", "C6", "C7", "C8", "C9", "C10",
            "D1", "D2", "D3", "D4", "D5", "D6", "D7", "D8", "D9", "D10",
            "E1", "E2", "E3", "E4", "E5", "E6", "E7", "E8", "E9", "E10",
            "F1", "F2", "F3", "F4", "F5", "F6", "F7", "F8", "F9", "F10",
            "G1", "G2", "G3", "G4", "G5", "G6", "G7", "G8", "G9", "G10",
            "H1", "H2", "H3", "H4", "H5", "H6", "H7", "H8", "H9", "H10",
            "I1", "I2", "I3", "I4", "I5", "I6", "I7", "I8", "I9", "I10",
            "J1", "J2", "J3", "J4", "J5", "J6", "J7", "J8", "J9", "J10"
    );

    public void addShip(Ship ship){
        this.playerShips.add(ship);
        // TODO: Delete this before "final"
        // seeShipPlacement(ship);
        addToCombinedShipLocations(ship);
        setMaxHealth();
    }

    public void makePlayersBattleshipsVisible(){
        for (int i = 0; i < this.playerShips.size(); i++){
            Ship ship = this.playerShips.get(i);
            for (int x = 0; x < ship.getPlacement().length; x++){
                this.board.updateBoard(ship.getPlacement()[x], "S");
            }
        }
    }

    private void addToCombinedShipLocations(Ship ship){
        for (int i = 0; i < ship.getPlacement().length; i++){
            this.combinedShipLocations.add(ship.getPlacement()[i]);
        }
    }

    private void setMaxHealth(){
        this.health = this.combinedShipLocations.size();
    }

    public boolean isGuessValid(String guess){
        return VALID_GUESSES.contains(guess);
    }

    public int convertGuessToInteger(String guess){
        int tens = 0;
        char letter = guess.charAt(0);
        String number = guess.substring(1);
        switch (letter){
            case('A'): break;
            case ('B'): tens += 10; break;
            case ('C'): tens += 20; break;
            case ('D'): tens += 30; break;
            case ('E'): tens += 40; break;
            case ('F'): tens += 50; break;
            case ('G'): tens += 60; break;
            case ('H'): tens += 70; break;
            case ('I'): tens += 80; break;
            case ('J'): tens += 90; break;
        }

        return tens + Integer.valueOf(number);
    }

    private void seeShipPlacement(Ship ship){
        int[] placement = ship.getPlacement();
        for (int i = 0; i < placement.length; i++){
            System.out.print(placement[i]);
            if (i == placement.length - 1){
                System.out.print("\n");
            } else { System.out.print("-");}
        }
    }

    // Bot shoots a shot at the players board.
    // Marks that position with H if hit, X if miss.
    // Returns int position if HIT
    // Returns int 0 if NOT HIT
    public int takeAHit(int position){
        if (combinedShipLocations.contains(position)){
            this.board.updateBoard(position, "🎯");
            this.health -= 1;
            return position;
        } else {
            this.board.updateBoard(position, "❌");
            return 0;
        }
    }

    public boolean isDead(){
        return health == 0;
    }

}
