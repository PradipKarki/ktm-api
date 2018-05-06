package com.ktm.documentary.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ktm.documentary.model.Documentary;
import com.ktm.documentary.service.DocumentaryService;

@RestController
@RequestMapping("/documentary")
@RefreshScope
public class DocumentaryController {

	@Autowired
	private DocumentaryService documentaryService;

	@Value("#{'${documentary.nepal.youtube}'.split(',')}")
	private List<String> youTubeDocumentary = new ArrayList<>();

	@Value("#{'${documentary.nepal.vimeo}'.split(',')}")
	private List<String> vimeoDocumentary = new ArrayList<>();

	@GetMapping("/{id}")
	@CrossOrigin(origins = "http://localhost:4200")
	public Documentary getDocumentaryVideoById(@PathVariable(value = "id") String documnetaryId) throws IOException {
		return this.documentaryService.getDocumentaryVideoByVideoId(documnetaryId);
	}
	
	@RequestMapping(value = "/getAll", method = RequestMethod.GET)
	@CrossOrigin(origins = "http://localhost:4200")
	public List<Documentary> getDocumentaryVideos() throws IOException {
		return this.documentaryService.getDocumentaryVideos(this.youTubeDocumentary);
	}

}
