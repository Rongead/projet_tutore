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
	static String nomFichier;

	public static void main(String[] args)
	{
		source = args[0];
		racine = args[1];
		nomFichier = source.substring(0, source.indexOf("."));
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
		String subLigne;

		cptPS = cptPC = cptT1 = cptL1 = cptL2 = cptT2 = 0;

		fichierDestination = racine + "/" + lien(cptDiapo);
		try   //On compte le nombre de DP, de T1 et de T2 dans un premier scanner
		{
			scIn = new Scanner ( new FileInputStream (source), "utf-8"  );
			while ( scIn.hasNextLine() )
			{
				ligne = scIn.nextLine();
				if (!(ligne.substring(0).equals("")))
				{
					if (ligne.substring(0,3).equals("DP:") && diapoMax < 99)   //nombre de DP maximum de 99
					{
						diapoMax++;
					}
					if(ligne.substring(0,3).equals("T1:") || ligne.substring(0,3).equals("T2:"))   //nombre de T1 et de T2 pour le nav
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

		try   //Deuxième scanner pour cette fois écrire les pages HTML
		{
			scIn = new Scanner ( new FileInputStream ( source), "utf-8"  );
			pw = new PrintWriter ( new OutputStreamWriter ( new FileOutputStream(fichierDestination), "utf-8" ) );

			initalisationHTML(pw, "");
			while ( scIn.hasNextLine() )
			{
				ligne = scIn.nextLine();

				if (!(ligne.substring(0).equals("")))
				{

					subLigne = ligne.substring(3);

					switch (ligne.substring(0,3)){   //switch pour les différentes balises
						case "TP:":   //pour la création du header
							fermetureBalises( pw, cptPC, cptPS, cptL1, cptL2);
							if (subLigne.contains("Google"))   //Si dans le header il y a le mot Google, il est afficher d'une certaine façon
							{
								subLigne = subLigne.replaceAll("Google","<span class=\"bleu\">G</span><span class=\"rouge\">o</span><span class=\"jaune\">o</span><span class=\"bleu\">g</span><span class=\"vert\">l</span><span class=\"rouge\">e</span>");
							}
							header = "\n<--! Mise en place de l'entête -->\n\t\t<header>\n\t\t\t<img class=\"logo\" src=\"images/maxi_logo.png\" alt=\"logo\">\n\t\t\t<p>" + subLigne  + "</p>\n\t\t\t<img class=\"logo\" src=\"images/maxi_logo.png\" alt=\"logo\">\n\t\t</header>\n<--! Ouverture de la diapositive -->\n\t\t<article>\n";
							pw.write (header);
							break;

						case "T1:":   //pour la création d'un titre de niveau 1
							fermetureBalises( pw, cptPC, cptPS, cptL1, cptL2);
							cptT1++;

							pw.write ("\t\t\t<h1>" + cptT1 + " " + subLigne +"</h1>\n");
							break;

						case "T2:":   //pour la création d'un titre de niveau 2
							fermetureBalises( pw, cptPC, cptPS, cptL1, cptL2);
							cptT2++;
							pw.write ("\t\t\t<h2 id=titre" + cptT2 + ">"+ cptT1 + "." + cptT2 + " " + subLigne +"</h2>\n");
							break;

						case "t2:":   //pour la création d'un titre de niveau 3
							fermetureBalises( pw, cptPC, cptPS, cptL1, cptL2);
							pw.write ("\t\t\t<h3>"+ subLigne +"</h3>\n");
							break;

						case "DP:":   //pour la création d'une autre page

							if(cptDiapo > 0)
							{
								fermetureBalises( pw, cptPC, cptPS, cptL1, cptL2);
								fermetureHTML(pw, cptDiapo, nav, diapoMax, logPage);
								pw.close();
								cptT2 = 1;
								fichierDestination = racine + "/" + lien(cptDiapo);
								pw = new PrintWriter ( new OutputStreamWriter ( new FileOutputStream(fichierDestination), "utf-8" ) );
								initalisationHTML(pw, header);
							}

							cptDiapo++;
							break;

						case "L1:":   //pour la création d'une liste de niveau 1
							fermetureBalisesP(pw, cptPC, cptPS );

							if (cptL1 == 0)
							{
								pw.write("\t\t\t<ul>\n"+"\t\t\t\t<li>"+subLigne +"</li>\n");
							}
							else
							{
								pw.write("\t\t\t\t<li>"+subLigne +"</li>\n");
							}

							cptL1++;
							break;

						case "L2:":   //pour la création d'une liste de niveau 2
							fermetureBalisesP(pw, cptPC, cptPS );

							if (cptL1 != 0 && cptL2 == 0)
							{
								pw.write("\t\t\t\t<ul>\n"+"\t\t\t\t\t<li>"+subLigne +"</li>\n");
							}
							else
							{
								if ( cptL1 != 0 )
								{
									pw.write("\t\t\t\t\t<li>"+subLigne +"</li>\n");
								}
							}

							cptL2++;
							break;

						case "PS:":   //pour la création d'un paragraphe simple
							fermetureBalisesL( pw, cptL1, cptL2 );

							if (cptPC != 0)
							{
								pw.write("\n\t\t\t</p>\n");
							}

							if(cptPS==0)
							{
								pw.write ("\n\t\t\t<p>\n\t\t\t\t"+  subLigne );
							}
							else
							{
								pw.write ("\n\t\t\t\t<br />" + subLigne );
							}
							cptPS++;
							break;

						case "PC:":   //pour la création d'un paragraphe encadré
							fermetureBalisesL( pw, cptL1, cptL2 );

							if (cptPS != 0)
							{
								cptPS=0;
								pw.write("\n\t\t\t</p>\n");
							}

							if(cptPC==0)
							{
								pw.write ("\n\t\t\t<p class=\"encadrer\">\n\t\t\t\t"+  subLigne );
							}
							else
							{
								pw.write ("\n\t\t\t\t<br />" + subLigne );
							}
							cptPC++;
							break;

						case "IM:":   //pour la création d'une image
							fermetureBalisesP(pw, cptPC, cptPS );   //ferme les balises
							afficherImage(pw, ligne);break;   //Affiche l'image

					}

					if (!(ligne.substring(0,3).equals("L2:")) && !(ligne.substring(0,3).equals("L1:")))
					{
						cptL2 = 0;
						cptL1 = 0;
					}
					if (!(ligne.substring(0,3).equals("PS:"))) cptPS = 0;
					if (!(ligne.substring(0,3).equals("PC:"))) cptPC = 0;

				}
			}

			fermetureBalisesP(pw, cptPC, cptPS);   //ferme les balises
			fermetureHTML(pw, cptDiapo, nav, diapoMax, logPage);   //ferme la page HTML
			pw.close();

		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

	}

	public static void fermetureBalisesP(PrintWriter pw, int cptPC, int cptPS)   //Pour fermer les balises P
	{
		if (cptPC != 0 || cptPS != 0)
		{
			pw.write("\n\t\t\t</p>\n\n");
		}
	}

	public static void fermetureBalisesL(PrintWriter pw, int cptL1, int cptL2 )   //Pour fermer les balises L
	{
		if ( cptL1 != 0 )
		{
			if ( cptL2 != 0 ) pw.write("\t\t\t\t</ul>\n");
			pw.write("\t\t\t</ul>\n");
		}
	}

	public static void fermetureBalises(PrintWriter pw, int cptPC, int cptPS, int cptL1, int cptL2)   //Pour fermer les balises
	{
		if ( cptL1 != 0 || cptL2 != 0 ) fermetureBalisesL( pw, cptL1, cptL2 );
		if ( cptPC != 0 || cptPS != 0 ) fermetureBalisesP(pw, cptPC, cptPS );
	}

	public static void initalisationHTML(PrintWriter pw, String header)   //Initialise la page HTML
	{
		pw.write("<!DOCTYPE html>\n"                                                        +
		"<html>\n"                                                                          +
		"     <head>\n"                                                                     +
		"          <title></title>\n"                                                       +
		"          <meta charset=\"UTF-8\">\n"                                              +
		"          <link rel=\"stylesheet\" type=\"text/css\" href=\"css/style.css\">\n"    +
		"          <link rel=\"icon\" type=\"image/png\" href=\"images/maxi_logo.png\">\n"  +
		"     </head>\n\n"                                                                  +
		"     <body>\n"
		+ header                                                                            );
	}

	public static String[][] tabNavMaker( String nav, String logPage )   //Enregistre toute les pages avec les T1 et les T2 dans un tableau
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

	public static void navMaker(PrintWriter pw, String nav, String logPage)   //Créer le nav grâce aux position enregistré
	{
		String[][] tabNav = tabNavMaker(nav, logPage);
		String sortie, sortieTitre;
		int cptT1 = 0, cptT2 = 0;
		pw.write("\n<--! Création du fil d'Ariane -->\n\t\t<nav>\n\t\t\t<ul>\n");
		if(tabNav[0][0] != null)
		{
			for(int i = 0; i < tabNav[0].length; i++)
			{
				sortie = lien(cptT1+1);

				if(tabNav[0][i].equals("T1:"))
				{

					if(cptT2 > 0)
					{
						pw.write("\t\t\t\t\t</ul>\n");
						cptT2 = 0;
					}
					if(cptT1 > 0)
					{
						pw.write("\t\t\t\t</li>\n");
					}
					pw.write("\t\t\t\t<li>\n\t\t\t\t\t<a href=" + sortie + ">" + tabNav[1][i] + "</a>\n");
					cptT1++;
				}
				else
				{
					if(cptT2 == 0)
					{
						pw.write("\t\t\t\t\t<ul>\n");
					}
					pw.write("\t\t\t\t\t\t<li>\n\t\t\t\t\t\t\t<a href="+ sortie +"#titre"+ String.valueOf(cptT2 + 1) + ">" + tabNav[1][i] + "</a>\n\t\t\t\t\t\t</li>\n");
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

	public static void fermetureHTML(PrintWriter pw, int cptDiapo, String nav, int diapoMax, String logPage)   //Ferme la page HTML tout en créant le footer
	{
		String s = "";
		nav = nav.substring(0, (nav.length() - 1));
		pw.write("\t\t</article>\n");
		navMaker(pw, nav, logPage);
		s = "\n<--! Mise en place du pied de page -->\n\t\t<footer>\n";

		s = s + "\t\t\t<a href=\""+ nomFichier +"00.html\"><div><p>D</p></div></a>\n";   //Créer le lien pour aller au début

		if (cptDiapo == 1){		s = s + "\t\t\t<p class=\"croix\">⨯</p>\n";}
		else{s = s + "\t\t\t<a href=\"" + lien(cptDiapo - 2) + "\"><div><p>" +"⇠</p></div></a>\n";}

		s = s + "\t\t\t<h1>Page " + cptDiapo + "/" + diapoMax + "</h1>\n";

		if (cptDiapo == diapoMax){s = s + "\t\t\t<p class=\"croix\">⨯</p>\n";}
		else{s = s + "\t\t\t<a href=\"" + lien(cptDiapo) + "\"><div><p>⇢</p></div></a>\n";}

		s = s + "\t\t\t<a href=\"" + lien(diapoMax - 1) + "\"><div><p>F</p></div></a>\n";   //Créer le lien pour aller à la fin

		s = s + "\t\t</footer>\n\t</body>\n</html>\n";

		pw.write(s);
	}

	public static void afficherImage(PrintWriter pw, String ligne)   //Affiche une image
	{
		int posPoint;
		posPoint = ligne.indexOf ( ':', 3 );
		pw.write("\t\t\t<img src=images/"+ligne.substring(3,posPoint)+" alt="+ligne.substring(posPoint+1)+">\n");
	}

	public static String lien(int cpt)   //Retourne le lien
	{
		String retour;
		if(cpt < 10){retour = nomFichier +"0" + (cpt)+".html";}
		else        {retour = nomFichier + (cpt)+".html";}

		return retour;
	}

}
