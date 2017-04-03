package edu.sharif.ce.snapshot.core.model.entity;


import java.io.Serializable;

/**
 * The distributed Chandy/Lamport snapshot.
 *
 * @author Alireza Aghamohammadi
 * @see Bank
 */
public class Snapshot implements Serializable {
  // The identifier of bank account
  private int id;

  // The amount of money held in a bank account at a given moment
  private int balance;

  // The amount of money in transit
  private int moneyInTransit;

  /**
   * Gets id.
   *
   * @return the identifier of bank account
   */
  public int getId() {
    return id;
  }

  /**
   * Gets balance.
   *
   * @return the balance
   */
  public int getBalance() {
    return balance;
  }

  /**
   * Get money in transit.
   *
   * @return the the amount of money in transit.
   */
  public int getMoneyInTransit() {
    return moneyInTransit;
  }
}
