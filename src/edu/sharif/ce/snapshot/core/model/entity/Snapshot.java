package edu.sharif.ce.snapshot.core.model.entity;


import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * The distributed Chandy/Lamport snapshot.
 *
 * @author Alireza Aghamohammadi
 * @see Bank
 */
public class Snapshot implements Serializable {
  // The identifier of bank account.
  private int id;

  // The amount of money held in a bank account at a given moment.
  private int balance;

  // The amount of money in transit.
  private int moneyInTransit;

  // Each banks in the system records its local state and the state of its incoming channels.
  private Set<Integer> incomingChannels = new HashSet<>();

  /**
   * Gets id.
   *
   * @return the identifier of bank account
   */
  public int getId() {
    return id;
  }

  /**
   * Gets balance.
   *
   * @return the balance
   */
  public int getBalance() {
    return balance;
  }

  /**
   * Get money in transit.
   *
   * @return the the amount of money in transit.
   */
  public int getMoneyInTransit() {
    return moneyInTransit;
  }

  /**
   * Start snapshot.
   *
   * @param bankId  the bank id
   * @param balance the balance
   * @param banks   the banks
   */
  public void startSnapshot(int bankId, int balance, List<Bank> banks) {
    this.balance = balance;
    this.moneyInTransit = 0;
    incomingChannels
      .addAll(
        banks
          .parallelStream()
          .filter(b -> b.getId() != bankId)
          .map(Bank::getId)
          .collect(Collectors.toSet()));
  }

  /**
   * Stop snapshot.
   */
  public void stopSnapshot() {
    this.balance = 0;
    this.moneyInTransit = 0;
    incomingChannels.clear();
  }
}
