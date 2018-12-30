package util

import java.io.File

object DirectoryUtils {
  val currentDir = new File(".").getAbsolutePath
  private def init(classObj: Class[_]): StringBuilder = {
    val strBuilder = new StringBuilder(currentDir)
    strBuilder
      .append(File.separator)
      .append(s"src${File.separator}main${File.separator}scala${File.separator}")
  }
  def getCurrentDirectory(classObj: Class[_]): String = init(classObj).append(classObj.getPackage.getName.replace(".", File.separator)).toString
  def getClassPath(classObj: Class[_]): String = init(classObj).append(classObj.getName.replace("$", "").replace(".", File.separator)).append(".scala").toString
}
