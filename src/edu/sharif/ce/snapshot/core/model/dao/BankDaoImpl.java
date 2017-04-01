package edu.sharif.ce.snapshot.core.model.dao;


import java.util.ArrayList;
import java.util.List;

import edu.sharif.ce.snapshot.config.Configuration;
import edu.sharif.ce.snapshot.core.model.entity.Bank;

/**
 * The Bank data access object class.
 *
 * @author Alireza Aghamohammadi
 */
public class BankDaoImpl implements BankDao {
  /**
   * The Banks.
   */
  private List<Bank> banks;

  /**
   * Instantiates a new Bank dao.
   */
  public BankDaoImpl() {
    banks = new ArrayList<>(Configuration.NUMBER_OF_BANKS.get());
    for (int i = 0; i < banks.size(); i++)
      banks.add(new Bank(i, Configuration.INITIAL_BANK_CREDIT.get()));
  }

  @Override
  public void deposit(Bank bank) {
    Bank that = banks.get(bank.getId());
    that.setBalance(that.getBalance() + bank.getBalance());
  }

  @Override
  public void withdraw(Bank bank) {
    Bank that = banks.get(bank.getId());
    that.setBalance(that.getBalance() - bank.getBalance());
  }
}
