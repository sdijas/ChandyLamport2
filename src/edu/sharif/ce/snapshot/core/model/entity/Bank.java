package edu.sharif.ce.snapshot.core.model.entity;


import java.io.Serializable;

/**
 * The Bank Model.
 *
 * @author Alireza Aghamohammadi
 */
public class Bank implements Serializable {


  // The amount of money held in a bank account at a given moment
  private int balance;

  // The identifier bank account
  private int id;

  // Each bank has a snapshot
  private Snapshot snapshot;

  /**
   * Instantiates a new Bank.
   *
   * @param id      the id
   * @param balance the balance
   */
  public Bank(int id, int balance) {
    this.id = id;
    this.balance = balance;
    this.snapshot = new Snapshot();
  }

  /**
   * Sets balance.
   *
   * @param balance the balance
   */
  public void setBalance(int balance) {
    this.balance = balance;
  }

  /**
   * Gets balance.
   *
   * @return the amount of money held in a bank account at a given moment
   */
  public int getBalance() {
    return balance;
  }

  /**
   * Gets id.
   *
   * @return the identifier of bank
   */
  public int getId() {
    return id;
  }

  /**
   * Gets snapshot.
   *
   * @return the snapshot of bank
   */
  public Snapshot getSnapshot() {
    return snapshot;
  }

  @Override
  public String toString() {
    return "Bank[Id(" + getId() + ")," + getSnapshot() + ")]";
  }
}
