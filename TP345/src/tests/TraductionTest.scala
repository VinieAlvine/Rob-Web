package tests

import org.junit.Assert._
import org.junit.Test
import library._

class TraductionTest {
  //attributs sous forme de liste
  //1)
  val lhttpsequiv: List[(String, String)] = List(("https-equiv", "Content-Type"))
  val lcontent: List[(String, String)] = List(("content", "text/html; charset=utf-8"))
 
  //2)
  val lname: List[(String, String)] = List(("name", "keywords"))
  val lcontent2: List[(String, String)] = List(("content", "moodle, Cours : GEN - Initiation au génie logiciel"))
 
  //attributs sous forme de clé-valeur (le nom de l'attribut et sa propriété)
  //1)
  val httpsequiv: (String, String) = ("https-equiv", "Content-Type")
  val content1: (String, String) = ("content", "text/html; charset=utf-8")
 
   //2)
  val name: (String, String) = ("name", "keywords")
  val content2: (String, String) = ("content", "moodle, Cours : GEN - Initiation au génie logiciel")
 
  //attributs de balises
  val meta1: List[(String, String)] = List(httpsequiv, content1)
  val meta2: List[(String, String)] = List(name, content2)
 
  @Test
  def testAttributsToString1 {
    assertNotNull(Traduction.attributsToString1(meta1,""))
    
    //on vérifie que notre fonction termine
    assertEquals(Traduction.attributsToString1(meta1,"")._1, Nil)
    
  }
}