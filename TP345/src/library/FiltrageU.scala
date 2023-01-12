package library

object FiltrageU extends FiltrageURLs {
  /**
   * A partir d'un document Html h, rend la liste des URLs accessibles à partir
   * de h (ces URLs sont des hyperliens h) tels que ces URLs sont tous des URLs
   * d'annonces du site de référence
   *
   * @param h le document Html
   * @return la liste des URLs d'annonces contenues dans h
   */
  def filtreAnnonce(h: Html): List[String] = {
    var listurls: List[String] = extraitUrls(h)
    var listurlsAnnonces: List[String] = List()
    for (url <- listurls) {
      var urls: String = url.reverse
      var ur: String = urls.slice(0, 9)
      var integerOrNot: Boolean = ur.matches("-?\\d+")
      if (integerOrNot) { listurlsAnnonces = url :: listurlsAnnonces }
    }
    listurlsAnnonces
  }

  def extraitUrls(h: Html): List[String] = {
    /* création d'une liste de String pour stocker les URL */
    var listurls: List[String] = List()
    h match {

      case Tag(name, attributes, childrens) // si la liste des attributs n'est pas vide 
      if (attributes.nonEmpty) =>
        //on parcours la liste  des attributs
        for ((h, w) <- attributes) {
          if (h == "href") {
            listurls = w :: listurls
          }
        }
        // s'il a des enfants
        if (childrens.nonEmpty) {
          for (chil <- childrens) {
            chil match {

              case Tag(name, attributes, childrens) => listurls = listurls ++ extraitUrls(chil)
              case Texte(content)                   => Nil
            }
          }

        }

      //
      case Tag(name, attributes, childrens) // si la liste des attributs est  vide 
      if (attributes.isEmpty) => for (chil <- childrens) {
        chil match {
          case Tag(name, attributes, childrens) => listurls = listurls ++ extraitUrls(chil)
          case Texte(content)                   => Nil

        }
      }
      //case Texte(content)=>null
    }
    listurls
  }

}
