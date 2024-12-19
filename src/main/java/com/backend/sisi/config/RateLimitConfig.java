package com.backend.sisi.config;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Bucket4j;
import io.github.bucket4j.Refill;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.time.Duration;

@Configuration
public class RateLimitConfig {

    @Bean
    public FilterRegistrationBean<RateLimitFilter> rateLimitFilter() {
        FilterRegistrationBean<RateLimitFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(rateLimitFilterBean());
        registrationBean.addUrlPatterns("/api/v1/vendors/*");
        registrationBean.addUrlPatterns("/api/v1/users/*");

        return registrationBean;
    }

    @Bean
    public RateLimitFilter rateLimitFilterBean() {
        return new RateLimitFilter();
    }

    public class RateLimitFilter extends OncePerRequestFilter {

        private final Bucket bucket;

        public RateLimitFilter() {
            Bandwidth limit = Bandwidth.classic(10, Refill.greedy(10, Duration.ofSeconds(1)));
            this.bucket = Bucket4j.builder().addLimit(limit).build();
        }

        @Override
        protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
                throws ServletException, IOException {
            if (bucket.tryConsume(1)) {
                response.setHeader("X-Rate-Limit-Limit", "10");
                response.setHeader("X-Rate-Limit-Remaining", String.valueOf(bucket.getAvailableTokens()));
                response.setHeader("X-Rate-Limit-Reset", String.valueOf(Duration.ofSeconds(1).getSeconds()));
                filterChain.doFilter(request, response);
            } else {
                response.setStatus(HttpStatus.TOO_MANY_REQUESTS.value());
                response.getWriter().write("Too many requests");
            }
        }
    }
}