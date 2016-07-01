// Naturally tail recursive
def gcd(a: Int, b: Int): Int =
  if (b == 0) a else gcd(b, a % b)

// Not tail recursive => stack overflow possible
def factorial(n: Int): Int =
  if(n == 0) 1 else n * factorial (n - 1)

gcd(14, 21)

factorial(5)

def factorial_tail(n: Int): Int = {
  def loop(acc: Int, n: Int): Int =
    if(n == 1) acc else loop(acc * n, n - 1)
  loop(1, n)
}

factorial_tail(5)