/**
* @author Sebastien PRUNIER && Patrice MAISONNEUUVE
* date: 07/01/2019
*/

import iut.algo.*;
import java.io.*;
import java.util.*;

public class temp
{
	static String[] indicateur = {"TP:", "T1:", "T2:", "t2:", "DP:", "PS:", "PC:"};
	public static void main(String[] args)
	{
		String source = args[0], racine = args[1];
          try
          {
			int i = 0;
			String ligne = "";
               Scanner sc = new Scanner( new FileReader( source ) );
               PrintWriter pw = new PrintWriter ( new FileWriter (racine + "/sortie.html") );
               pw.write("<!DOCTYPE html>\n");
               pw.write("<html>\n");
               pw.write("     <head>\n");
               pw.write("          <title></title>\n");
               pw.write("          <meta charset=UTF-8>\n");
               pw.write("          <link rel=\"stylesheet\" type=\"text/css\" href=\"style.css\">\n");
               pw.write("     </head>\n");
               pw.write("     <body>\n");
               while( sc.hasNext() )
               {
                    ligne += sc.nextLine() + "\n";
				i++;
               }
			String[] tabLignes = miseEnTab(ligne, i);
			for (int j = 0 ; j < i ; j++ )
			{
				pw.write(recomposeur(tabLignes[j]));
			}

			pw.write("     </body>\n");
			pw.write("</html>");
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
          String[][] balises = { { "<header>", "<h1>", "<h2>", "<h2>", "<article>", "<p>", "<p class=\"encadrer\">" },
                                 { "</header>", "</h1>", "</h2>", "</h2>", "</article>", "</p>", "</p>" } };
          String fermetureBalises = "", ouvertureBalises = "";
          String retour = "";
		int balise;

          do
          {
			balise = baliseFinder(ligne);
			if(balise >= 0)
               {
				fermetureBalises = balises[1][balise]+ "\n" + fermetureBalises;
				if(indexFinder(ligne) >= 0)
				{
					if(indexFinder(ligne.substring(3)) >= 0)
					{
						retour = retour + balises[0][balise] + "\n" + ligne.substring(0, indexFinder(ligne.substring(3))) + "\n";
					}
					else
					{
						retour = retour + balises[0][balise] + "\n" + ligne.substring(3) + "\n";
					}
				}
				ligne = ligne.substring(indexFinder(ligne)+2);
			}
          }while( ligneUtile( ligne ) );
          return (retour + fermetureBalises + "\n");
     }

	public static String[] miseEnTab(String ligne, int nbLgn)
	{
		String[] tab = new String[nbLgn];
		for(int i = 0; i < nbLgn; i++)
		{
			tab[i] = ligne.substring(0, (ligne.indexOf("\n") - 1)))
			ligne = ligne.substring((ligne.indexOf("\n")) + 3)
		}
	}
}
