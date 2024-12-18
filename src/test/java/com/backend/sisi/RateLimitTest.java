package com.backend.sisi;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class RateLimitTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testRateLimitHeaders() throws Exception {
        mockMvc.perform(get("/api/v1/users"))
                .andExpect(status().isOk())
                .andExpect(header().exists("X-Rate-Limit-Limit"))
                .andExpect(header().exists("X-Rate-Limit-Remaining"))
                .andExpect(header().exists("X-Rate-Limit-Reset"));
    }

    @Test
    public void testRateLimitExceeded() throws Exception {
        for (int i = 0; i < 11; i++) {
            mockMvc.perform(get("/api/v1/users"));
        }
        mockMvc.perform(get("/api/v1/users"))
                .andExpect(status().isTooManyRequests());
    }
}