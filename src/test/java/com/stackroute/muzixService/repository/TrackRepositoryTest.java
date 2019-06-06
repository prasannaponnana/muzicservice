package com.stackroute.muzixService.repository;

import com.stackroute.muzixService.domain.Track;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class TrackRepositoryTest {
@Autowired
private TrackRepository trackRepository;
    private Track track;

    @Before
    public void setUp() throws Exception {

        track = new Track();
        track.setTrackId(5);
        track.setTrackName("ganna");
        track.setTrackComments("good");

    }


    @After
    public void tearDown() throws Exception {

        trackRepository.deleteAll();
    }



   /* @Test
    public void trackByName() {
    }*/
   @Test
   public void testSaveUser(){
       trackRepository.save(track);
       Track fetchTrack = trackRepository.findById(track.getTrackId()).get();
       Assert.assertEquals(5,fetchTrack.getTrackId());

   }

    @Test
    public void testSaveUserFailure(){
        Track testTrack = new Track(2,"music","music application");
        trackRepository.save(track);
        Track fetchTrack = trackRepository.findById(track.getTrackId()).get();
        Assert.assertNotSame(testTrack,track);
    }

    @Test
    public void testGetAllUser(){
        Track track = new Track(5,"ganna","good");
        Track track1 = new Track(4,"music","track");
        trackRepository.save(track);
        trackRepository.save(track1);

        List<Track> list = trackRepository.findAll();
        Assert.assertEquals("music",list.get(0).getTrackName());

    }
    @Test
    public void testDeleteTrack(){
        Track track=new Track(4,"music","nice");
        trackRepository.delete(track);
        boolean deletedTrack=trackRepository.existsById(14);
        assertEquals(false,deletedTrack);
    }
    @Test
    public void testDeleteTrackFailure(){
        Track track = new Track();
        trackRepository.delete(track);
        boolean trackDelete=trackRepository.existsById(4);
        Assert.assertNotSame(true,trackDelete);
    }
    //test for update operation
    @Test
    public void testUpdateTrackSuccess() {
        trackRepository.save(track);
        Track fetchTrack = trackRepository.findById(track.getTrackId()).get();
        assertEquals(5, fetchTrack.getTrackId());

    }

    @Test
    public void testUpdateTrackFailure() {
        Track track= new Track(1, "music", "track");
        trackRepository.save(track);
        Track fetchTrack = trackRepository.findById(track.getTrackId()).get();
        Assert.assertNotSame(track, fetchTrack);
    }

}