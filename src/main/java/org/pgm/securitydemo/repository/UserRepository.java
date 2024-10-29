package org.pgm.securitydemo.repository;

import org.pgm.securitydemo.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
    //select * from tbl_user where username="abcd";
}
