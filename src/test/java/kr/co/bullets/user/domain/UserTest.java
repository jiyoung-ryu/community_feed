package kr.co.bullets.user.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class UserTest {
    private final UserInfo userInfo = new UserInfo("test", "");
    private final User user1 = new User(1L, userInfo);;
    private final User user2 = new User(2L, userInfo);

    @Test
    void givenCreateSameIdUserWhenEqualSameIdThenReturnTrue() {
        // given
        UserInfo testInfo = new UserInfo("test1", "1");
        User oneUser = new User(1L, testInfo);

        // when, then
        assertEquals(oneUser, user1);
    }

    @Test
    void givenUser1WhenFollowUser2ThenUser1IncreaseFollowingCountUser2IncreaseFollowerCount() {
        // given, when
        user1.follow(user2);

        // then
        assertEquals(1, user1.getFollowingCount());
        assertEquals(0, user1.getFollowerCount());
        assertEquals(0, user2.getFollowingCount());
        assertEquals(1, user2.getFollowerCount());
    }

    @Test
    void givenUser1FollowUser2WhenUser1UnfollowUser2ThenReturnZero() {
        // given
        user1.follow(user2);

        //when
        user1.unfollow(user2);

        // then
        assertEquals(0, user1.getFollowingCount());
        assertEquals(0, user1.getFollowerCount());
        assertEquals(0, user2.getFollowingCount());
        assertEquals(0, user2.getFollowerCount());
    }

    @Test
    void whenUser1UnfollowUser2ThenAllCountZero() {
        //when
        user1.unfollow(user2);

        // then
        assertEquals(0, user1.getFollowingCount());
        assertEquals(0, user1.getFollowerCount());
        assertEquals(0, user2.getFollowingCount());
        assertEquals(0, user2.getFollowerCount());
    }


    @Test
    void givenOneUserWhenFollowSameUserThenThrowError() {
        assertThrows(IllegalArgumentException.class, () -> user1.follow(user1));
    }

    @Test
    void givenOneUserWhenUnfollowSameUserThenThrowError() {
        assertThrows(IllegalArgumentException.class, () -> user1.unfollow(user1));
    }
}
