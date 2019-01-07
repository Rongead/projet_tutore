/**
 * @author Sebastien PRUNIER && Patrice MAISONNEUUVE
 * date: 07/01/2019
 */

import iut.algo.*;
import java.io.*;
import java.util.*;

public class temp
{
     String[][] balises = { {"<header>", "<h1>", "<h2>", "<h3>", "<div>"},
                            {"</header>", "</h1>", "</h2>", "</h3>", "</div>"}};

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
                    pw.write(recomposeur(ligne));
               }
               sc.close();
          }
          catch ( Exception e )
          {
               e.printStackTrace();
          }
     }


     public boolean ligneUtile (String ligne)
     {
          int i, cpt;
          String[] indicateur = {"TP:", "T1:", "T2:", "t2:", "DP:"};
          for (int i = 0; i < ligne.length ; i++)
          {
            if( ligne.indexOf(indicateur[i]) != -1 )
            {
              return true;
            }
          }
          return false;
     }

     public boolean indexFinder (String ligne)
     {
       if( ligneUtile(ligne) )
       {
          String[] indicateur = {"TP:", "T1:", "T2:", "t2:", "DP:"};
          for (int i = 0; i < indicateur.length() ; i++)
          {
              if( ligne.indexOf(indicateur[i]) != -1 )
              {
                return i;
              }
          }
       }
     }

     public boolean baliseFinder (String ligne)
     {
       if( ligneUtile(ligne) )
       {
          String[] indicateur = {"TP:", "T1:", "T2:", "t2:", "DP:"};
          for (int i = 0; i < indicateur.length() ; i++)
          {
            if( ligne.indexOf(indicateur[i]) != -1 )
            {
              return i;
            }
          }
       }
     }

     public String recomposeur ( String ligne )
     {
       String[][] balises = { {"<header>", "<h1>", "<h2>", "<h3>", "<div>"},
                              {"</header>", "</h1>", "</h2>", "</h3>", "</div>"}};
       String fermetureBalises, ouvertureBalises = "";
       String retour = "";

       while( ligneUtile( ligne ) )
       {
         ouvertureBalises = ouvertureBalises + balises[0][baliseFinder(ligne)];
         fermetureBalises = balises[1][baliseFinder(ligne)] + fermetureBalises;
         ligne = ligne.substring(indexFinder(ligne)+2);
       }

       retour = ouvertureBalises + ligne + fermetureBalises;
       return retour;
     }
}
