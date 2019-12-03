package br.edu.ifrn.seguranca;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.logout;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.unauthenticated;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
public class LoginTest {
	
  @Autowired
  private WebApplicationContext context;

  private MockMvc mockMvc;

  @Before
  public void setup() {
    mockMvc = MockMvcBuilders
            .webAppContextSetup(context)
            .apply(springSecurity())
            .alwaysDo(print())
            .build();
  }

  @Test
  public void loginDisponivelParaTodos() throws Exception {
    mockMvc
            .perform(get("/login"))
            .andExpect(status().isOk());
  }

  @Test
  public void admintradorPodeLogar() throws Exception {
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


  @Test
  public void invalidoLoginNegado() throws Exception {
    String loginErrorUrl = "/login?error";
    mockMvc
            .perform(formLogin().password("invalido"))
            .andExpect(status().isFound())
            .andExpect(redirectedUrl(loginErrorUrl))
            .andExpect(unauthenticated());

    mockMvc
            .perform(get(loginErrorUrl))
            // .andExpect(content().string(containsString("Invalido usuário e senha")));
            .andExpect(MockMvcResultMatchers.view().name("/login"));
  }
  
}
