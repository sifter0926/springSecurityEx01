package org.pgm.securitydemo.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.pgm.securitydemo.domain.Board;
import org.pgm.securitydemo.domain.User;
import org.pgm.securitydemo.repository.BoardRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
@Transactional
@Log4j2
public class BoardServiceImpl implements BoardService {
    private final BoardRepository boardRepository;

    @Override
    public void insert(Board board, User user) {
        log.info("insert board"+user);
        board.setUser(user);
        boardRepository.save(board);
    }

    @Override
    public List<Board> list() {
        return boardRepository.findAll();
    }

    @Override
    public Board findById(Long num) {
        Board board = boardRepository.findById(num).get();
        board.setHitcount(board.getHitcount() + 1);
        boardRepository.save(board);
        return board;
    }

    @Override
    public void update(Board board, User user) {
        Board oldBoard = boardRepository.findById(board.getNum()).get();
        oldBoard.setUser(user);
        oldBoard.setTitle(board.getTitle());
        oldBoard.setContent(board.getContent());
        boardRepository.save(oldBoard);
    }

    @Override
    public void delete(Long num) {
        boardRepository.deleteById(num);
    }
}
