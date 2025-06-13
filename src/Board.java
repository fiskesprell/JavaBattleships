import java.util.HashMap;

public class Board {
    private HashMap<Integer, String> board = new HashMap<Integer, String>();
    private String username;

    public Board(String username){
        this.username = username;
        CreateCleanBoard();


    }

    public void PrintBoard(){
        System.out.println("_BOARD OF: " + username + "_");
        for (int i = 1; i < 101; i++){
            System.out.print(board.get(i) + " ");
            if (i % 10 == 0 && i > 8){
                System.out.print("\n");
            }
        }
    }

    public void UpdateBoard(int key, String value){
        this.board.put(key, value);
    }


    private void CreateCleanBoard(){
        for (int i = 1; i < 101; i++){
            board.put(i, "~");
        }
    }


}
