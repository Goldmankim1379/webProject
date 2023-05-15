package com.spring.web.schedule.service;

import java.util.List;

import com.spring.web.vo.scheduleVO;

public interface ScheduleService {

	List<scheduleVO> getMainScheduleList(scheduleVO searchVO);


}