package org.pgm.securitydemo.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.pgm.securitydemo.domain.Comment;
import org.pgm.securitydemo.service.CommentService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comments")
@RequiredArgsConstructor
@Log4j2
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/{board_id}")
    public Comment createComment(@PathVariable("board_id") Long board_id,
                                 @RequestBody Comment comment) {

        return commentService.saveComment(board_id,comment);

    }
}
