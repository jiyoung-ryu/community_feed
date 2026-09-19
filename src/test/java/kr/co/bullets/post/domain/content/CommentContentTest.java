package kr.co.bullets.post.domain.content;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CommentContentTest {
    @Test
    void givenContentLengthIsOkWhenCreatePostContentThenNotThrowError() {
        // given
        String content = "this is a test content";

        // when, then
        assertDoesNotThrow(() -> new CommentContent(content));
    }

    @Test
    void givenContentLengthIsOverLimitCreatePostContentThenThrowError() {
        // given
        String content = "a".repeat(101);

        // when, then
        assertThrows(IllegalArgumentException.class, () -> new CommentContent(content));
    }

    @ParameterizedTest
    @ValueSource(strings = {"뷁", "닭", "굵"})
    void givenContentLengthIsOverLimitAndKoreanCreatePostContentThenThrowError(String koreanContent) {
        // given
        String content = koreanContent.repeat(101);

        // when, then
        assertThrows(IllegalArgumentException.class, () -> new CommentContent(content));
    }

    @Test
    void givenContentLengthIsUnderLimitCreatePostContentThenNotThrowError() {
        // given
        String content = "abcd";

        // when, then
        assertDoesNotThrow(() -> new CommentContent(content));
    }

    @ParameterizedTest
    @NullAndEmptySource
    void givenContentLengthIsEmptyLimitCreatePostContentThenThrowError(String source) {
        assertThrows(IllegalArgumentException.class, () -> new CommentContent(source));
    }
}
