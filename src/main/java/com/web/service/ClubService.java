package com.web.service;

import com.web.dto.ClubDto;
import com.web.model.Club;

import java.util.List;

public interface ClubService {
    List<ClubDto> findAllClubs();

    Club saveClub(ClubDto clubDto);

    ClubDto findClubById(long clubId);

    void editClub(ClubDto clubDto);

    void delete(long clubId);
}
