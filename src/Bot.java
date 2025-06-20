import java.util.ArrayList;
import java.util.Random;
public class Bot extends Player {
    ArrayList<Integer> usedGuesses = new ArrayList<>();
    // The "Root" hit that the "algorithm" bases itself off of
    // When guessing the next number. Only update this if exploringHit = false.
    int rootHit = 0;

    // 0 = Right, 1 = Down, 2 = Left, 3 = up.
    int direction = 0;
    boolean exploringHit = false;
    
    // Is this rly needed? TODO: Delete MaYBE?
    boolean isFlipped = false;
    boolean lastGuessHit = false;
	int lastGuessHitInt = 0;
	int exploringStage = 0;

	// This needs to be minimum 1 max 5

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
			if (!usedGuesses.contains(randomNumber)){
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
				canContinue = !(this.rootHit % 10 == 0);
				break;
			// Down
			case (1):
				canContinue = !((this.rootHit + 10) > 100);
				break;
			// Left
			case (2):
				canContinue = !(this.rootHit % 10 == 1);
				break;
			// Up
			case (3):
				canContinue = !((this.rootHit - 10) < 0);
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
				returnGuess = rootHit + (1 * this.correctGuessesCounter);
				break;
			case (1):
				returnGuess = rootHit + (10 * this.correctGuessesCounter);
				break;
			case (2):
				returnGuess = rootHit - (1 * this.correctGuessesCounter);
				break;
			case (3):
				returnGuess = rootHit - (10 * this.correctGuessesCounter);
				break;
		}
		return returnGuess;
    }

	public void botGuesses(Player player){
		int potentialRoot = 0;
		// Generates a random guess (Does not actually play the guess)
		int guess = guessRandomly();


		if (0 < exploringStage){
			switch (exploringStage) {
				// Stage 1: Test all directions until a second hit is found.
				case (1): {
					int attempts = 0;
					while (!guessesCanContinueDirection() && 0 == lastGuessHitInt && attempts < 4){
						if (direction < 3)
							direction++;
						else
							direction = 0;

						attempts++;
					}

					if (0 != lastGuessHitInt){
						exploringStage = 2;
					}
					break;
				}

				// Stage 2: Flip once one end of the ship is reached.
				case (2): {
					if (!guessesCanContinueDirection() || 0 == lastGuessHitInt){
						flipGuessDirection();
						exploringStage = 3;
					}
					break;
				}

				// Stage 3: Continue until second miss then reset values
				case (3): {
					if (!guessesCanContinueDirection() || 0 == lastGuessHitInt){
						exploringStage = 0;
						rootHit = 0;
						direction = 0;
					}
					break;
				}
			}
			// Stage 4: Once missed during stage 3, return to random guesses
			if (!(0 == exploringStage)) {
				guess = keepGuessingCurrentDirection();
			}
		}

		// Plays the guess
		potentialRoot = player.takeAHit(guess);

		// If we are exploring a root hit - update correctGuessesCounter.
		if (0 != exploringStage && 0 != potentialRoot){
			correctGuessesCounter++;
		} else {
			correctGuessesCounter = 1;
		}

		if (0 != exploringStage) {
			lastGuessHitInt = potentialRoot;
		}

		// Only update the root when not exploring a current root & a hit is found.
		if (0 == exploringStage && 0 != potentialRoot){
			rootHit = potentialRoot;
			exploringStage = 1;
		}

		// Adds the played guess to usedGuesses
		usedGuesses.add(guess);
	}


}
