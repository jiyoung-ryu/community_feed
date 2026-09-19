package kr.co.bullets.post.domain.content;

import java.time.LocalDateTime;

public class DatetimeInfo {
    private boolean isEdited;
    private LocalDateTime dateTime;

    public DatetimeInfo() {
        this.isEdited = false;
        this.dateTime = LocalDateTime.now();
    }

    public void updateEditDatetime() {
        this.isEdited = true;
        this.dateTime = LocalDateTime.now();
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public boolean isEdited() {
        return isEdited;
    }
}
