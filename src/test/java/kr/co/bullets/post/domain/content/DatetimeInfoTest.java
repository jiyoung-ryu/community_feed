package kr.co.bullets.post.domain.content;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DatetimeInfoTest {

    @Test
    void givenCreatedWhenUpdateThenEditedTrueAndTimeIsUpdated() {
        // given
        DatetimeInfo info = new DatetimeInfo();
        LocalDateTime datetime = info.getDateTime();

        // when
        info.updateEditDatetime();

        // then
        assertNotEquals(datetime, info.getDateTime());
        assertTrue(info.isEdited());
    }

}
