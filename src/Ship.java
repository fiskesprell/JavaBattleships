import java.util.HashMap;
import java.util.Arrays;

public class Ship {
    private int shipLength;
    private int[] placement;
    private int[] hits;

    public Ship(int shipLength, int root, boolean vertical){
        this.shipLength = shipLength;
        this.placement = new int[shipLength];
        placeShip(root, vertical);
    }

    // Root is the top part of a vertical ship, or the leftmost part of a horizontal ship
    private void placeShip(int root, boolean vertical){
        if (vertical){
            for (int i = 0; i < shipLength; i++){
                this.placement[i] = (root + (i * 10));
            }
        } else {
            for (int i = 0; i < shipLength; i++){
                this.placement[i] = (root + i);
            }
        }
    }

    // Returns True if the ship is dead
    public boolean ShipIsDead(){
        int[] placementClone = placement.clone();
        int[] hitsClone = hits.clone();
        Arrays.sort(placementClone);
        Arrays.sort(hitsClone);

        return (placementClone == hitsClone);
    }

    public int[] getPlacement(){
        return this.placement;
    }


}
