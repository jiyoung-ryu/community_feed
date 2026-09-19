package kr.co.bullets.post.application;

import kr.co.bullets.common.FakeObjectFactory;
import kr.co.bullets.post.application.dto.CreatePostRequestDto;
import kr.co.bullets.post.domain.Post;
import kr.co.bullets.post.domain.PostPublicationState;
import kr.co.bullets.user.application.UserService;
import kr.co.bullets.user.application.dto.CreateUserRequestDto;
import kr.co.bullets.user.domain.User;

public class PostServiceTestTemplate {

    final UserService userService = FakeObjectFactory.getUserService();
    final PostService postService = FakeObjectFactory.getPostService();

    final User user = userService.createUser(new CreateUserRequestDto("user1", null));
    final User otherUser = userService.createUser(new CreateUserRequestDto("user1", null));

    CreatePostRequestDto dto = new CreatePostRequestDto(user.getId(), "this is test content", PostPublicationState.PUBLIC);
    final Post post = postService.createPost(dto);
}
