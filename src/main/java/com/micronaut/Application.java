package com.micronaut;

import java.util.Arrays;

import javax.inject.Singleton;
import javax.transaction.Transactional;

import com.micronaut.model.Client;
import com.micronaut.repository.ClientRepository;

import io.micronaut.context.event.StartupEvent;
import io.micronaut.runtime.Micronaut;
import io.micronaut.runtime.event.annotation.EventListener;

@Singleton
public class Application {

	private final ClientRepository clientRepository;
	
    public Application(ClientRepository clientRepository) {
		this.clientRepository = clientRepository;
	}

	public static void main(String[] args) {
        Micronaut.run(Application.class, args);
    }
	
	@EventListener
	@Transactional
	public void init(StartupEvent startupEvent) {
		clientRepository.saveAll(Arrays.asList(new Client("Jhonatan", 30), 
				                               new Client("Marcos", 33),
                                               new Client("Leonardo", 50),
                                               new Client("Natalia", 33)
                                               ));
	}
}
