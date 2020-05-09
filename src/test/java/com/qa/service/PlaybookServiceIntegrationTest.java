package com.qa.service;


import com.qa.domain.Playbook;
import com.qa.domain.Plays;
import com.qa.dto.PlaybookDTO;
import com.qa.repo.PlaybookRepository;
import com.qa.repo.PlaysRepository;
import com.qa.services.PlaybookService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PlaybookServiceIntegrationTest {

    @Autowired
    private PlaybookService service;

    @Autowired
    private PlaybookRepository repository;

    @Autowired
    private PlaysRepository playsRepository;

    @Autowired
    private ModelMapper mapper;

    private Playbook updatePlaybook;
    private Playbook testPlaybook;
    private Playbook testPlaybookWithId;
    private Plays testPlay;
    private Long id = 1L;

    private PlaybookDTO mapToDTO(Playbook playbook){
        return this.mapper.map(playbook, PlaybookDTO.class);
    }

    @Before
    public void setUp(){
        this.testPlaybook = new Playbook("test");
        this.updatePlaybook = new Playbook("update");
        this.testPlay = new Plays("test play");
        testPlay.setId(id);
        this.repository.deleteAll();
        this.testPlaybookWithId = this.repository.save(this.testPlaybook);
        this.testPlay = this.playsRepository.save(this.testPlay);
    }

    @Test
    public void readPlaybookTest(){
        assertThat(this.service.readPlaybooks())
                .isEqualTo(
                        Stream.of(this.mapToDTO(testPlaybookWithId)).collect(Collectors.toList())
                );
    }

    @Test
    public void createPlaybookTest(){
        assertEquals(this.mapToDTO(this.testPlaybookWithId), this.service.createPlaybook(testPlaybook));
    }

    @Test
    public void findPlaybookByIdTest(){
        assertThat(this.service.findPlaybookById(this.testPlaybookWithId.getId())).isEqualTo(this.mapToDTO(this.testPlaybookWithId));
    }

    @Test
    public void deletePlaybookTest(){
        assertThat(this.service.deletePlaybook(this.testPlaybookWithId.getId())).isFalse();
    }

    @Test
    public void updatePlaybookTest(){
        assertNotEquals(this.service.updatePlaybook(testPlaybookWithId.getId(), updatePlaybook), this.mapToDTO(this.testPlaybookWithId));
    }
    @Test
    public void addPlayToPlaybookTest(){
        assertNotEquals(this.service.addPlays(testPlaybookWithId.getId(), testPlay.getId()), this.mapToDTO(this.testPlaybookWithId));
    }
}
