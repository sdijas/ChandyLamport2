package edu.sharif.ce.snapshot.core.rmi;


import java.rmi.Remote;
import java.rmi.RemoteException;

import edu.sharif.ce.snapshot.core.model.entity.Bank;

/**
 * The interface Withdraw.
 *
 * @author Alireza Aghamohammadi
 */
@FunctionalInterface
public interface Withdraw extends Remote {
  /**
   * Withdraw.
   *
   * @param bank the bank
   * @throws RemoteException the remote exception
   */
  void withdraw(Bank bank) throws RemoteException;
}
