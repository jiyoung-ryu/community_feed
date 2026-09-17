package kr.co.bullets.user.domain;

public class UserRelationCount {
    private int count;

    public UserRelationCount() {
        this.count = 0;
    }

    public void increase() {
        this.count++;
    }

    public void decrease() {
        if (count <= 0) {
          return;
        }
        this.count--;
    }
}
