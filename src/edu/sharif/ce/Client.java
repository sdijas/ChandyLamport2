package edu.sharif.ce;


import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import edu.sharif.ce.snapshot.config.Configuration;

public class Client {
  public static void main(String[] args) throws Exception {
    Registry r = LocateRegistry.getRegistry(Configuration.RMI_PORT.get());

    String[] names = r.list();
    for (String s : names) {
      System.out.println(s);
    }

  }
}
