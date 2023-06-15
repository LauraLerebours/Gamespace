
/* 
This is the Connect class that uses Spot objects to play connect 4. 
@version: 6/8/22
@author: Laura Lerebours
*/
import java.util.Scanner;
class Connect {
	private Spot[][] board = new Spot[6][7];
	public Connect() {
		for (int r = 0; r < board.length; r++) {
			for (int c = 0; c < board[r].length; c++) {
				Spot temp = new Spot(' ');
				board[r][c] = temp;
			}
		}
	}
	public void playGame(Scanner connect) throws InterruptedException {
		System.out.println(this.toString());
		int i = 1;
		while (i < 42 && !checkWin()) {
			promptPlayer(connect, i);
			System.out.println(this.toString());
			i++;
		}
		if (i == 42) {
			Animation tie = new Animation("It's a tie");
			tie.slowType();
		} else if (checkWin()) {
			if (i % 2 == 0) {
				Animation win = new Animation("Player 1, YOU WON");
				win.slowType();
			} else {
				Animation win = new Animation("Player 2, YOU WON");
				win.slowType();
			}
		}
		returnToHome(connect);
	}
	public void promptPlayer(Scanner connect, int count) throws InterruptedException {
		int player = 0;
		char shape = ' ';
		if (count % 2 == 0) {
			player = 2;
			shape = 'X';
		} else {
			player = 1;
			shape = '0';
		}
		boolean moron = true;
		do {
			Animation question = new Animation(
					"Player " + player + ", please type what column you want your '" + shape + "' to go in");
			question.slowType();
			int col = connect.nextInt() - 1;
			if (col > 7) {
				Animation wrong = new Animation(
						"Invalid Number dumbo. Thats not in the board. Remember: if you want to go back to home type 0");
				wrong.slowType();
			} else {
				moron = false;
				Spot temp = new Spot(shape);
				System.out.println();
				if (col == -1) {
					returnToHome(connect);
				} else if (findRow(col) == -1) {
					Animation wrong = new Animation(
							"Row is filled pick a new row. Remember: if you want to go back to home type 0");
					wrong.slowType();
					promptPlayer(connect, count);
				} else {
					board[findRow(col)][col] = temp;
				}
			}
		} while (moron);
	}
	public int findRow(int column) {
		for (int i = 5; i > 0; i--) {
			if (!(board[i][column].isFilled())) {
				return i;
			}
		}
		return -1;
	}
	public boolean checkWin() {
		return checkLines() || checkDiag();
	}
	public boolean checkLines() {
		for (int r = 0; r < board.length; r++) {
			for (int c = 0; c < 4; c++) {
				if ((board[r][c].isFilled()) && (board[r][c].equalsto(board[r][c + 1]) && board[r][c].equalsto(board[r][c + 2])
						&& board[r][c].equalsto(board[r][c + 3]))) {
					return true;
				}
			}
		}
		for (int r = 0; r < 3; r++) {
			for (int c = 0; c < board[r].length; c++) {
				if ((board[r][c].isFilled()) && (board[r][c].equalsto(board[r + 1][c]) && board[r][c].equalsto(board[r + 2][c])
						&& board[r][c].equalsto(board[r + 3][c]))) {
					return true;
				}
			}
		}
		return false;
	}
	public boolean checkDiag() {
		for (int r = 0; r < 3; r++) {
			for (int c = 6; c > 2; c--) {
				if ((board[r][c].isFilled()) && (board[r][c].equalsto(board[r + 1][c - 1])
						&& board[r][c].equalsto(board[r + 2][c - 2]) && board[r][c].equalsto(board[r + 3][c - 3]))) {
					return true;
				}
			}
		}
		for (int r = 0; r < 3; r++) {
			for (int c = 0; c < 4; c++) {
				if ((board[r][c].isFilled()) && (board[r][c].equalsto(board[r + 1][c + 1])
						&& board[r][c].equalsto(board[r + 2][c + 2]) && board[r][c].equalsto(board[r + 3][c + 3]))) {
					return true;
				}
			}
		}
		return false;
	}
	public void reset() {
		for (int r = 0; r < board.length; r++) {
			for (int c = 0; c < board[r].length; c++) {
				board[r][c] = new Spot(' ');
			}
		}
	}
	public void returnToHome(Scanner game) throws InterruptedException {
		boolean moron = true;
		do {
			Animation ending = new Animation("Type 1 to play Connect 4, Type 2 to play Tic Tac Toe, Type 3 to play Rock Paper Scissors,Type 4 to play memory, and type 0 to end");
			ending.slowType();
			int input = game.nextInt();
			if (input > 4 || input < 0) {
				Animation wrong = new Animation("Invalid number dumbo. That's not 1,2,3,4, or 0.");
				wrong.slowType();
			} else {
				moron = false;
				if (input == 1) {
					reset();
					playGame(game);
				} else if (input == 2) {
					reset();
					Tic game2 = new Tic();
					game2.playGame(game);
				} else if (input == 3) {
					Animation welcome = new Animation("Welcome to Rock Paper Scissors. If you want to go to home type 0 at anytime.");
					welcome.slowType();
					new RPS();
				} else if (input == 4){
					new Memory();
				} else if (input == 0) {
					Animation goodByeSequence = new Animation("GOODBYE WE WILL MISS YOU");
					goodByeSequence.loadingScreen();
					System.out.println(":)");
					System.exit(0);
				}
			}
		} while (moron);
	}
	public String toString() {
		String game = "  1  2  3  4  5  6  7\n";
		for (int r = 0; r < board.length; r++) {
			game = game + (r + 1);
			for (int c = 0; c < board[r].length; c++) {
				game = game + "[" + board[r][c] + "]";
			}
			game = game + "\n";
		}
		return game;
	}
}