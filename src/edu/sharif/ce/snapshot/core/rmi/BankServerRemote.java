package edu.sharif.ce.snapshot.core.rmi;


import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import edu.sharif.ce.snapshot.core.model.dao.BankDaoImpl;
import edu.sharif.ce.snapshot.core.model.entity.Bank;

/**
 * Provides an access to remote bank via remote method invocation.
 *
 * @author Alireza Aghamohammadi
 * @see Bank
 */
public class BankServerRemote extends UnicastRemoteObject implements RMIInterface {
  private BankDaoImpl bankDao;
  private final Lock depositLock = new ReentrantLock();
  private final Lock withdrawLock = new ReentrantLock();

  /**
   * Instantiates a new Bank server remote.
   *
   * @param bankDao the bank dao
   * @throws RemoteException the remote exception
   */
  public BankServerRemote(BankDaoImpl bankDao) throws RemoteException {
    this.bankDao = bankDao;
  }

  @Override
  public void withdraw(Bank bank) throws RemoteException {
    withdrawLock.lock();
    try {
      boolean isWithdraw = bankDao.withdraw(bank);
      if (isWithdraw) {
        //TODO
      } else {
        System.err.println("withdraw money: " + bank.getBalance() + " failed.");
      }
    } finally {
      withdrawLock.unlock();
    }
  }

  @Override
  public boolean deposit(int senderId, Bank bank) throws RemoteException {
    depositLock.lock();
    try {
      System.err.println("deposit starting: " + bank.getBalance() + " from sender=" + senderId);
      bankDao.deposit(bank);
      System.err.println("deposit finished, new balance=" + bankDao.getBank(bank.getId()).getBalance());
      return true;
    } finally {
      depositLock.unlock();
    }
  }
}
