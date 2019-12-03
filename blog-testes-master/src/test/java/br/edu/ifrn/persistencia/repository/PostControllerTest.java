package br.edu.ifrn.persistencia.repository;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.servlet.Filter;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostControllerTest {
	 
	@Autowired
    private WebApplicationContext context;
    
    @Autowired
    private Filter springSecurityFilterChain;
 
    private MockMvc mockMvc;
 
    @Before
    public void setup() {
      mockMvc = MockMvcBuilders
        			.webAppContextSetup(context)
        			.addFilters(this.springSecurityFilterChain)
        			.build();
    }

    @Test
    public void verificaHomeCarregamento() throws Exception {
       mockMvc
       			.perform(MockMvcRequestBuilders.get("/newPost")
        		.with(user("admin").password("admin")))
        		.andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.model().hasNoErrors())
                //.andExpect(MockMvcResultMatchers.model().attributeExists("post"))
                //.andExpect(MockMvcResultMatchers.view().name("/error"));
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
	
    
}
