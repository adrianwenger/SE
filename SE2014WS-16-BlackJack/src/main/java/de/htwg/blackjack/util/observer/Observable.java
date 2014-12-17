package de.htwg.blackjack.util.observer;

import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author Adrian Wenger
 */
public class Observable implements IObservable {


    /**
     * List to save Subscriber Objects.
     */
    private final List<IObserver> subscribers = new ArrayList<IObserver>();

    /**
     * add single observer to subscriber list.
     *
     * @param s Obersver
     */
    public final void addObserver(final IObserver s) {
        if (!subscribers.contains(s)) {
        subscribers.add(s);
    }
    }

    /**
     * notify each observer in subscriber List.
     */
    public final void notifyObservers() {
        for (IObserver observer : subscribers) {
            observer.update();
        }
    }

    /**
     * remove specific Subscriber.
     * @param s subscriber
     */
    public void removeObserver(IObserver s) {
        subscribers.remove(s);
    }

    /**
     *
     * @return list of subscribers
     */
    public final List<IObserver> getSubscribers() {
        return subscribers;
}

}
