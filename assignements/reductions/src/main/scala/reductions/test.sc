def max(a: Float, b: Float): Float = if (a > b) a else b

val input1 = Array[Float](0f, 1f, 6f, 7f, 10f, 10f, 5f, 11f)
(input1 zip (0 to input1.length)).slice(1,5) toList

(input1 zip (0 to input1.length)).slice(1,5).map{case (x, i) => x / i} toList

(input1 zip (0 to input1.length)).slice(1,5).map{case (x, i) => x / i}.reduce(max)

1 until 10