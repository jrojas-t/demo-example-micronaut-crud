package com.micronaut.controller;

import java.util.List;
import java.util.Optional;

import javax.inject.Inject;

import com.micronaut.model.Client;
import com.micronaut.repository.ClientRepository;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.MutableHttpResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Delete;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.Put;
import io.micronaut.http.annotation.QueryValue;
import io.micronaut.scheduling.TaskExecutors;
import io.micronaut.scheduling.annotation.ExecuteOn;

@Controller
@ExecuteOn(value = TaskExecutors.IO)
public class ClientController {

	@Inject
	private ClientRepository clientRepository;

	public ClientController(ClientRepository clientRepository) {
		this.clientRepository = clientRepository;
	}

	@Get(value = "/clients", produces = MediaType.APPLICATION_JSON)
	public HttpResponse<List<Client>> get() {
		try {
			List<Client> listClients = clientRepository.findAll();
			if (!listClients.isEmpty()) {
				return HttpResponse.ok().body(listClients);
			} else {
				return HttpResponse.noContent();
			}
		} catch (Exception e) {
			return HttpResponse.serverError();
		}
	}

	@Get(value = "/client/{idClient}", produces = MediaType.APPLICATION_JSON)
	public MutableHttpResponse<Optional<Client>> getById(@QueryValue(value = "idClient") Long id) {
		try {
			Optional<Client> client = clientRepository.findById(id);
			if (client != null) {
				return HttpResponse.ok().body(client);
			} else {
				return HttpResponse.noContent();
			}
		} catch (Exception e) {
			return HttpResponse.serverError();
		}
	}

	@Post(value = "/client", consumes = MediaType.APPLICATION_JSON)
	public HttpResponse<?> save(@Body Client client) {
		try {
			if (client != null) {
				return HttpResponse.created(client);
			} else {
				return HttpResponse.badRequest();
			}
		} catch (Exception e) {
			return HttpResponse.serverError();
		}
	}

	@Delete(value = "/client/{id}", consumes = MediaType.APPLICATION_JSON)
	public HttpResponse<?> remove(Long id) {
		try {
			if (id != null) {
				clientRepository.deleteById(id);
				return HttpResponse.ok();
			} else {
				return HttpResponse.badRequest();
			}
		} catch (Exception e) {
			return HttpResponse.serverError();
		}
	}

	@Put(value = "/client/{id}", consumes = MediaType.APPLICATION_JSON)
	public HttpResponse<?> update(Long id, @Body Client client) {
		try {
			if (id != null) {
				client.setId(id);
				clientRepository.update(client);
				return HttpResponse.ok();
			} else {
				return HttpResponse.badRequest();
			}
		} catch (Exception e) {
			return HttpResponse.serverError();
		}
	}

}
