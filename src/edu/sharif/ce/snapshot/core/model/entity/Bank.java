package edu.sharif.ce.snapshot.core.model.entity;


/**
 * The Bank Model.
 *
 * @author Alireza Aghamohammadi
 */
public class Bank {

  // The amount of money held in a bank account at a given moment
  private int balance;

  // The identifier bank account
  private int id;

  /**
   * Instantiates a new Bank.
   *
   * @param id      the id
   * @param balance the balance
   */
  public Bank(int id, int balance) {
    this.id = id;
    this.balance = balance;
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


}
