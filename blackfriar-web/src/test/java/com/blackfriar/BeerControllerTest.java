package com.blackfriar;

import com.blackfriar.domain.Beer;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.MediaTypes;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by paulwatson on 27/03/2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration(value = "src/main/java")
@ContextConfiguration(classes = { TestConfig.class })
public class BeerControllerTest {

    @Autowired
    private BeerService beerService;

    @Autowired
    private WebApplicationContext ctx;

    private MockMvc mockMvc;

    @Before
    public void setup() {

        //this.mockMvc = standaloneSetup(new BeerController()).build();

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
