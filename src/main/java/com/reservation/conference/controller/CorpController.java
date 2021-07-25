package com.reservation.conference.controller;

import com.reservation.conference.dto.CorpJoinDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.reservation.conference.utils.HttpResponses.RESPONSE_OK;

@RestController
@RequestMapping("/corp")
public class CorpController {

    /**
     *
     * 해당 API는 아직 기능구현이 되지 않았습니다.
     *
    @PostMapping("/join")
    public ResponseEntity join(@RequestBody CorpJoinDto corpJoinDto) {

        return RESPONSE_OK;
    }
     */
}
