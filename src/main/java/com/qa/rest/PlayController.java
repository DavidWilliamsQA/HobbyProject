package com.qa.rest;

import com.qa.domain.Plays;
import com.qa.dto.PlayDTO;
import com.qa.services.PlayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
public class PlayController {

    private final PlayService service;

    @Autowired
    public PlayController(PlayService service) {
        this.service = service;
    }

    @GetMapping("/getAllPlays")
    public ResponseEntity<List<PlayDTO>> getAllPlays(){
        return ResponseEntity.ok(this.service.readPlays());
    }

    @PostMapping("/createPlay")
    public ResponseEntity<PlayDTO> createPlay(@RequestBody Plays plays){
        return new ResponseEntity<PlayDTO>(this.service.createPlays(plays), HttpStatus.CREATED);
    }

    @DeleteMapping("/deletePlay/{id}")
    public ResponseEntity<?> deletePlay(@PathVariable Long id){
        return this.service.deletePlay(id)
                ? ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build()
                : ResponseEntity.noContent().build();
    }

    @GetMapping("/getPlayById/{id}")
    public ResponseEntity<PlayDTO> getPlayById(@PathVariable Long id){
        return ResponseEntity.ok(this.service.findPlayById(id));
    }

    @PutMapping("/updatePlay/{id}")
    public ResponseEntity<PlayDTO> updatePlayById(@PathVariable Long id, @RequestBody Plays plays){
        return ResponseEntity.ok(this.service.updatePlay(id,plays));
    }

    @PutMapping("/updatePlay2")
    public ResponseEntity<PlayDTO> updatePlay(@PathParam("id") Long id, @RequestBody Plays plays){
        return ResponseEntity.ok(this.service.updatePlay(id, plays));
    }
}
