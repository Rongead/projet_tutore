/**
 * @author Sebastien PRUNIER && Patrice MAISONNEUUVE
 * date: 07/01/2019
 */

import iut.algo.*;
import java.io.*;
import java.util.*;

public class
{
     public static void main(String[] args)
     {
          String source, racine;
<<<<<<< HEAD
=======
          String[] identifiants = {"TP", "T1", "T2", "t2", "DP", "L1", "L2", "IM", "PS", "PC", "AN"};
          String[][] balises = { {"<header>", "<h1>", "<h2>", "<h3>", "<div>", "<ol>", "<li>", "<img rel"}, }
>>>>>>> 56d990aad93b3fde0be61a3bc0ffa0216845e12d

          System.out.print("Entrez la source du fichier html : ");
          source = Clavier.lireString();
          System.out.print("Entrez le répertoire de destination : ");
          racine = Clavier.lireString();

          try
          {
               Scanner sc = new Scanner( new FileReader( source ) );
               PrintWriter pw = new PrintWriter ( new FileWriter (racine + "/sortie.html") );
               pw.write("<!DOCTYPE html>");
               pw.write("<html>");
               pw.write("     <head>");
               pw.write("          <title></title>")
               pw.write("          <meta charset=UTF-8")
               pw.write("          <link rel=\"stylesheet\" type=\"text/css\" href=\"style.css\">")
               pw.write("     </head>");
               pw.write("     <body>");
               while( sc.hasNext() )
               {
                    ligne = sc.nextLine();
                    
               }
               sc.close();
          }
          catch ( Exception e )
          {
               e.printStackTrace();
          }
     }
}
