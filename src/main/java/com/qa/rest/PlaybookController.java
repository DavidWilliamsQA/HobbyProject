package com.qa.rest;

import com.qa.domain.Playbook;
import com.qa.dto.PlaybookDTO;
import com.qa.services.PlaybookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;
import java.util.Set;

@RestController
public class PlaybookController {

    public final PlaybookService service;

    @Autowired
    public PlaybookController(PlaybookService service) {
        this.service = service;
    }

    @GetMapping("/getAllPlaybooks")
    public ResponseEntity<List<PlaybookDTO>> getAllPlaybooks(){
        return ResponseEntity.ok(this.service.readPlaybooks());
    }

    @PostMapping("/createPlaybooks")
    public ResponseEntity<PlaybookDTO> createPlaybook(@RequestBody Playbook playbook){
        return new ResponseEntity<PlaybookDTO>(this.service.createPlaybook(playbook), HttpStatus.CREATED);
    }

    @PutMapping("/addPlaysToPlaybook/{id}")
    public ResponseEntity<PlaybookDTO> addPlaysToPlaybook(@PathVariable Long id, @RequestBody Long play){
        return ResponseEntity.ok(this.service.addPlays(id, play));
    }

    @DeleteMapping("/deletePlaybook/{id}")
    public ResponseEntity<?> deletePlaybook(@PathVariable Long id){
        return this.service.deletePlaybook(id)
                ? ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build()
                : ResponseEntity.noContent().build();
    }

    @GetMapping("/getPlaybookById/{id}")
    public ResponseEntity<PlaybookDTO> getPlaybookById(@PathVariable Long id){
        return ResponseEntity.ok(this.service.findPlaybookById(id));
    }

    @PutMapping("/updatePlaybook/{id}")
    public ResponseEntity<PlaybookDTO> updatePlaybookById(@PathVariable Long id, @RequestBody Playbook playbook){
        return ResponseEntity.ok(this.service.updatePlaybook(id, playbook));
    }

    @PutMapping("/updatePlaybook2")
    public ResponseEntity<PlaybookDTO> updatePlaybook(@PathParam("id") Long id, @RequestBody Playbook playbook){
        return ResponseEntity.ok(this.service.updatePlaybook(id, playbook));
    }
}
