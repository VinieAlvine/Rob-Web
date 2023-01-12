package tests

import org.junit.Assert._
import org.junit.Test
import library._

class AnalyseTest {

  val mypage = Tag("html", List(),
    List(
      Tag("head", List(),
        List(
          Tag("meta", List(("charset", "utf-8")), List()),
          Tag("title", List(), List(Texte("My Page"))))),
      Tag("body", List(), List(
        Tag("center", List(), List(
          Tag("a", List(("href", "http://www.irisa.fr")),
            List(Texte("Word")))))))))

  @Test
  def analyseTest1 {
    assertEquals("My Page", Analyse.title(mypage))
  }
  
  @Test
  def analyseTest2 {
    assertTrue(Analyse.title(mypage) != "MyPage")
  }

}