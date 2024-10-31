package com.web.controller;

import com.web.dto.ClubDto;
import com.web.entity.Club;
import com.web.service.ClubService;
import com.web.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ClubController {

    private ClubService clubService;
    private UserService userService;

    @Autowired
    public ClubController(ClubService clubService, UserService userService) {
        this.clubService = clubService;
        this.userService = userService;
    }

    @GetMapping("/clubs")
    public String listClubs(Model model) {
        userService.userForFrontEnd(model);
        List<ClubDto> clubs = clubService.findAllClubs();
        model.addAttribute("clubs", clubs);
        return "clubs-list";
    }

    @GetMapping("/clubs/new")
    public String createClubForm(Model model) {
        Club club = new Club();
        model.addAttribute("club", club);
        return "clubs-create";
    }

    @GetMapping("/clubs/{clubId}")
    public String clubDetail(@PathVariable("clubId") long clubId, Model model) {
        userService.userForFrontEnd(model);
        model.addAttribute("club", clubService.findClubById(clubId));
        return "clubs-detail";
    }

    @GetMapping("/clubs/{clubId}/edit")
    public String editClubForm(@PathVariable("clubId") long clubId, Model model) {
        model.addAttribute("club", clubService.findClubById(clubId));
        return "clubs-edit";
    }

    @GetMapping("/clubs/{clubId}/delete")
    public String deleteClub(@PathVariable("clubId") long clubId) {
        clubService.delete(clubId);
        return "redirect:/clubs";
    }

    @GetMapping("/clubs/search")
    public String searchClub(@RequestParam(value = "query") String query, Model model) {
        model.addAttribute("clubs", clubService.searchClubs(query));
        return "clubs-list";
    }

    @PostMapping("/clubs/new")
    public String saveClub(@Valid @ModelAttribute("club") ClubDto clubDto,
                           BindingResult result,
                           Model model) {
        if (result.hasErrors()) {
            model.addAttribute("club", clubDto);
            return "clubs-create";
        }
        clubService.saveClub(clubDto);
        return "redirect:/clubs";
    }

    @PostMapping("/clubs/{clubId}/edit")
    public String editClub(@PathVariable("clubId") long clubId,
                           @Valid @ModelAttribute("club") ClubDto clubDto,
                           BindingResult result) {
        if (result.hasErrors()) {
            return "clubs-edit";
        }
        clubDto.setId(clubId);
        clubService.editClub(clubDto);
        return "redirect:/clubs";
    }
}
