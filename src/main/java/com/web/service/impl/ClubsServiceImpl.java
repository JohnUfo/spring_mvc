package com.web.service.impl;


import com.web.dto.ClubDto;
import com.web.mapper.ClubMapper;
import com.web.repository.ClubRepository;
import com.web.service.ClubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.web.mapper.ClubMapper.mapToClub;

@Service
public class ClubsServiceImpl implements ClubService {

    private ClubRepository clubRepository;

    @Autowired
    public ClubsServiceImpl(ClubRepository clubRepository) {
        this.clubRepository = clubRepository;
    }

    @Override
    public List<ClubDto> findAllClubs() {
        return clubRepository.findAll().stream().map(ClubMapper::mapToClubDto).collect(Collectors.toList());
    }

    @Override
    public void saveClub(ClubDto clubDto) {
        clubRepository.save(mapToClub(clubDto));
    }

    @Override
    public ClubDto findClubById(long clubId) {
        return clubRepository.findById(clubId).map(ClubMapper::mapToClubDto).orElse(null);
    }

    @Override
    public void editClub(ClubDto clubDto) {
        clubRepository.save(mapToClub(clubDto));
    }

    @Override
    public void delete(long clubId) {
        clubRepository.deleteById(clubId);
    }

    @Override
    public List<ClubDto> searchClubs(String query) {
        return clubRepository.searchClubs(query).stream().map(ClubMapper::mapToClubDto).toList();
    }
}
