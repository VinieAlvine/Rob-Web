package tests

import org.junit.Assert._
import org.junit.Test
import library._

class FiltrageUTest {

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

  val search: String =
    "https://search.vivastreet.com/annonces/fr?lb=new&search=1&start_field=1&keywords=d%C3%A9veloppeur+rennes&cat_1=&geosearch_text=&searchGeoId=0"

  val mypage2 = Tag("html", List(),
    List(
      Tag("head", List(),
        List(
          Tag("meta", List(("charset", "utf-8")), List()),
          Tag("title", List(), List(Texte("My Page"))))),
      Tag("body", List(), List(
        Tag("center", List(), List(
          Tag("a", List(("href", search)),
            List(Texte("Word")))))))))

  val page: Html = Tag("html", List(), List(
    Tag("head", List(), List(
      Tag("meta", List(("charset", "utf-8")), List()),
      Tag("title", List(), List(Texte("Ma page"))),
      Tag("link", List(("rel", "stylesheet"), ("href", "stylesheet.css")), List()))),
    Tag("body", List(), List(
      Tag("h1", List(), List(Texte("Résultats de la recherche du robot web :"))),
      Tag("ol", List(), List(Tag("li", List(), List(Tag("a", List(("href", search)), List(Texte("t")))))))))))

  val url1: String =
    "https://www.vivastreet.com/recherche-emploi-informatique-internet-telecom/la-creche-79260/d-veloppeur-informatique-/123491164"
  val url2: String =
    "https://www.vivastreet.com/emploi-fonction-informatique-internet-telecom/paris-13eme-ardt-75013/full-stack-ing-nieur---nlp---ai---cdi---paris--/246225438"
  val url3: String =
    "https://www.vivastreet.com/emploi-fonction-informatique-internet-telecom/le-plessis-robinson-92350/lead-tech-d-veloppement-full-stack/213297091"

  val exNil: List[(String, String)] = Nil
  val ex2: List[Html] = Nil

  @Test
  def filtrageTest1 {
    assertEquals(Nil, FiltrageU.filtreAnnonce(mypage))
  }

  @Test
  def filtrageTest2 {
    assertFalse(FiltrageU.filtreAnnonce(mypage).contains(url1))
  }

  /*@Test
  def filtrageTest3 {
    assertTrue(FiltrageU.filtreAnnonce(mypage2).contains(url1))
  }*/

}