/** Projet tutoré
*  Générateur de diaporama HTML
*  Groupe 29
*  @author CONTE Lucas, MAISONNEUVE Patrice, NEVEU Aubin, PONTY Arthur, PRUNIER Sébastien
*  @date   09/01/2018 8:55
*/

import java.util.*;
import java.io.*;


public class patriceVersion
{
	static String source;
	static String racine;

	public static void main(String[] args)
	{
		source = args[0];
		racine = args[1];
		initialiserDiapo();
	}

	public static void initialiserDiapo()
	{
		String ligne;
		Scanner     scIn;
		PrintWriter pw;
		int cptDiapo = 0, diapoMax = 0;
		int cptPS, cptPC, cptT1 , cptL1, cptL2, cptT2;
		String fichierDestination;
		String header = "", nav = "";
		String logPage = "";

		cptPS = cptPC = cptT1 = cptL1 = cptL2 = cptT2 = 0;

		fichierDestination = racine + "/sortie";

		if(cptDiapo < 10)
		{
			fichierDestination += "0" + cptDiapo + ".html";
		}
		else
		{
			fichierDestination += cptDiapo + ".html";
		}

		try
		{
			scIn = new Scanner ( new FileInputStream ( "exemple.data"), "utf-8"  );
			while ( scIn.hasNextLine() )
			{
				ligne = scIn.nextLine();
				if (!(ligne.substring(0).equals("")))
				{
					if (ligne.substring(0,3).equals("DP:") && diapoMax < 99)
					{
						diapoMax++;
					}
					if(ligne.substring(0,3).equals("T1:") || ligne.substring(0,3).equals("T2:"))
					{
						nav += ligne + "#";
						logPage += String.valueOf(diapoMax - 1);
					}
				}
			}
			scIn.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

		try
		{
			scIn = new Scanner ( new FileInputStream ( "exemple.data"), "utf-8"  );
			pw = new PrintWriter ( new OutputStreamWriter ( new FileOutputStream(fichierDestination), "utf-8" ) );

			initalisationHTML(pw, "");
			while ( scIn.hasNextLine() )
			{
				ligne = scIn.nextLine();

				if (!(ligne.substring(0).equals(""))) {

					switch (ligne.substring(0,3)){
						case "TP:":
							fermetureBalisesL( pw, cptL1, cptL2 );
							fermetureBalisesP(pw, cptPC, cptPS );
							header = "\n\t\t<header>\n\t\t\t<img class=\"logo\" src=\"images/maxi_logo.png\" alt=\"logo\">\n\t\t\t" + ligne.substring(3) + "\n\t\t\t<img class=\"logo\" src=\"images/maxi_logo.png\" alt=\"logo\">\n\t\t</header>\n\n\t\t<article>\n";
							pw.write (header);
							cptL1 = cptL2 = 0; //reinitialisation des compteurs de liste
							cptPS = cptPC = 0; //reinitialisation des compteurs de paragraphe
							break;

						case "T1:":
							fermetureBalisesL( pw, cptL1, cptL2 );
							fermetureBalisesP(pw, cptPC, cptPS );
							cptT1++;

							pw.write ("\t\t\t<h1>" + cptT1 + " " + ligne.substring(3)+"</h1>\n");
							cptL1 = cptL2 = 0; //reinitialisation des compteurs de liste
							cptPS = cptPC = 0; //reinitialisation des compteurs de paragraphe
							break;

						case "T2:":
							fermetureBalisesL( pw, cptL1, cptL2 );
							fermetureBalisesP(pw, cptPC, cptPS );
							cptT2++;
							pw.write ("\t\t\t<h2 id=titre" + cptT2 + ">"+ cptT1 + "." + cptT2 + " " + ligne.substring(3)+"</h2>\n");
							cptL1 = cptL2 = 0; //reinitialisation des compteurs de liste
							cptPS = cptPC = 0; //reinitialisation des compteurs de paragraphe
							break;

						case "t2:":
							fermetureBalisesL( pw, cptL1, cptL2 );
							fermetureBalisesP(pw, cptPC, cptPS );
							pw.write ("\t\t\t<h3>"+ ligne.substring(3)+"</h3>\n");
							cptL1 = cptL2 = 0; //reinitialisation des compteurs de liste
							cptPS = cptPC = 0; //reinitialisation des compteurs de paragraphe
							break;

						case "DP:":

							if(cptDiapo > 0)
							{
								fermetureBalisesL( pw, cptL1, cptL2 );
								fermetureBalisesP(pw, cptPC, cptPS );
								fermetureHTML(pw, cptDiapo, nav, diapoMax, logPage);
								pw.close();
								cptL1 = cptL2 = 0; //reinitialisation des compteurs de liste
								cptPS = cptPC = 0; //reinitialisation des compteurs de paragraphe
								cptT2 = 1;
								fichierDestination = racine + "/sortie";

								if(cptDiapo < 10)
								{
									fichierDestination += "0" + cptDiapo + ".html";
								}
								else
								{
									fichierDestination += cptDiapo + ".html";
								}
								pw = new PrintWriter ( new OutputStreamWriter ( new FileOutputStream(fichierDestination), "utf-8" ) );
								initalisationHTML(pw, header);
							}

							cptDiapo++;
							break;

						case "L1:":
							fermetureBalisesP(pw, cptPC, cptPS );

							if (cptL1 == 0)
							{
								pw.write("\t\t\t<ul>\n"+"\t\t\t\t<li>"+ligne.substring(3)+"</li>\n");
							}
							else
							{
								pw.write("\t\t\t\t<li>"+ligne.substring(3)+"</li>\n");
							}

							cptPS = cptPC = 0;
							cptL1++;
							break;

						case "L2:":
							fermetureBalisesP(pw, cptPC, cptPS );

							if (cptL1 != 0 && cptL2 == 0)
							{
								pw.write("\t\t\t\t\t<ul>\n"+"\t\t\t\t\t\t<li>"+ligne.substring(3)+"</li>\n");
							}
							else
							{
								if ( cptL1 != 0 )
								{
									pw.write("\t\t\t\t\t<li>"+ligne.substring(3)+"</li>\n");
								}
							}

							cptPS = cptPC = 0; //reinitialisation des compteurs de paragraphe
							cptL2++;
							break;

						case "PS:":
							fermetureBalisesL( pw, cptL1, cptL2 );

							if (cptPC != 0)
							{
								cptPC=0;
								pw.write("\n\t\t\t</p>\n");
							}

							if(cptPS==0)
							{
								pw.write ("\n\t\t\t<p>\n\t\t\t\t"+  ligne.substring(3));
							}
							else
							{
								pw.write ("\n\t\t\t\t<br />" + ligne.substring(3));
							}
							cptL1 = cptL2 = 0; //reinitialisation des compteurs de liste
							cptPS++;
							break;

						case "PC:":
							fermetureBalisesL( pw, cptL1, cptL2 );

							if (cptPS != 0)
							{
								cptPS=0;
								pw.write("\n\t\t\t</p>\n");
							}

							if(cptPC==0)
							{
								pw.write ("\n\t\t\t<p class=\"encadrer\">\n\t\t\t\t"+  ligne.substring(3));
							}
							else
							{
								pw.write ("\n\t\t\t\t<br />" + ligne.substring(3));
							}

							cptL1 = cptL2 = 0; //reinitialisation des compteurs de liste
							cptPC++;
							break;

						case "IM:":
							fermetureBalisesP(pw, cptPC, cptPS );
							afficherImage(pw, ligne);break;

					}

				}
			}

			fermetureBalisesP(pw, cptPC, cptPS);
			fermetureHTML(pw, cptDiapo, nav, diapoMax, logPage);
			pw.close();

		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

	}

	public static void fermetureBalisesP(PrintWriter pw, int cptPC, int cptPS)
	{
		if (cptPC != 0 || cptPS != 0)
		{
			pw.write("\n\t\t\t</p>\n\n");
		}
	}

	public static void fermetureBalisesL(PrintWriter pw, int cptL1, int cptL2 )
	{
		if ( cptL1 != 0 )
		{
			if ( cptL2 != 0 ) pw.write("\t\t\t\t\t</ul>\n");
			pw.write("\t\t\t</ul>\n\n");
		}
	}

	public static void initalisationHTML(PrintWriter pw, String header)
	{
		pw.write("<!DOCTYPE html>\n"                                                        +
		"<html>\n"                                                                          +
		"     <head>\n"                                                                     +
		"          <title></title>\n"                                                       +
		"          <meta charset=\"UTF-8\">\n"                                              +
		"          <link rel=\"stylesheet\" type=\"text/css\" href=\"css/style.css\">\n"    +
		"          <link rel=\"icon\" type=\"image/png\" href=\"images/maxi_logo.png\">\n"  +
		"     </head>\n\n"                                                                    +
		"     <body>\n"
		+ header                                                                            );
	}

	public static String[][] tabNavMaker( String nav, String logPage )
	{
		int cptNav = 1;

		for (int i = 0; i < nav.length() ; i++ )
		{
			if(nav.charAt(i) == '#') cptNav++;
		}

		String[][] tabNav = new String[3][cptNav];

		for ( int j = 0; j < cptNav; j++)
		{
			String tempString = "";
			int indexFin = nav.indexOf("#");
			if ( indexFin - 1 > 0 )
			{
				tempString = nav.substring( 0, ( indexFin  ) );
			}
			else
			{
				tempString = nav;
			}

			if ( tempString.length() > 2)
			{
				tabNav[0][j] = tempString.substring(0,3);
				tabNav[1][j] = tempString.substring(3);
				tabNav[2][j] = String.valueOf(logPage.charAt(j));

				if(nav.length() > 4) nav = nav.substring( indexFin + 1);
			}
		}

		return tabNav;
	}

	public static void navMaker(PrintWriter pw, String nav, String logPage)
	{
		String[][] tabNav = tabNavMaker(nav, logPage);
		String sortie, sortieTitre;
		int cptT1 = 0, cptT2 = 0;
		pw.write("\n\t\t<nav>\n\t\t\t<ul>\n");
		if(tabNav[0][0] != null)
		{
			for(int i = 0; i < tabNav[0].length; i++)
			{
				if(cptT1 < 10)
				{
					sortie = "sortie0" + tabNav[2][i] + ".html";
				}
				else
				{
					sortie = "sortie" + tabNav[2][i] + ".html";
				}

				if(tabNav[0][i].equals("T1:"))
				{

					if(cptT2 > 0)
					{
						pw.write("\t\t\t\t\t</ul>");
						cptT2 = 0;
					}
					if(cptT1 > 0)
					{
						pw.write("\t\t\t\t</li>");
					}
					pw.write("\t\t\t\t<li>\n\t\t\t\t\t<a href=" + sortie + ">" + tabNav[1][i] + "</a>" + "\n");
					cptT1++;
				}
				else
				{
					if(cptT2 == 0)
					{
						pw.write("\t\t\t\t\t<ul>\n");
					}
					pw.write("\t\t\t\t\t\t<li>\n\t\t\t\t\t\t\t<a href="+ sortie +"#titre"+ String.valueOf(cptT2 + 1) + ">" + tabNav[1][i] + "</a>" +"\n\t\t\t\t\t\t</li>\n");
					cptT2++;
				}
			}
		}
		if(cptT2 > 0)
		{
			pw.write("\t\t\t\t\t</ul>\n\t\t\t\t</li>\n");
		}
		pw.write("\t\t\t</ul>\n\t\t</nav>\n");
	}

	public static void fermetureHTML(PrintWriter pw, int cptDiapo, String nav, int diapoMax, String logPage)
	{
		String s = "";
		nav = nav.substring(0, (nav.length() - 1));
		pw.write("\t\t</article>\n");
		navMaker(pw, nav, logPage);
		s = "\n\t\t<footer>\n";

		s = s + "\t\t\t<p><a href= sortie00.html>D</a></p>\n";

		if (cptDiapo == 1)		s = s + "\t\t\t<p>⨯</p>\n";
		else
		{
			s = s + lien(cptDiapo - 2) + "⇠</a></p>\n";
		}

		s = s + "\t\t\t<h1>page " + cptDiapo + "/" + diapoMax + "</h1>\n";

		if (cptDiapo == diapoMax)
		{
			s = s + "\t\t\t<p>⨯</p>\n";
		}
		else
		{
			s = s + lien(cptDiapo) +"⇢</a></p>\n";
		}

		s = s + lien(diapoMax - 1) + "F</a></p>\n";

		s = s + "\t\t</footer>\n" + "\t</body>\n" + "</html>\n";

		pw.write(s);
	}

	public static void afficherImage(PrintWriter pw, String ligne)
	{
		int posPoint;
		posPoint = ligne.indexOf ( ':', 3 );
		pw.write("<img src=images/"+ligne.substring(3,posPoint)+" alt="+ligne.substring(posPoint+1)+">");
	}

	public static String lien(int cpt)
	{
		String retour;
		if(cpt < 10)
		{
			retour = "\t\t\t<p><a href=\"sortie0" + (cpt)+".html\">";
		}
		else
		{
			retour = "\t\t\t<p><a href=\"sortie" + (cpt)+".html\">";
		}

		return retour;
	}

}
