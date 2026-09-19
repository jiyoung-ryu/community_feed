package kr.co.bullets.post.application.dto;

import kr.co.bullets.post.domain.PostPublicationState;

public record UpdatePostRequestDto(Long postId, Long userId, String content, PostPublicationState state) {
}
