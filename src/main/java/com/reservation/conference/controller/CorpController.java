package com.reservation.conference.controller;

import com.reservation.conference.dto.CorpJoinDto;
import com.reservation.conference.service.CorpService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.reservation.conference.utils.HttpResponses.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/corp")
public class CorpController {

    private final CorpService corpService;


    @PostMapping("/join")
    public ResponseEntity join(@RequestBody CorpJoinDto corpJoinDto) throws Exception {

       if(corpService.join(corpJoinDto)) {
           return RESPONSE_CREATED;
       }
       return RESPONSE_BAD_REQUEST;
    }

}
