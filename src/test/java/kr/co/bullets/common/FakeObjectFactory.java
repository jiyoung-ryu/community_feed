package kr.co.bullets.common;

import kr.co.bullets.post.application.CommentService;
import kr.co.bullets.post.application.PostService;
import kr.co.bullets.post.application.interfaces.CommentRepository;
import kr.co.bullets.post.application.interfaces.LikeRepository;
import kr.co.bullets.post.application.interfaces.PostRepository;
import kr.co.bullets.post.repository.FakeCommentRepository;
import kr.co.bullets.post.repository.FakeLikeRepository;
import kr.co.bullets.post.repository.FakePostRepository;
import kr.co.bullets.user.application.UserRelationService;
import kr.co.bullets.user.application.UserService;
import kr.co.bullets.user.application.interfaces.UserRelationRepository;
import kr.co.bullets.user.application.interfaces.UserRepository;
import kr.co.bullets.user.repository.FakeUserRelationRepository;
import kr.co.bullets.user.repository.FakeUserRepository;

public class FakeObjectFactory {

    private static final UserRepository fakeUserRepository = new FakeUserRepository();
    private static final UserRelationRepository fakeUserRelationRepository = new FakeUserRelationRepository();
    private static final PostRepository fakePostRepository = new FakePostRepository();
    private static final CommentRepository fakeCommentRepository = new FakeCommentRepository();
    private static final LikeRepository fakeLikeRepository = new FakeLikeRepository();

    private static final UserService userService = new UserService(fakeUserRepository);
    private static final UserRelationService userRelationService = new UserRelationService(fakeUserRelationRepository, userService);
    private static final PostService postService = new PostService(userService, fakePostRepository, fakeLikeRepository);
    private static final CommentService commentService = new CommentService(userService, postService, fakeCommentRepository, fakeLikeRepository);

    public static UserService getUserService() {
        return userService;
    }

    public static UserRelationService getUserRelationService() {
        return userRelationService;
    }

    public static PostService getPostService() {
        return postService;
    }

    public static CommentService getCommentService() {
        return commentService;
    }
}
