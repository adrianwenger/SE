package de.htwg.blackjack.util.observer;

/**
 *
 * @author Adrian Wenger
 */
public interface IObservable {
    /**
     * add a subscriber.
     * @param s subscriber
     */
    void addObserver(IObserver s);

    /**
     * notify the subscribers about change in subject.
     */
    void notifyObservers();
}
