package com.leandro.simpleEverythingAPI.models;

/**
 * @author leandroinacio
 *
 */
public class CurrentUser {
	private String jwt;
	private User user;
	
	public CurrentUser(String jwt, User user) {
		super();
		this.jwt = jwt;
		this.user = user;
	}
		
	public String getJwt() {
		return jwt;
	}
	public void setJwt(String jwt) {
		this.jwt = jwt;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "CurrentUser [jwt=" + jwt + ", user=" + user + "]";
	}
	
}
