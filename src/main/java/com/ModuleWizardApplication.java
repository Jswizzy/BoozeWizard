package com;

import com.model.Module;
import com.model.ModuleList;
import com.model.ModuleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@SpringBootApplication
public class ModuleWizardApplication {

	private static final Logger log = LoggerFactory.getLogger(ModuleWizardApplication.class);
	private static final RestTemplate restTemplate = new RestTemplate();

	public static void main(String[] args) {
		SpringApplication.run(ModuleWizardApplication.class, args);
	}

	@Bean
	public CommandLineRunner loadModules(ModuleRepository repository) {
		return (args) -> {
			// save a couple of customers
//			repository.save(new Module("They Drew First Blood!!!", "John Rambo"));
//			repository.save(new Module("Sorry but your a Joke Artist", "How not to Choke"));
//			repository.save(new Module("Kim", "Bauer"));
//			repository.save(new Module("David", "Palmer"));
//			repository.save(new Module("Michelle", "Dessler"));

			// fetch all customers
			log.info("Modules found with findAll():");
			log.info("-------------------------------");
			for (Module module : repository.findAll()) {
				log.info(module.toString());
			}
			log.info("");

			ModuleList resultList = restTemplate.getForObject("http://localhost:3000/module", ModuleList.class);

			List modules = resultList.getModules();

			log.info("Modules added from rest:");
			log.info("-------------------------------");
			for (Object module : modules) {
				repository.save((Module) module);
				log.info(module.toString());
			}

//			// fetch an individual customer by ID
//			Module module = repository.findOne(1L);
//			log.info("Customer found with findOne(1L):");
//			log.info("--------------------------------");
//			log.info(module.toString());
//			log.info("");

//			// fetch customers by last name
//			log.info("Customer found with findByLastNameStartsWithIgnoreCase('Bauer'):");
//			log.info("--------------------------------------------");
//			for (Module bauer : repository
//					.findByNameStartsWithIgnoreCase("Bauer")) {
//				log.info(bauer.toString());
//			}
//			log.info("");

		};
	}

}
