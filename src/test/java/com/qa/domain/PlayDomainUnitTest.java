package com.qa.domain;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PlayDomainUnitTest {
    private Plays plays;
    private Plays other;

    @Before
    public void setUp(){
        plays = new Plays(1L, "test play");
        other = new Plays(1L, "test play");
    }

    @Test
    public void settersTest(){
        assertNotNull(plays.getId());
        assertNotNull(plays.getDescription());

        plays.setId(null);
        assertNull(plays.getId());
        plays.setDescription(null);
        assertNull(plays.getDescription());
    }

    @Test
    public void equalsWithNull(){
        assertFalse(plays.equals(null));
    }

    @Test
    public void equalsWithDifferentObject(){
        assertFalse(plays.equals(new Object()));
    }

    @Test
    public void createPlayWithId(){
        assertEquals(1L, plays.getId(),0);
        assertEquals("test play", plays.getDescription());
    }

    @Test
    public void checkEquality(){
        assertTrue(plays.equals(plays));
    }

    @Test
    public void checkEqualityBetweenDifferentObjects(){
        assertTrue(plays.equals(other));
    }

    @Test(expected = NullPointerException.class)
    public void playsIdNullButOtherIdNotNull(){
        plays.setId(null);
        assertFalse(plays.equals(other));
    }

    @Test
    public void playsIdNotEqual(){
        other.setId(2L);
        assertFalse(plays.equals(other));
    }

    @Test(expected = NullPointerException.class)
    public void checkEqualityBetweenDifferentObjectsNullDescription(){
        plays.setDescription(null);
        other.setDescription(null);
        assertTrue(plays.equals(other));
    }

    @Test
    public void constructorWithoutId(){
        Plays plays = new Plays("test");
        assertNull(plays.getId());
        assertNotNull(plays.getDescription());
    }

    @Test
    public void emptyConstructor(){
        Plays plays = new Plays();
        assertNull(plays.getId());
    }

    @Test
    public void hashCodeTestWithNull(){
        Plays play = new Plays(null, null);
        Plays other = new Plays(null, null);
        assertEquals(play.hashCode(), other.hashCode());
    }

    @Test
    public void hashCodeTest(){
        assertEquals(plays.hashCode(), other.hashCode());
    }

}
