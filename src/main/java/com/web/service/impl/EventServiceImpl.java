package com.web.service.impl;


import com.web.dto.EventDto;
import com.web.mapper.EventMapper;
import com.web.model.Club;
import com.web.model.Event;
import com.web.repository.ClubRepository;
import com.web.repository.EventRepository;
import com.web.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.web.mapper.EventMapper.mapToEvent;

@Service
public class EventServiceImpl implements EventService {

    private EventRepository eventRepository;
    private ClubRepository clubRepository;

    @Autowired
    public EventServiceImpl(EventRepository eventRepository, ClubRepository clubRepository) {
        this.eventRepository = eventRepository;
        this.clubRepository = clubRepository;
    }

    @Override
    public void createEvent(Long clubId, EventDto eventDto) {
        Club club = clubRepository.findById(clubId).get();
        Event event = mapToEvent(eventDto);
        event.setClub(club);
        eventRepository.save(event);
    }

    @Override
    public List<EventDto> findAllEvents() {
        return eventRepository.findAll().stream().map(EventMapper::mapToEventDto).collect(Collectors.toList());
    }

    @Override
    public EventDto findByEventId(Long eventId) {
        return eventRepository.findById(eventId).map(EventMapper::mapToEventDto).orElse(null);
    }
}
