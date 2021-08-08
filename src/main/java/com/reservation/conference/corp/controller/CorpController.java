package com.reservation.conference.corp.controller;

import com.reservation.conference.corp.dto.CorpJoinDto;
import com.reservation.conference.corp.service.CorpService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

import static com.reservation.conference.utils.HttpResponses.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/corp")
public class CorpController {

    private final CorpService corpService;


    @PostMapping("/join")
    public ResponseEntity join(@RequestBody CorpJoinDto corpJoinDto) throws Exception {
        System.out.println("CorpJoinDto::" + corpJoinDto.getCorpEmail());
        if(corpService.join(corpJoinDto)) {
            return RESPONSE_CREATED;
        }

        return RESPONSE_BAD_REQUEST;
    }

    @PostMapping("/deleteCorp")
    public ResponseEntity deleteCorpId(@RequestParam String password, HttpSession session) throws Exception {

        if(corpService.deleteCorp((String)session.getAttribute("id"), password)) {
            return RESPONSE_OK;
        }
        return RESPONSE_BAD_REQUEST;
    }

}
