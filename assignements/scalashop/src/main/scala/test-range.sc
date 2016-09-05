val w = 100
val n = 10000
val step = w / n
val p = 0 to w by step

p zip p.tail map {case (from, end) => {
  if((w - end) < step) (from, w) else (from, end)
}}