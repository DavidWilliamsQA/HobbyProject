package com.qa.rest;

import com.qa.domain.Plays;
import com.qa.dto.PlayDTO;
import com.qa.services.PlayService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class PlayControllerUnitTest {

    @InjectMocks
    private PlayController playController;

    @Mock
    private PlayService playService;

    private List<Plays> plays;
    private Plays testPlays;
    private Plays testPlaysWithId;
    private Long id = 1L;
    private PlayDTO playDTO;

    private final ModelMapper mapper = new ModelMapper();

    private PlayDTO mapToDTO(Plays plays){
        return this.mapper.map(plays, PlayDTO.class);
    }

    @Before
    public void setUp(){
        this.plays = new ArrayList<>();
        this.testPlays = new Plays("this is a test play");
        this.plays.add(testPlays);
        this.testPlaysWithId = new Plays(testPlays.getDescription());
        this.testPlaysWithId.setId(this.id);
        this.playDTO = this.mapToDTO(testPlaysWithId);
    }

    @Test
    public void getAllPlaysTest(){
        when(playService.readPlays()).thenReturn(this.plays.stream().map(this::mapToDTO).collect(Collectors.toSet()));
        assertFalse("No play found", this.playController.getAllPlays().getBody().isEmpty());
        verify(this.playService, times(1)).readPlays();
    }

    @Test
    public void createPlaysTest(){
        when(this.playService.createPlays(testPlays)).thenReturn(this.playDTO);
        assertEquals(this.playController.createPlay(testPlays), new ResponseEntity<PlayDTO>(this.playDTO, HttpStatus.CREATED));
        verify(this.playService, times(1)).createPlays(testPlays);
    }

    @Test
    public void deletePlayTestFalse(){
        this.playController.deletePlay(id);
        verify(playService, times(1)).deletePlay(id);
    }

    @Test
    public void deletePlayTestTrue(){
        when(playService.deletePlay(3L)).thenReturn(true);
        this.playController.deletePlay(3L);
        verify(playService, times(1)).deletePlay(3L);
    }

    @Test
    public void getPlayByIdTest(){
        when(this.playService.findPlayById(id)).thenReturn(this.playDTO);
        assertEquals(this.playController.getPlayById(id), new ResponseEntity<PlayDTO>(this.playDTO,HttpStatus.OK));
        verify(playService, times(1)).findPlayById(id);
    }

    @Test
    public void updatePlayTest(){
        this.playController.updatePlay(id, testPlays);
        verify(playService, times(1)).updatePlay(id, testPlays);

    }

    @Test
    public void updatePlayByIdTest(){
        this.playController.updatePlayById(id, testPlays);
        verify(playService, times(1)).updatePlay(id, testPlays);

    }
}
