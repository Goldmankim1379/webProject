package com.spring.web.schedule.service.impl;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.spring.web.vo.scheduleVO;

@Mapper
public interface ScheduleMapper {

	List<scheduleVO> getMainScheduleList(scheduleVO searchVO);

}