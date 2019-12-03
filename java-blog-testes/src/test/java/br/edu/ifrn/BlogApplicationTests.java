package br.edu.ifrn;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.servlet.Filter;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import br.edu.ifrn.model.User;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BlogApplicationTests {

    
    @Autowired
    private WebApplicationContext context;
    
    @Autowired
    private Filter springSecurityFilterChain;
 
    private MockMvc mockMvc;
 
    @Before
    public void setup() {
        mockMvc = MockMvcBuilders
        			.webAppContextSetup(context)
        			.addFilters(springSecurityFilterChain)
        			.build();
    }
    
    @Test
    public void contextLoads() throws Exception {
    }
 
    
    @Test
    public void testeInitialDisplay() throws Exception {
    
        /*
        mockMvc.perform(post("/home")
        		
                .andExpect(status().is(302))
                .andDo(print())
                .andExpect(forwardedUrl("/login"));
       */
    	// mockMvc.perform(get("/").with(user("admin")));
    	
    	mockMvc.perform(get("/home").with(user("admin").password("admin").roles("USER","ADMIN"))); 
    	
    	
    	
    }
   

}
