package edu.sharif.ce.snapshot.core.model.dao;


/**
 * The interface Bank data access object.
 *
 * @author Alireza Aghamohammadi
 */
public interface BankDao {
  /**
   * Deposit.
   *
   * @param amount the amount
   */
  public void deposit(double amount);

  /**
   * Withdraw.
   *
   * @param amount the amount
   */
  public void withdraw(double amount);
}
