package library


object Analyse extends AnalysePage {
  

  //1 - fetch
  // 2 - filtrer les urls de la page html de fetch
  // 3 - fetch de toutes les urls ? --> list[html]
  // 4 - filtrage Html pour avoir couple Url,docuhtml
  // 5 - extraire juste le titre et en faire un couple (titre, Url) titre --> parcours html si case (a,b,c) a = "titre" on prend la valeur
  /**
   * A partir d'une URL de requête sur le site de référence et d'une expression exp,
   * retourne de pages issues de la requête et satisfaisant l'expression.
   *
   * @param url l'URL de la requête sur le site de référence
   * @param exp l'expression à vérifier sur les pages trouvées
   * @return la liste des couples (titre,ref) où ref est l'URL d'une page
   * satisfaisant l'expression et titre est son titre.
   */

    def resultats(url: String, exp: Expression): List[(String, String)] = {
      var listUrlDocs: List[(String, Html)] = List()
      var listDocs: List[Html] = List()
      var p: List[(String, String)] = List()
      val getHtml: Html = OutilsWebObjet.obtenirHtml(url)
      var listUrls: List[String] = FiltrageU.filtreAnnonce(getHtml)
      for (i <- listUrls) {
        listDocs = listDocs :+ (OutilsWebObjet.obtenirHtml(i))
        for (y <- listDocs) {
  
          if (FiltrageU.filtreAnnonce(OutilsWebObjet.obtenirHtml(i)) == FiltrageU.filtreAnnonce(y))
            listUrlDocs = listUrlDocs :+ (i, y)
  
          for (r <- listUrlDocs) {
            if (FiltragePage.filtreHtml(y, exp)) {
              p = p :+ (r._1,title(OutilsWebObjet.obtenirHtml(r._1)))
             
            }
          }
  
        }
      }
      p.distinct
  
    }


  def title(h: Html): String = {
    var res: String = ""
    h match {
      case Texte(_) => ""
      case Tag(a, b, c) => if (a == "title") {
        c match {
          case List(Texte(a)) => res = res + a
        }
      } else {
        for (i <- c) {
          res += title(i)
        }
      }
    }
    res
  }

  
}