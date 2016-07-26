abstract class JSON

case class JSeq(elem: List[JSON])            extends JSON
case class JObj(bindings: Map[String, JSON]) extends JSON
case class JNum(num: Double)                 extends JSON
case class JStr(str: String)                 extends JSON
case class JBool(b: Boolean)                 extends JSON
case object JNull                            extends JSON

val data = JObj(Map (
    "firstName" -> JStr("John"),
    "lastName" -> JStr("Smith"),
    "address" -> JObj(Map(
      "streetAddress" -> JStr("21 2nd Street"),
      "state" -> JStr("NY"),
      "postalCode" -> JNum(10021)
    )),
    "phoneNumbers" -> JSeq(List(
      JObj(Map("type" -> JStr("home"), "number" -> JStr("212 555-1234"))),
      JObj(Map("type" -> JStr("fax"), "number" -> JStr("646 555-1234")))
    ))
));

def show(json: JSON): String = json match {
  case JSeq(elems) => "[" + (elems map show mkString ", ") + "]"
  case JObj(bindings) => {
    val assocs = bindings map {
      case (key, value) => "\"" + key + "\": " + show(value)
    }
    "{\n" + (assocs mkString ",\n") + "}"
  }
  case JNum(num) => num.toString
  case JStr(str) => ("\"" + str + "\"")
  case JBool(b) => b.toString
  case JNull => "null"
}

show(data)

val data2 = List(
  JObj(Map (
    "firstName" -> JStr("John"),
    "lastName" -> JStr("Smith"),
    "address" -> JObj(Map(
      "streetAddress" -> JStr("21 2nd Street"),
      "state" -> JStr("NY"),
      "postalCode" -> JNum(10021)
    )),
    "phoneNumbers" -> JSeq(List(
      JObj(Map("type" -> JStr("home"), "number" -> JStr("212 555-1234"))),
      JObj(Map("type" -> JStr("fax"), "number" -> JStr("646 555-1234")))
    ))
  )),
  JObj(Map (
    "firstName" -> JStr("Jim"),
    "lastName" -> JStr("Doe"),
    "address" -> JObj(Map(
      "streetAddress" -> JStr("53 Road"),
      "state" -> JStr("LA"),
      "postalCode" -> JNum(65999)
    )),
    "phoneNumbers" -> JSeq(List(
      JObj(Map("type" -> JStr("home"), "number" -> JStr("555 919 911")))
    ))
  ))
);

// The left hand side of a generator can be a pattern
for {
  JObj(bindings) <- data2
  JSeq(phones) = bindings("phoneNumbers")
  JObj(phone) <- phones
  number = phone("number")
  JStr(digits) = number
  if digits startsWith "212"
} yield (bindings("firstName"), bindings("lastName"))