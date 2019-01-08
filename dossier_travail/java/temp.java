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
		int nbLgn = 0;
		try
		{
			Scanner sc = new Scanner( new FileReader( source ) ); //Lecture du niveau
			while( sc.hasNext() )
			{
				nbLgn++;
				sc.nextLine();
			}
			sc.close();
		}
		catch ( Exception e )
		{
			e.printStackTrace();
		}

		String[] tabLignes = new String[nbLgn];

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
				if(line != null)
				{
					tabLignes[i] = line;
					i++;
				}
               }
			tabLignes = miseEnTab(tabLignes);
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

	public static String[] miseEnTab(String[] tab)
	{
		int cptPs;
		String memo = "";
		int cptMemo = 0;
		String tuezmoi;
		for(int i = 0; i < tab.length; i++)
		{
			if(tab[i].length() > 4 && tab[i].substring(0,3).equals("PS:"))
			{
				cptPs = 0;
				for(int j = i; j < tab.length; j++)
				{
					if(tab[j].length() > 4 && tab[j].substring(0,3).equals("PS:"))
					{
						cptPs++;
					}
					else
					{
						break;
					}
				}
				for(int j = 1; j < cptPs; j++)
				{
					tab[i] += "<br />" + tab[i+j].substring(3);
					memo += String.valueOf(i+j) + ";";
					cptMemo++;
				}
				i += cptPs;
			}
		}
		int[] history = new int[cptMemo];
		int j = 0, i = 0;
		tuezmoi = "";
		while(i != -1)
		{
			i = memo.indexOf(";");
			if(i != -1)
			{
				System.out.println(memo);
				tuezmoi = memo.substring(0, i);
				memo = memo.substring(i + 1);
				history[j] = Integer.parseInt(tuezmoi);
				j++;
			}
		}

		System.out.print(memo);
		int cpt = 0;
		cptMemo = 0;
		String[] temp = new String[tab.length - history.length];
		for(i = 0; i < temp.length; i++)
		{
			while(cptMemo < history.length && cpt == history[cptMemo])
			{
				cpt++;
				cptMemo++;
			}
			temp[i] = tab[cpt];
			cpt++;
		}

		for(i = 0; i < temp.length; i++)
		{
			System.out.println(temp[i]);
		}
		return temp;
	}
}
