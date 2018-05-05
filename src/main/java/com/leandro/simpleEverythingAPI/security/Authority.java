package com.leandro.simpleEverythingAPI.security;

import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;

public class Authority {

	@Id
	private Long id;

	@NotNull
	private AuthorityName name;

	public Authority(Long id, AuthorityName name) {
		super();
		this.id = id;
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public AuthorityName getName() {
		return name;
	}

	public void setName(AuthorityName name) {
		this.name = name;
	}

}