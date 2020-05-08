package com.qa.dto;

import com.qa.domain.Plays;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class PlayDTOUnitTest {

    private PlayDTO plays;
    private PlayDTO other;

    @Before
    public void setUp(){
        plays = new PlayDTO(1L, "test play");
        other = new PlayDTO(1L, "test play");
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
        PlayDTO plays = new PlayDTO("test");
        assertNull(plays.getId());
        assertNotNull(plays.getDescription());
    }

    @Test
    public void emptyConstructor(){
        PlayDTO plays = new PlayDTO();
        assertNull(plays.getId());
    }

    @Test
    public void hashCodeTestWithNull(){
        PlayDTO play = new PlayDTO(null, null);
        PlayDTO other = new PlayDTO(null, null);
        assertEquals(play.hashCode(), other.hashCode());
    }

    @Test
    public void hashCodeTest(){
        assertEquals(plays.hashCode(), other.hashCode());
    }

}
