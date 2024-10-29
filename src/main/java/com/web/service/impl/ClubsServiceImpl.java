package com.web.service.impl;


import com.web.dto.ClubDto;
import com.web.model.Club;
import com.web.repository.ClubRepository;
import com.web.service.ClubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClubsServiceImpl implements ClubService {

    @Autowired
    private ClubRepository clubRepository;

    public ClubsServiceImpl(ClubRepository clubRepository) {
        this.clubRepository = clubRepository;
    }

    @Override
    public List<ClubDto> findAllClubs() {
        List<Club> clubs = clubRepository.findAll();
        return clubs.stream().map(this::mapToClubDto).collect(Collectors.toList());
    }

    @Override
    public Club saveClub(Club club) {
        return clubRepository.save(club);
    }

    @Override
    public ClubDto findClubById(long clubId) {
        return clubRepository.findById(clubId).map(this::mapToClubDto).orElse(null);
    }

    @Override
    public void editClub(ClubDto clubDto) {
        Club club = mapToClub(clubDto);
        clubRepository.save(club);
    }

    private Club mapToClub(ClubDto club) {
        return Club.builder()
                .id(club.getId())
                .title(club.getTitle())
                .photoUrl(club.getPhotoUrl())
                .content(club.getContent())
                .CreatedOn(club.getCreatedOn())
                .updatedOn(club.getUpdatedOn())
                .build();
    }

    private ClubDto mapToClubDto(Club club) {
        return ClubDto.builder()
                .id(club.getId())
                .title(club.getTitle())
                .photoUrl(club.getPhotoUrl())
                .content(club.getContent())
                .CreatedOn(club.getCreatedOn())
                .updatedOn(club.getUpdatedOn())
                .build();
    }
}
