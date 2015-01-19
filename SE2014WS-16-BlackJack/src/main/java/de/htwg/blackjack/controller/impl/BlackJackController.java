package de.htwg.blackjack.controller.impl;

import com.google.inject.Injector;
import de.htwg.blackjack.controller.IBlackJackController;
import de.htwg.blackjack.controller.ICalcProfitController;
import de.htwg.blackjack.controller.IGameState;
import de.htwg.blackjack.model.IDeck;
import de.htwg.blackjack.model.IPlayer;
import static de.htwg.blackjack.util.StaticCollections.BLACKJACK;
import de.htwg.blackjack.util.observer.Observable;


/**
 *
 * @author Adrian Wenger, Philipp Schultheiß
 */
/**
 * Der Controller muss beide die View und das Model kennen. da dieser für die
 * Kommunikation zwischen den Beiden sorgt
 */
public final class BlackJackController extends Observable
        implements IBlackJackController {


    /**
     * Deck Object to be created.
     */
    private IDeck deck;
    /**
     * Player Object to be created.
     */
    private IPlayer player;
    /**
     * Player Object (Dealer) to be created.
     */
    private IPlayer dealer;
    /**
     * statusLine.
     */
    private String statusLine;

    /**
     * saves current Game state of BlackJack.
     */
    private IGameState currentState;

    /**
     * Set up Google Guice Dependency Injector. Build up the application,
     * resolving dependencies automatically by Guice
     */
    private Injector injector;
          
    /**
     * Create CalcProfitController.
     */
    private ICalcProfitController calcController
            = new CalcProfitController(this);


    /**
     * setInjector.
     * @param injector injector from BlackJack.java
     */
    @Override
    public void setInjector(Injector injector) {
        this.injector = injector;
    }

    /**
     *
     * @param calcCont ICalcProfitController
     */
    public void setCalcController(final ICalcProfitController calcCont) {
        this.calcController = calcCont;
    }

    /**
     * this.deck = new Deck(numOfDeck); before DependencyInjection.
     *
     * @param numOfDeck number of Decks
     */
    @Override
    public void setDeck(final int numOfDeck) {
        this.deck = injector.getInstance(IDeck.class);
        this.deck.setNumOfDecks(numOfDeck);
    }

    /**
     *
     * @param pla Player
     */
    @Override
    public void setPlayer(final String pla) {
        this.player = injector.getInstance(IPlayer.class);
        this.player.setName(pla);
    }

    /**
     * set Dealer.
     */
    @Override
    public void setDealer() {
        this.dealer = injector.getInstance(IPlayer.class);
        this.dealer.setName("Dealer");
    }

    /**
     *
     * @param status statusLine
     */
    @Override
    public void setStatusLine(final String status) {
        this.statusLine = status;
        notifyObservers();
    }

    /**
     * change game state.
     *
     * @param state GameState
     */
    @Override
    public void setCurrentState(final IGameState state) {
        this.currentState = state;
        notifyObservers();
    }

    /**
     *
     * @return calcController
     */
    public ICalcProfitController getCalcController() {
        return calcController;
    }

    /**
     *
     * @return deck
     */
    @Override
    public IDeck getDeck() {
        return deck;
    }

    /**
     *
     * @return player
     */
    @Override
    public IPlayer getPlayer() {
        return player;
    }

    /**
     *
     * @return dealer
     */
    @Override
    public IPlayer getDealer() {
        return dealer;
    }

    /**
     *
     * @return statusLine
     */
    public String getStatusLine() {
        return statusLine;
    }

    /**
     *
     * @return printPlayersHand
     */
    @Override
    public String getFirstTwoCardsPlayer() {
        player.add(deck.dealCard());
        player.add(deck.dealCard());

        return (player.printPlayersHand());
    }

    /**
     *
     * @return printPlayersHand
     */
    @Override
    public String getFirstTwoCardsDealer() {
        dealer.add(deck.dealCard());
        dealer.add(deck.dealCard());

        return (dealer.printPlayersHand());
    }

    /**
     *
     * @return printPlayersHand
     */
    @Override
    public String getCardPlayer() {
        player.add(deck.dealCard());

        return (player.printPlayersHand());
    }

    /**
     *
     * @return printPlayersHand
     */
    @Override
    public String getCardDealer() {
        dealer.add(deck.dealCard());

        return (dealer.printPlayersHand());
    }

    /**
     *
     * @return current GameState
     */
    @Override
    public IGameState getCurrentState() {
        return currentState;
    }

    /**
     * checks if Dealer needs another card, after Player got his card.
     *
     * @return true if Dealer took card
     */
    @Override
    public boolean checkIfDealerNeedsCard() {
        int valPlayer = this.player.getValue();
        int valDealer = this.dealer.getValue();

        boolean bol1 = valPlayer <= BLACKJACK;
        boolean bol2 = valDealer < BLACKJACK;
        boolean bol3 = valDealer <= valPlayer;

        if (bol1 && bol2 && bol3) {
            getCardDealer();
            return true;
        }
        return false;
    }

    /**
     * Check BlackJack.
     *
     * @param subject Player Object
     * @return blackjack?
     */
    @Override
    public boolean hasBlackJack(final IPlayer subject) {
        return subject.getValue() == BLACKJACK;
    }

    /**
     * Changes the IGameState Object.
     */
    @Override
    public void checkGameState() {
        // new Game. Initialize with StateInGame
        if (this.currentState == null) {
            this.setCurrentState(new StateInGame(this, calcController));
            this.currentState.change();
        } else {
            // check if GameState will change
            this.currentState.change();
        }
        calcController.calcProfit();
    }

    /**
     * creates a new round.
     */
    @Override
    public void createNewRound() {
        this.currentState = null;
        this.player.clearHand();
        // adjust Player Stake
        this.calcController.calcStake();
        this.getPlayer().setRoundStake(0);
        this.dealer.clearHand();
        this.deck = null;
        notifyObservers();
        
    }

    /**
     * returns stautsLine.
     *
     * @return statusLine
     */
    @Override
    public String output() {
        return statusLine;
    }

    /**
     * End Game.
     */
    @Override
    public void endGame() {
        // maybe Dealer needs another Card
        while (checkIfDealerNeedsCard()) {
            this.setStatusLine("Dealer is taking antoher Card:\n");
            this.setStatusLine("Dealer: ");
            this.setStatusLine(this.getDealer().printPlayersHand() + "\n");
        }

        if (this.currentState instanceof StateInGame) {
            this.currentState.change();
        }
//        // Player <= Dealer && Dealer < BLACKJACK
//        if ((this.player.getValue() <= dealer.getValue())
//                && dealer.getValue() < BLACKJACK) {
//            //setCurrentState(new StateLost(this, calcController));
//            //this.currentState.change();
//        }
        //checkGameState();
    }

}
