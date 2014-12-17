package de.htwg.blackjack.util.observer;

import java.util.List;


/**
 *
 * @author Adrian Wenger
 */
public interface IObservable {


    /**
     * add a subscriber.
     *
     * @param s subscriber
     */
    void addObserver(final IObserver s);

    /**
     * notify the subscribers about change in subject.
     */
    void notifyObservers();

    /**
     *
     * @return list of subscribers
     */
    List<IObserver> getSubscribers();

    /**
     * remove specific Subscriber.
     *
     * @param s subscriber
     */
    void removeObserver(final IObserver s);

}
