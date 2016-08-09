import week4.{BankAccount, Consolidator}

val a = new BankAccount
val b = new BankAccount
val c = new Consolidator(List(a, b))

c.totalBalance

a deposit 20
c.totalBalance

a deposit 30
c.totalBalance