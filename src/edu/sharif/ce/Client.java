package edu.sharif.ce;


import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import edu.sharif.ce.snapshot.config.Configuration;
import edu.sharif.ce.snapshot.core.model.entity.Bank;
import edu.sharif.ce.snapshot.core.rmi.RMIInterface;
import edu.sharif.ce.snapshot.util.RandomGenerator;

/**
 * The type Client.
 */
public class Client {
  /**
   * The constant time.
   */
  public static int time;

  /**
   * The entry point of application.
   *
   * @param args the input arguments
   * @throws Exception the exception
   */
  public static void main(String[] args) throws Exception {
    Registry r = LocateRegistry.getRegistry(Configuration.RMI_PORT.get());
    RMIInterface bankServerRemote = (RMIInterface) r.lookup("localhost/BankServer0");

    // Thread pool to transfer money
    ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();


    executorService.scheduleAtFixedRate(() -> {
      try {
        bankServerRemote.sendMoney(1, new Bank(2, RandomGenerator.generateRandomAmount()));
        for (Bank b : bankServerRemote.getBankDao().allBanks())
          System.err.println(b);
      } catch (RemoteException e) {
        e.printStackTrace();
      }
    }, 0, Configuration.TIMEOUT_PERIOD.get(), TimeUnit.MILLISECONDS);

  }
}
