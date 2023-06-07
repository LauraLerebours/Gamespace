/* 
This is the Tic class that uses Spot objects to play Tic Tac Toe.
@version: 6/8/22
@author: Laura Lerebours
*/
import java.util.Scanner;
class Tic {
	private Spot[][] board = new Spot[3][3];
	public Tic() {
		for (int r = 0; r < board.length; r++) {
			for (int c = 0; c < board[r].length; c++) {
				Spot temp = new Spot(' ');
				board[r][c] = temp;
			}
		}
	}
	public void playGame(Scanner tic) {
		System.out.println(this.toString());
		int i = 1;
		while (i < 10 && !checkWin()) {
			promptPlayer(tic, i);
			System.out.println(this.toString());
			i++;
		}
		if (i == 10) {
			Animation win = new Animation("It's a tie");
			win.slowType();
		} else if (checkWin()) {
			if (i % 2 == 0) {
				Animation win = new Animation("Player 1, YOU WON");
				win.slowType();
			} else {
				Animation win = new Animation("Player 1, YOU WON");
				win.slowType();
			}
		}
		returnToHome(tic);
	}
	public void promptPlayer(Scanner tic, int count) {
		int player = 0;
		char shape = ' ';
		if (count % 2 == 0) {
			player = 2;
			shape = 'O';
		} else {
			player = 1;
			shape = 'X';
		}
		boolean moron = true;
		int row = 0;
		int col = 0;
		do {
			Animation question = new Animation(
					"Player " + player + ", please type what row you want your '" + shape + "' to go in");
			question.slowType();
			int rowturn = tic.nextInt() - 1;
			if (rowturn == -1) {
				returnToHome(tic);
			} else if (rowturn > 2) {
				Animation wrong = new Animation(
						"Invalid Number dumbo. Pick something that is in the board. Remember: if you want to go back to home type 0.");
				wrong.slowType();
			} else {
				moron = false;
				row = rowturn;
			}
		} while (moron);
		moron = true;
		do {
			Animation question = new Animation(
					"Player " + player + ", please type what column you want your '" + shape + "' to go in");
			question.slowType();
			int colturn = tic.nextInt() - 1;
			if (colturn == -1) {
				returnToHome(tic);
			} else if (colturn > 2) {
				Animation wrong = new Animation(
						"Invalid Number dumbo. Pick something that is in the board. Remember: if you want to go back to home type 0.");
				wrong.slowType();
			} else {
				moron = false;
				col = colturn;
			}
		} while (moron);
		Spot temp = new Spot(shape);
		if (board[row][col].isFilled()) {
			Animation wrong = new Animation("Spot is taken pick a new one. Remember: if you want to go back to home type 0.");
			wrong.slowType();
			promptPlayer(tic, count);
		} else {
			board[row][col] = temp;
		}
	}
	public boolean checkWin() {
		if (checkDiagonals() || checkLines()) {
			return true;
		}
		return false;
	}
	public boolean checkDiagonals() {
		if (board[1][1].isFilled()) {
			if (board[0][0].equalsto(board[1][1]) && board[2][2].equalsto(board[1][1])) {
				return true;
			} else if (board[0][2].equalsto(board[1][1]) && board[2][0].equalsto(board[1][1])) {
				return true;
			}
		}
		return false;
	}
	public boolean checkLines() {
		for (int i = 0; i < 3; i++) {
			if ((board[i][1].isFilled())
					&& (board[i][0].equalsto(board[i][1]) && board[i][2].equalsto(board[i][1]))) {
				return true;
			} else if ((board[1][i].isFilled())
					&& (board[0][i].equalsto(board[1][i]) && board[2][i].equalsto(board[1][i]))) {
				return true;
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
	public void returnToHome(Scanner game) {
		boolean moron = true;
		do {
			Animation ending = new Animation("Type 1 to play Connect 4, Type 2 to play Tic Tac Toe, and type 0 to end");
			ending.slowType();
			int input = game.nextInt();
			if (input > 2) {
				Animation wrong = new Animation("Invalid number dumbo. That's not 1,2, or 0.");
				wrong.slowType();
			} else {
				moron = false;
				if (input == 1) {
					reset();
					Connect game2 = new Connect();
					game2.playGame(game);
				} else if (input == 2) {
					reset();
					playGame(game);
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
		String game = "  1  2  3\n";
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