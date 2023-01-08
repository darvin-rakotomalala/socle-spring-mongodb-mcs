package com.poc.controller;

import com.poc.repository.CustomMongoRepository;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "request")
public class CustomRequestController {

    private final CustomMongoRepository customMongoRepository;

    @ApiOperation(value = "WS used to execute request")
    @PostMapping
    public Object executeRequest(
            @ApiParam(name = "request", value = "{\n" +
                    "    \"find\": \"notes\",\n" +
                    "    \"batchSize\": 10,\n" +
                    "    \"filter\": {\"title\": \"Spring Data\"}\n" +
                    "}", required = true)
            @RequestBody String request) {
        return customMongoRepository.executMongoRequest(request);
    }

}
