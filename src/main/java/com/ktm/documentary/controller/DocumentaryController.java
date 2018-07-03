package com.ktm.documentary.controller;

import com.ktm.documentary.model.Documentary;
import com.ktm.documentary.service.DocumentaryService;
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

@RestController
@RequestMapping("/documentary")
@RefreshScope
public class DocumentaryController {

    @Value("#{'${documentary.nepal.youtube}'.split(',')}")
    private final List<String> youTubeDocumentary = new ArrayList<>();
    @Value("#{'${documentary.nepal.vimeo}'.split(',')}")
    private final List<String> vimeoDocumentary = new ArrayList<>();
    @Autowired
    private DocumentaryService documentaryService;

    @GetMapping("/{id}")
    @CrossOrigin(origins = "http://localhost:4200")
    public Documentary getDocumentaryVideoById(@PathVariable String id) throws IOException {
        return this.documentaryService.getDocumentaryVideoByVideoId(id);
    }

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    @CrossOrigin(origins = "http://localhost:4200")
    public List<Documentary> getDocumentaryVideos() throws IOException {
        return this.documentaryService.getDocumentaryVideos(this.youTubeDocumentary);
    }

}
