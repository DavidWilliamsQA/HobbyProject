package com.qa.rest;

import com.qa.domain.Playbook;
import com.qa.domain.Plays;
import com.qa.dto.PlayDTO;
import com.qa.dto.PlaybookDTO;
import com.qa.services.PlayService;
import com.qa.services.PlaybookService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.*;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class PlaybookControllerUnitTest {

    @InjectMocks
    private PlaybookController playbookController;

    @Mock
    private PlaybookService playbookService;

    private Set<Playbook> playbooks;
    private Playbook testPlaybook;
    private Playbook testPlaybookWithId;
    private Long id = 1L;
    private PlaybookDTO playbookDTO;
    private Plays testPlay;

    private final ModelMapper mapper = new ModelMapper();

    private PlaybookDTO mapToDTO(Playbook playbook){
        return this.mapper.map(playbook, PlaybookDTO.class);
    }

    @Before
    public void setUp(){
        this.playbooks = new HashSet<>();
        this.testPlaybook = new Playbook("Tyrants");
        this.playbooks.add(testPlaybook);
        this.testPlaybookWithId = new Playbook(testPlaybook.getName());
        this.testPlaybookWithId.setId(this.id);
        this.playbookDTO = this.mapToDTO(testPlaybookWithId);
        this.testPlay = new Plays("Trap");
    }

    @Test
    public void getAllPlaybooksTest(){
        when(playbookService.readPlaybooks()).thenReturn(this.playbooks.stream().map(this::mapToDTO).collect(Collectors.toList()));
        assertFalse("Playbook not found", Objects.requireNonNull(this.playbookController.getAllPlaybooks().getBody()).isEmpty());
        verify(playbookService, times(1)).readPlaybooks();
    }

    @Test
    public void createPlaybookTest(){
        when(this.playbookService.createPlaybook(testPlaybook)).thenReturn(this.playbookDTO);
        assertEquals(this.playbookController.createPlaybook(testPlaybook), new ResponseEntity<PlaybookDTO>(this.playbookDTO, HttpStatus.CREATED));
        verify(this.playbookService, times(1)).createPlaybook(testPlaybook);
    }

    @Test
    public void deletePlaybookTestFalse(){
        this.playbookController.deletePlaybook(id);
        verify(playbookService, times(1)).deletePlaybook(id);
    }

    @Test
    public void deletePlaybookTestTrue(){
        when(playbookService.deletePlaybook(3L)).thenReturn(true);
        this.playbookController.deletePlaybook(3L);
        verify(playbookService, times(1)).deletePlaybook(3L);
    }

    @Test
    public void getPlayByIdTest(){
        when(this.playbookService.findPlaybookById(id)).thenReturn(this.playbookDTO);
        assertEquals(this.playbookController.getPlaybookById(id), new ResponseEntity<PlaybookDTO>(this.playbookDTO, HttpStatus.OK));
        verify(playbookService, times(1)).findPlaybookById(id);
    }

    @Test
    public void updatePlaybook(){
        this.playbookController.updatePlaybook(id, testPlaybook);
        verify(playbookService, times(1)).updatePlaybook(id, testPlaybook);

    }

    @Test
    public void updatePlaybookById(){
        this.playbookController.updatePlaybookById(id, testPlaybook);
        verify(playbookService, times(1)).updatePlaybook(id, testPlaybook);

    }

    @Test
    public void addPlayToPlaybook(){
        this.playbookController.addPlaysToPlaybook(id, testPlay.getId());
        verify(playbookService, times(1)).addPlays(id, testPlay.getId());
    }

    @Test
    public void deletePlayFromPlaybook(){
        this.playbookController.deletePlayFromPlaybook(id, testPlay.getId());
        verify(playbookService, times(1)).deletePlays(id, testPlay.getId());
    }

}
