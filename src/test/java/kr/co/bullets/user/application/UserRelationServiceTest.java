package kr.co.bullets.user.application;

import kr.co.bullets.common.FakeObjectFactory;
import kr.co.bullets.user.application.dto.CreateUserRequestDto;
import kr.co.bullets.user.application.dto.FollowUserRequestDto;
import kr.co.bullets.user.domain.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class UserRelationServiceTest {
    private final UserService userService = FakeObjectFactory.getUserService();
    private final UserRelationService userRelationService = FakeObjectFactory.getUserRelationService();
    private User user1;
    private User user2;
    private FollowUserRequestDto relationDto;

    @BeforeEach
    void setUp() {
        // given
        CreateUserRequestDto req = new CreateUserRequestDto("Doe", "");
        this.user1 = userService.createUser(req);
        this.user2 = userService.createUser(req);
        this.relationDto = new FollowUserRequestDto(user1.getId(), user2.getId());
    }


    @Test
    void givenCreateTwoUserWhenFollowThenUserFollowOtherUser() {
        // when
        userRelationService.followUser(relationDto);

        // then
        assertEquals(1, user1.getFollowingCount());
        assertEquals(1, user2.getFollowerCount());
    }

    @Test
    void givenFollowUserWhenFollowAgainThenThrowException() {
        // given
        userRelationService.followUser(relationDto);

        // when, then
        assertThrows(IllegalArgumentException.class, () -> userRelationService.followUser(relationDto));
    }

    @Test
    void givenFollowUserWhenFollowSelfThenThrowException() {
        // given
        FollowUserRequestDto sameUser = new FollowUserRequestDto(user1.getId(), user1.getId());

        // when, then
        assertThrows(IllegalArgumentException.class, () -> userRelationService.followUser(sameUser));
    }

    @Test
    void givenFollowUserWhenUnfollowThenUserUnfollowOtherUser() {
        // given
        userRelationService.followUser(relationDto);

        // when
        userRelationService.unfollowUser(relationDto);

        // then
        assertEquals(0, user1.getFollowingCount());
        assertEquals(0, user2.getFollowerCount());
    }

    @Test
    void givenUnfollowUserWhenUnfollowAgainThenThrowException() {
        // when, then
        assertThrows(IllegalArgumentException.class, () -> userRelationService.unfollowUser(relationDto));
    }

    @Test
    void givenUnfollowUserWhenUnfollowSelfThenThrowException() {
        // given
        FollowUserRequestDto sameUser = new FollowUserRequestDto(user1.getId(), user1.getId());

        // when, then
        assertThrows(IllegalArgumentException.class, () -> userRelationService.unfollowUser(sameUser));
    }
}
