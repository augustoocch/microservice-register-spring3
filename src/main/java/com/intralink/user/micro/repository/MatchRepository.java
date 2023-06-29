package com.intralink.user.micro.repository;

import com.intralink.user.micro.model.Match;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MatchRepository extends JpaRepository<Match, Long>{
}
