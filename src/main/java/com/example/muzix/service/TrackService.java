package com.example.muzix.service;

import com.example.muzix.exceptions.NotFoundException;
import com.example.muzix.exceptions.TrackExistsException;
import com.example.muzix.model.Track;
import com.example.muzix.repository.TrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Primary
public class TrackService implements TrackServiceInterface {

  @Autowired
  private TrackRepository trackRepository;

  public TrackService(TrackRepository trackRepository) {
    this.trackRepository = trackRepository;
  }

  public Track addTrack(Track track){
    trackRepository.save(track);
    return track;
  }

  public Track getTrackById(int id){
    return trackRepository.findById(id)
      .orElseThrow(()-> new NotFoundException(id));
  }

  public List<Track> getAllTracks(){
    return trackRepository.findAll();
  }

  public void deleteTrack(int id){
    trackRepository.deleteById(id);
  }

  public void updateTrack(Track track){
    trackRepository.save(track);
  }

  public List<Track> getTrackByName(String trackName){
    return trackRepository.findByTrackName(trackName);
  }

  public List<Track> getTrackByAlbum(String trackAlbum){
    return trackRepository.findByTrackAlbum(trackAlbum);
  }

  public void getMessage(){
    System.out.println("Ran from track service");
  }

}
