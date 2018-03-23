package com.ktm.youtube.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ktm.youtube.model.YouTubePO;
import com.ktm.youtube.service.YouTubeService;

@RestController
@RequestMapping("/youtube")
public class YouTubeController {
	 
	private static final String SEARCH_QUERY_NEPAL = "nepal";
	
	@Autowired
    private YouTubeService youtubeService;
    
    @RequestMapping(value = "/getAll", method=RequestMethod.GET)
	@CrossOrigin(origins = "http://localhost:4200")
	public List<YouTubePO> getYouTubeVideos() throws IOException {
        //get the list of YouTube videos that match the search term
        return youtubeService.fetchVideosByQuery(SEARCH_QUERY_NEPAL);
    }
	
}
