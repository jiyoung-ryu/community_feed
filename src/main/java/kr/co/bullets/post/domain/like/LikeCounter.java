package kr.co.bullets.post.domain.like;

public class LikeCounter {

    private int likeCount;

    public LikeCounter(int likeCount) {
        this.likeCount = likeCount;
    }

    public LikeCounter() {
        this(0);
    }

    public void increase() {
        likeCount++;
    }

    public void decrease() {
        if (likeCount <= 0) {
            return;
        }
        likeCount--;
    }

    public int getLikeCount() {
        return likeCount;
    }
}
