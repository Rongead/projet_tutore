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
			pw.write("          <link rel=\"icon\" type=\"image/png\" href=\"images/maxi_logo.png\">\n");
               pw.write("     </head>\n");
               pw.write("     <body>\n");
               while( sc.hasNext() )
               {
				String line = sc.nextLine();
				if(ligneUtile(line))
				{
					ligne += line + "#";
					i++;
				}
               }
			ligne = ligne.substring(0, (ligne.length() - 1));
			String[] tabLignes = miseEnTab(ligne, i);
			for (int j = 0 ; j < tabLignes.length ; j++ )
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

	public static String[] miseEnTab(String lignes, int nbLgn)
	{
		String[] tab = new String[nbLgn];
		int cptPs = 0;
		boolean sauce = false;
		for(int i = 0; i < nbLgn; i++)
		{
			int retourLigne = lignes.indexOf("#");
			if(retourLigne != -1)
			{
				tab[i] = lignes.substring(0, (retourLigne));
				lignes = lignes.substring(retourLigne + 1);
			}
			else
			{
				tab[i] = lignes;
			}
		}

		for (int i = 0; i < nbLgn ; i++ )
		{
			if(tab[i].substring(0,3).equals("PS:"))
			{
				for (int j = i; j < nbLgn ; j++)
				{
					//if( tab[j].substring(0,3).equals("PS:")^!(ligneUtile(tab[i])) )
					if(!(tab[j].substring(0,3).equals("PS:")))
					{
						break;
					}
					else
					{
						tab[i] = tab[i] +  "<br />" + tab[j].substring(3);
						for(int x = j; x < tab.length - 1; x++)
						{
							tab[x] = tab[x+1];
						}
					}
				}
			}
		}

		String[] temp = new String[nbLgn - cptPs];
		for(int i = 0; i < temp.length; i++)
		{
			temp[i] = tab[i];
		}

		for(int i = 0; i < temp.length; i++)
		{
			System.out.println(temp[i]);
		}
		return temp;
	}
}
