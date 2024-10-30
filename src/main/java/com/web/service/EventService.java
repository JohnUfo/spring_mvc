package com.web.service;

import com.web.dto.EventDto;

public interface EventService {

    void createEvent(Long clubId, EventDto eventDto);


}
