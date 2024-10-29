package com.web.service.impl;


import com.web.dto.ClubDto;
import com.web.model.Club;
import com.web.repository.ClubRepository;
import com.web.service.ClubService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClubsServiceImpl implements ClubService {

    private ClubRepository clubRepository;

    public ClubsServiceImpl(ClubRepository clubRepository) {
        this.clubRepository = clubRepository;
    }

    @Override
    public List<ClubDto> findAllClubs() {
        List<Club> clubs = clubRepository.findAll();
        return clubs.stream().map(club -> mapToClubDto(club)).collect(Collectors.toList());
    }

    @Override
    public Club saveClub(Club club) {
        return clubRepository.save(club);
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
