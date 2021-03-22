package com.sainsburys.psr.spike.controller;

import com.sainsburys.psr.spike.response.IndexResponse;
import com.sainsburys.psr.spike.services.PublishService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Controller
@RequiredArgsConstructor
public class RootController {

    private final PublishService publishService;

    @RequestMapping(path = "/", produces = APPLICATION_JSON_VALUE)
    @ResponseBody
    public IndexResponse index() {
        return IndexResponse.builder()
            .status(publishService.sendMessage("chicken"))
            .build();
    }
}
