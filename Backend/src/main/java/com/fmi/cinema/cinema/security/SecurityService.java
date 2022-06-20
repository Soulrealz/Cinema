package com.fmi.cinema.cinema.security;

import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public class SecurityService
{

	private final static String USERNAME = "username";

	private final HttpServletRequest httpServletRequest;

	public SecurityService(HttpServletRequest httpServletRequest)
	{
		this.httpServletRequest = httpServletRequest;
	}

	public String getActor()
	{
		final String actor = httpServletRequest.getHeader(USERNAME);

		return actor;
	}
}
