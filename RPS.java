import java.util.Scanner;

public class RPS {
    Scanner input = new Scanner(System.in);
    public RPS() throws InterruptedException{
        this.opening();
    }
    public void opening() throws InterruptedException{
        Animation rules = new Animation("The rules are simple: \nRock beats Scissors, Scissors beats Paper, and Paper beats Rock.");
        rules.slowType();
        Animation rules2 = new Animation("You will be playing against the computer. Rock = 1, Paper = 2, Scissors = 3.");
        rules2.slowType();
        Animation entry = new Animation("How many games do you want to play");
        entry.slowType();
        Scanner input = new Scanner(System.in);
        int games = input.nextInt();
        game(games);
        input.close();
    }
    public void game(int games) throws InterruptedException{
        int player = 0;
        int computer = 0;
        int playerWins = 0;
        int computerWins = 0;
        int round = 1;
        while (round <= games){
            System.out.println("Round " + round + ":");
            System.out.println("Enter your choice: ");
            try (Scanner input = new Scanner(System.in)) {
                player = input.nextInt();
            }
            computer = (int)(Math.random() * 3) + 1;
            if (!isValidEntry(player)){
                new Animation("Invalid entry. Please enter a number between 1 and 3.").slowType();
                continue;
            }
            if (player == 1 && computer == 3){
                new Animation("Rock beats Scissors!").slowType();
                new Animation("You win!").slowType();
                playerWins++;
                round++;
            }
            else if(player > computer){
                new Animation(convert(player) + " beats " + convert(computer) + "!").slowType();
                new Animation("You win!").slowType();
                playerWins++;
                round++;
            }
            else if (player == computer){
                new Animation("You both picked " + convert(player) + "!").slowType();
                new Animation("It's a tie!").slowType();
                round++;
            }
            else{
                new Animation(convert(computer) + " beats " + convert(player) + "!").slowType();
                new Animation("You lose!").slowType();
                computerWins++;
                round++;
            }
            Thread.sleep(1000);
        }
        if (playerWins > computerWins){
            System.out.println("You win the game!");
        }
        else{
            System.out.println("You lose the game!");
        }
        returnToHome(input);
    }
    public String convert(int entry){
        if (entry == 1){
            return "Rock";
        }
        else if (entry == 2){
            return "Paper";
        }
        else{
            return "Scissors";
        }
    }
    public boolean isValidEntry(int entry){
        return (entry == 1 || entry == 2 || entry == 3);
    }
    public void returnToHome(Scanner game) throws InterruptedException {
		boolean moron = true;
		do {
			Animation ending = new Animation("Type 1 to play Connect 4, Type 2 to play Tic Tac Toe,Type 3 to play Rock Paper Scissors, and type 0 to end");
			ending.slowType();
			int input = game.nextInt();
			if (input > 4 || input < 0) {
				Animation wrong = new Animation("Invalid number dumbo. That's not 1,2, or 0.");
				wrong.slowType();
			} else {
				moron = false;
				if (input == 1) {
                    Connect game1 = new Connect();
					game1.playGame(game);
				} else if (input == 2) {
					Tic game2 = new Tic();
					game2.playGame(game);
                } else if (input == 3) {
                    Animation welcome = new Animation("Welcome to Rock Paper Scissors. If you want to go to home type 0 at anytime.");
					welcome.slowType();		
                    new RPS();
				} else if(input == 4){
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

}
