package lcbo;

import lcbo.auth.LcboUser;
import lcbo.auth.UserRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.FormLoginRequestBuilder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.unauthenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class AuthenticationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Before
    public void setup() {
        userRepository.deleteAll();
        userRepository.save(new LcboUser("user", "password"));
    }

    @After
    public void tearDown() {
        userRepository.deleteAll();
    }

    @Test
    public void loginWithValidUserThenAuthenticated() throws Exception {
        FormLoginRequestBuilder login = formLogin()
                .user("user")
                .password("password");

        mockMvc.perform(login)
                .andExpect(authenticated().withUsername("user"));
    }

    @Test
    public void loginWithInvalidUserThenUnauthenticated() throws Exception {
        FormLoginRequestBuilder login = formLogin()
                .user("invalid")
                .password("invalidpassword");

        mockMvc.perform(login)
                .andExpect(unauthenticated());
    }

    @Test
    public void accessUnsecuredResourceThenOk() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk());
    }

    @Test
    public void accessSecuredResourceUnauthenticatedThenRedirectsToLogin() throws Exception {
        mockMvc.perform(get("/search"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrlPattern("**/login"));
    }

    @Test
    @WithMockUser
    public void accessSecuredResourceAuthenticatedThenOk() throws Exception {
        mockMvc.perform(get("/search"))
                .andExpect(status().isOk());
    }

    @Test
    public void signupWithValidUserThenAuthenticated() throws Exception {
        FormLoginRequestBuilder signup = formLogin("/signup")
                .user("newuser")
                .password("newpassword");
        mockMvc.perform(signup)
                .andExpect(forwardedUrl("/login"));

        FormLoginRequestBuilder login = formLogin()
                .user("newuser")
                .password("newpassword");
        mockMvc.perform(login)
                .andExpect(authenticated().withUsername("newuser"));
    }

    @Test
    public void signupWithInvalidUserThenUnauthenticated() throws Exception {
        FormLoginRequestBuilder signup = formLogin("/signup")
                .user("user")
                .password("password");

        mockMvc.perform(signup)
                .andExpect(unauthenticated());
    }
}
