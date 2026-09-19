package kr.co.bullets.post.application.dto;

import kr.co.bullets.post.domain.PostPublicationState;

public record CreatePostRequestDto(Long userId, String content, PostPublicationState state) {
}
