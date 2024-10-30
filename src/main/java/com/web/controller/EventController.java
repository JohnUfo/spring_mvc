package com.web.controller;

import com.web.dto.EventDto;
import com.web.model.Event;
import com.web.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class EventController {

    private EventService eventService;

    @Autowired
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping("/events/{clubId}/new")
    public String createEventForm(@PathVariable("clubId") Long clubId, Model model) {
        Event event = new Event();
        model.addAttribute("event", event);
        model.addAttribute("clubId", clubId);
        return "events-create";
    }

    @PostMapping("/events/{clubId}")
    public String createEventForm(@PathVariable("clubId") Long clubId,
                                  @ModelAttribute("event") EventDto eventDto,
                                  Model model) {
        eventService.createEvent(clubId, eventDto);
        return "redirect:/clubs/" + clubId;
    }

}
