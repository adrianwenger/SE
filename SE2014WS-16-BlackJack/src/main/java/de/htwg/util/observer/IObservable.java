package de.htwg.util.observer;

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
     * remove subscriber.
     * @param s subscriber
     */
    void removeObserver(IObserver s);

    /**
     * remove all subscribers.
     */
    void removeAllObservers();

    /**
     * notify the subscribers about change in subject.
     */
    void notifyObservers();
}
