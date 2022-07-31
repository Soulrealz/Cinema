package com.fmi.cinema.cinema.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "movies")
public class Movie
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column
	private String name;
	@Column
	private Long duration;
	@Column
	private String genre;
	@Column
	private LocalDate year;

	public Movie() {}

	public Movie(Long id, String name, Long duration, String genre, LocalDate year) {
		this.id = id;
		this.name = name;
		this.duration = duration;
		this.genre = genre;
		this.year = year;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Long getDuration() {
		return duration;
	}

	public String getGenre() {
		return genre;
	}

	public LocalDate getYear() {
		return year;
	}
}