package kr.co.bullets.user.repository.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import kr.co.bullets.common.repository.TimeBaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name="community_user_relation")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@IdClass(UserRelationId.class)
public class UserRelationEntity extends TimeBaseEntity {

    @Id
    private Long followingUserId;

    @Id
    private Long followerUserId;
}
