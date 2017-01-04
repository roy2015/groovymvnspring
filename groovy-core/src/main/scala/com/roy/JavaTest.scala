package com.roy

import org.apache.commons.lang3.StringUtils

/**
  * Created by BG244210 on 1/4/2017.
  */


object JavaTest {
  def test1 = {
    val list = List(1, 2, 3)
    list.foreach(println)
    val s = "123 "
    println(StringUtils.isBlank(s))
  }

  def main(args: Array[String]): Unit = {
    test1
  }


}
