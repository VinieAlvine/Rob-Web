package library

import java.io.{ File, PrintWriter }

object Application extends App {
  
  app()

  def app(): Unit = {

    val exp: Expression = ParserExpression.lireExpression
    val processStart = System.nanoTime()
    val url: String = "https://search.vivastreet.com/annonces/fr?lb=new&search=1&start_field=1&keywords=" + urlRec(exp)
    //print(url)
    println("File is in process ...")
    val list: List[(String, String)] = Analyse.resultats(url, exp)
    val h: Html = ProduireHtml.resultatVersHtml(list)
    val res: String = Traduction.traduire(h)
    val file = new PrintWriter("robots.html")
    try {

      file.write(res)

    } finally (file.close())
    val ProcessEnd = System.nanoTime()
    println("Execution Time  :  [ " + ((ProcessEnd - processStart) / 1000000000) + " second ]  \nEnd process ...")
  }

  /*def urlRec(e: Expression): String = {

    e match {
      case Mot(a) => a
      case Et(x, y) => if (estMot(x) && estMot(y)) "fr?lb=new&search=1&start_field=1&keywords=" + urlRec(x) + "-" + urlRec(y)
      else if (estMot(x) && !estMot(y)) "fr?lb=new&search=2&start_field=1&keywords=" + urlRec(x) + "fr?lb=new&search=2&start_field=1&keywords=" + urlRec(y)
      else if (!estMot(x) && estMot(y)) "fr?lb=new&search=2&start_field=1&keywords=" + urlRec(y) + "fr?lb=new&search=2&start_field=1&keywords=" + urlRec(x)
      else "&fr?lb=new&search=3&start_field=1&keywords=" + urlRec(x) + "-" + urlRec(y)

      case Ou(v, w) => if (estMot(v) && estMot(w)) "fr?lb=new&search=1&start_field=1&keywords=" + urlRec(v) + "-" + urlRec(w)
      else if (estMot(v) && !estMot(w)) "fr?lb=new&search=2&start_field=1&keywords=" + urlRec(v) + "fr?lb=new&search=2&start_field=1&keywords=" + urlRec(w)
      else if (!estMot(v) && estMot(w)) "fr?lb=new&search=2&start_field=1&keywords=" + urlRec(w) + "fr?lb=new&search=2&start_field=1&keywords=" + urlRec(v)
      else "&fr?lb=new&search=3&start_field=1&keywords=" + urlRec(v) + "-" + urlRec(w)

    }
  }*/
  
  def urlRec(e: Expression): String = {
    var u: String = ""
    e match {
      case Mot(a)   => u. + (a)
      case Ou(a, b) => u.++(urlRec(a)) + "+" ++ urlRec(b)
      case Et(a, b) => u.++(urlRec(a)) + "+" ++ urlRec(b)
    }
  }

  
  def estMot(e: Expression): Boolean = {
    e match {
      case Mot(a) => true
      case _      => false
    }
  }
}
