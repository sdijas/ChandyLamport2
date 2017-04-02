package edu.sharif.ce;


import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;

import edu.sharif.ce.snapshot.config.Configuration;
import edu.sharif.ce.snapshot.core.model.entity.Bank;
import edu.sharif.ce.snapshot.core.rmi.RMIInterface;

public class Client {
  public static Registry r;

  public static void main(String[] args) throws Exception {
    r = LocateRegistry.getRegistry(Configuration.RMI_PORT.get());

    RMIInterface bankServerRemote = (RMIInterface) r.lookup("localhost/BankServer0");
    bankServerRemote.takeMoney(1, new Bank(2, 500));
    List<Bank> banks = bankServerRemote.getBankDao().allBanks();
    banks.forEach(System.err::println);
  }
}
