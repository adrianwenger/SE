package de.htwg.util.observer;

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
     * @param s Obersver
     */
    public final void addObserver(final IObserver s) {
        subscribers.add(s);
    }

    /**
     * remove single observer from subscriber list.
     * @param s Observer
     */
    public final void removeObserver(final IObserver s) {
        subscribers.remove(s);
    }

    /**
     * clear subscriber list.
     */
    public final void removeAllObservers() {
        subscribers.clear();
    }

    /**
     * notify each observer in subscriber List.
     */
    public final void notifyObservers() {
        for (IObserver observer : subscribers) {
            observer.update();
        }
    }
}
