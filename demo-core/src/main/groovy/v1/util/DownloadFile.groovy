package v1.util

/**
 * Created by BG244210 on 2016/7/8.
 */
def stream = new URL("https://raw.githubusercontent.com/roy2015/githubdemo/master/SimpleScriptService.groovy").openStream()
def stream2 = new URL("https://raw.githubusercontent.com/roy2015/githubdemo/master/SimpleScriptService.groovy").openConnection()
def total = stream2.getContentLength()

println "初始文件大小为：" + (total / 1024 / 1024) + "M"
def len
def hasRead = 0;
byte[] arr = new byte[1024]
def out = new FileOutputStream("D://test//123.groovy")
def lastResult = 0
while ((len = stream.read(arr)) != -1) {
    out.write(arr, 0, len)
    hasRead += len
    def decimal = hasRead / total * 100 + ""

    if (!decimal.equals("100"))
        decimal = decimal.substring(0, decimal.indexOf("."))

    if (lastResult.equals(Integer.parseInt(decimal))) {
        lastResult++
        println("下载进度:" + decimal + "%")
    }
}

stream.close()
out.close()
println("下载完成!");

