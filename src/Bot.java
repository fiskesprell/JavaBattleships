import java.util.ArrayList;
import java.util.Random;
public class Bot extends Player {
    ArrayList<Integer> usedGuesses = new ArrayList<>();
    // The "Root" hit that the "algorithm" bases itself off of
    // When guessing the next number. Only update this if exploringHit = false.
    int lastHit = 0;

    // 0 = Right, 1 = Down, 2 = Left, 3 = up.
    int direction = 0;
    boolean exploringHit = false;
    
    // Is this rly needed? TODO: Delete MaYBE?
    boolean isFlipped = false;
    boolean lastGuessHit = false;
    int correctGuessesCounter = 1;
    

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


    // Generates random number to guess location of ships.
    // Generates numbers from 1 to 100 until it finds one
    // that is not previously used and returns it.
    private int guessRandomly(){
    	Random r = new Random();
	int randomNumber;
	while (true){
		randomNumber = r.nextInt(101);
		if (!usedGuesses.contains(randomNumber) && 0 != randomNumber){
			break;
		}
	}
	return randomNumber;
    }

    private boolean guessesCanContinueDirection(){
	boolean canContinue = true;
	switch (this.direction){
		// Right
		case (0):
			canContinue = !(this.lastHit % 10 == 0);
			break;
		// Down
		case (1):
			canContinue = !((this.lastHit + 10) > 100);
			break;
		// Left
		case (2):
			canContinue = !(this.lastHit % 10 == 1);
			break;
		// Up
		case (3):
			canContinue = !((this.lastHit - 10) < 0);
			break;
	}
	return canContinue;
    }

    private void flipGuessDirection(){
    	switch (this.direction){
		case (0):
			this.direction = 2;
			break;
		case (1):
			this.direction = 3;
			break;
		case (2):
			this.direction = 0;
			break;
		case (3):
			this.direction = 1;
			break;
	}
    }

    private int keepGuessingCurrentDirection(){
    	int returnGuess = 0;
	switch (this.direction){
		case (0):
			returnGuess = lastHit + (1 * this.correctGuessesCounter);
			break;
		case (1):
			returnGuess = lastHit + (10 * this.correctGuessesCounter);
			break;
		case (2):
			returnGuess = lastHit - (1 * this.correctGuessesCounter);
			break;
		case (3):
			returnGuess = lastHit - (10 * this.correctGuessesCounter);
			break;
	}
	return returnGuess;
    }

	

    // Some thoughts:
    // This happens every new turn.
    // Guess is generated randomly UNLESS it previously hit.
    // Therefore: if previouslyHit -> !generateRandomly
    // also if previouslyHit -> tryRight
    // also if previouslyHit && !tryRight -> tryDown etc.
    //

    public void botGuesses(Player player){
	int potentialHit = 0;
	int guess = guessRandomly();
	
	
	// What happens when a previous shot hits
	if (this.exploringHit == true){
		// If (cant continue in direction):
		// change to opposite direction.
		if (!guessesCanContinueDirection()){
			if (isFlipped = false){
				this.correctGuessesCounter = 0;
				flipGuessDirection();
				this.isFlipped = true;
			} else {
				this.exploringHit = false;
				this.isFlipped = false;
			}
		} else if (!this.lastGuessHit){
			flipGuessDirection();
			this.isFlipped = true;
			this.correctGuessesCounter = 1;
		}
		else {
			guess = keepGuessingCurrentDirection();
			this.correctGuessesCounter++;
		}

		// if last hit was miss, and isFlipped = true
		// exploringHit = false
		if (!this.lastGuessHit && isFlipped){
			this.exploringHit = false;
			this.correctGuessesCounter = 1;
		}
	}
	
	potentialHit = player.takeAHit(guess);
	usedGuesses.add(guess);

	if (potentialHit != 0){
		if (!this.exploringHit){
			this.lastHit = potentialHit;
		}
		this.exploringHit = true;
		this.lastGuessHit = true;
	}
    }

}
