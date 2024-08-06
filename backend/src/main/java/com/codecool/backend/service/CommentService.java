package com.codecool.backend.service;

import com.codecool.backend.controller.dto.NewCommentDTO;
import com.codecool.backend.exception.MemberIsNotFoundException;
import com.codecool.backend.model.Comment;
import com.codecool.backend.model.Post;
import com.codecool.backend.model.Member;
import com.codecool.backend.repository.CommentRepository;
import com.codecool.backend.repository.PostRepository;
import com.codecool.backend.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final MemberRepository memberRepository;
    private final PostRepository postRepository;

    @Autowired
    public CommentService(CommentRepository commentRepository, MemberRepository memberRepository, PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.memberRepository = memberRepository;
        this.postRepository = postRepository;
    }


    public UUID createComment(NewCommentDTO commentDTO, UUID userId, UUID postId) {
        try {
            Comment newComment = new Comment();
            newComment.setComment(commentDTO.comment());
            newComment.setMember(getMemberForComment(userId));
            newComment.setPost(getPostForComment(postId));
            commentRepository.save(newComment);
            return newComment.getCommentPublicId();
        } catch (RuntimeException e) {
            throw new RuntimeException("Error creating comment" + e.getMessage());
        }
    }

    private Member getMemberForComment(UUID memberId) {
        return memberRepository.findByPublicId(memberId).orElseThrow(() -> new MemberIsNotFoundException("Member not found."));
    }

    private Post getPostForComment(UUID postId) {
        return postRepository.findByPublicId(postId);
    }
}
