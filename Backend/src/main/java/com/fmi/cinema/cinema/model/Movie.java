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

    private Integer duration;

	private String genre;

	private LocalDate year;

	public Movie()
	{
	}

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public Integer getDuration()
	{
		return duration;
	}

	public void setDuration(Integer duration)
	{
		this.duration = duration;
	}

	public String getGenre()
	{
		return genre;
	}

	public void setGenre(String genre)
	{
		this.genre = genre;
	}

	public LocalDate getYear()
	{
		return year;
	}

	public void setYear(LocalDate year)
	{
		this.year = year;
	}
}