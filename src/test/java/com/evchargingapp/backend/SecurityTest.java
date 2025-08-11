package com.evchargingapp.backend;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class SecurityTest {

    @Autowired
    private MockMvc mockMvc;

    // Use a @TestConfiguration to provide a mock UserDetailsService bean.
    // This is the modern way to achieve the same result as the deprecated @MockBean.
    @TestConfiguration
    static class TestSecurityConfiguration {
        @Bean
        public UserDetailsService userDetailsService() {
            return mock(UserDetailsService.class);
        }
    }

    @Test
    public void whenRequestingSecuredEndpointWithoutAuth_thenUnauthorized() throws Exception {
        // This test simulates a request to a protected endpoint without a valid user.
        mockMvc.perform(get("/api/users/1/vehicles"))
                .andExpect(status().isUnauthorized());
    }

    // You can add more tests here to check authenticated access once your controller is ready.
}