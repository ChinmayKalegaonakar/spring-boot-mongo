package com.example.muzix.repository;

import com.example.muzix.model.Artist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArtistRepository extends JpaRepository<Artist,Integer> {
  @Query(value = "SELECT ARTIST_ID FROM SONGMAPPING WHERE TRACK_ID = :trackId",nativeQuery = true)
  List<Integer> getArtistOfTrack(@Param("trackId") int trackId);
}
