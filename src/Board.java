import java.util.HashMap;

public class Board {
    private HashMap<Integer, String> board = new HashMap<Integer, String>();
    private String username;

    public Board(String username){
        this.username = username;
        CreateCleanBoard();


    }

    // Prints the current visible board
    public void PrintBoard(){
        System.out.println("_BOARD OF: " + username + "_");
        for (int i = 1; i < 101; i++){
            System.out.print(board.get(i) + " ");
            if (i % 10 == 0 && i > 8){
                System.out.print("\n");
            }
        }
    }

    // Adds a new value on the visible board
    public void UpdateBoard(int key, String value){
        if (key < 101) {
            this.board.put(key, value);
        } else { System.out.println("ERROR: Board/UpdateBoard - You tried to update the board with a value > 101"); }
    }

    // Fills the 'board' HashMap with tildes from key value 1 to 101
    private void CreateCleanBoard(){
        for (int i = 1; i < 101; i++){
            board.put(i, "~");
        }
    }


}
