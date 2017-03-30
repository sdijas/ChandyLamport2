package edu.sharif.ce.config;

import java.io.IOException;
import java.util.Properties;

/**
 * The Configuration class. This class holds global variables
 *
 * @author Alireza Aghamohammadi
 */
public final class Configuration {
  private static Properties properties;
  private static final String CONFIGURATION_FILE = "config.properties";

  /**
   * The initial credit of bank.
   */
  public static final Setting<Integer> INITIAL_BANK_CREDIT = () -> Integer.parseInt(properties.getProperty("InitialBankCredit"));


  /**
   * make sure there is no instance of this class can create
   */
  private Configuration() {
  }

  static {
    properties = new Properties();
    try {
      properties.load(Configuration.class.getClassLoader().getResourceAsStream(CONFIGURATION_FILE)); // load config.properties file
    } catch (IOException e) {
      System.err.println("Loading configuration file " + CONFIGURATION_FILE + " failed");
    }
  }

  /**
   * The interface Setting.
   *
   * @param <T> the type parameter
   */
  @FunctionalInterface
  public interface Setting<T> {
    /**
     * Get t.
     *
     * @return the t
     */
    T get();
  }
}
