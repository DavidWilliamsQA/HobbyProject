package com.qa.service;

import com.qa.domain.Playbook;
import com.qa.domain.Plays;
import com.qa.dto.PlayDTO;
import com.qa.dto.PlaybookDTO;
import com.qa.exception.PlaybookNotFoundException;
import com.qa.repo.PlaybookRepository;
import com.qa.repo.PlaysRepository;
import com.qa.services.PlaybookService;
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
public class PlaybookServiceUnitTest {

    @InjectMocks
    private PlaybookService service;

    @Mock
    private PlaybookRepository repository;

    @Mock
    private PlaysRepository playsRepository;

    @Mock
    private ModelMapper mapper;

    private Plays testPlays;
    private List<Playbook> playbookList;
    private Playbook testPlaybook;
    private PlaybookDTO testPlaybookWithPlayDTO;
    private Playbook testPlaybookWithPlay;
    private Long id = 1L;
    private Long playId = 1L;
    private Playbook testPlaybookWithId;
    private PlaybookDTO playbookDTO;

    private PlaybookDTO mapToDTO(Playbook playbook){
        return this.mapper.map(playbook, PlaybookDTO.class);
    }

    @Before
    public void SetUp(){
        this.testPlays = new Plays("test Play");
        this.playbookList = new ArrayList<>();
        this.testPlaybook = new Playbook("test play");
        this.testPlaybookWithPlay = new Playbook("playbook with play");
        this.playbookList.add(testPlaybook);
        this.testPlaybookWithId = new Playbook(testPlaybook.getName());
        this.testPlaybookWithId.setId(id);
        this.playbookDTO = this.mapToDTO(testPlaybookWithId);
        this.testPlaybookWithPlay.getPlays().add(testPlays);
        this.testPlaybookWithPlayDTO = this.mapToDTO(testPlaybookWithPlay);
    }

    @Test
    public void getAllPlaybookTest(){
        when(repository.findAll()).thenReturn(this.playbookList);
        when(this.mapper.map(testPlaybookWithId, PlaybookDTO.class)).thenReturn(playbookDTO);
        assertFalse("Service returned no Notes", this.service.readPlaybooks().isEmpty());
        verify(repository,times(1)).findAll();
    }

    @Test
    public void createPlaybookTest(){
        when(repository.save(testPlaybook)).thenReturn(testPlaybookWithId);
        when(this.mapper.map(testPlaybookWithId, PlaybookDTO.class)).thenReturn(playbookDTO);
        assertEquals(this.service.createPlaybook(testPlaybook), this.playbookDTO);
        verify(repository,times(1)).save(this.testPlaybook);
    }

    @Test
    public void findPlaybookByIdTest(){
        when(this.repository.findById(id)).thenReturn(java.util.Optional.ofNullable(testPlaybookWithId));
        when(this.mapper.map(testPlaybookWithId, PlaybookDTO.class)).thenReturn(playbookDTO);
        assertEquals(this.service.findPlaybookById(this.id), this.playbookDTO);
        verify(repository,times(1)).findById(id);
    }

    @Test
    public void deletePlaybookByExistingId(){
        when(this.repository.existsById(id)).thenReturn(true,false);
        assertFalse(service.deletePlaybook(id));
        verify(repository,times(1)).deleteById(id);
        verify(repository,times(2)).existsById(id);
    }

    @Test(expected = PlaybookNotFoundException.class)
    public void deletePlaybookByNonExistingId(){
        when(this.repository.existsById(id)).thenReturn(false);
        service.deletePlaybook(id);
        verify(repository, times(1)).existsById(id);
    }

    @Test
    public void updatePlaybookTest(){
        when(repository.findById(id)).thenReturn(java.util.Optional.ofNullable(testPlaybook));
        testPlaybook.setName(testPlaybook.getName());
        when(this.repository.save(testPlaybook)).thenReturn(testPlaybookWithId);
        when(this.mapper.map(testPlaybookWithId, PlaybookDTO.class)).thenReturn(playbookDTO);
        assertEquals(this.service.updatePlaybook(id, testPlaybook), this.playbookDTO);
        verify(repository,times(1)).save(this.testPlaybook);
    }

    @Test
    public void addPlaysToPlaybook(){
        when(repository.findById(id)).thenReturn(java.util.Optional.ofNullable(testPlaybook));
        when(playsRepository.findById(testPlays.getId())).thenReturn(java.util.Optional.ofNullable(testPlays));
        testPlaybook.getPlays().add(testPlays);
        when(this.repository.save(testPlaybook)).thenReturn(testPlaybookWithId);
        when(this.mapper.map(testPlaybookWithId, PlaybookDTO.class)).thenReturn(testPlaybookWithPlayDTO);
        assertEquals(this.service.addPlays(id, testPlays.getId()), this.testPlaybookWithPlayDTO);
        verify(repository,times(1)).save(this.testPlaybook);
    }

    @Test
    public void deletePlayFromPlaybook(){
        when(repository.findById(id)).thenReturn(java.util.Optional.ofNullable(testPlaybook));
        when(playsRepository.findById(testPlays.getId())).thenReturn(java.util.Optional.ofNullable(testPlays));
        testPlaybook.getPlays().remove(testPlays);
        when(this.repository.save(testPlaybook)).thenReturn(testPlaybookWithId);
        when(this.mapper.map(testPlaybookWithId, PlaybookDTO.class)).thenReturn(testPlaybookWithPlayDTO);
        assertEquals(this.service.addPlays(id, testPlays.getId()), this.testPlaybookWithPlayDTO);
        verify(repository,times(1)).save(this.testPlaybook);
    }


}
