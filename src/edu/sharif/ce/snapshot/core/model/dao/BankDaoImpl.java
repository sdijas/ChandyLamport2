package edu.sharif.ce.snapshot.core.model.dao;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import edu.sharif.ce.Client;
import edu.sharif.ce.snapshot.config.Configuration;
import edu.sharif.ce.snapshot.core.model.entity.Bank;
import edu.sharif.ce.snapshot.core.rmi.RMIInterface;

/**
 * The Bank data access object class.
 *
 * @author Alireza Aghamohammadi
 */
public class BankDaoImpl implements BankDao, Serializable {
  /**
   * The Banks.
   */
  private List<Bank> banks;

  /**
   * Instantiates a new Bank dao.
   */
  public BankDaoImpl() {
    banks = new ArrayList<>(Configuration.NUMBER_OF_BANKS.get());
    for (int i = 0; i < Configuration.NUMBER_OF_BANKS.get(); i++)
      banks.add(new Bank(i, Configuration.INITIAL_BANK_CREDIT.get()));
  }

  @Override
  public void deposit(Bank bank) {
    Bank that = banks.get(bank.getId());
    that.setBalance(that.getBalance() + bank.getBalance());
  }

  @Override
  public boolean withdraw(Bank bank) {
    Bank that = banks.get(bank.getId());
    if (that.getBalance() >= bank.getBalance()) {
      that.setBalance(that.getBalance() - bank.getBalance());
      return true;
    }
    return false;
  }

  @Override
  public Bank getBank(int id) {
    return banks.get(id);
  }

  @Override
  public List<Bank> allBanks() {
    return banks;
  }

  @Override
  public RMIInterface getRemoteBank(Bank bank) {
    try {
      return (RMIInterface) Client.r.lookup("localhost/BankServer" + bank.getId());
    } catch (Exception e) {
      e.printStackTrace();
      System.err.println("Failed to ger remote interface for bank:" + bank.getId());
      return null;
    }
  }

}
