package com.codecool.backend.service;

import com.codecool.backend.controller.dto.CommentDTO;
import com.codecool.backend.controller.dto.NewCommentDTO;
import com.codecool.backend.model.Comment;
import com.codecool.backend.model.Member;
import com.codecool.backend.model.Post;
import com.codecool.backend.repository.CommentRepository;
import com.codecool.backend.repository.MemberRepository;
import com.codecool.backend.repository.PostRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class CommentServiceTest {
    @Mock
    private PostRepository postRepository;
    @Mock
    private MemberRepository memberRepository;
    @Mock
    private CommentRepository commentRepository;

    private CommentService commentService;
    private Member member;
    private Post post;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        this.commentService = new CommentService(commentRepository, memberRepository, postRepository);

        member = new Member();
        member.setFirstName("Nagy");
        member.setLastName("Lajos");
        member.setUsername("nagy_mancs");
        member.setPassword("Meow123");
        member.setEmail("mancs@gmail.com");
        memberRepository.save(member);
        member.setPublicId(UUID.fromString("efc544d8-9ed6-4dfb-968b-1b939d202ee8"));

        post = new Post();
        post.setDescription("meow");
        post.setPicture("".getBytes());
        post.setMember(member);
        post.setNumOfReport(0);
        postRepository.save(post);
        post.setPublicId(UUID.fromString("f356a8e1-2832-4764-8eba-60fc29ba3cef"));
    }

    @Test
    void testCreateCommentSuccessful() {
        NewCommentDTO commentDTO = new NewCommentDTO("I like this post!");
        UUID generatedId = UUID.fromString("1bdd6b66-4064-410d-9e74-eeaddb913796");
        when(memberRepository.findByPublicId(UUID.fromString("efc544d8-9ed6-4dfb-968b-1b939d202ee8"))).thenReturn(Optional.of(member));
        when(commentRepository.save(any(Comment.class))).thenAnswer(invocation -> {
            Comment savedComment = invocation.getArgument(0);
            savedComment.setCommentPublicId(generatedId);
            return savedComment;
        });
        UUID actual = commentService.createComment(commentDTO, member.getPublicId(), post.getPublicId());
        assertEquals(generatedId, actual);
    }

    @Test
    void testCreateCommentFailed() {
        NewCommentDTO commentDTO = new NewCommentDTO("I like this post!");
        assertThrows(RuntimeException.class, () -> commentService.createComment(commentDTO, member.getPublicId(), post.getPublicId()));
    }
}
