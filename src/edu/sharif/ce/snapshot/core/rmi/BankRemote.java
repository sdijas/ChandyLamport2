package edu.sharif.ce.snapshot.core.rmi;


import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import edu.sharif.ce.snapshot.core.model.entity.Bank;

/**
 * Provides an access to remote Bank via remote method invocation.
 *
 * @author Alireza Aghamohammadi
 * @see Bank
 */
public class BankRemote extends UnicastRemoteObject implements Deposit, Withdraw {
  private Bank bank;

  /**
   * Instantiates a new Bank remote.
   *
   * @param bank the bank
   * @throws RemoteException the remote exception
   */
  public BankRemote(Bank bank) throws RemoteException {
    this.bank = bank;
  }

  @Override
  public void withdraw(Bank bank) throws RemoteException {
    //TODO
  }

  @Override
  public boolean deposit(Bank bank) throws RemoteException {
    //TODO
    return false;
  }
}
