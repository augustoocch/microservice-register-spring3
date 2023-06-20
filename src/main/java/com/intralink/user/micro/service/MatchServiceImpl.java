package com.intralink.user.micro.service;

import com.intralink.user.micro.model.Match;
import com.intralink.user.micro.model.Users;
import com.intralink.user.micro.repository.MatchRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class MatchServiceImpl implements MatchService{

    @Autowired
    MatchRepository matchRepository;

    @Override
    public Match save(Match userMatchTable) throws Exception {
        if (matchRepository.findById(userMatchTable.getIdUsr()) != null) {
            throw new Exception("User already exist");
        }else {
            return matchRepository.save(userMatchTable);
        }
    }
}
