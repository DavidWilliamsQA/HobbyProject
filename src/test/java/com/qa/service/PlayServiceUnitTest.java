package com.qa.service;

import com.qa.domain.Plays;
import com.qa.dto.PlayDTO;
import com.qa.exception.PlayNotFoundException;
import com.qa.repo.PlaysRepository;
import com.qa.services.PlayService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
public class PlayServiceUnitTest {

    @InjectMocks
    private PlayService service;

    @Mock
    private PlaysRepository repository;

    @Mock
    private ModelMapper mapper;

    private List<Plays> playsList;
    private Plays testPlays;
    private Long id = 1L;
    private Plays testPlayWithId;
    private PlayDTO playDTO;

    private PlayDTO mapToDTO(Plays plays){
        return this.mapper.map(plays, PlayDTO.class);
    }

    @Before
    public void SetUp(){
        this.playsList = new ArrayList<>();
        this.testPlays = new Plays("test play");
        this.playsList.add(testPlays);
        this.testPlayWithId = new Plays(testPlays.getDescription());
        this.testPlayWithId.setId(id);
        this.playDTO = this.mapToDTO(testPlayWithId);
    }

    @Test
    public void getAllPlaysTest(){
        when(repository.findAll()).thenReturn(this.playsList);
        when(this.mapper.map(testPlayWithId, PlayDTO.class)).thenReturn(playDTO);
        assertFalse("Service returned no Notes", this.service.readPlays().isEmpty());
        verify(repository,times(1)).findAll();
    }

    @Test
    public void createPlayTest(){
        when(repository.save(testPlays)).thenReturn(testPlayWithId);
        when(this.mapper.map(testPlayWithId, PlayDTO.class)).thenReturn(playDTO);
        assertEquals(this.service.createPlays(testPlays), this.playDTO);
        verify(repository,times(1)).save(this.testPlays);
    }

    @Test
    public void findPlayByIdTest(){
        when(this.repository.findById(id)).thenReturn(java.util.Optional.ofNullable(testPlayWithId));
        when(this.mapper.map(testPlayWithId, PlayDTO.class)).thenReturn(playDTO);
        assertEquals(this.service.findPlayById(this.id), this.playDTO);
        verify(repository,times(1)).findById(id);
    }

    @Test
    public void deletePlayByExistingId(){
        when(this.repository.existsById(id)).thenReturn(true,false);
        assertFalse(service.deletePlay(id));
        verify(repository,times(1)).deleteById(id);
        verify(repository,times(2)).existsById(id);
    }

    @Test(expected = PlayNotFoundException.class)
    public void deletePlayByNonExistingId(){
        when(this.repository.existsById(id)).thenReturn(false);
        service.deletePlay(id);
        verify(repository, times(1)).existsById(id);
    }




}
