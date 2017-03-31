package edu.sharif.ce.snapshot.core.model.dao;


import edu.sharif.ce.snapshot.core.model.entity.Bank;

/**
 * The interface Bank data access object.
 *
 * @author Alireza Aghamohammadi
 */
public interface BankDao {
  /**
   * Deposit.
   *
   * @param bank the bank
   */
  public void deposit(Bank bank);

  /**
   * Withdraw.
   *
   * @param bank the bank
   */
  public void withdraw(Bank bank);
}
