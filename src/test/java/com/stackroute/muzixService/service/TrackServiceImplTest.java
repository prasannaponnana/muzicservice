package com.stackroute.muzixService.service;

import com.stackroute.muzixService.domain.Track;
import com.stackroute.muzixService.exceptions.TrackAlreadyExistsException;
import com.stackroute.muzixService.exceptions.TrackNotFoundException;
import com.stackroute.muzixService.repository.TrackRepository;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

public class TrackServiceImplTest {
private Track track;
    //Create a mock for UserRepository
    @Mock
    private TrackRepository trackRepository;

    //Inject the mocks as dependencies into UserServiceImpl
    @InjectMocks
    private TrackServiceImpl trackService;
    List<Track> list= null;
    @Before
    public void setUp() throws Exception {

        MockitoAnnotations.initMocks(this);

        track = new Track();
        track.setTrackId(4);
        track.setTrackName("music");
        track.setTrackComments("track");

    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void saveTracks() throws TrackAlreadyExistsException {


            when(trackRepository.save((Track)any())).thenReturn(track);
            Track savedTrack = trackService.saveTracks(track);
            Assert.assertEquals(track,savedTrack);

            //verify here verifies that userRepository save method is only called once
            verify(trackRepository,times(1)).save(track);

        }



    @Test
    public void getAllTracks() {
        trackRepository.save(track);
        //stubbing the mock to return specific data
        when(trackRepository.findAll()).thenReturn(list);
        List<Track> tracklist = trackService.getAllTracks();
        Assert.assertEquals(list,tracklist);
    }


    @Test
    public void updateTrack() {
        Track updatedTrack = trackRepository.save(track);

        when(trackRepository.findAll()).thenReturn(list);
        List<Track> tracklist = trackService.getAllTracks();
        Assert.assertEquals(list,tracklist);
    }


    @Test
    public void deleteTrack() {
        Track deleteTrack = trackRepository.save(track);

        when(trackRepository.findAll()).thenReturn(list);
        List<Track> deletelist = trackService.getAllTracks();
        Assert.assertEquals(list,deletelist);
    }


    @Test
    public void findTrackById() {
        Track findTrackById = trackRepository.save(track);

        when(trackRepository.findAll()).thenReturn(list);
        List<Track> findlist = trackService.getAllTracks();
        Assert.assertEquals(list,findlist);
    }



    @Test
    public void trackByName() {
        Track  trackByName= trackRepository.save(track);

        when(trackRepository.findAll()).thenReturn(list);
        List<Track> namelist = trackService.getAllTracks();
        Assert.assertEquals(list,namelist);
    }
    }












