package com.codecool.backend;

import com.codecool.backend.controller.dto.NewPostDTO;
import com.codecool.backend.model.Member;
import com.codecool.backend.model.Post;
import com.codecool.backend.repository.MemberRepository;
import com.codecool.backend.repository.PostRepository;
import com.codecool.backend.security.jwt.JwtUtils;
import com.codecool.backend.service.PostService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-test.properties")
class BackendApplicationIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private PostService postService;

    @Autowired
    private MemberRepository memberRepository;

    @MockBean
    private JwtUtils jwtUtils;

    @BeforeEach
    public void setup() {
        memberRepository.deleteAll();
        postRepository.deleteAll();

        Member member = new Member();
        member.setUsername("user");
        member.setPassword("password");
        member.setEmail("user@gmail.com");
        member.setFirstName("User");
        member.setLastName("Test");
        memberRepository.save(member);

        Post post = new Post();
        post.setMember(member);
        post.setDescription("meow");
        post.setPublicId(UUID.fromString("84ba7cdd-abf9-4fde-8c3a-2ac9eac67006"));
        post.setPicture("".getBytes());
        post.setNumOfReport(0);
        postRepository.save(post);
    }

    @Test
    @WithMockUser(username = "user", roles = "USER")
    void testCreatePost() throws Exception {


        NewPostDTO newPostDTO = new NewPostDTO("kutyi", "");

        mockMvc.perform(post("/api/post/create")
                        .content(objectMapper.writeValueAsString(newPostDTO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = "USER")
    void testGetAllPosts() throws Exception {
        mockMvc.perform(get("/api/post/getAll"))
                .andExpect(status().isOk());
        UUID postId = postRepository.findAll().get(0).getPublicId();
        List<Post> posts = postRepository.findAll();
        Post post = postRepository.findByPublicId(postId);
        assertEquals(post.getDescription(), "meow");
        assertEquals(posts.size(), 1);
    }
}
