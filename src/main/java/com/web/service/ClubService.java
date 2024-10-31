package com.web.service;

import com.web.dto.ClubDto;

import java.util.List;

public interface ClubService {
    List<ClubDto> findAllClubs();

    void saveClub(ClubDto clubDto);

    ClubDto findClubById(long clubId);

    void editClub(ClubDto clubDto);

    void delete(long clubId);

    List<ClubDto> searchClubs(String query);
}
