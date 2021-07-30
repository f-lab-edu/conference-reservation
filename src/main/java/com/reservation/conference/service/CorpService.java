package com.reservation.conference.service;

import com.reservation.conference.dto.CorpJoinDto;
import com.reservation.conference.mapper.CorpMapper;
import com.reservation.conference.utils.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor    //초기화 되지 않는 final필드에 생성자 생성
public class CorpService {

    private final CorpMapper corpMapper;


    public boolean join(CorpJoinDto corpJoinDto) throws Exception {
        //아이디 중복 체크
        boolean checkResult = checkCorpIdExist(corpJoinDto.getId());
        if(!checkResult) {
            return false;
        }

        String encryptPassword = SecurityUtil.encryptPassword(corpJoinDto.getPassword());
        corpJoinDto.setPassword(encryptPassword);

        return corpMapper.insertCorp(corpJoinDto);
    }

    /**
     * 회원가입 시, 아이디 중복 체크
     */
    public boolean checkCorpIdExist(String id) {
        CorpJoinDto resultDto = corpMapper.findById(id);

        return resultDto == null;
    }

    /**
     * 회원 탈퇴
     */
    public boolean deleteCorp(String id, String password) throws Exception {
        //입력한 비밀번호 확인
        String encryptPassword = SecurityUtil.encryptPassword(password);
        CorpJoinDto corpInfo = corpMapper.findCorpByIdAndPassword(id, encryptPassword);
        if(corpInfo == null) {
            return false;
        }

        return corpMapper.deleteCorp(corpInfo.getId());
    }

}
