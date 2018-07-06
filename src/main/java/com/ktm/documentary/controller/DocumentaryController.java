package com.ktm.documentary.controller;

import com.ktm.ApiConstants;
import com.ktm.documentary.model.Documentary;
import com.ktm.documentary.service.DocumentaryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/documentary")
@RefreshScope
@Api(tags = ApiConstants.DOCUMENTARY, description = "Retrieve Documentary from Data Source")
public class DocumentaryController {

  @Value("#{'${documentary.nepal.youtube}'.split(',')}")
  private final List<String> youTubeDocumentary = new ArrayList<>();
  @Value("#{'${documentary.nepal.vimeo}'.split(',')}")
  private final List<String> vimeoDocumentary = new ArrayList<>();
  @Autowired
  private DocumentaryService documentaryService;

  @GetMapping
  @CrossOrigin(origins = "http://localhost:4200")
  @ApiOperation("Retrieve all Documentary Videos")
  public List<Documentary> getAllDocumentaryVideos() throws IOException {
    return this.documentaryService.getDocumentaryVideos(this.youTubeDocumentary);
  }

  @GetMapping("/{id}")
  @CrossOrigin(origins = "http://localhost:4200")
  @ApiOperation("Return a Single Documentary Video by Video Id")
  public Documentary getDocumentaryVideoById(@PathVariable String id) throws IOException {
    return this.documentaryService.getDocumentaryVideoByVideoId(id);
  }

}
