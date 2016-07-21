package v1.util


def listDirectory(path, indent){
    File dir = new File(path)
    dir.eachFile { file ->
        (0 ..< indent).each { print "-"}
        println "${file.getName()}"

        if(file.isDirectory()){
            listDirectory(file.getAbsolutePath(), 2+ indent)
        }

    }
}

//path = args[0]
listDirectory("E:/downloadpkgs",0)

