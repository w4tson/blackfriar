package com.blackfriar.github;

import com.blackfriar.github.dto.GithubUser;
import com.blackfriar.testconfig.ClientTestConfig;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.PropertyPlaceholderAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Profile;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { GithubClient.class, ClientTestConfig.class, PropertyPlaceholderAutoConfiguration.class })
@ComponentScan("com.blackfriar")
@ActiveProfiles("test")
public class GithubClientTest {

    @Autowired
    RestTemplate restTemplate;

    private MockRestServiceServer server;

    @Autowired
    GithubClient githubClient;


    @Before
    public void setup() {
        server = MockRestServiceServer.createServer(restTemplate);
    }

    @Test
    public void getUser() throws Exception {

        String mockResponse = "{\n" +
                "  \"login\": \"foo\",\n" +
                "  \"id\": 123456,\n" +
                "  \"avatar_url\": \"https://avatars2.githubusercontent.com/u/123456?v=3\",\n" +
                "  \"gravatar_id\": \"\",\n" +
                "  \"url\": \"https://api.github.com/users/foo\",\n" +
                "  \"html_url\": \"https://github.com/foo\",\n" +
                "  \"followers_url\": \"https://api.github.com/users/foo/followers\",\n" +
                "  \"following_url\": \"https://api.github.com/users/foo/following{/other_user}\",\n" +
                "  \"gists_url\": \"https://api.github.com/users/foo/gists{/gist_id}\",\n" +
                "  \"starred_url\": \"https://api.github.com/users/foo/starred{/owner}{/repo}\",\n" +
                "  \"subscriptions_url\": \"https://api.github.com/users/foo/subscriptions\",\n" +
                "  \"organizations_url\": \"https://api.github.com/users/foo/orgs\",\n" +
                "  \"repos_url\": \"https://api.github.com/users/foo/repos\",\n" +
                "  \"events_url\": \"https://api.github.com/users/foo/events{/privacy}\",\n" +
                "  \"received_events_url\": \"https://api.github.com/users/foo/received_events\",\n" +
                "  \"type\": \"User\",\n" +
                "  \"site_admin\": false,\n" +
                "  \"name\": \"John\",\n" +
                "  \"company\": null,\n" +
                "  \"blog\": null,\n" +
                "  \"location\": \"London\",\n" +
                "  \"email\": null,\n" +
                "  \"hireable\": null,\n" +
                "  \"bio\": null,\n" +
                "  \"public_repos\": 14,\n" +
                "  \"public_gists\": 4,\n" +
                "  \"followers\": 3,\n" +
                "  \"following\": 2,\n" +
                "  \"created_at\": \"2012-11-12T15:46:37Z\",\n" +
                "  \"updated_at\": \"2016-11-24T08:14:28Z\"\n" +
                "}";

        server.expect(requestTo("https://api.github.com/users/foo"))
                .andExpect(method(GET))
                .andRespond(withSuccess(mockResponse, MediaType.APPLICATION_JSON));

        GithubUser actualUser = githubClient.getUser("foo");
        assertThat(actualUser.getLogin()).isEqualTo("foo");
    }

}