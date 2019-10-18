package com.example.muzix.controller;

import com.example.muzix.model.Track;
import com.example.muzix.service.TrackService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@WebMvcTest
public class controllerTest {
  @Autowired
  private MockMvc mockMvc;
  private Track track;
  @MockBean
  private TrackService trackService;
  @InjectMocks
  private TrackController trackController;

  private List<Track> trackList = null;

  @Before
  public void setUp() {

    MockitoAnnotations.initMocks(this);
    mockMvc = MockMvcBuilders.standaloneSetup(trackController).build();
    Track track1 = new Track("track1", "album1", (float) 3.12, 200);
    trackList = new ArrayList();
    trackList.add(track1);
  }

  @Test
  public void saveTrack() throws Exception {
    when(trackService.addTrack(any())).thenReturn(track);
    mockMvc.perform(MockMvcRequestBuilders.post("/add")
      .contentType(MediaType.APPLICATION_JSON).content(asJsonString(track)))
      .andExpect(MockMvcResultMatchers.status().isOk())
      .andDo(MockMvcResultHandlers.print());
  }

  @Test
  public void getTrackById() throws Exception {
    Track track2 = new Track("onfire", "firenation", (float) 3.14, 300);
    track2.setTrackId(2);
    when(trackService.getTrackById(1)).thenReturn(track);
    mockMvc.perform(MockMvcRequestBuilders.post("/add")
      .contentType(MediaType.APPLICATION_JSON).content(asJsonString(track2)));
    mockMvc.perform(MockMvcRequestBuilders.get("/track/1"))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.content().string(asJsonString(track2)));


  }

  @Test
  public void getAllTrack() throws Exception {
    when(trackService.getAllTracks()).thenReturn(trackList);
    mockMvc.perform(MockMvcRequestBuilders.get("/")
      .contentType(MediaType.APPLICATION_JSON).content(asJsonString(track)))
      .andExpect(MockMvcResultMatchers.status().isOk())
      .andDo(MockMvcResultHandlers.print());
  }

  private static String asJsonString(final Object obj) {
    try {
      return new ObjectMapper().writeValueAsString(obj);

    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

}
