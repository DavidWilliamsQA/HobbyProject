package com.qa.service;

import com.qa.domain.Playbook;
import com.qa.dto.PlaybookDTO;
import com.qa.exception.PlaybookNotFoundException;
import com.qa.repo.PlaybookRepository;
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
    private ModelMapper mapper;

    private List<Playbook> playbookList;
    private Playbook testPlaybook;
    private Long id = 1L;
    private Playbook testPlaybookWithId;
    private PlaybookDTO playbookDTO;

    private PlaybookDTO mapToDTO(Playbook playbook){
        return this.mapper.map(playbook, PlaybookDTO.class);
    }

    @Before
    public void SetUp(){
        this.playbookList = new ArrayList<>();
        this.testPlaybook = new Playbook("test play");
        this.playbookList.add(testPlaybook);
        this.testPlaybookWithId = new Playbook(testPlaybook.getName());
        this.testPlaybookWithId.setId(id);
        this.playbookDTO = this.mapToDTO(testPlaybookWithId);
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




}
