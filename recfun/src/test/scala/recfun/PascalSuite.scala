package recfun

import org.scalatest.FunSuite


import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import Main.pascal

@RunWith(classOf[JUnitRunner])
class PascalSuite extends FunSuite {

  test("pascal: col=0,row=2") {
    assert(pascal(0,2) === 1)
  }

  test("pascal: col=1,row=2") {
    assert(pascal(1,2) === 2)
  }

  test("pascal: col=1,row=3") {
    assert(pascal(1,3) === 3)
  }

  test("pascal: col=3,row=10") {
    assert(pascal(5,10) === 252)
  }
}
