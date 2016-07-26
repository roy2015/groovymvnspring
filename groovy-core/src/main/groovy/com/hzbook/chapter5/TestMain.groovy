package com.hzbook.chapter5

/**
 * Created by BG244210 on 2016/7/26.
 */


def useListtoImpl() {
    def library = [
            ['Ken', 'groovy'],
            ['Ken', 'uml'],
            ['Roy', 'java']
    ]

    //add two new loans
    library << ['Kelly', 'photo shop']
    library.add(['John', 'scala'])

    println "Library: ${library}"
    println("Roy has borrowed groovy ?  ${library.contains(['Ken', 'groovy'])} ")

}


def useMaptoImpl() {
    def library = ['Ken': ['groovy', 'uml'],
                   'Roy': ['java']]

    library['Ken'] = library['Ken'] << 'j2ee'

    println "Library: ${library}"
    println("Ken has boeeowed ${library['Ken'].size()} books. And list is bellow:")
    library['Ken'].each { a -> println a }

}

//useListtoImpl()
useMaptoImpl()