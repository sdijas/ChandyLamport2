package edu.sharif.ce.config;

/**
 * The Configuration class. This class holds global variables
 *
 * @author Alireza Aghamohammadi
 */
public final class Configuration {

  /**
   * make sure there is no instance of this class can create
   */
  private Configuration() {
  }

  /**
   * The interface Setting.
   *
   * @param <T> the type parameter
   */
  public interface Setting<T> {
    /**
     * Get t.
     *
     * @return the t
     */
    T get();
  }
}
