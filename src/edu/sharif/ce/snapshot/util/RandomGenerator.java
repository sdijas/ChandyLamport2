package edu.sharif.ce.snapshot.util;


import java.util.Random;

import edu.sharif.ce.snapshot.config.Configuration;

/**
 * The utility class that help to generate random feature
 *
 * @author Alireza Aghamohammadi
 */
public class RandomGenerator {
  /**
   * Generate random amount money between minimum transfer amount and maximum transfer amount.
   *
   * @return the int
   */
  public static int generateRandomAmount() {
    return new Random().nextInt(Configuration.MAXIMUM_TRANSFER_AMOUNT.get() - Configuration.MINIMUM_TRANSFER_AMOUNT.get()) + Configuration.MINIMUM_TRANSFER_AMOUNT.get();
  }

  /**
   * Generate random queueing delay.
   *
   * @return the int
   */
  public static int generateQueueingDelay() {
    return (new Random().nextInt(Configuration.MAXIMUM_QUEUEING_DELAY.get()) + Configuration.MINIMUM_QUEUEING_DELAY.get()) * Configuration.TIMEOUT_PERIOD.get();
  }

  /**
   * Generate random bank id.
   *
   * @return the int
   */
  public static int generateRandomBankId() {
    return new Random().nextInt(Configuration.NUMBER_OF_BANKS.get());
  }
}
