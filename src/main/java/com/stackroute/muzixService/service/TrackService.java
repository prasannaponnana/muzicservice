package com.stackroute.muzixService.service;


import com.stackroute.muzixService.domain.Track;
import com.stackroute.muzixService.exceptions.SameCommentExists;
import com.stackroute.muzixService.exceptions.TrackAlreadyExistsException;
import com.stackroute.muzixService.exceptions.TrackNotFoundException;

import java.util.List;

public interface TrackService {



    public Track saveTracks(Track track) throws TrackAlreadyExistsException;
    /*public Track deleteTrack(int trackId);
    public Track updateTrack(Track track);
    public Track retrivetrack(String trackName);
*/

   /* public List<Track> getAllTracks();*/

    List<Track> getAllTracks();

    Track updateTrack(int trackIid, String comment) throws TrackNotFoundException;

    Track deleteTrack(int trackId) throws TrackNotFoundException;

    Track findTrackById(int trackId);

    Track trackByName(String trackName) throws TrackNotFoundException;
}



