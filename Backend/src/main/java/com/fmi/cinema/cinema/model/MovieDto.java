package com.fmi.cinema.cinema.model;

import java.time.LocalDate;

public class MovieDto
{

	private final String name;

	private final Integer duration;

	private final String genre;

	private final LocalDate year;

	public MovieDto(final String name,
					final Integer duration,
					final String genre,
					final LocalDate year)
	{
		this.name = name;
		this.duration = duration;
		this.genre = genre;
		this.year = year;
	}

	public String getName()
	{
		return name;
	}

	public Integer getDuration()
	{
		return duration;
	}

	public String getGenre()
	{
		return genre;
	}

	public LocalDate getYear()
	{
		return year;
	}
}
