package br.edu.ifrn.seguranca;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.logout;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.servlet.Filter;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HomeControllerTest {

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
    public void verificaHomePageCarregamento() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/home"))
                .andExpect(MockMvcResultMatchers.model().hasNoErrors())
                .andExpect(MockMvcResultMatchers.model().attributeExists("pager"))
                .andExpect(MockMvcResultMatchers.view().name("/home"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
    
    @Test
    public void adminPodeLogar() throws Exception {
      mockMvc
              .perform(formLogin().user("admin").password("admin"))
              .andExpect(status().isFound())
              .andExpect(redirectedUrl("/home"))
              .andExpect(authenticated().withUsername("admin"));

      mockMvc
              .perform(logout())
              .andExpect(status().isFound())
              .andExpect(redirectedUrl("/login?logout"));
    }
    
}