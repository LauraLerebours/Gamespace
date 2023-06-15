/* 
This is the Animation class takes String objects and prints them in different ways.
@version: 6/8/22
@author: Laura Lerebours
*/
class Animation {
	private String word;
	public Animation(String word) {
		this.word = word;
	}
	public void loadingScreen() {
		String[] letters = { " ", "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r",
				"s", "t", "u", "v", "w", "x", "y", "z" };
		String end = "";
		for (int i = 0; i < word.length(); i++) {
			String temp = word.substring(i, i + 1);
			int j = 0;
			for (; j < letters.length && !temp.equalsIgnoreCase(letters[j]); j++) {
				System.out.println(end + letters[j].toUpperCase() + "\f");
				for (int k = 0; k < 10; k++) {
					System.out.println();
				}
				try {
					Thread.sleep(20);
				} catch (InterruptedException ex) {
					Thread.currentThread().interrupt();
				}
			}
			if (i == word.length() - 1) {
				for (int k = 0; k < 20; k++) {
					System.out.println();
				}
			}
			System.out.println(end + letters[j].toUpperCase() + "\f");
			end = end + letters[j].toUpperCase();
		}
		try {
			Thread.sleep(1000);
		} catch (InterruptedException ex) {
			Thread.currentThread().interrupt();
		}
	}
	public void slowType() {
		for (int i = 0; i < word.length(); i++) {
			String temp = word.substring(i, i + 1);
			System.out.print(temp);
			try {
				Thread.sleep(15);
			} catch (InterruptedException ex) {
				Thread.currentThread().interrupt();
			}
		}
		System.out.println();
	}
}