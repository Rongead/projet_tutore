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
		String source = args[0], racine = args[1];
          try
          {
               Scanner sc = new Scanner( new FileReader( source ) );
               PrintWriter pw = new PrintWriter ( new FileWriter (racine + "/sortie.html") );
               pw.write("<!DOCTYPE html>");
               pw.write("<html>");
               pw.write("     <head>");
               pw.write("          <title></title>");
               pw.write("          <meta charset=UTF-8>");
               pw.write("          <link rel=\"stylesheet\" type=\"text/css\" href=\"style.css\">");
               pw.write("     </head>");
               pw.write("     <body>");
               while( sc.hasNext() )
               {
                    int i = 0;
                    String ind;
                    String ligne = sc.nextLine();
                    pw.write(recomposeur(ligne));
               }
               sc.close();
			pw.close();
          }
          catch ( Exception e )
          {
               e.printStackTrace();
          }
     }

     public static boolean ligneUtile (String ligne)
     {
          String[] indicateur = {"TP:", "T1:", "T2:", "t2:", "DP:"};
          for (int i = 0; i < indicateur.length ; i++)
          {
               if( ligne.indexOf(indicateur[i]) != -1 )
               {
                    return true;
               }
          }
          return false;
     }

     public static int indexFinder (String ligne)
     {
          String[] indicateur = {"TP:", "T1:", "T2:", "t2:", "DP:"};
          for (int i = 0; i < indicateur.length ; i++)
          {
               if( ligne.indexOf(indicateur[i]) != -1 )
               {
                    return ligne.indexOf(indicateur[i]);
               }
          }
		return -1;
     }

     public static int baliseFinder (String ligne)
     {
          String[] indicateur = {"TP:", "T1:", "T2:", "t2:", "DP:"};
          for (int i = 0; i < indicateur.length ; i++)
          {
               if( ligne.indexOf(indicateur[i]) != -1 )
               {
                    return i;
               }
          }
		return -1;
     }

     public static String recomposeur ( String ligne )
     {
          String[][] balises = { {"<header>", "<h1>", "<h2>", "<h3>", "<div>"},
          {"</header>", "</h1>", "</h2>", "</h3>", "</div>"}};
          String fermetureBalises = "", ouvertureBalises = "";
          String retour = "";
		int balise;

          while( ligneUtile( ligne ) )
          {
			balise = baliseFinder(ligne);
			if(balise >= 0)
               {
				fermetureBalises = balises[1][balise] + fermetureBalises;
				if(indexFinder(ligne) >= 0)
				{
					retour = retour + balises[0][balise] + ligne.substring(0, indexFinder(ligne.substring(3)));
				}
				ligne = ligne.substring(indexFinder(ligne)+2);
			}
          }
          return (retour + fermetureBalises);
     }
}
