package org.pgm.securitydemo.service;

import lombok.RequiredArgsConstructor;
import org.pgm.securitydemo.domain.Board;
import org.pgm.securitydemo.domain.User;
import org.pgm.securitydemo.repository.BoardRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {
    private final BoardRepository boardRepository;

    @Override
    public void insert(Board board, User user) {
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
        board.updateHitcount();
        return board;
    }

    @Override
    public void update(Board board) {
        Board oldBoard = boardRepository.findById(board.getNum()).get();
        oldBoard.setTitle(board.getTitle());
        oldBoard.setContent(board.getContent());
        boardRepository.save(oldBoard);
    }

    @Override
    public void delete(Long num) {
        boardRepository.deleteById(num);
    }
}
