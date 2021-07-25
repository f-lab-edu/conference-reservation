package com.reservation.conference.mapper;

import com.reservation.conference.dto.CorpJoinDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface CorpMapper {

    boolean insertCorp(CorpJoinDto corpJoinDto);

    CorpJoinDto findById(@Param("id") String id);
}
