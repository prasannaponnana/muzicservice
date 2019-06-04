package com.stackroute.muzixService.config;

import com.stackroute.muzixService.domain.Track;
import com.stackroute.muzixService.repository.TrackRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

    @Component
    public class  CommandLineAppStartupRunner  implements CommandLineRunner{

        @Value("${track.trackId}")
        private int trackId;
        @Value("${track.trackName}")
        private String trackName;
        @Value("${track.track}")
        private String trackComments;

        @Autowired
        private TrackRepository trackRepository;
        Track track=new Track();


        public CommandLineAppStartupRunner(TrackRepository trackRepository){
            this.trackRepository=trackRepository;
        }

        @Override
        public void run(String... args) throws Exception{
            track.setTrackId(trackId);
            track.setTrackName(trackName);
            track.setTrackComments(trackComments);
            trackRepository.save(track);
        }

    }

