package com.web.controller;

import com.web.dto.EventDto;
import com.web.entity.Event;
import com.web.service.EventService;
import com.web.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class EventController {

    private EventService eventService;
    private UserService userService;

    @Autowired
    public EventController(EventService eventService, UserService userService) {
        this.eventService = eventService;
        this.userService = userService;
    }

    @GetMapping("/events")
    public String eventList(Model model) {
        userService.userForFrontEnd(model);
        model.addAttribute("events", eventService.findAllEvents());
        return "events-list";
    }

    @GetMapping("/events/{clubId}/new")
    public String createEventForm(@PathVariable("clubId") Long clubId, Model model) {
        Event event = new Event();
        model.addAttribute("event", event);
        model.addAttribute("clubId", clubId);
        return "events-create";
    }

    @GetMapping("/events/{eventId}")
    public String viewEvent(@PathVariable("eventId") Long eventId, Model model) {
        userService.userForFrontEnd(model);
        model.addAttribute("event", eventService.findByEventId(eventId));
        return "events-detail";
    }

    @GetMapping("/events/{eventId}/edit")
    public String editEventForm(@PathVariable("eventId") long eventId, Model model) {
        userService.userForFrontEnd(model);
        model.addAttribute("event", eventService.findByEventId(eventId));
        return "events-edit";
    }

    @GetMapping("/events/{eventId}/delete")
    public String deleteEvent(@PathVariable("eventId") long eventId) {
        eventService.delete(eventId);
        return "redirect:/events";
    }

    @PostMapping("/events/{clubId}")
    public String createEventForm(@PathVariable("clubId") Long clubId,
                                  @Valid @ModelAttribute("event") EventDto eventDto,
                                  BindingResult result,
                                  Model model) {
        if (result.hasErrors()) {
            model.addAttribute("clubId", clubId);
            model.addAttribute("event", eventDto);
            return "events-create";
        }
        eventService.createEvent(clubId, eventDto);
        return "redirect:/clubs/" + clubId;
    }

    @PostMapping("/events/{eventId}/edit")
    public String editEvent(@PathVariable("eventId") long eventId,
                            @Valid @ModelAttribute("event") EventDto editedEventDto,
                            BindingResult result,
                            Model model) {
        if (result.hasErrors()) {
            model.addAttribute("eventId", eventId);
            model.addAttribute("event", editedEventDto);
            return "events-edit";
        }
        EventDto eventDto = eventService.findByEventId(eventId);
        editedEventDto.setId(eventId);
        editedEventDto.setClub(eventDto.getClub());
        eventService.updateEvent(editedEventDto);
        return "redirect:/events";
    }

}
