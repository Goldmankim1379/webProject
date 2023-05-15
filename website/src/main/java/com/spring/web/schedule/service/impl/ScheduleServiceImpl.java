package com.spring.web.schedule.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.web.schedule.service.ScheduleService;
import com.spring.web.vo.scheduleVO;

@Service("ScheduleService")
public class ScheduleServiceImpl implements ScheduleService{

	@Autowired
	private ScheduleMapper mapper;

	@Override
	public List<scheduleVO> getMainScheduleList(scheduleVO searchVO) {
		return mapper.getMainScheduleList(searchVO); 
	}

	
}
