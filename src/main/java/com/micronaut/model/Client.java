package com.micronaut.model;

import io.micronaut.data.annotation.GeneratedValue;
import io.micronaut.data.annotation.Id;
import io.micronaut.data.annotation.MappedEntity;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@RequiredArgsConstructor
@MappedEntity
public class Client {

	@GeneratedValue
	@Id
	private Long id;
	private @NonNull String name;
	private @NonNull int age;

}
