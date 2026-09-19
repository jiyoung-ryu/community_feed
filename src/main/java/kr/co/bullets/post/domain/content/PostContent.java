package kr.co.bullets.post.domain.content;

public class PostContent extends Content {

    private static final int MIN_CONTENT_LENGTH = 5;
    private static final int MAX_CONTENT_LENGTH = 500;

    public PostContent(String content) {
        super(content);
    }

    protected void checkLength(String content) {
        if (content == null || content.isEmpty()) {
            throw new IllegalArgumentException("content should not be null");
        }

        if (content.length() < MIN_CONTENT_LENGTH) {
            throw new IllegalArgumentException(String.format("post should be over %d", MIN_CONTENT_LENGTH));
        }

        if (content.length() > MAX_CONTENT_LENGTH) {
            throw new IllegalArgumentException(String.format("post should be under %d", MAX_CONTENT_LENGTH));
        }
    }
}
