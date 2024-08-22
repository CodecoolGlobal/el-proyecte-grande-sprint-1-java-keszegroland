package com.codecool.backend.service;

import com.codecool.backend.controller.dto.NewPostDTO;
import com.codecool.backend.controller.dto.PostDTO;
import com.codecool.backend.model.Member;
import com.codecool.backend.model.Post;
import com.codecool.backend.repository.MemberRepository;
import com.codecool.backend.repository.PostRepository;
import com.codecool.backend.repository.ReportRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PostServiceTest {
    @Mock
    private PostRepository postRepository;
    @Mock
    private MemberRepository memberRepository;
    @Mock
    private ReportRepository reportRepository;

    private PostService postService;

    private Post post;

    private Member member;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        postService = new PostService(postRepository, memberRepository, reportRepository);

        member = new Member();
        member.setFirstName("Nagy");
        member.setLastName("Lajos");
        member.setUsername("nagy_mancs");
        member.setPassword("Meow123");
        member.setEmail("mancs@gmail.com");
        memberRepository.save(member);

        post = new Post();
        post.setDescription("meow");
        post.setPicture("".getBytes());
        post.setMember(member);
        postRepository.save(post);

    }

    private PostDTO convertPostToDTO(Post post) {
        return new PostDTO(post.getPublicId(), post.getMember().getUsername(), post.getDescription(), convertImageToBase64(post.getPicture()), post.getCreationDate());
    }

    private String convertImageToBase64(byte[] picture) {
        String base64Image = null;
        if (picture != null) {
            base64Image = "data:image/png;base64," + Base64.getEncoder().encodeToString(picture);
        }
        return base64Image;
    }

    private byte[] convertBase64Image(NewPostDTO newPostDTO) {
        String pictureBase64Data = newPostDTO.picture();
        if (pictureBase64Data != null && !pictureBase64Data.isEmpty()) {
            return Base64.getDecoder().decode(pictureBase64Data);
        }
        return null;
    }

    @Test
    void testGetAllPosts() {
        List<Post> posts = List.of(post);
        when(postRepository.findAll()).thenReturn(posts);
        List<PostDTO> expected = List.of(convertPostToDTO(post));
        List<PostDTO> actual = postService.getAllPosts();
        assertEquals(expected, actual);
    }

    @Test
    void testGetPostByMemberPublicId() {
        List<Post> posts = List.of(post);
        when(postRepository.findAllByMemberPublicId(UUID.fromString("af584ddd-41b7-4fa3-b245-7a49f8c7316b"))).thenReturn(posts);
        List<PostDTO> expected = List.of(convertPostToDTO(post));
        List<PostDTO> actual = postService.getPostsByMemberPublicId(UUID.fromString("af584ddd-41b7-4fa3-b245-7a49f8c7316b"));
        assertEquals(expected, actual);
    }

    @Test
    void testCreateNewPostSuccessful() {
        Authentication authentication = mock(Authentication.class);
        SecurityContext securityContext = mock(SecurityContext.class);
        SecurityContextHolder.setContext(securityContext);

        when(securityContext.getAuthentication()).thenReturn(authentication);
        when(authentication.getName()).thenReturn("nagy_mancs");

        when(memberRepository.findByUsername("nagy_mancs")).thenReturn(Optional.of(member));

        NewPostDTO newPostDTO = new NewPostDTO("meow", "");

        Post post2 = new Post();
        post2.setDescription(newPostDTO.description());
        post2.setPicture(convertBase64Image(newPostDTO));
        post2.setMember(member);
        UUID generatedUUID = UUID.fromString("af584ddd-41b7-4fa3-b245-7a49f8c7316b");

        when(postRepository.save(any(Post.class))).thenAnswer(invocation -> {
            Post savedPost = invocation.getArgument(0);
            savedPost.setPublicId(generatedUUID);
            return savedPost;
        });

        UUID actual = postService.createNewPost(newPostDTO);

        assertEquals(generatedUUID, actual);
    }

    @Test
    void testCreateNewPostFail() {
         NewPostDTO postDTO = new NewPostDTO("meow", "");
         assertThrows(RuntimeException.class, () -> postService.createNewPost(postDTO));
    }

}
