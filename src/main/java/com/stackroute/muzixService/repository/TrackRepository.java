package com.stackroute.muzixService.repository;

import com.stackroute.muzixService.domain.Track;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
@Repository
public interface TrackRepository extends JpaRepository<Track,Integer> {

    @Query("select track from Track track where track.trackName=:trackName")
        public Track trackByName(String trackName);


}


