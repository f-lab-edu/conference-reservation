package com.reservation.conference.corp.mapper;

import com.reservation.conference.corp.dto.CorpJoinDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface CorpMapper {

    boolean insertCorp(CorpJoinDto corpJoinDto);

    CorpJoinDto findById(@Param("id") String id);

    CorpJoinDto findCorpByIdAndPassword(@Param("id") String id, @Param("password") String password);

    boolean deleteCorp(@Param("id") String id);

}
