package kr.co.bullets.post.domain.content;

public abstract class Content {
    String contentText;
    final DatetimeInfo datetimeInfo;

    Content(String content) {
        checkLength(content);
        this.contentText = content;
        this.datetimeInfo = new DatetimeInfo();
    }

    public void updateContent(String content) {
        checkLength(content);
        this.contentText = content;
        this.datetimeInfo.updateEditDatetime();
    }

    abstract void checkLength(String content);

    public String getContentText() {
        return contentText;
    }
}
