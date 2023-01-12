package library

object Traduction extends HtmlVersString {
  /**
   * @param attributs les attibuts d'une balise
   * @param chaine les attributs et leurs valeurs sous forme de chaine de caractère
   * @return les attributs sous forme de chaine de caractère et une liste nulle (indiquant que tous les attributs ont été lu)
   */
  def attributsToString1(attribut: List[(String, String)], chaine: String): (List[(String, String)], String) = {
    var attributs: List[(String, String)] = attribut
    var resultat: String = chaine
    attributs match {
      case (attNom, attValeur) :: suite => {
        resultat += " " + attNom + " = \"" + attValeur + "\""
        attributsToString1(suite, resultat)
      }
      //cas par défaut
      case _ => (attributs, resultat)
    }
  }

  /**
   * @param attributs les attributs d'une balise
   * @return l'écriture html de ces attributs
   */
  def attributsToString(attributs: List[(String, String)]): String = {
    attributsToString1(attributs, "")._2
  }

  /**
   * @param h un objet Html
   * @return une chaine de caractère représentant l'écriture Html de l'objet
   * dans l'affichage on ne tient pas compte des cas (balises) exceptionnel.le.s.
   * c'est-à-dire que la fermeture des balises se fait à la ligne après le contenu de la balise
   */
  def traduire(h: Html): String = {
    h match {
      case Texte(contenu) => contenu
      case Tag(baliseNom, attributs, fils) => {
        var resultat: String = ""
        //puisque la fonction prends en paramètre un Objet Html on utilise une boucle pour pour lui passer les fils de ses différentes balises
        for (i <- fils) { resultat += traduire(i) };
        "<" + baliseNom + attributsToString(attributs) + ">\n" + resultat + "\n</" + baliseNom + ">"
      }
    }
  }
}