package recfun

import scala.annotation.tailrec

object Main {
  def main(args: Array[String]) {
    println("Pascal's Triangle")
    for (row <- 0 to 10) {
      for (col <- 0 to row)
        print(pascal(col, row) + " ")
      println()
    }
  }

  /**
    * Exercise 1
    */
  def pascal(c: Int, r: Int): Int = {
    // if we are in the left-most or right-most column for a given row, our value is 1
    if(c == 0 || c == r) 1
    // our value is the sum of values of our two ancestors.
    else pascal(c - 1, r - 1) + pascal(c, r - 1)
  }

  /**
    * Exercise 2
    */
  def balance(chars: List[Char]): Boolean = {

    def balanceIter(bal: Int, chars: List[Char]): Boolean = {
      // if we ever see more closing than we have had opening, fail it
      if(bal<0) false
      // if we have run out of characters to parse, we win if our balance is 0
      else if(chars.isEmpty) bal == 0
      // if we find an opening paren, increment our counter and continue with remainder
      else if(chars.head == '(') balanceIter(bal+1,chars.tail)
      // if we find a closing paren, decrement our counter and continue with remainder
      else if(chars.head == ')') balanceIter(bal-1,chars.tail)
      // we have neither an open or close, so just skip this char and continue with remainder
      else balanceIter(bal,chars.tail)
    }

    balanceIter(0, chars)
  }

  /**
    * Exercise 3
    */
  def countChange(money: Int, coins: List[Int]): Int = {

    def countIter(amt: Int, cur: List[Int]): Int = {
      // no amount to change, so we can give exact change for free!
      if (amt == 0) 1
      // if we have an amount to change, but no currency to give it, we fail.
      else if (cur.isEmpty) 0
      // if we can't change our amount with the first coin, skip this coin
      else if (amt - cur.head < 0) countChange(amt, cur.tail)
        // count the ways to make change with 1 of the first coin, plus count with only the other remaining coins.
      else countChange(amt - cur.head, coins) + countChange(amt, cur.tail)
    }

    countIter(money, coins)
  }
}
