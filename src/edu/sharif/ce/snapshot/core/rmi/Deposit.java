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
   * @param senderId the sender id
   * @param bank     the bank
   * @return the true if deposit success otherwise return false
   * @throws RemoteException the remote exception
   */
  boolean deposit(int senderId, Bank bank) throws RemoteException;
}
