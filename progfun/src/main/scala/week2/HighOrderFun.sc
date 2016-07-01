def sum(f: Int => Int)(a: Int, b: Int): Int =
  if (a > b) 0 else f(a) + sum(f)(a + 1, b)

def product(f: Int => Int)(a: Int, b: Int): Int =
  if (a > b) 1 else f(a) * product(f)(a + 1, b)

def fact(n: Int) = product(x => x)(1, n)

sum(x => x * x)(1, 1000)
product(x => x)(1, 5)
fact(5)

def mapReduce(map: Int => Int)(a: Int, b: Int)(reduce: (Int, Int) => Int, unit: Int): Int=
  if (a > b) unit else reduce(map(a), mapReduce(map)(a + 1, b)(reduce, unit))

mapReduce(x => x * x)(1, 1000)((x, y) => x + y, 0)
mapReduce(x => x)(1, 5)((x, y) => x * y, 1)


def sum_tail(f: Int => Int)(a: Int, b: Int): Int = {
  def loop(a: Int, acc: Int): Int = {
    if(a > b) acc else loop(a + 1, acc + f(a))
  }
  loop(a, 0)
}

sum_tail((x: Int) => x * x)(1, 1000)
