package tests

import org.junit.Assert._
import org.junit.Test
import library._

class FiltragePageTest {

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

  val mypage2 = Tag("html", List(),
    List(
      Tag("head", List(),
        List(
          Tag("meta", List(("charset", "utf-8")), List()),
          Tag("title", List(), List(Texte("My Page"))))),
      Tag("body", List(), List(
        Tag("center", List(), List(
          Tag("a", List(("href", "http://www.irisa.fr")),
            List(Texte("développeurazdazerennes")))))))))

  val mypage3 = Tag("html", List(),
    List(
      Tag("head", List(),
        List(
          Tag("meta", List(("charset", "utf-8")), List()),
          Tag("title", List(), List(Texte("My Page"))))),
      Tag("body", List(), List(
        Tag("center", List(), List(
          Tag("a", List(("href", "http://www.irisa.fr")),
            List(Texte("développeur azdaze rennes")))))))))

  @Test
  def testFiltrageHtml1 {
    val e: Expression = Mot("href")
    assertFalse(FiltragePage.filtreHtml(mypage, e))
  }

  @Test
  def testFiltrageHtml2 {
    val e: Expression = Mot("Word")
    assertTrue(FiltragePage.filtreHtml(mypage, e))
  }

  @Test
  def testFiltrageHtml3 {
    val e: Expression = Mot("not here")
    assertFalse(FiltragePage.filtreHtml(mypage, e))
  }

  @Test
  def testFiltrageHtml4 {
    val e: Expression = Mot("voiture")
    assertFalse(FiltragePage.filtreHtml(mypage, e))
  }

  @Test
  def testFiltrageHtml5 {
    val e: Expression = Mot("développeur")
    assertTrue(FiltragePage.filtreHtml(mypage2, e))
  }

  @Test
  def testFiltrageHtml6 {
    val e: Expression = Mot("développeur")
    assertTrue(FiltragePage.filtreHtml(mypage3, e))
  }
  
  @Test
  def testFiltrageHtml7 {
    val e: Expression = Mot("rennes")
    assertTrue(FiltragePage.filtreHtml(mypage3, e))
  }
  
  @Test
  def testFiltrageHtml8 {
    val e: Expression = Ou(Mot("rennes"), Mot("développeur"))
    assertTrue(FiltragePage.filtreHtml(mypage3, e))
  }
  
  @Test
  def testFiltrageHtml9 {
    val e: Expression = Et(Mot("rennes"), Mot("développeur"))
    assertTrue(FiltragePage.filtreHtml(mypage3, e))
  }
  
  @Test
  def testFiltrageHtml10 {
    val e: Expression = Ou(Mot("rennes"), Mot("développeur"))
    assertFalse(FiltragePage.filtreHtml(mypage, e))
  }

}