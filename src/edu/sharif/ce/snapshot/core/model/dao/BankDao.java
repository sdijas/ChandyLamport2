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
   * @return the true if balance of bank greater than amount of withdraw otherwise return false
   */
  public boolean withdraw(Bank bank);

  /**
   * Gets bank.
   *
   * @param id the id
   * @return the bank with expected id
   */
  public Bank getBank(int id);
}
