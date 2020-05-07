package com.qa.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.domain.Playbook;
import com.qa.dto.PlayDTO;
import com.qa.dto.PlaybookDTO;
import com.qa.repo.PlaybookRepository;
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
public class PlaybookControllerIntegrationTest {

    @Autowired
    private MockMvc mock;

    @Autowired
    private PlaybookRepository repository;

    @Autowired
    private ModelMapper mapper;

    private ObjectMapper objectMapper = new ObjectMapper();
    private Playbook testPlaybook;
    private Playbook testPlaybookWithId;
    private Long id;
    private PlaybookDTO playbookDTO;

    private PlaybookDTO mapToDTO(Playbook playbook){
        return this.mapper.map(playbook, PlaybookDTO.class);
    }

    @Before
    public void setUp(){
        this.repository.deleteAll();
        this.testPlaybook = new Playbook("test playbook");
        this.testPlaybookWithId = this.repository.save(testPlaybook);
        this.id = testPlaybookWithId.getId();
        this.playbookDTO = this.mapToDTO(testPlaybookWithId);
    }

    @Test
    public void getAllPlaybooksTest() throws Exception{
        List<PlaybookDTO> playbookDTOList = new ArrayList<>();
        playbookDTOList.add(playbookDTO);
        String content = this.mock.perform(
                request(HttpMethod.GET, "/getAllPlaybooks")
                        .accept(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();
        assertEquals(content, this.objectMapper.writeValueAsString(playbookDTOList));
    }

    @Test
    public void getPlaybooksById() throws Exception{
        String content = this.mock.perform(
                request(HttpMethod.GET, "/getPlaybookById/" + this.id)
                        .accept(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();
        assertEquals(content, this.objectMapper.writeValueAsString(playbookDTO));
    }

    @Test
    public void createPlaybookTest() throws Exception{
        String content = this.mock.perform(
                request(HttpMethod.POST, "/createPlaybooks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(this.objectMapper.writeValueAsString(testPlaybook))
                        .accept(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString();
        assertEquals(content, this.objectMapper.writeValueAsString(playbookDTO));
    }

    @Test
    public void deletePlaybookTest() throws Exception{
        this.mock.perform(
                request(HttpMethod.DELETE, "/deletePlaybook/" + this.id)
        ).andExpect(status().isNoContent());
    }


}
