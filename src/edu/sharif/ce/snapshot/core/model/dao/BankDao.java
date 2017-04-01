package edu.sharif.ce.snapshot.core.model.dao;


import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import edu.sharif.ce.snapshot.core.model.entity.Bank;
import edu.sharif.ce.snapshot.core.rmi.RMIInterface;

/**
 * The interface Bank data access object.
 *
 * @author Alireza Aghamohammadi
 */
public interface BankDao {
  /**
   * RMIInterface.
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


  /**
   * Gets remote bank.
   *
   * @param bank the bank
   * @return the remote bank
   * @throws RemoteException       the remote exception
   * @throws NotBoundException     the not bound exception
   * @throws MalformedURLException the malformed url exception
   */
  public RMIInterface getRemoteBank(Bank bank) throws RemoteException, NotBoundException, MalformedURLException;
}
