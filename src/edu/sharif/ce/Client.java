package edu.sharif.ce;


import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import edu.sharif.ce.snapshot.config.Configuration;
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
      time++;
      System.err.println(time);
    }, 0, Configuration.TIMEOUT_PERIOD.get(), TimeUnit.MILLISECONDS);

  }
}
