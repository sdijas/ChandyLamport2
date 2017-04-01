package edu.sharif.ce.snapshot.core.rmi;


import java.rmi.Remote;
import java.rmi.RemoteException;

import edu.sharif.ce.snapshot.core.model.entity.Bank;

/**
 * The interface RMIInterface.
 *
 * @author Alireza Aghamohammadi
 */
public interface RMIInterface extends Remote {

  /**
   * Deposit boolean.
   *
   * @param senderId the sender id
   * @param bank     the bank
   * @return the boolean
   * @throws RemoteException the remote exception
   */
  boolean deposit(int senderId, Bank bank) throws RemoteException;

  /**
   * Withdraw.
   *
   * @param bank the bank
   * @throws RemoteException the remote exception
   */
  void withdraw(Bank bank) throws RemoteException;
}
