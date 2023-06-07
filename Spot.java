/* 
This is the Spot class that holds a char in a specific spot and is used to play
Connect 4 and Tic Tac Toe.
@version: 6/8/22
@author: Laura Lerebours
*/
class Spot {
	private char shape;
	public Spot(char shape) {
		this.shape = shape;
	}
	public boolean isFilled() {
		return (shape == 'X' || shape == 'O' || shape == '0');
	}
	public boolean equalsto(Spot other) {
		return (this.shape == other.shape);
	}
	public String toString() {
		return shape + "";
	}
}