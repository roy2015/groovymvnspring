package com.roy

/**
  * Created by BG244210 on 1/3/2017.
  */
import scala.actors.Actor

object actor_test {

  def calculateSum(number: Int) = {
    val num = number
    val caller = Actor.self // 获得当前线程的引用

    for(i <- 1 to num){
      Actor.actor{
        caller ! {      // 调用！发送消息
          println(i)  // 打印每次发送出去的i
          i   // 发送i，下面receive中case的sumInSent类型为Int
        }
      }
    }

    // 下句的 /: 等效于List.foldLeft方法
    val sum = (0 /: (1 to num)){
      (partialSum, elem) =>
        Actor.receive{  // 接收并用case匹配传入的Int值
          case sumInSent: Int => partialSum + elem    // 统计和
        }
    }
    println("sum = " + sum)
  }

  def main(args: Array[String]): Unit = {
    calculateSum(10)
  }
}
