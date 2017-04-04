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
   * Receive money.
   *
   * @param senderId the sender id
   * @param bank     the bank
   * @return the boolean
   * @throws RemoteException the remote exception
   */
  boolean receiveMoney(int senderId, Bank bank) throws RemoteException;


  /**
   * Send money.
   *
   * @param recipientId the recipient id
   * @param bank        the bank
   * @param time        the time
   * @throws RemoteException the remote exception
   */
  void sendMoney(int recipientId, Bank bank, int time) throws RemoteException;

  /**
   * Gets bank dao.
   *
   * @return the bank dao
   * @throws RemoteException the remote exception
   */
  public BankDaoImpl getBankDao() throws RemoteException;

  /**
   * Receive token.
   *
   * @param receiverBankId the receiver bank id
   * @param senderBankId   the sender bank id
   * @throws RemoteException the remote exception
   */
  void receiveToken(int receiverBankId, int senderBankId) throws RemoteException;

}
