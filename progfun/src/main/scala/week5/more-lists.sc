val xs = List(2,4,6,8,10,12,14,16,18,20)
val ys = List(11,12)


// Number of elements
xs.length

// Last element
xs.last

// All elem excepts last one
xs.init

// N first elem
xs take 2

// All except N first
xs drop 4

// n-th elem
xs(5)
xs apply 5

// Concat
xs ::: ys
xs ++ ys

xs.reverse

xs updated (5, 13)

xs indexOf 16

xs indexOf 21

xs contains 12



