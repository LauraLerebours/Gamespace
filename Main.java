/* 
This is the main class that uses the rest of the classes to simulate the game server
called "Gamespace".
@version: 6/8/22
@author: Laura Lerebours
*/
import java.util.Scanner;
class Main {
	public static void main(String[] args) throws InterruptedException {
		Scanner play = new Scanner(System.in);
		Animation test = new Animation("WELCOME TO GAMESPACE");
		test.loadingScreen();
		boolean error = true;
		do {
			Animation question = new Animation("Welcome to the arena. Type 1 for tic tac toe, 2 for connect 4, 3 for rock paper scissors, and 4 for the memory game.");
			question.slowType();
			int choice = play.nextInt();
			if (choice > 4 || choice < 1) {
				Animation wrong = new Animation("Invalid Number dumbo. Thats not 1, 2, 3, or 4.");
				wrong.slowType();
			} else {
				error = false;
				if (choice == 1) {
					Animation welcome = new Animation("Welcome to tic tac toe. If you want to go to home type 0 at anytime.");
					welcome.slowType();
					try {
						Thread.sleep(1000);
					} catch (InterruptedException ex) {
						Thread.currentThread().interrupt();
					}
					Tic game = new Tic();
					game.playGame(play);
				} else if (choice == 2) {
					Animation welcome = new Animation("Welcome to Connect 4. If you want to go to home type 0 at anytime.");
					welcome.slowType();
					try {
						Thread.sleep(1000);
					} catch (InterruptedException ex) {
						Thread.currentThread().interrupt();
					}
					Connect game = new Connect();
					game.playGame(play);
				} else if(choice == 3){
					Animation welcome = new Animation("Welcome to Rock Paper Scissors. If you want to go to home type 0 at anytime.");
					welcome.slowType();
					try {
						Thread.sleep(1000);
					} catch (InterruptedException ex) {
						Thread.currentThread().interrupt();
					}
					RPS game = new RPS();
				} else if(choice == 4){
					new Memory();
				}
			}
		} while (error);
	}
}