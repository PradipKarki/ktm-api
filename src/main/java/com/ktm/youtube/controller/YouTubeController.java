package com.ktm.youtube.controller;

import com.ktm.youtube.model.YouTubePO;
import com.ktm.youtube.service.YouTubeService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@PropertySource("classpath:messages.properties")
@RequestMapping("/youtube")
@RefreshScope
public class YouTubeController {

    @Autowired
    Environment env;

    @Autowired
    private YouTubeService youtubeService;

    @ApiResponses({@ApiResponse(code = 401, message = "Not Authorized"),
            @ApiResponse(code = 404, message = "Not found")})
    @ApiOperation(value = "Returns all YouTube videos",
            response = YouTubePO.class)
    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    @CrossOrigin(origins = "http://localhost:4200")
    public List<YouTubePO> getYouTubeVideos() throws IOException {
        String searchQueryNepal = this.env
                .getProperty("App.Nepal.SearchQueryKeyWord"); //$NON-NLS-1$
        return this.youtubeService.fetchVideosByQuery(searchQueryNepal);
    }

}
