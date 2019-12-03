package br.edu.ifrn.localizaimovel.config;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.edu.ifrn.localizaimovel.domain.Imovel;
import br.edu.ifrn.localizaimovel.repository.ImovelRepository;

@Configuration
public class Instantiation implements CommandLineRunner {

	@Autowired
	private ImovelRepository imovelRepository;
	
	@Override
	public void run(String... args) throws Exception {
		
		// Limpar os registros no banco
		imovelRepository.deleteAll();

		File[] files = new File("src/main/resources/json/").listFiles();

		for (File file : files) {
		    if (file.isFile()) {
				ObjectMapper objectMapper = new ObjectMapper();
				 
			    List<Imovel> Listaimoveis = objectMapper.readValue(new File("src/main/resources/json/"+file.getName()),objectMapper.getTypeFactory().constructCollectionType(List.class, Imovel.class));
			 
				// Salvar os registros no banco
				imovelRepository.saveAll(Listaimoveis);

		    }
		}
		
		
		
	}

}
