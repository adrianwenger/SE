package de.htwg.controller;

import de.htwg.model.Player;

/**
 * this class is used to implement the stateInGame.
 *
 * @author Adi
 */
public final class State {
}

/**
 *
 * @author Adrian Wenger
 */
final class StateInGame implements IGameState {

    /**
     * BlackJack Value 21.
     */
    private static final int BLACKJACK = 21;

    /**
     * IBlackJack Controller.
     */
    private final IBlackJackController controller;

    /**
     * Public Constructor.
     *
     * @param cont IBlackJackController
     */
    public StateInGame(final IBlackJackController cont) {
        this.controller = cont;
    }

    /*
     public void checkGameStatus() {
     changeGameState();
        
     if (hasBlackJack(getDealer())) {
     statusLine = "You Loose! Dealer got BlackJack! GAME OVER!";
     notifyObservers();
     System.exit(0);
     }
     if (hasBlackJack(getPlayer())) {
     statusLine = "BLACKJACK!!!!! You win!";
     notifyObservers();
     System.exit(0);
     }
     if (player.getValue() > BLACKJACK) {
     statusLine = "You loose! Value bigger than 21!";
     notifyObservers();
     System.exit(0);
     }
     if (dealer.getValue() > BLACKJACK) {
     statusLine = "You win! Dealer get bigger value than 21!";
     notifyObservers();
     System.exit(0);
     }
     if (player.getValue() < BLACKJACK && dealer.getValue() < BLACKJACK) {
     statusLine = "Take another card!";
     notifyObservers();
     }

     }

     */
    /**
     * change GameState if nessecary.
     */
    public void change() {
        // Player lost (Dealer got BlackJack)
        if (this.controller.hasBlackJack(this.controller.getDealer())) {
            controller.setCurrentState(new StateBlackJack(controller,
                    controller.getDealer()));
            // Player won (Player got BlackJack)
        } else if (this.controller.hasBlackJack(this.controller.getPlayer())) {
            controller.setCurrentState(new StateBlackJack(controller,
                    controller.getPlayer()));
        }
        // Player lost (Player > BlackJack)
        if (this.controller.getPlayer().getValue() > BLACKJACK) {
            this.controller.setCurrentState(new StateLost(controller));
            // Player won (Player < BlackJack && Dealer > BlackJack)
        } else if ((this.controller.getPlayer().getValue() < BLACKJACK
                && this.controller.getDealer().getValue() > BLACKJACK)) {
            // Player won
            this.controller.setCurrentState(new StateWon(controller));
            // Player lost (Player && Dealer > BlackJack)
        } else if (this.controller.getPlayer().getValue() > BLACKJACK
                && this.controller.getDealer().getValue() > BLACKJACK) {
            this.controller.setCurrentState(new StateLost(controller));
        } else {
            this.controller.setStatusLine("New Card?");
        }
    }
}

/**
 * @author Adrian Wenger
 */
final class StateLost implements IGameState {

    /**
     * BlackJack Controller.
     */
    private final IBlackJackController controller;

    /**
     * Public Constructor.
     *
     * @param blackJackController controller
     */
    public StateLost(final IBlackJackController blackJackController) {
        this.controller = blackJackController;
    }

    /**
     *
     */
    public void change() {
        this.controller.setStatusLine(this.controller.getPlayer().getName()
                + " you lost. Your Cards are: "
                + this.controller.getPlayer().printPlayersHand());
        System.exit(0);
    }
}

/**
 *
 * @author Adrian Wenger
 */
final class StateWon implements IGameState {

    /**
     * BlackJack Controller.
     */
    private final IBlackJackController controller;

    /**
     * Public Constructor.
     *
     * @param blackJackController controller
     */
    public StateWon(final IBlackJackController blackJackController) {
        this.controller = blackJackController;
    }

    /**
     * GameState not longer changeable.
     */
    public void change() {
        this.controller.setStatusLine("Player won!");
        System.exit(0);
    }
}

/**
 *
 * @author Adrian Wenger
 */
final class StateBlackJack implements IGameState {

    /**
     * BlackJack Controller.
     */
    private final IBlackJackController controller;
    /**
     * Player Object.
     */
    private final Player subject;

    /**
     * Public Constructor.
     *
     * @param blackJackController controller
     * @param sub Player object
     */
    public StateBlackJack(final IBlackJackController blackJackController,
            final Player sub) {
        this.controller = blackJackController;
        this.subject = sub;
    }

    /**
     * now other State available.
     */
    public void change() {
        this.controller.setStatusLine(subject.getName()
                + " reached Black Jack. Game Over!");
        System.exit(0);
    }

}
