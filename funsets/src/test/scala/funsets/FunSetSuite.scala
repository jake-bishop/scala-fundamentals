package funsets

import org.scalatest.FunSuite


import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

/**
 * This class is a test suite for the methods in object FunSets. To run
 * the test suite, you can either:
 *  - run the "test" command in the SBT console
 *  - right-click the file in eclipse and chose "Run As" - "JUnit Test"
 */
@RunWith(classOf[JUnitRunner])
class FunSetSuite extends FunSuite {

  /**
   * Link to the scaladoc - very clear and detailed tutorial of FunSuite
   *
   * http://doc.scalatest.org/1.9.1/index.html#org.scalatest.FunSuite
   *
   * Operators
   *  - test
   *  - ignore
   *  - pending
   */

  /**
   * Tests are written using the "test" operator and the "assert" method.
   */
  // test("string take") {
  //   val message = "hello, world"
  //   assert(message.take(5) == "hello")
  // }

  /**
   * For ScalaTest tests, there exists a special equality operator "===" that
   * can be used inside "assert". If the assertion fails, the two values will
   * be printed in the error message. Otherwise, when using "==", the test
   * error message will only say "assertion failed", without showing the values.
   *
   * Try it out! Change the values so that the assertion fails, and look at the
   * error message.
   */
  // test("adding ints") {
  //   assert(1 + 2 === 3)
  // }


  import FunSets._

  test("contains is implemented") {
    assert(contains(x => true, 100))
  }

  /**
   * When writing tests, one would often like to re-use certain values for multiple
   * tests. For instance, we would like to create an Int-set and have multiple test
   * about it.
   *
   * Instead of copy-pasting the code for creating the set into every test, we can
   * store it in the test class using a val:
   *
   *   val s1 = singletonSet(1)
   *
   * However, what happens if the method "singletonSet" has a bug and crashes? Then
   * the test methods are not even executed, because creating an instance of the
   * test class fails!
   *
   * Therefore, we put the shared values into a separate trait (traits are like
   * abstract classes), and create an instance inside each test method.
   *
   */

  trait TestSets {
    val s1 = singletonSet(1)
    val s2 = singletonSet(2)
    val s3 = singletonSet(3)
    val s4: Set = x => Array(2,4,6,8) contains x
    val s5: Set = x => Array(3, 6, 9) contains x
  }

  /**
   * This test is currently disabled (by using "ignore") because the method
   * "singletonSet" is not yet implemented and the test would fail.
   *
   * Once you finish your implementation of "singletonSet", exchange the
   * function "ignore" by "test".
   */
  test("singletonSet(1) contains 1") {

    /**
     * We create a new instance of the "TestSets" trait, this gives us access
     * to the values "s1" to "s3".
     */
    new TestSets {
      /**
       * The string argument of "assert" is a message that is printed in case
       * the test fails. This helps identifying which assertion failed.
       */
      assert(contains(s1, 1), "Singleton")
    }
  }

  test("union contains all elements of each set") {
    new TestSets {
      val s = union(s1, s2)
      assert(contains(s, 1), "Union 1")
      assert(contains(s, 2), "Union 2")
      assert(!contains(s, 3), "Union 3")
    }
  }

  test("intersect contains elements in both sets") {
    new TestSets {
      val s = intersect(s4, s5)
      assert(contains(s,6))
    }
  }

  test("diff contains unique elements from set s not in set t") {
    new TestSets {
      val s = diff(s4, s5)
      assert(!contains(s, 1))
      assert(contains(s, 2))
      assert(!contains(s, 3))
      assert(contains(s, 4))
      assert(!contains(s, 6))
      assert(contains(s, 8))
      assert(!contains(s, 9))
    }
  }

  test("filter returns subset of s where p holds (x > 5)") {
    new TestSets {
      def p(x: Int): Boolean = x > 5

      assert(!contains(filter(s4, p), 2))
      assert(!contains(filter(s4, p), 4))
      assert(contains(filter(s4, p), 6))
      assert(contains(filter(s4, p), 8))
      assert(!contains(filter(s5, p), 3))
      assert(contains(filter(s5, p), 6))
      assert(contains(filter(s5, p), 9))
    }
  }

  test("filter does not return elements not in set but where p holds (x > 5)") {
    new TestSets {
      def p(x: Int): Boolean = x > 5

      assert(!contains(filter(s4, p), 10))
      assert(!contains(filter(s5, p), 12))
    }
  }

  test("forall returns true if all elements in a set are in bounds and even") {
    val s1: Set = x => Array(-200, -50, -42, 0, 2, 4, 6, 8, 20, 66, 400, 998) contains x
    val s2: Set = x => Array(-2, 1, 2) contains x

    def p(x: Int): Boolean = x % 2 == 0

    // all within bounds are even
    assert(forall(s1, p))
    assert(!forall(s2, p))
  }

  test("forall returns true if no elements are in bounds") {
    val s1: Set = x => Array(-1002, 1001) contains x
    def p(x: Int): Boolean = x % 2 == 0

    assert(forall(s1, p))
  }

  test("exists returns true if set contains an even number") {
    val s1: Set = x => Array(-57, -1, 3, 6, 9, 101) contains x
    def p(x: Int): Boolean = x % 2 == 0

    assert(exists(s1, p))
  }

  test("exists returns false if set contains no even numbers") {
    val s1: Set = x => Array(-57, -1, 3, 5, 9, 101) contains x
    def p(x: Int): Boolean = x % 2 == 0

    assert(!exists(s1, p))
  }

  test("exists returns false if set is out of bounds") {
    val s1: Set = x => Array(-1001, 1002) contains x
    def p(x: Int): Boolean = x % 2 == 0

    assert(!exists(s1, p))
  }

  test("map doubles all elements in set") {
    val s1: Set = x => Array(-5, -2, 0, 3, 7, 25, 60) contains x
    def double(x: Int): Int = x * 2
    def p(x: Int): Boolean = Array(-10, -4, 0, 6, 14, 50, 120) contains x

    assert(forall(map(s1, double), p))
  }

}
