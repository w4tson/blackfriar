package com.blackfriar.controllers;

import com.blackfriar.BeerService;
import com.blackfriar.config.TestConfig;
import com.blackfriar.controllers.BeerController;
import com.blackfriar.domain.Beer;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.hateoas.MediaTypes;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = { TestConfig.class, BeerController.class})
public class BeerControllerTest {

    @MockBean
    private BeerService beerService;

    @Autowired
    private WebApplicationContext ctx;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(this.ctx).build();
    }

    @Test
    public void testGetOneBeer() throws Exception {

        Beer beer = new Beer();
        beer.setId(1L);
        beer.setName("Anchor Steam");
        beer.setPrice(10L);
        when(beerService.getById(anyLong())).thenReturn(Optional.of(beer));

        this.mockMvc.perform(MockMvcRequestBuilders
                .get("/api/beers/1")
                .accept(MediaTypes.HAL_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.links[0].href", is("http://localhost/api/beers/1")));


    }
}
