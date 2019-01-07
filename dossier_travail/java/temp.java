/**
 * @author Sebastien PRUNIER && Patrice MAISONNEUUVE
 * date: 07/01/2019
 */

import iut.algo.*;
import java.io.*;
import java.util.*;

public class temp
{
     public static void main(String[] args)
     {
          String source, racine;

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
               pw.write("          <meta charset=UTF-8>")
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
