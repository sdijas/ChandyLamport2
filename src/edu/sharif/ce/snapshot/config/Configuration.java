package edu.sharif.ce.snapshot.config;

import java.io.IOException;
import java.util.DoubleSummaryStatistics;
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
   * The constant INITIAL_BANK_CREDIT.
   */
  public static final Setting<Integer> INITIAL_BANK_CREDIT = () -> Integer.parseInt(properties.getProperty("initialBankCredit"));
  /**
   * The constant NUMBER_OF_BANKS.
   */
  public static final Setting<Integer> NUMBER_OF_BANKS = () -> Integer.parseInt(properties.getProperty("numberOfBanks"));

  /**
   * The constant RMI_PORT.
   */
  public static final Setting<Integer> RMI_PORT = () -> Integer.parseInt(properties.getProperty("rmiPort"));

  /**
   * The constant MINIMUM_TRANSFER_AMOUNT.
   */
  public static final Setting<Integer> MINIMUM_TRANSFER_AMOUNT = () -> Integer.parseInt(properties.getProperty("minimumTransferAmount"));

  /**
   * The constant MAXIMUM_TRANSFER_AMOUNT.
   */
  public static final Setting<Integer> MAXIMUM_TRANSFER_AMOUNT = () -> Integer.parseInt(properties.getProperty("maximumTransferAmount"));

  /**
   * The constant TIMEOUT_PERIOD.
   */
  public static final Setting<Integer> TIMEOUT_PERIOD = () -> Integer.parseInt(properties.getProperty("timeoutPeriod"));

  /**
   * The constant MINIMUM_QUEUEING_DELAY.
   */
  public static final Setting<Integer> MINIMUM_QUEUEING_DELAY = () -> Integer.parseInt(properties.getProperty("minimumQueueingDelay"));

  /**
   * The constant MAXIMUM_QUEUEING_DELAY.
   */
  public static final Setting<Integer> MAXIMUM_QUEUEING_DELAY = () -> Integer.parseInt(properties.getProperty("maximumQueueingDelay"));

  /**
   * The constant CHANCE.
   */
  public static final Setting<Double> CHANCE = () -> Double.parseDouble(properties.getProperty("chance"));

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
