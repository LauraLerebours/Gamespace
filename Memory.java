import java.util.Scanner;

public class Memory{
    public Memory() throws InterruptedException{
        game();
    }
    public static int random(){
        return (int)(Math.random() * 9 + 1);
    }
    public static void game() throws InterruptedException{
        try (Scanner input = new Scanner(System.in)) {
            new Animation("Welcome to Memory!").slowType();
            new Animation("The game is simple: you will be shown a sequence of numbers and you have to repeat it back to me.").slowType();
            new Animation("Each number will be shown for 3 seconds.").slowType();
            new Animation("Let's begin!").slowType();
            boolean play = true;
            int count = 1;
            while(play){
                int num = 0;
                for(int i = 0; i < count; i++){
                    num *= 10;
                    num += random();
                }
                System.out.println(num);
                try{
                    Thread.sleep(3000);
                }
                catch(InterruptedException e){
                    System.out.println("Error");
                }
                clearScreen();
                new Animation("What was the sequence?").slowType();
                int guess = input.nextInt();
                if(guess == num){
                    new Animation("Correct!").slowType();
                    clearScreen();
                    count++;
                }
                else{
                    new Animation("Incorrect!").slowType();
                    new Animation("The correct sequence was " + num + ".").slowType();
                    new Animation("You got " + (count - 1) + " correct.").slowType();
                    play = false;
                }
            }
            new Animation("Thanks for playing!").slowType();
            returnToHome(input);
        }
    }
    public static void clearScreen(){
        for(int i = 0; i < 50; i++){
            System.out.println();
        }
    }
    public static void returnToHome(Scanner game) throws InterruptedException {
		boolean moron = true;
		do {
			Animation ending = new Animation("Type 1 to play Connect 4, Type 2 to play Tic Tac Toe,Type 3 to play Rock Paper Scissors, and type 0 to end");
			ending.slowType();
			int input = game.nextInt();
			if (input > 4 || input < 0) {
				Animation wrong = new Animation("Invalid number dumbo. That's not 1,2,3,4 or 0.");
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
				} else if (input == 4) {
                    new Memory();
                }else if (input == 0) {
					Animation goodByeSequence = new Animation("GOODBYE WE WILL MISS YOU");
					goodByeSequence.loadingScreen();
					System.out.println(":)");
					System.exit(0);
				}
			}
		} while (moron);
	}
}