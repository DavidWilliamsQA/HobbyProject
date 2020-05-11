package com.qa.service;

import com.qa.domain.Plays;
import com.qa.dto.PlayDTO;
import com.qa.repo.PlaysRepository;
import com.qa.services.PlayService;
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
public class PlayServiceIntegrationTest {

    @Autowired
    private PlayService service;

    @Autowired
    private PlaysRepository repository;

    @Autowired
    private ModelMapper mapper;

    private Plays testPlay;

    private Plays testPlayWithId;

    private Plays updatePlay;

    private PlayDTO mapToDTO(Plays plays){
        return this.mapper.map(plays, PlayDTO.class);
    }

    @Before
    public void setUp(){
        this.testPlay = new Plays("test");
        this.updatePlay = new Plays("update");
        this.repository.deleteAll();
        this.testPlayWithId = this.repository.save(this.testPlay);
        //this.updatePlay = this.repository.save(this.updatePlay);
    }

    @Test
    public void readPlaysTest(){
        assertThat(this.service.readPlays())
                .isEqualTo(
                        Stream.of(this.mapToDTO(testPlayWithId)).collect(Collectors.toList())
                );
    }

    @Test
    public void createPlaysTest(){
        assertEquals(this.mapToDTO(this.testPlayWithId), this.service.createPlays(testPlay));
    }

    @Test
    public void findPlayByIdTest(){
        assertThat(this.service.findPlayById(this.testPlayWithId.getId())).isEqualTo(this.mapToDTO(this.testPlayWithId));
    }

    @Test
    public void deletePlayTest(){
        assertThat(this.service.deletePlay(this.testPlayWithId.getId())).isFalse();
    }

    @Test
    public void updatePlayTest(){
        assertNotEquals(this.service.updatePlay(testPlayWithId.getId(), updatePlay), this.mapToDTO(this.testPlayWithId));
    }

}
