package edu.sharif.ce;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

import edu.sharif.ce.snapshot.config.Configuration;
import edu.sharif.ce.snapshot.core.model.dao.BankDaoImpl;
import edu.sharif.ce.snapshot.core.model.entity.Bank;
import edu.sharif.ce.snapshot.core.rmi.BankServerRemote;

/**
 * The launcher of application
 */
public class Main {
  /**
   * The entry point of application.
   *
   * @param args the input arguments
   * @throws RemoteException       the remote exception
   * @throws AlreadyBoundException the already bound exception
   * @throws MalformedURLException the malformed url exception
   */
  public static void main(String[] args) throws RemoteException, AlreadyBoundException, MalformedURLException {
    startRMIRegistry();
    bind();
  }

  private static void startRMIRegistry() throws RemoteException {
    LocateRegistry.createRegistry(Configuration.RMI_PORT.get());
  }

  private static void bind() throws RemoteException, AlreadyBoundException, MalformedURLException {
    BankDaoImpl bankDao = new BankDaoImpl();
    for (Bank bank : bankDao.allBanks())
      Naming.bind("rmi://" + bank.getId(), new BankServerRemote(bankDao));
  }
}
