package com.blackfriar.controllers;


import com.blackfriar.github.GithubClient;
import com.blackfriar.github.dto.GithubUser;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
@RequestMapping(path="/api/github", produces = MediaTypes.HAL_JSON_VALUE)
public class GithubController {

    @Autowired
    GithubClient githubClient;

    @ApiOperation("Get github user information")
    @ApiResponses({
            @ApiResponse(code = 404, message = "No github user could be found")
    })
    @RequestMapping(value = "/user/{username}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<GithubUser> getUser(@PathVariable String username) {
        return new ResponseEntity<GithubUser>(githubClient.getUser(username), HttpStatus.OK);
    }
}
