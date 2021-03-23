package com.sainsburys.psr.spike.controller;

import com.sainsburys.psr.spike.response.IndexResponse;
import com.sainsburys.psr.spike.services.ListenService;
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
    private final ListenService listenService;

    @RequestMapping(path = "/", produces = APPLICATION_JSON_VALUE)
    @ResponseBody
    public IndexResponse index() {
        return IndexResponse.builder()
            .status(publishService.sendMessage("chicken"))
            .build();
    }

    @RequestMapping(path = "/listen", produces = APPLICATION_JSON_VALUE)
    @ResponseBody
    public IndexResponse listen() {
        listenService.listen("badger_queue");
        listenService.listen("unicorn_queue");
        return IndexResponse.builder()
            .status(true)
            .build();
    }
}
