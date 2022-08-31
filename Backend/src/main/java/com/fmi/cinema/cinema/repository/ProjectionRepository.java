package com.fmi.cinema.cinema.repository;

import com.fmi.cinema.cinema.model.Projection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface ProjectionRepository extends JpaRepository<Projection, Long> {
    Optional<Projection> findById(final Long id);

    Optional<Projection> findByRoomId(final Long id);

    Optional<Projection> findByMovieId(final Long id);

    Optional<Projection> findByProjectionTime(final LocalDateTime projectionTime);

    @Query("""
           SELECT p FROM Projection p
           WHERE DATE(p.projectionTime) = CURDATE()
           """)
    Page<Projection> findAll(final Pageable pageable);

    @Query("""
           SELECT p FROM Projection p
           WHERE p.projectionTime = :selectedDateTime
           """)
    Page<Projection> findAll(@Param("selectedDate") final LocalDateTime selectedDateTime, final Pageable pageable);

    @Query("""
           SELECT p FROM Projection p
           WHERE p.movieId = :selectedMovieId
           """)
    Page<Projection> findAll(@Param("selectedMovieId") final Long selectedMovieId, final Pageable pageable);

    @Query("""
           SELECT p FROM Projection p
           WHERE p.projectionTime BETWEEN :fromDateTime AND :toDateTime
           """)
    Page<Projection> findAll(@Param("fromDateTime") final LocalDateTime fromDateTime,
                             @Param("toDateTime") final LocalDateTime toDateTime, final Pageable pageable);
}
