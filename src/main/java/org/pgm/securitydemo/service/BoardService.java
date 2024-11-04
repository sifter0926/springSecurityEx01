package org.pgm.securitydemo.service;

import org.pgm.securitydemo.domain.Board;
import org.pgm.securitydemo.domain.User;

import java.util.List;

public interface BoardService {
    void insert(Board board, User user);
    public List<Board> list();
    public Board findById(Long num);
    public void update(Board board, User user);
    public void delete(Long num);
}
