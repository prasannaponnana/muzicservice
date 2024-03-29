package com.stackroute.muzixService.service;


import com.stackroute.muzixService.domain.Track;
import com.stackroute.muzixService.exceptions.SameCommentExists;
import com.stackroute.muzixService.exceptions.TrackAlreadyExistsException;
import com.stackroute.muzixService.exceptions.TrackNotFoundException;
import com.stackroute.muzixService.repository.TrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import springfox.documentation.annotations.Cacheable;

import java.util.List;

@Service
@Primary
@EnableCaching
class TrackServiceImpl implements TrackService{
    TrackRepository trackRepository;

    @Autowired
    public TrackServiceImpl(TrackRepository trackRepository) {
        this.trackRepository = trackRepository;
    }

    @Override
    @CacheEvict("track")
    public Track saveTracks(Track track) throws TrackAlreadyExistsException {
        if(trackRepository.existsById(track.getTrackId())){
            throw new TrackAlreadyExistsException("track already exists");
        }
        Track savedTracks = trackRepository.save(track);
        if(savedTracks == null){
            throw new TrackAlreadyExistsException("track already exists");
        }
        return savedTracks;
    }
@Cacheable("track")
    @Override
    public List<Track> getAllTracks() {
        return  trackRepository.findAll();
    }
@CachePut("track/{id}/{comments}")
    @Override
    public Track updateTrack(int trackId, String comment) throws TrackNotFoundException {

        Track track = trackRepository.getOne(trackId);
        if (track.getTrackComments().equals(comment)){
            throw new TrackNotFoundException("Same comment exists already");
        }
        track.setTrackComments(comment);
        Track updatedTrack = trackRepository.save(track);
        return updatedTrack;
    }

@CacheEvict("track/{id}")
    @Override
    public Track deleteTrack(int trackId) throws TrackNotFoundException{
        if (trackRepository.findById(trackId) == null){
            throw new TrackNotFoundException("track not found");
        }
        Track track = trackRepository.getOne(trackId);
        trackRepository.deleteById(trackId);
        return track;
    }

@CacheEvict("track")
    @Override
    public Track findTrackById(int trackId) {
            Track track = trackRepository.getOne(trackId);
            return track;
        }
        @CacheEvict("track")
    @Override
    public Track trackByName(String trackName) throws TrackNotFoundException {
        Track track = trackRepository.trackByName(trackName);
        if (track == null){
            throw new TrackNotFoundException("track the name");
        }
        return track;
    }

    }






