package edu.sharif.ce.snapshot.core.model.dao;


import java.util.List;

import edu.sharif.ce.snapshot.core.model.entity.Bank;
import edu.sharif.ce.snapshot.core.rmi.RMIInterface;

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
   * @return the true if balance of bank greater than amount of sendMoney otherwise return false
   */
  public boolean withdraw(Bank bank);

  /**
   * Gets bank.
   *
   * @param id the id
   * @return the bank with expected id
   */
  public Bank getBank(int id);

  /**
   * All banks list.
   *
   * @return the list
   */
  public List<Bank> allBanks();


  /**
   * Gets remote bank.
   *
   * @param bank the bank
   * @return the remote bank
   */
  public RMIInterface getRemoteBank(Bank bank);
}
