package com.hzbook.chapter3

/**
 * Created by BG244210 on 2016/7/26.
 */



def test1(){
    def age = 25
    println("my age is ${age}")
    println("""
           my
           age
           is
           ${age}
""")
    println('my age is ${age}')
//    print 3/5
}


print args
test1()
