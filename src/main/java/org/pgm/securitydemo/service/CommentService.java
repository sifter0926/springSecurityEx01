package org.pgm.securitydemo.service;

import org.pgm.securitydemo.domain.Comment;

import java.util.List;

public interface CommentService {
    public Comment saveComment(Comment comment);
    public Comment addReply(Long parentId, Comment comment);
    public List<Comment> getReplies(Long parentId);
    public List<Comment> getTopComments(Long board_id);
    public void deleteComment(Long comment_id);
}


