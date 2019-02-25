package com.ktm.rest.job.documentary.controller;

import static com.ktm.rest.ApiConstants.DOCUMENTARY;
import static com.ktm.utils.StringUtility.listToString;

import com.ktm.rest.job.common.JobLauncher;
import com.ktm.rest.job.documentary.job.DocumentaryApiJob;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/jobs/documentary")
@RefreshScope
@Api(tags = DOCUMENTARY, value = DOCUMENTARY)
public class DocumentaryApiController {

  @Value("#{'${documentary.nepal.youtube}'.split(',')}")
  private final List<String> youTubeDocumentary = new ArrayList<>();

  @Value("#{'${documentary.nepal.vimeo}'.split(',')}")
  private final List<String> vimeoDocumentary = new ArrayList<>();

  @Autowired private DocumentaryApiJob documentaryApiJob;

  @GetMapping("/nepal")
  @CrossOrigin(origins = "http://localhost:4200")
  @ApiOperation("Retrieve all YouTube Videos Related to Nepal News")
  public void getDocumentaryVideosAndSaveToDB() {
    JobLauncher.run(documentaryApiJob, new String[] {listToString(youTubeDocumentary)});
  }
}
