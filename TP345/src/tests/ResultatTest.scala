package tests

import org.junit.Assert._
import org.junit.Test
import library._

class ResultatTest {
  var l: List[(String, String)]= ("b","u") :: Nil
   var l3: List[(String, String)]= ("t","u1") :: Nil
  var l2: List[(String, String)]= ("x","y") :: Nil

 // var p: String= "x"

  val page: Html= Tag("html", List(), List(
          Tag("head", List(), List(
            Tag("meta", List(("charset", "utf-8")), List()),
            Tag("title", List(), List(Texte("Ma page"))),
            Tag("link", List(("rel", "stylesheet"), ("href", "stylesheet.css")), List()))),
          Tag("body", List(), List(
            Tag("h1", List(), List(Texte("Résultats de la recherche du robot web :"))),
            Tag("ol", List(), List(Tag("li", List(), List(Tag("a", List(("href", "u")), List(Texte("t"))) ))))))))

   @Test
   def resultatVersHtml1{
    assertNotEquals(page,ProduireHtml.resultatVersHtml(l)) 
  }
  @Test
  def resultatVersHtml2{
    assertNotEquals(page,ProduireHtml.resultatVersHtml(l2)) 
  }
  @Test
  def resultatVersHtml3{
    assertNotEquals(page,ProduireHtml.resultatVersHtml(l3)) 
  }
}