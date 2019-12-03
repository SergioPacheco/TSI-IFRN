package br.edu.ifrn;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import br.edu.ifrn.controller.HomeController;

public class SmokeTest {
	
	@Autowired
	private HomeController homeController; 
	
	@Test
	public void contexLoads() throws Exception {
		assertThat(homeController);
	}

}
