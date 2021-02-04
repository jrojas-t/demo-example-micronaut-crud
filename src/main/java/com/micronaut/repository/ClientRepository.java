package com.micronaut.repository;

import java.util.List;

import com.micronaut.model.Client;

import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.query.builder.sql.Dialect;
import io.micronaut.data.repository.CrudRepository;

@JdbcRepository(dialect = Dialect.H2)
public interface ClientRepository extends CrudRepository<Client, Long>{

	@Override
	public List<Client> findAll();
	
}
