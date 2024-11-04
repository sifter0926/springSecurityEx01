package org.pgm.securitydemo.repository;

import org.pgm.securitydemo.domain.Board;
import org.pgm.securitydemo.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByBoardAndParentIsNull(Board board);
    List<Comment> findByParentId(Long parentId);
}
