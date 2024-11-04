package org.pgm.securitydemo.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.pgm.securitydemo.domain.Board;
import org.pgm.securitydemo.domain.Comment;
import org.pgm.securitydemo.repository.BoardRepository;
import org.pgm.securitydemo.repository.CommentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@Log4j2
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final BoardRepository boardRepository;

    @Override
    public Comment saveComment(Comment comment) {
        Board board = boardRepository.findById(comment.getBoard().getNum()).orElseThrow();
        board.setReplycount(board.getReplycount() + 1);
        boardRepository.save(board);
        Comment savedComment = commentRepository.save(comment);
        return savedComment;
    }

    @Override
    public Comment addReply(Long parentId, Comment comment) {
        Comment parentComment = commentRepository.findById(parentId).orElseThrow();
        parentComment.addChild(comment);

        Board board = boardRepository.findById(comment.getBoard().getNum()).orElseThrow();
        board.setReplycount(board.getReplycount() + 1);
        boardRepository.save(board);

        return commentRepository.save(comment);
    }

    @Override
    public List<Comment> getReplies(Long parentId) {
        return commentRepository.findByParentId(parentId);
    }

    @Override
    public List<Comment> getTopComments(Long board_id) {
        Board board = boardRepository.findById(board_id).orElseThrow();
        return commentRepository.findByBoardAndParentIsNull(board);
    }

    @Override
    public void deleteComment(Long comment_id) {
        commentRepository.deleteById(comment_id);
    }
}
