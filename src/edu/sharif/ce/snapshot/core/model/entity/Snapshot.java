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
  // The amount of money held in a bank account at a given moment.
  private int balance;

  // The amount of money in transit.
  private int moneyInTransit;

  // Each banks in the system records its local state and the state of its incoming channels.
  private Set<Integer> incomingChannels = new HashSet<>();

  /**
   * Gets balance.
   *
   * @return the balance
   */
  private int getBalance() {
    return balance;
  }

  /**
   * Get money in transit.
   *
   * @return the the amount of money in transit.
   */
  private int getMoneyInTransit() {
    return moneyInTransit;
  }

  /**
   * Start snapshot.
   *
   * @param bank    the bank
   * @param balance the balance
   * @param banks   the banks
   */
  public void startSnapshot(Bank bank, int balance, List<Bank> banks) {
    this.balance = balance;
    this.moneyInTransit = 0;
    incomingChannels
      .addAll(
        banks
          .parallelStream()
          .filter(b -> b.getId() != bank.getId())
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

  /**
   * Stop recording.
   *
   * @param bank the bank
   */
  public void stopRecording(Bank bank) {
    incomingChannels.remove(bank.getId());
  }

  /**
   * Is recording?
   *
   * @return the true if bank is recording snapshot otherwise return false
   */
  public boolean isRecording() {
    return incomingChannels.size() != 0;
  }

  /**
   * Increment money in transit.
   *
   * @param recipientBankId the recipient bank id
   * @param bank            the bank
   */
  public void incrementMoneyInTransit(int recipientBankId, Bank bank) {
    if (incomingChannels.contains(recipientBankId)) {
      this.moneyInTransit += bank.getBalance();
    }

  }

  @Override
  public String toString() {
    return "Snapshot[Balance(" + getBalance() + "), MoneyInTransit(" + getMoneyInTransit() + ")]";
  }
}
