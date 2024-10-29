package org.pgm.securitydemo.repository;

import org.pgm.securitydemo.domain.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> {
}
