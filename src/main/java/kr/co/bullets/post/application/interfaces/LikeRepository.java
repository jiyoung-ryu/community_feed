package kr.co.bullets.post.application.interfaces;

import kr.co.bullets.post.domain.Post;
import kr.co.bullets.post.domain.comment.Comment;
import kr.co.bullets.user.domain.User;

public interface LikeRepository {
    boolean checkLike(Post post, User user);

    boolean checkLike(Comment post, User user);

    void like(Post post, User user);

    void like(Comment comment, User user);

    void unlike(Post post, User user);

    void unlike(Comment comment, User user);
}
