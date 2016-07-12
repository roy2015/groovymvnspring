package v1.util

/**
 * Created by BG244210 on 2016/7/11.
 */
def createMetaClass(Class clazz, Closure closure) {
    /*
        为传入的Class对象创建一个ExpandoMetaClass实例，但不将该ExpandoMetaClass实例注册到MetaClassRegistry对象中
    */
    def emc = new ExpandoMetaClass(clazz, false)
    /*
        该closure用来初始化ExpandoMetaClass对象，这种写法的思想与Template Method Pattern有异曲同工之妙
    */
    closure(emc)
    emc.initialize() // 完成初始化过程
    return emc
}

def executeScript(dslScriptCode, rootName, closure) {
    Script dslScript = new GroovyShell().parse(dslScriptCode)  // 读取并解析DSL代码，返回一个Script对象

    dslScript.metaClass = createMetaClass(dslScript.class) { emc ->
        /*
            动态新增一个名为"$rootName"的方法，注意"$rootName"的值决定于运行时，比如本例中的值为person
        */
        emc."$rootName" = closure
    }

    return dslScript.run() // 执行DSL代码
}

class Name {
    String firstname
    String lastname
    String toString() {
        "$firstname.$lastname"
    }
}

class Person {
    Name name
    Person(name) {
        this.name = name
    }
    String toString() {
        "My name is $name"
    }
}

/*
PersonDelegate对象是下面作为参数传入‘person方法’的closure的delegate，形象点说，closure就是那对大括号{}以及大括号中的内容
如果您对closure的delegate不太熟悉，可以参考在下的另一篇文章《Groovy解惑——closure中的delegate》(http://www.blogjava.net/BlueSUN/archive/2007/12/22/169580.html)
    person {

    }
*/
class PersonDelegate {
    def person
    PersonDelegate(person) {
        this.person = person
    }
    /*
        关于methodMissing这一特殊方法，请参考在下的另一篇文章《Groovy高效编程——动态改变对象的能力》(http://www.blogjava.net/BlueSUN/archive/2007/07/15/130318.html)
    */
    def methodMissing(String name, Object args) {
        if ('name' == name && args[0] instanceof Closure) {
            def nameClosure = args[0]
            /*
                给nameClosure的delegate赋值，nameClosure就是name旁边的那个closure即一对大括号{}以及大括号中的内容
            */
            nameClosure.delegate = new NameDelegate(person)
            nameClosure.resolveStrategy = Closure.DELEGATE_FIRST // 指明closure中变量和方法的解析策略，本例选择DELEGATE_FIRST
            nameClosure()
        }
    }
    /*
        关于propertyMissing这一特殊方法，请参考在下的另一篇文章《Groovy高效编程——动态改变对象的能力》(http://www.blogjava.net/BlueSUN/archive/2007/07/15/130318.html)
    */
    def propertyMissing(String name) {}
}

/*
类似于PersonDelegate，
NameDelegate对象是下面作为参数传入‘name方法’的closure的delegate
        name {

        }
*/
class NameDelegate {
    def person
    NameDelegate(person) {
        this.person = person
    }
/*
    下面这些getter和setter是为了实现下面这种赋值而写的： firstname = '山风'和lastname  = '小子'
    person {
        name {
            firstname = '山风'
            lastname  = '小子'
        }
    }
*/
    def getFirstname() {
        return person.name.firstname
    }
    def setFirstname(String firstname) {
        person.name.firstname = firstname
    }
    def getLastname() {
        return person.name.lastname
    }
    def setLastname(String lastname) {
        person.name.lastname = lastname
    }

    def methodMissing(String name, Object args) {
        if ('firstname' == name) {
            person.name.firstname = args[0]
        } else if ('lastname' == name) {
            person.name.lastname = args[0]
        }
    }
    def propertyMissing(String name) {}
}

/*
    在这篇文章中，演示了两种赋值方式，各位可以根据自己的喜好选择一种，我个人偏好第一种 :)
*/

// 本例DSL的第一种写法
def dslScriptCode = '''
    person {
        name {
            firstname 'Daniel'
            lastname  'Sun'
        }
    }
'''

def scriptClosure = { Closure personClosure ->
    def person = new Person(new Name())
    personClosure.delegate = new PersonDelegate(person)
    personClosure.resolveStrategy = Closure.DELEGATE_FIRST
    personClosure()

    return person
}
def person = executeScript(dslScriptCode, 'person', scriptClosure)

println person
