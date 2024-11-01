package com.web.repository;

import com.web.entity.Club;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClubRepository extends JpaRepository<Club, Long> {

    Optional<Club> findByTitle(String url);

    @Query("select c from Club c where c.title like concat('%',:query,'%')")
    List<Club> searchClubs(String query);
}
