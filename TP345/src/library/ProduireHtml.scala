package library

object ProduireHtml extends ProductionResultat {
  var titre = Tag("", List(), List())
  val meta = Tag("", List(), List())
  val head = Tag("", List(), List())
  var page: Html = Tag("", List(), List())

  def listHtml(l: List[(String, String)]): List[Html] = {
    var Nosref: List[Html] = List()
    for (e <- l) {
      val balise = Tag("li", List(), List(Tag("a", List(("href", e._1)), List(Texte(e._2)))))
      Nosref = balise :: Nosref
    }
    Nosref
  }

  def resultatVersHtml(l: List[(String, String)]): Html = {
    var Nosref: List[Html] = List()
    var page: Html = Tag("", List(), List())

    l match {
      case Nil => Nil
      case (x, y) :: rest =>
        page = Tag("html", List(), List(
          Tag("head", List(), List(
            Tag("meta", List(("charset", "utf-8")), List()),
            Tag("title", List(), List(Texte("Ma page"))),
            Tag("link", List(("rel", "stylesheet"), ("href", "stylesheet.css")), List()))),
          Tag("body", List(), List(
            Tag("h1", List(), List(Texte("Résultats de la recherche du robot web :"))),
            Tag("ol", List(), List(Tag("li", List(), List(Tag("a", List(("href", x)), Texte(y) :: listHtml(rest))))))))))
    }

    page
  }

}