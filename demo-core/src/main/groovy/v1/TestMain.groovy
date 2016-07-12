package v1

import com.roy.v8.bill.UserVo

/**
 * Created by BG244210 on 2016/7/7.
 */

def test1() {
    def langs = new XmlParser().parse(UserVo.class.getResourceAsStream("/languages.xml"))
    println "type = ${langs.attribute("type")}"
    langs.language.each {
        println it.text()
    }
}


def test2() {
    def money = new ExpandoMetaClass(Integer.class, true)
    money.getYuan << {
        ->
        def x = delegate as Integer
        return x
    }

    money.getJiao << {
        ->
        def x = delegate as float
        return x / 10
    }

    money.getFen << {
        ->
        def x = delegate as float
        return x / 100
    }

    money.initialize()

    println 12.yuan + 3.jiao

}

def test3(){
    print new Song('guo', 'jun').firstname
}

//test1()
//test2()
test3()


