val f: String => String = { case "ping" => "pong" }

f("ping")

try {
  f("abc")
} catch {
  case _ => println("Error")
}

val f2: PartialFunction[String, String] = { case "ping" => "pong" }

f2.isDefinedAt("ping")
f2.isDefinedAt("abc")