package db.dao;

import org.jdbi.v3.json.Json;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.time.LocalDateTime;

public interface MoviesDao {

    @Json
    @SqlQuery("SELECT to_jsonb(u) FROM global.users as u WHERE user_id = :userId")
    User selectByUserId(@Bind("userId") long userId);

    @Json
    @SqlQuery("SELECT to_jsonb(u) FROM global.users as u")
    List<User> selectAll();

    @SqlUpdate("UPDATE global.users SET registered_at = :registeredAt WHERE user_id = :userId")
    boolean updateRegisteredAtTime(@Bind("userId") long userId, @Bind("registeredAt") LocalDateTime registeredAt);

    @SqlUpdate("INSERT INTO global.users (user_id, email, registered_at) VALUES (:userId, :email, :registeredAt)")
    int insertUser(@BindBean User user);

    @SqlUpdate("DELETE FROM global.users WHERE user_id = :userId")
    int deleteUserById(@Bind("userId") long userId);
}