package db.dao;

import db.domain.Movie;
import org.jdbi.v3.json.Json;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.SqlQuery;

public interface MovieDao {

    @Json
    @SqlQuery("SELECT to_jsonb(m) FROM public.movies as m WHERE id = :id")
    Movie selectById(@Bind("id") int id);

    @Json
    @SqlQuery("SELECT to_jsonb(m) FROM public.movies as m WHERE price = :price")
    Movie selectByPrice(@Bind("price") int price);
}
