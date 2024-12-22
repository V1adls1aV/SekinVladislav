package hw06.core.settings;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

/**
 * Configuration class for whole application.
 */
public class Settings {
  private static final Config config = ConfigFactory.load();

  public static DatabaseType getDatabaseType() {
    return DatabaseType.valueOf(config.getString("app.database.type"));
  }
}
