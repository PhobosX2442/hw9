package util;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.io.InputStream;
import java.util.Properties;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DbCredentials {
    private String host;
    private int port;
    private String dbName;
    private String username;
    private String password;

    // Удобный JDBC URL (для PostgreSQL, адаптируйте под драйвер при необходимости)
    public String getUrl() {
        return "jdbc:postgresql://" + host + ":" + port + "/" + dbName;
    }

    // Реализация загрузки кредов
    public static DbCredentials load() {
        // 1) Попытка загрузки из окружения
        String host = System.getenv("DB_HOST");
        String portStr = System.getenv("DB_PORT");
        String dbName = System.getenv("DB_NAME");
        String user = System.getenv("DB_USERNAME");
        String pass = System.getenv("DB_PASSWORD");

        if (host != null && portStr != null && dbName != null && user != null && pass != null) {
            try {
                int port = Integer.parseInt(portStr);
                return new DbCredentials(host, port, dbName, user, pass);
            } catch (NumberFormatException ex) {
                // неверный формат порта — продолжим к следующему этапу загрузки
            }
        }

        // 2) Попытка загрузки из файла properties на Classpath (например, db.properties)
        Properties props = new Properties();
        String[] keys = { "host", "port", "dbName", "username", "password" };
        try (InputStream is = DbCredentials.class.getClassLoader().getResourceAsStream("db.properties")) {
            if (is != null) {
                props.load(is);
                host = props.getProperty("host");
                String portProp = props.getProperty("port");
                dbName = props.getProperty("dbName");
                user = props.getProperty("username");
                pass = props.getProperty("password");

                if (host != null && portProp != null && dbName != null && user != null && pass != null) {
                    int port = Integer.parseInt(portProp);
                    return new DbCredentials(host, port, dbName, user, pass);
                }
            }
        } catch (Exception e) {
            // проигнорируем детали и перейдём к финальной ошибке
        }

        // 3) Если нигде не нашли креды — ошибка
        throw new IllegalStateException("Не удалось загрузить DbCredentials. Установите переменные окружения DB_HOST, DB_PORT, DB_NAME, DB_USERNAME, DB_PASSWORD или добавьте db.properties в classpath с ключами host, port, dbName, username, password.");
    }
}
