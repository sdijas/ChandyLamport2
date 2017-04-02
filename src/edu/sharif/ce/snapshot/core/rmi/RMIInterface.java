package edu.sharif.ce.snapshot.core.rmi;


import java.rmi.Remote;
import java.rmi.RemoteException;

import edu.sharif.ce.snapshot.core.model.dao.BankDaoImpl;
import edu.sharif.ce.snapshot.core.model.entity.Bank;

/**
 * The interface RMIInterface.
 *
 * @author Alireza Aghamohammadi
 */
public interface RMIInterface extends Remote {


  /**
   * Take money boolean.
   *
   * @param senderId the sender id
   * @param bank     the bank
   * @return the boolean
   * @throws RemoteException the remote exception
   */
  boolean takeMoney(int senderId, Bank bank) throws RemoteException;


  /**
   * Transfer money.
   *
   * @param recipientId the recipient id
   * @param bank        the bank
   * @throws RemoteException the remote exception
   */
  void transferMoney(int recipientId, Bank bank) throws RemoteException;

  /**
   * Gets bank dao.
   *
   * @return the bank dao
   * @throws RemoteException the remote exception
   */
  public BankDaoImpl getBankDao() throws RemoteException;
}
