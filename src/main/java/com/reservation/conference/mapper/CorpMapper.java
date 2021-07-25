package com.reservation.conference.mapper;

import com.reservation.conference.dto.CorpJoinDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CorpMapper {

    boolean insertCorp(CorpJoinDto corpJoinDto);

    boolean findById(String id);
}
