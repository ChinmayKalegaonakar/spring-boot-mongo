package com.example.muzix.repository;

import com.example.muzix.model.Track;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface TrackRepository extends MongoRepository<Track,Integer> {

  @Query(value = "select * from TRACK where TRACK_NAME like %:trackName%",nativeQuery = true)
  List<Track> findByTrackName(@Param("trackName") String trackName);

  @Query(value = "select * from Track t where t.track_album like %?1%",nativeQuery = true)
  List<Track> findByTrackAlbum(String trackAlbum);

}
