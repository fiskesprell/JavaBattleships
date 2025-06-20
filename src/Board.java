import java.util.HashMap;

public class Board {
    private HashMap<Integer, String> board = new HashMap<Integer, String>();
    private String username;

    public Board(String username){
        this.username = username;
        createCleanBoard();
    }

    // Prints the current visible board
    public void printBoard(){
        System.out.println("_BOARD OF: " + username + "_");
        System.out.println("  1 2 3 4 5 6 7 8 9 10");
        System.out.print("A ");
        for (int i = 1; i < 101; i++){
            System.out.print(board.get(i) + " ");
            if (i % 10 == 0 && i > 8){
                System.out.print("\n");
            }
            switch (i){
                case(10): System.out.print("B "); break;
                case(20): System.out.print("C "); break;
                case(30): System.out.print("D "); break;
                case(40): System.out.print("E "); break;
                case(50): System.out.print("F "); break;
                case(60): System.out.print("G "); break;
                case(70): System.out.print("H "); break;
                case(80): System.out.print("I "); break;
                case(90): System.out.print("J "); break;
            }
        }
    }

    // Adds a new value on the visible board
    public void updateBoard(int key, String value){
        if (key < 101) {
            this.board.put(key, value);
        } else { System.out.println("ERROR: Board/UpdateBoard - You tried to update the board with a value > 101"); }
    }


    // Fills the 'board' HashMap with tildes from key value 1 to 101
    private void createCleanBoard(){
        for (int i = 1; i < 101; i++){
            board.put(i, "💧");
        }
    }



}
