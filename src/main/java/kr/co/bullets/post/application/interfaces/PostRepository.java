package kr.co.bullets.post.application.interfaces;

import kr.co.bullets.post.domain.Post;

import java.util.Optional;

public interface PostRepository {

    Post save(Post post);

    Optional<Post> findById(Long id);
}
