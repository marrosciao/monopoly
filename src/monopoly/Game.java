package monopoly;

import monopoly.gui.TextGUI;

public final class Game {
	private static final int MAX_TURNS = 20;
	private static final int TURNS_TO_PRISON = 2;
	
	private Players players;
	private Dice dice;
	private int turnsPlayed;
	private byte consecutiveTurns;
	final private Board board;
	
	public Game() {
		turnsPlayed = 0;
		consecutiveTurns = 0;
		board = BoardConstructor.createBoard();
	}
	
	public void start() {
		players = TextGUI.getPlayers().shuffle();
		
		while (turnsPlayed < MAX_TURNS) {
			TextGUI.turnStartMessage(players.current());
			TextGUI.printPosition(players.current());
			
			playTurn(players.current());
			prisonManager(players.current());
			TextGUI.printPosition(players.current());

			decideNextPlayer();
			TextGUI.separator();
		}
	}
	
	private void playTurn(Player player) {
		dice = TextGUI.getDice();
		board.move(player, dice.result());
		board.action(players);
	}
	
	private void prisonManager(Player player) {
		if ((consecutiveTurns == TURNS_TO_PRISON) && dice.same()) {
			TextGUI.prisonMessage(player);
			board.moveToPrison(player);
			consecutiveTurns = 0;
		}
		else if (dice.same())
			consecutiveTurns++;
	}

	private void decideNextPlayer() {	
		if (!dice.same() || consecutiveTurns == 0) {
			players.next();
			turnsPlayed++;
			consecutiveTurns = 0;
		}
	}
}
