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
public class Observable implements IObservable{

    private List<IObserver> subscribers = new ArrayList<IObserver>(2);

    public void addObserver(IObserver s) {
        subscribers.add(s);
    }

    public void removeObserver(IObserver s) {
        subscribers.remove(s);
    }

    public void removeAllObservers() {
        subscribers.clear();
    }

    public void notifyObservers() {
        for (Iterator<IObserver> iter = subscribers.iterator(); iter.hasNext();) {
            IObserver observer = iter.next();
            observer.update();
        }
    }
    
}
