package v1.util

import org.slf4j.Logger
import org.slf4j.LoggerFactory

logger = LoggerFactory.getLogger("demo");

def listDirectory(path, indent){
    File dir = new File(path)
    dir.eachFile { file ->
        (0 ..< indent).each { print "-"}
         logger.debug(file.getName())

        if(file.isDirectory()){
            listDirectory(file.getAbsolutePath(), 2+ indent)
        }

    }
}

//path = args[0]
listDirectory("E:/downloadpkgs",0)

