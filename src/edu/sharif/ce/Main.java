package edu.sharif.ce;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import edu.sharif.ce.snapshot.config.Configuration;
import edu.sharif.ce.snapshot.core.model.dao.BankDaoImpl;
import edu.sharif.ce.snapshot.core.model.entity.Bank;
import edu.sharif.ce.snapshot.core.rmi.BankServerRemote;

/**
 * The launcher of application
 */
public class Main {
  private static Registry r;


  /**
   * The entry point of application.
   *
   * @param args the input arguments
   * @throws RemoteException       the remote exception
   * @throws AlreadyBoundException the already bound exception
   */
  public static void main(String[] args) throws RemoteException, AlreadyBoundException {
    startRMIRegistry();
    bind();
  }

  private static void startRMIRegistry() throws RemoteException {
    r = LocateRegistry.createRegistry(Configuration.RMI_PORT.get());
  }


  private static void bind() throws AlreadyBoundException, RemoteException {
    System.setProperty("java.rmi.server.hostname", "localhost");
    BankDaoImpl bankDao = new BankDaoImpl();
    BankServerRemote bankServerRemote = new BankServerRemote(bankDao);
    for (Bank bank : bankDao.allBanks()) {
      r.bind("localhost/BankServer" + bank.getId(), bankServerRemote);
    }
  }
}
