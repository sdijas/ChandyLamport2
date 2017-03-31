package edu.sharif.ce.snapshot.core.model.dao;


import java.util.ArrayList;
import java.util.List;

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
  List<Bank> banks = new ArrayList<>();

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
