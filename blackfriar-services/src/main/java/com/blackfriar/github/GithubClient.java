package com.blackfriar.github;

import com.blackfriar.github.dto.GithubUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class GithubClient {

    @Autowired
    RestTemplate restTemplate;

    public GithubUser getUser(String username) {
        return restTemplate.getForEntity("https://api.github.com/users/{username}", GithubUser.class, username).getBody();
    }
}
