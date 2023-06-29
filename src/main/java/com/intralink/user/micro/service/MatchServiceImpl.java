package com.intralink.user.micro.service;

import com.intralink.user.micro.model.Match;
import com.intralink.user.micro.repository.MatchRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Transactional
@Service
public class MatchServiceImpl implements MatchService{

    @Autowired
    MatchRepository matchRepository;

    @Override
    public Match save(Match match) throws Exception {
        return matchRepository.save(match);
    }
}
