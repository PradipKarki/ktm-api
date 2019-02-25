package com.ktm.rest.documentary.controller;

import static com.ktm.rest.ApiConstants.DOCUMENTARY;

import com.ktm.rest.documentary.model.Documentary;
import com.ktm.rest.documentary.service.DocumentaryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/documentary")
@RefreshScope
@Api(tags = DOCUMENTARY, value = DOCUMENTARY)
public class DocumentaryController {

  @Autowired private DocumentaryService documentaryService;

  @GetMapping
  @CrossOrigin(origins = "http://localhost:4200")
  @ApiOperation("Retrieve all Documentary Videos")
  public List<Documentary> getAllDocumentaryVideos() {
    return documentaryService.retrieveAll();
  }

  @GetMapping("/{id}")
  @CrossOrigin(origins = "http://localhost:4200")
  @ApiOperation("Return a Single Documentary Video by Video Id")
  public Optional<Documentary> getDocumentaryVideoById(@PathVariable String id) {
    return documentaryService.read(id);
  }
}
