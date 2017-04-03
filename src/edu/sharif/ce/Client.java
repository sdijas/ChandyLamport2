package edu.sharif.ce;


import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import edu.sharif.ce.snapshot.config.Configuration;
import edu.sharif.ce.snapshot.core.model.entity.Bank;
import edu.sharif.ce.snapshot.core.rmi.RMIInterface;

/**
 * The type Client.
 */
public class Client {
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
      int randomAmount = new Random().nextInt(Configuration.MAXIMUM_TRANSFER_AMOUNT.get() - Configuration.MINIMUM_TRANSFER_AMOUNT.get()) + Configuration.MINIMUM_TRANSFER_AMOUNT.get();
      try {
        bankServerRemote.sendMoney(1, new Bank(2, randomAmount));
        for (Bank b : bankServerRemote.getBankDao().allBanks())
          System.err.println(b);
      } catch (RemoteException e) {
        e.printStackTrace();
      }
    }, 0, Configuration.TIMEOUT_PERIOD.get(), TimeUnit.MILLISECONDS);

  }
}
