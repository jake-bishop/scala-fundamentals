import example.Hello
import org.scalatest.FunSuite

/**
  * Created by bishopja on 7/8/2016.
  */
class HelloTest extends FunSuite {
test("sayHello method works correctly"){
  val hello = new Hello
  assert(hello.sayHello("Scala") == "Hello, Scala!")
}
}
