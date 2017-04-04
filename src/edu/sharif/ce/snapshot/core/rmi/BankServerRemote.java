package edu.sharif.ce.snapshot.core.rmi;


import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import edu.sharif.ce.snapshot.config.Configuration;
import edu.sharif.ce.snapshot.core.model.dao.BankDaoImpl;
import edu.sharif.ce.snapshot.core.model.entity.Bank;
import edu.sharif.ce.snapshot.core.model.entity.Snapshot;
import edu.sharif.ce.snapshot.util.RandomGenerator;

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
  private final Lock tokenLock = new ReentrantLock();

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
  public void sendMoney(int recipientId, Bank bank, int time) throws RemoteException {
    withdrawLock.lock();
    try {
      boolean isWithdraw = bankDao.withdraw(bank);
      if (isWithdraw) {
        String report = "-\t" + time + "\t" + bank.getBalance() + "\t-\t";
        int delay = RandomGenerator.generateQueueingDelay();
        try {
          Thread.sleep(delay);
        } catch (InterruptedException e) {
          System.err.println("failed to transfer money");
        }
        boolean isTransferred = bankDao.getRemoteBank(bankDao.getBank(recipientId)).receiveMoney(bank.getId(), new Bank(recipientId, bank.getBalance()));
        if (isTransferred) {
          report = report + (time + delay / Configuration.TIMEOUT_PERIOD.get());
          System.err.println(report);
        }
      } else {
        System.err.println("sendMoney money: " + bank.getBalance() + " failed.");
      }
    } finally {
      withdrawLock.unlock();
    }
  }

  @Override
  public boolean receiveMoney(int senderId, Bank bank) throws RemoteException {
    depositLock.lock();
    try {
      bankDao.getBank(bank.getId()).getSnapshot().incrementMoneyInTransit(senderId, bank);
      bankDao.deposit(bank);
      return true;
    } finally {
      depositLock.unlock();
    }
  }

  @Override
  public void receiveToken(int receiverBankId, int senderBankId) throws RemoteException {
    tokenLock.lock();
    depositLock.lock();
    withdrawLock.lock();
    try {
      try {
        Thread.sleep(RandomGenerator.generateQueueingDelay());
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      Snapshot snapshot = bankDao.getBank(senderBankId).getSnapshot();
      if (!snapshot.isRecording()) {
        snapshot.startSnapshot(bankDao.getBank(senderBankId), bankDao.getBank(senderBankId).getBalance(), bankDao.allBanks());
        ExecutorService executorService = Executors.newFixedThreadPool(Configuration.NUMBER_OF_BANKS.get() - 1);
        bankDao
          .allBanks()
          .parallelStream()
          .filter(b -> b.getId() != senderBankId)
          .forEach(bank -> executorService.execute(() -> {
            try {
              bankDao.getRemoteBank(bank).receiveToken(senderBankId, bank.getId());
            } catch (RemoteException e) {
              System.err.println("Failed to sent marker to bank:" + bank.getId());
            }
          }));
      }
      snapshot.stopRecording(bankDao.getBank(receiverBankId));
      if (!snapshot.isRecording())
        bankDao.getBank(senderBankId).getSnapshot().stopSnapshot();
    } finally {
      tokenLock.unlock();
      depositLock.unlock();
      withdrawLock.unlock();
    }
  }

  @Override
  public BankDaoImpl getBankDao() throws RemoteException {
    return bankDao;
  }
}
