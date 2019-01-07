/**
 * @author Sebastien PRUNIER && Patrice MAISONNEUUVE
 * date: 07/01/2019
 */

import iut.algo.*;
import java.io.*;
import java.util.*;

public class temp
{
     String[][] balises = { {"<header>", "<h1>", "<h2>", "<h3>", "<div>", ""},
                            {"</header>", "</h1>", "</h2>", "</h3>", "</div>", ""}};
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
                    int i = 0;
                    String ind;
                    ligne = sc.nextLine();
                    pw.write(scan(ligne));
               }
               sc.close();
          }
          catch ( Exception e )
          {
               e.printStackTrace();
          }
     }

     public int indexeur (String ligne)
     {
          String[] indicateur = {"TP:", "T1:", "T2:", "t2:", "DP:", "//"};
          int i, cpt;
          String premchar = ligne.substring(0,2);
          String retour;
          while(i < indicateur.length )
          {
               int index = ligne.indexOf(indicateur[i]);
               if( index != -1 )
               {
                    return index;
               }
          }
     }
}
