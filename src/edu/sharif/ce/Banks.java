package edu.sharif.ce;


import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

import edu.sharif.ce.snapshot.config.Configuration;
import edu.sharif.ce.snapshot.core.model.entity.Bank;
import edu.sharif.ce.snapshot.core.rmi.RMIInterface;
import edu.sharif.ce.snapshot.util.RandomGenerator;

/**
 * The Banks.
 */
public class Banks {
  /**
   * The constant time.
   */
  private static int time;

  /**
   * The entry point of application.
   *
   * @param args the input arguments
   * @throws Exception the exception
   */
  public static void main(String[] args) throws Exception {
    Registry r = LocateRegistry.getRegistry(Configuration.RMI_PORT.get());
    // Thread pool to transfer money
    ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();

    // each bank send random money to other banks
    executorService.scheduleAtFixedRate(() -> {
      time++;
      IntStream.range(0, Configuration.NUMBER_OF_BANKS.get())
        .forEach(i ->
          IntStream.range(0, Configuration.NUMBER_OF_BANKS.get())
            .forEach(j -> {
              try {
                RMIInterface bankServerRemote = (RMIInterface) r.lookup("localhost/BankServer" + i);
                if (RandomGenerator.hasChance() && j != i) {
                  bankServerRemote.sendMoney(j, new Bank(i, RandomGenerator.generateRandomAmount()), time);
                }
              } catch (RemoteException e) {
                System.err.println("Failed to transfer money to random banks");
              } catch (NotBoundException e) {
                System.err.println("Couldn't find remote bank");
              }
            }));
    }, 0, Configuration.TIMEOUT_PERIOD.get(), TimeUnit.MILLISECONDS);

    Scanner scanner = new Scanner(System.in);
    scanner.next();
    ExecutorService e = Executors.newFixedThreadPool(1);
    e.execute(() -> {
      try {
        RMIInterface bankServerRemote = (RMIInterface) r.lookup("localhost/BankServer0");
        bankServerRemote.receiveToken(0, 0);
      } catch (RemoteException e1) {
        System.err.println("Failed to receive token from remote");
      } catch (NotBoundException e1) {
        System.err.println("Couldn't find remote bank");
      }
    });
  }
}
