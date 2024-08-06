package com.codecool.backend;

import com.codecool.backend.controller.dto.MemberLoginDTO;
import com.codecool.backend.controller.dto.NewMemberDTO;
import com.codecool.backend.model.Member;
import com.codecool.backend.repository.MemberRepository;
import com.codecool.backend.security.jwt.JwtUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-test.properties")
public class UserControllerIT {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private JwtUtils jwtUtils;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @BeforeEach
    public void setup() {
        memberRepository.deleteAll();
        Member member = new Member();
        member.setUsername("apple");
        member.setPassword(passwordEncoder.encode("123"));
        member.setEmail("apple@gmail.com");
        member.setFirstName("Apple");
        member.setLastName("Green");
        memberRepository.save(member);
    }

    @Test
    public void sendSignUpRequestAndItReturnsCreatedStatus() throws Exception {
        NewMemberDTO newMemberDTO = new NewMemberDTO("Apple", "Red", "redApple", "123", "red@gmail.com");
        mockMvc.perform(post("/api/member/signUp")
                        .content(objectMapper.writeValueAsString(newMemberDTO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = "USER")
    public void loginUserAndTheStatusIsOk() throws Exception {
        MemberLoginDTO memberLoginDTO = new MemberLoginDTO("apple", "123");
        mockMvc.perform(post("/api/member/login")
                        .content(objectMapper.writeValueAsString(memberLoginDTO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = "USER")
    public void loginWithWrongUserNameAndStatusShouldBeUnauthorized() throws Exception {
        MemberLoginDTO memberLoginDTO = new MemberLoginDTO("appleee", "123");
        mockMvc.perform(post("/api/member/login")
                        .content(objectMapper.writeValueAsString(memberLoginDTO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser(roles = "USER")
    public void loginWithWrongPasswordAndStatusShouldBeUnauthorized() throws Exception {
        MemberLoginDTO memberLoginDTO = new MemberLoginDTO("apple", "123456");
        mockMvc.perform(post("/api/member/login")
                        .content(objectMapper.writeValueAsString(memberLoginDTO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized());
    }

}
