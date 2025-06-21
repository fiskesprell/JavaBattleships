import java.util.ArrayList;
import java.util.Random;
public class Bot extends Player {
    ArrayList<Integer> usedGuesses = new ArrayList<>();
    // The "Root" hit that the "algorithm" bases itself off of
    // When guessing the next number. Only update this if exploringHit = false.
    int rootHit = 0;
    // 0 = Right, 1 = Down, 2 = Left, 3 = up.
    int direction = 0;
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
			randomNumber = r.nextInt(1,101);
			if (!usedGuesses.contains(randomNumber)){
				break;
			}
			System.out.println("Infinite B"); // TODO: Delete
		}
		return randomNumber;
    }

	private boolean isGuessValid(int guess){
		return guess >= 1 && guess <= 100 && !usedGuesses.contains(guess);
	}

    private boolean guessesCanContinueDirection(int coordinate){
		switch (this.direction){
			// Right
			case (0): return !(coordinate % 10 == 0);
			// Down
			case (1): return (coordinate < 91);
			// Left
			case (2): return !(coordinate % 10 == 1);
			// Up
			case (3): return (coordinate > 10);
			default: return false;
		}
    }

    private void flipGuessDirection(){
		correctGuessesCounter = 1;
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
		switch (this.direction){
			case (0):
				return rootHit + (1 * this.correctGuessesCounter);
			case (1):
				return rootHit + (10 * this.correctGuessesCounter);
			case (2):
				return rootHit - (1 * this.correctGuessesCounter);
			case (3):
				return rootHit - (10 * this.correctGuessesCounter);
			default: return 0;
		}
    }

	public void botGuesses(Player player){
		int guessCoordinate = guessRandomly(); // is overwritten if in exploring stage

		// Stage 3: check for miss. If found; reset values & return to random mode.
		if (3 == exploringStage){
			if (0 == lastGuessHitInt){
				correctGuessesCounter = 1;
				exploringStage = 0;
				direction = 0;
				rootHit = 0;
			}
		}

		// Stage 2: check for miss. If found; flip direction and go to step 3.
		if (2 == exploringStage){
			if (0 == lastGuessHitInt){
				flipGuessDirection();
				exploringStage = 3;
			}
		}

		// Stage 1: Check to the right of root.
		//		- if hit: go to stage 2
		//		- if miss: increase
		if (1 == exploringStage && lastGuessHitInt != rootHit && 0 != lastGuessHitInt){
			exploringStage = 2;
		}

		// Action to happen at the end of every exploring stage
		if (0 < exploringStage){
			// If you are at stage 1 and the next coordinate you
			// want to guess is Out Of Bounds, change direction.
			if (1 == exploringStage){
				int nextGuess = 0;
				while (!isGuessValid(nextGuess) && 0 != nextGuess) {
					nextGuess = keepGuessingCurrentDirection();
					if (!isGuessValid(nextGuess)){
						direction++;
					}

					// TODO: Delete print statemnet
					System.out.println("Infinite A");
				}
			}

			// If you are at stage 2 and the next coordinate you
			// want to guess is Out of Bounds, go to stage 3 (reset)
			if (2 == exploringStage){
				if (!guessesCanContinueDirection(keepGuessingCurrentDirection())){
					exploringStage = 3;
				}
			}


			// TODO: This results in an infinite. FIX IT ASAP.
			if (guessesCanContinueDirection(keepGuessingCurrentDirection())){
				guessCoordinate = keepGuessingCurrentDirection();
				while (usedGuesses.contains(guessCoordinate)){
					guessCoordinate = keepGuessingCurrentDirection();
					System.out.println("Infinite C");
				}
			} else {
				System.out.println("Tried to make an invalid guess @ coord: " + guessCoordinate);
			}
		}


		// Finalizes the actual guess.
		// Returns 0 if it misses
		// Returns coordinate if it hits
		lastGuessHitInt = player.takeAHit(guessCoordinate);
		usedGuesses.add(guessCoordinate);


		// Checks if finalized guess hit (during exploration mode)
		// If it does, increase the correctGuessesCounter by 1
		if (0 != lastGuessHitInt && 0 < exploringStage){
			correctGuessesCounter++;
		}

		// Checks if the finalized guess hit (during random mode)
		// If it did, activate exploration mode & set the root.
		// If already in exploring mode, it does not set a new root on hit.
		if (0 != lastGuessHitInt && 0 == exploringStage){
			rootHit = lastGuessHitInt;
			exploringStage = 1;
			direction = 0;
		}

		// Checks if finalized guess missed (during exploration mode)
		// if it does, reset correctGuessesCounter to 1
		// and increase direction by 1
		if (0 == lastGuessHitInt && 0 < exploringStage){
			correctGuessesCounter = 1;
			direction++;
		}


		// TODO: DELETE - Here for Debugging purposes
		System.out.println("BOT GUESSED: " + String.valueOf(guessCoordinate));
	}


}
