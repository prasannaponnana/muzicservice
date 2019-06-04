package com.stackroute.muzixService.controller;


import com.stackroute.muzixService.domain.Track;
import com.stackroute.muzixService.exceptions.SameCommentExists;
import com.stackroute.muzixService.exceptions.TrackAlreadyExistsException;
import com.stackroute.muzixService.exceptions.TrackNotAvailable;
import com.stackroute.muzixService.exceptions.TrackNotFoundException;
import com.stackroute.muzixService.service.TrackService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="api/v1")
public class TrackController {
    TrackService trackService;
    public TrackController(TrackService trackService){
        this.trackService=trackService;
    }
    @PostMapping("/track")
    public ResponseEntity<?> saveTracks(@RequestBody Track track) {
        ResponseEntity responseEntity;
        try {
            trackService.saveTracks(track);
            responseEntity = new ResponseEntity<String>("Successfully created", HttpStatus.CREATED);
        } catch (TrackAlreadyExistsException e) {
            responseEntity = new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
        }
        return responseEntity;
    }
    @PutMapping("track/{id}/{comments}")
    public ResponseEntity<?> updateTrack(@PathVariable("id") int id,@PathVariable("comments") String trackComments){
        ResponseEntity responseEntity;
        try{
            Track updatedTrack = trackService.updateTrack(id,trackComments);
            responseEntity = new ResponseEntity<Track>(updatedTrack , HttpStatus.OK);
        }
        catch (SameCommentExists ex){
            responseEntity = new ResponseEntity<String>(ex.getMessage() , HttpStatus.CONFLICT);
        }
        return responseEntity;
    }

    @DeleteMapping("/track")
    public ResponseEntity<?> deleteTrack(@PathVariable("id") int id){
        ResponseEntity responseEntity;
        try{
            Track track = trackService.deleteTrack(id);
            responseEntity = new ResponseEntity<Track>(track, HttpStatus.OK);
        }
        catch (TrackNotFoundException e){
            responseEntity = new ResponseEntity<String>(e.getMessage() , HttpStatus.NOT_FOUND);
        }
        return responseEntity;
    }

    @GetMapping("tracks")
    public ResponseEntity<?> listOfUsers() {
        ResponseEntity<?> listResponseEntity;

            List<Track> allUsers = trackService.getAllTracks();
            listResponseEntity = new ResponseEntity<List<Track>>(allUsers, HttpStatus.OK);

        return listResponseEntity;
    }

    @GetMapping("/track/{name}")
    public ResponseEntity<?> trackName(@PathVariable("name") String trackName){
        ResponseEntity responseEntity;
        try{
            Track track = trackService.trackByName(trackName);
            responseEntity = new ResponseEntity<Track>(track,HttpStatus.CREATED);
        }
        catch (TrackNotFoundException e){
            responseEntity = new ResponseEntity<String>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
        return responseEntity;
    }
}

