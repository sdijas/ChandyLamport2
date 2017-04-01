package edu.sharif.ce.snapshot.core.rmi;


import java.rmi.Remote;
import java.rmi.RemoteException;

import edu.sharif.ce.snapshot.core.model.entity.Bank;

/**
 * The interface Deposit.
 *
 * @author Alireza Aghamohammadi
 */
@FunctionalInterface
public interface Deposit extends Remote {
  /**
   * Deposit.
   *
   * @param bank the bank
   * @return the true if operation success otherwise return false
   * @throws RemoteException the remote exception
   */
  boolean deposit(Bank bank) throws RemoteException;
}
