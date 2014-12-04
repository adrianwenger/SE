/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package de.htwg.util.observer;

import java.util.ArrayList;
import java.util.Iterator;
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

    public final void addObserver(IObserver s) {
        subscribers.add(s);
    }

    public final void removeObserver(IObserver s) {
        subscribers.remove(s);
    }

    public final void removeAllObservers() {
        subscribers.clear();
    }

    public void notifyObservers() {
        for (Iterator<IObserver> iter = subscribers.iterator(); iter.hasNext();) {
            IObserver observer = iter.next();
            observer.update();
        }
    }
}
