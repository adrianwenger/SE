package de.htwg.controller.impl;

import de.htwg.controller.impl.State;
import org.junit.After;
import org.junit.Before;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author philippschultheiss
 */
public class StateTest {
    
    private State state;
    
    @Before
    public void setUp() {
        this.state = new State();
    }
    
    @Test
    public void test() {
        assert (state instanceof State);
    }
}
