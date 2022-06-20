package com.fmi.cinema.cinema.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
public class Movie
{
	@Id
	private Long id;
	private String name;
    private Long duration;
	private String genre;

	private LocalDate year;

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