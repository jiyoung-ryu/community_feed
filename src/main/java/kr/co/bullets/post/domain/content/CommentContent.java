package kr.co.bullets.post.domain.content;

public class CommentContent extends Content {

    private static final int MAX_CONTENT_LENGTH = 100;

    public CommentContent(String content) {
        super(content);
    }

    protected void checkLength(String content) {
        if (content == null || content.isEmpty()) {
            throw new IllegalArgumentException("content should not be null");
        }

        if (content.length() > MAX_CONTENT_LENGTH) {
            throw new IllegalArgumentException(String.format("comment should be under %d", MAX_CONTENT_LENGTH));
        }
    }
}
