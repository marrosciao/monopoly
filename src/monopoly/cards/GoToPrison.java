package monopoly.cards;

import monopoly.Players;
/**
 * 
 * 
 * @author Giovanni Caniato, Donatello Rovizzi, Mattia Pescimoro 
 *
 */
public class GoToPrison extends Card {

	/**
	 * Constructor of GoToPrison class
	 * @param text the text
	 */
	public GoToPrison(String text) {
		super(text);
	}
	
	/**
	 * 
	 * @param players the players who must move to prison
	 * @param result the dice roll
	 */
	public void thisEffect(Players players, int result) {
		players.current().imprison();
	}
}