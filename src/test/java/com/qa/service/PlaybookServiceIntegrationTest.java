package com.qa.service;


import com.qa.domain.Playbook;
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

    private Playbook testPlaybook;
    private Playbook testPlaybookWithId;

    private PlaybookDTO mapToDTO(Playbook playbook){
        return this.mapper.map(playbook, PlaybookDTO.class);
    }

    @Before
    public void setUp(){
        this.testPlaybook = new Playbook("test");
        this.repository.deleteAll();
        this.testPlaybookWithId = this.repository.save(this.testPlaybook);
    }

//    @Test
//    public void readPlaybookTest(){
//        assertThat(this.service.readPlaybooks())
//                .isEqualTo(
//                        Stream.of(this.mapToDTO(testPlaybookWithId)).collect(Collectors.toSet())
//                );
//    }

    @Test
    public void createPlaybookTest(){
        assertEquals(this.mapToDTO(this.testPlaybookWithId), this.service.createPlaybook(testPlaybook));
    }

//    @Test
//    public void findPlaybookByIdTest(){
//        assertThat(this.service.findPlaybookById(this.testPlaybookWithId.getId())).isEqualTo(this.mapToDTO(this.testPlaybookWithId));
//    }

    @Test
    public void deletePlaybookTest(){
        assertThat(this.service.deletePlaybook(this.testPlaybookWithId.getId())).isFalse();
    }
}
