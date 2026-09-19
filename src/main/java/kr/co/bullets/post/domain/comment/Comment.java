package kr.co.bullets.post.domain.comment;

import kr.co.bullets.common.domain.PositiveIntegerCounter;
import kr.co.bullets.post.domain.Post;
import kr.co.bullets.post.domain.content.CommentContent;
import kr.co.bullets.post.domain.content.Content;
import kr.co.bullets.post.domain.like.LikeCounter;
import kr.co.bullets.user.domain.User;

import java.util.Objects;

public class Comment {
    private final Long id;
    private final Post post;
    private final User author;
    private final Content content;
    private final PositiveIntegerCounter positiveIntegerCounter;

    public Comment(Long id, Post post, User author, Content content, PositiveIntegerCounter positiveIntegerCounter) {
        if (post == null) {
            throw new IllegalArgumentException("post should not be null");
        }
        if (author == null) {
            throw new IllegalArgumentException("author should not be null");
        }
        if (content == null) {
            throw new IllegalArgumentException("content should not be null or empty");
        }

        this.id = id;
        this.post = post;
        this.author = author;
        this.content = content;
        this.positiveIntegerCounter = positiveIntegerCounter;
    }

    public Comment(Long id, Post post, User author, Content content) {
        this(id, post, author, content, new PositiveIntegerCounter());
    }

    public Comment(Long id, Post post, User author, String content) {
        this(id, post, author, new CommentContent(content), new PositiveIntegerCounter());
    }

    public void updateContent(User user, String content) {
        if (!author.equals(user)) {
            throw new IllegalArgumentException("only author can update content");
        }
        this.content.updateContent(content);
    }

    public void like(User user) {
        if (author.equals(user)) {
            throw new IllegalArgumentException("author cannot like own comment");
        }
        positiveIntegerCounter.increase();
    }

    public void unlike() {
        positiveIntegerCounter.decrease();
    }

    public Long getId() {
        return id;
    }

    public Post getPost() {
        return post;
    }

    public User getAuthor() {
        return author;
    }

    public Content getContent() {
        return content;
    }

    public int getLikeCount() {
        return positiveIntegerCounter.getCount();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Comment comment = (Comment) o;
        return Objects.equals(id, comment.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
