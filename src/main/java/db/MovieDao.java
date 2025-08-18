package db;

import domain.Movie;
import org.jdbi.v3.json.Json;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.SqlQuery;

public interface MovieDao {

    @Json
    @SqlQuery("SELECT to_jsonb(m) FROM public.movies as m WHERE id = :id")
    Movie selectByMovieId(@Bind("id") int id);
}
