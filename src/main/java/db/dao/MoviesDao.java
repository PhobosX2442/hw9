package db.dao;

import db.domain.Movie;
import org.jdbi.v3.json.Json;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.time.LocalDateTime;
import java.util.List;

public interface MoviesDao {

    @Json
    @SqlQuery("SELECT to_jsonb(m) FROM public.movies as m WHERE id = :id")
    Movie selectByMovieId(@Bind("id") int id);
}

