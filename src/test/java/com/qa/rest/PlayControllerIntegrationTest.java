package com.qa.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.domain.Plays;
import com.qa.dto.PlayDTO;
import com.qa.repo.PlaysRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.request;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class PlayControllerIntegrationTest {

    @Autowired
    private MockMvc mock;

    @Autowired
    private PlaysRepository repository;

    @Autowired
    private ModelMapper mapper;

    private ObjectMapper objectMapper = new ObjectMapper();
    private Plays testPlays;
    private Plays testPlaysWithId;
    private Long id;
    private PlayDTO playDTO;

    private PlayDTO mapToDTO(Plays plays){
        return this.mapper.map(plays, PlayDTO.class);
    }

    @Before
    public void setUp(){
        this.repository.deleteAll();
        this.testPlays = new Plays("test play for integration test");
        this.testPlaysWithId = this.repository.save(testPlays);
        this.id = testPlaysWithId.getId();
        this.playDTO = this.mapToDTO(testPlaysWithId);
    }

    @Test
    public void getAllPlaysTest() throws Exception{
        List<PlayDTO> playDTOList = new ArrayList<>();
        playDTOList.add(playDTO);
        String content = this.mock.perform(
                request(HttpMethod.GET, "/getAllPlays")
                .accept(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();
        assertEquals(content, this.objectMapper.writeValueAsString(playDTOList));
    }

    @Test
    public void getPlaysById() throws Exception{
        String content = this.mock.perform(
                request(HttpMethod.GET, "/getPlayById/" + this.id)
                .accept(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();
        assertEquals(content, this.objectMapper.writeValueAsString(playDTO));
    }

    @Test
    public void createPlayTest() throws Exception{
        String content = this.mock.perform(
                request(HttpMethod.POST, "/createPlay")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(this.objectMapper.writeValueAsString(testPlays))
                        .accept(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString();
        assertEquals(content, this.objectMapper.writeValueAsString(playDTO));
    }

    @Test
    public void deletePlayTest() throws Exception{
        this.mock.perform(
                request(HttpMethod.DELETE, "/deletePlay/" + this.id)
        ).andExpect(status().isNoContent());
    }


}
