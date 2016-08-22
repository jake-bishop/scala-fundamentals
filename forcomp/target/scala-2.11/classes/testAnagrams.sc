
val test = "MyTestWord"
val sentence = List("This", "is", "my", "sentence")

test.groupBy(_.toLower).map(y => (y._1, y._2.length)).toList.sortBy(_._1)

sentence.mkString