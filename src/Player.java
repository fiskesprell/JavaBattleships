import java.util.ArrayList;

public class Player {
    public Board board = new Board("Player");

    public ArrayList<Ship> playerShips = new ArrayList<Ship>();


    public void AddShip(Ship ship){
        playerShips.add(ship);

        // Delete this
        int[] placement = ship.getPlacement();
        for (int i = 0; i < placement.length; i++){
            System.out.print(placement[i]);
            if (i == placement.length - 1){
                System.out.print("\n");
            } else { System.out.print("-");}
        }
    }

}
