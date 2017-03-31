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
