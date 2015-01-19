package de.htwg.blackjack.util.observer;

import de.htwg.blackjack.controller.IBlackJackController;
import de.htwg.blackjack.controller.impl.BlackJackController;
import java.util.List;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


public class ObservableTest {


    private boolean updated = false;
    private TestObserver testObserver;
    private Observable testObservable;
    private IBlackJackController controller;


    class TestObserver implements IObserver {


        @Override
        public void update() {
            updated = true;
        }

    }

    @Before
    public void setUp() throws Exception {
        testObserver = new TestObserver();
        testObservable = new Observable();
        testObservable.addObserver(testObserver);
        this.controller = new BlackJackController();
    }

    @Test
    public void testRemove() {
        assertFalse(updated);
        testObservable.removeObserver(testObserver);
        testObservable.notifyObservers();
        assertFalse(updated);
    }

    @Test
    public void testGetSubscribers() {
        this.controller.addObserver(testObserver);

        List<IObserver> observers = this.controller.getSubscribers();
        
        boolean result = observers.contains(testObserver);
        
        assert (result);
    }

}
