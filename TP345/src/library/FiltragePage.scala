package library

object FiltragePage extends FiltrageHtml {

  /**
   * A partir d'un document Html h et d'une requête e, dit si le document
   * satisfait l'expression e
   *
   * @param h le document Html
   * @param e l'expression
   * @return true si le document satisfait l'expression e
   */
  def filtreHtml(h: Html, e: Expression): Boolean = {
    e match {
      case Mot(b)   => tousLesTexts(h).contains(b)
      case Et(a, b) => filtreHtml(h, a) && filtreHtml(h, b)
      case Ou(a, b) => filtreHtml(h, a) || filtreHtml(h, b)
    }
  }

  def tousLesTexts(h: Html): String = {
    var res: String = ""
    h match {
      case Texte(a) => res += a
      case Tag(a, b, c) => for (i <- c) {
        res += tousLesTexts(i)
      }
    }
    res
  }
}