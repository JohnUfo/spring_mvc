package com.web.service;

import com.web.dto.ClubDto;
import com.web.model.Club;

import java.util.List;

public interface ClubService {
    List<ClubDto> findAllClubs();

    Club saveClub(Club club);

    ClubDto findClubById(long clubId);

    void editClub(ClubDto clubDto);
}
