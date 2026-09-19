package kr.co.bullets.post.application.dto;

public record CreateCommentRequestDto(Long postId, Long authorId, String content) {
}
