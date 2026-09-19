package kr.co.bullets.user.repository.entity;

import jakarta.persistence.*;
import kr.co.bullets.common.domain.PositiveIntegerCounter;
import kr.co.bullets.common.repository.TimeBaseEntity;
import kr.co.bullets.user.domain.User;
import kr.co.bullets.user.domain.UserInfo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name="community_user")
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class UserEntity extends TimeBaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String profileImage;
    private Integer followerCount;
    private Integer followingCount;

    public UserEntity(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.profileImage = user.getProfileImage();
        this.followerCount = user.getFollowerCount();
        this.followingCount = user.getFollowingCount();
    }

    public User toUser() {
        return User.builder()
                .id(id)
                .userInfo(new UserInfo(name, profileImage))
                .followerCount(new PositiveIntegerCounter(followerCount))
                .followingCount(new PositiveIntegerCounter(followingCount))
                .build();
    }
}
