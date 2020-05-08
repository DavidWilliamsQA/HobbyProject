package com.qa.dto;

import com.qa.domain.Playbook;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class PlaybookDTOUnitTest {

    private PlaybookDTO playbook;
    private PlaybookDTO other;

    @Before
    public void setUp(){
        playbook = new PlaybookDTO(1L, "test play");
        other = new PlaybookDTO(1L, "test play");
    }

    @Test
    public void settersTest(){
        assertNotNull(playbook.getId());
        assertNotNull(playbook.getName());

        playbook.setId(null);
        assertNull(playbook.getId());
        playbook.setName(null);
        assertNull(playbook.getName());
    }

    @Test
    public void equalsWithNull(){
        assertFalse(playbook.equals(null));
    }

    @Test
    public void equalsWithDifferentObject(){
        assertFalse(playbook.equals(new Object()));
    }

    @Test
    public void createPlaybookWithId(){
        assertEquals(1L, playbook.getId(),0);
        assertEquals("test play", playbook.getName());
    }

    @Test
    public void checkEquality(){
        assertTrue(playbook.equals(playbook));
    }

    @Test
    public void checkEqualityBetweenDifferentObjects(){
        assertTrue(playbook.equals(other));
    }

    @Test(expected = NullPointerException.class)
    public void playbookIdNullButOtherIdNotNull(){
        playbook.setId(null);
        assertFalse(playbook.equals(other));
    }

    @Test
    public void playbookIdNotEqual(){
        other.setId(2L);
        assertFalse(playbook.equals(other));
    }

    @Test(expected = NullPointerException.class)
    public void checkEqualityBetweenDifferentObjectsNullDescription(){
        playbook.setName(null);
        other.setName(null);
        assertTrue(playbook.equals(other));
    }

    @Test
    public void constructorWithoutId(){
        PlaybookDTO playbook = new PlaybookDTO("test");
        assertNull(playbook.getId());
        assertNotNull(playbook.getName());
    }

    @Test
    public void emptyConstructor(){
        PlaybookDTO playbook = new PlaybookDTO();
        assertNull(playbook.getId());
    }

    @Test
    public void hashCodeTestWithNull(){
        PlaybookDTO playbook = new PlaybookDTO(null, null);
        PlaybookDTO other = new PlaybookDTO(null, null);
        assertEquals(playbook.hashCode(), other.hashCode());
    }

    @Test
    public void hashCodeTest(){
        assertEquals(playbook.hashCode(), other.hashCode());
    }

}
