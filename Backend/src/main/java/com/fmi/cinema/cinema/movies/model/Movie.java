package com.fmi.cinema.cinema.movies.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Movie
{
	@Id
	private Long id;
	private String name;
    private Long duration;
	private String genre;

	private Date year;

	public Movie()
	{
	}

	public Long getId()
	{
		return id;
	}

	public String getName()
	{
		return name;
	}

	public String getGenre()
	{
		return genre;
	}

	public Long getDuration()
	{
		return duration;
	}
}