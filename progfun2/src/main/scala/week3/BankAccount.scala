package week3

/**
  * Object with state are usually represented by objects that
  * have some variable members
  */
class BankAccount {
  private var balance = 0
  def deposit(amount: Int): Unit = {
    if (amount > 0) balance += amount
  }
  def withdraw(amount: Int): Int = {
    if(0 < amount && amount <= balance) {
      balance -= amount
      balance
    } else throw new Error ("insufficient funds")
  }
}
