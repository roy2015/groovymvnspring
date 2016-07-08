package v1

import com.roy.v8.bill.UserVo

/**
 * Created by BG244210 on 2016/7/7.
 */

def langs = new XmlParser().parse(UserVo.class.getResourceAsStream("/languages.xml"))
println "type = ${langs.attribute("type")}"
langs.language.each {
    println it.text()
}



