package kr.co.bullets.post.application.interfaces;

import kr.co.bullets.post.domain.comment.Comment;

import java.util.Optional;

public interface CommentRepository {
    Comment save(Comment comment);

    Optional<Comment> findById(Long id);
}
