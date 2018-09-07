package com.byron.line.mapper;

import com.byron.line.domain.ResgisteReq;
import com.byron.line.domain.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {

    Integer findUserByNameAndPassword(User user);
    void insertUser(ResgisteReq resgisteReq);

}
