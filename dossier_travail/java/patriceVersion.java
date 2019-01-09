/** Projet tutoré
 *  Générateur de diaporama HTML
 *  Groupe 29
 *  @author CONTE Lucas, MAISONNEUVE Patrice, NEVEU Aubin, PONTY Arthur, PRUNIER Sébastien
 *  @date   09/01/2018 8:55
 */

import java.util.Scanner;
import java.io.FileInputStream;

import java.io.OutputStreamWriter;
import java.io.FileOutputStream;
import java.io.PrintWriter;



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
		int cptDiapo = 0;
		int cptPS = 0;
		int cptPC = 0;
		int cptT1 = 0;
		int cptT2 = 1;
		String fichierDestination = racine + "/sortie" + cptDiapo + ".html";
		String nav = "";
		String header = "";

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
							fermetureBalisesP(pw, cptPC, cptPS);
							header = "\t<header><img class=\"logo\" src=\"images/maxi_logo.png\" alt=\"logo\">\n" + ligne.substring(3) + "\n<img class=\"logo\" src=\"images/maxi_logo.png\" alt=\"logo\">\n</header>\n<article>\n";
							pw.write (header);
							cptPS = cptPC = 0;
							break;
						case "T1:":
							fermetureBalisesP(pw, cptPC, cptPS);
							cptT1++;
							nav += ligne + "#";
							pw.write ("\t\t<h1>" + cptT1 + " " + ligne.substring(3)+"</h1>\n");
							cptPS = cptPC = 0;
							break;
						case "T2:":
							fermetureBalisesP(pw, cptPC, cptPS);
							cptT2++;
							nav += ligne + "#";
							pw.write ("\t\t<h2>"+ cptT1 + "." + cptT2 + " " + ligne.substring(3)+"</h2>\n");
							cptPS = cptPC = 0;
							break;
						case "t2:":
							fermetureBalisesP(pw, cptPC, cptPS);
							pw.write ("\t\t<h3>"+ ligne.substring(3)+"</h3>\n");
							cptPS = cptPC = 0;
							break;
						case "DP:":
							if(cptDiapo > 0)
							{
								fermetureBalisesP(pw, cptPC, cptPS);
								fermetureHTML(pw, cptPC, cptPS, cptDiapo, nav);
								pw.close();
								fichierDestination = racine + "/sortie" + cptDiapo + ".html";
								pw = new PrintWriter ( new OutputStreamWriter ( new FileOutputStream(fichierDestination), "utf-8" ) );
								initalisationHTML(pw, header);
							}
							cptDiapo++;
							break;
						case "PS:":
							if (cptPC != 0)
							{
								cptPC=0;
								pw.write("\t\t\t</p>\n");
							}
							if(cptPS==0)
							{
								pw.write ("\t\t\t<p>"+  ligne.substring(3));
							}
							else
							{
								pw.write ("\t\t\t" + ligne.substring(3));
							}
							cptPS++;
							break;
						case "PC:":
							if (cptPS != 0)
							{
								cptPS=0;
								pw.write("</p>\n");
							}
							if(cptPC==0)
							{
								pw.write ("\t<p class=\"encadrer\">"+  ligne.substring(3));
							}
							else
							{
								pw.write (ligne.substring(3));
							}
							cptPC++;
							break;
					}
					System.out.println (ligne.substring(3));
				}
			}
			fermetureHTML(pw, cptPC, cptPS, cptDiapo, nav);
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
			cptPS=0;cptPC=0;
			pw.write("</p>\n");
		}
	}

	public static void initalisationHTML(PrintWriter pw, String header)
	{
		pw.write("<!DOCTYPE html>\n"                                                                +
			    "<html>\n"                                                                         +
			    "     <head>\n"                                                                    +
			    "          <title></title>\n"                                                      +
			    "          <meta charset=\"UTF-8\">\n"                                              +
			    "          <link rel=\"stylesheet\" type=\"text/css\" href=\"css/style.css\">\n"   +
			    "          <link rel=\"icon\" type=\"image/png\" href=\"images/maxi_logo.png\">\n" +
			    "     </head>\n"                                                                   +
			    "     <body>\n"
			    + header                                                                    );
	}

	public static String[][] tabNavMaker( String nav )
	{
		System.out.println(nav);
		int cptNav = 1;
		for (int i = 0; i < nav.length() ; i++ )
		{
			if(nav.charAt(i) == '#') cptNav++;
		}

		String[][] tabNav = new String[2][cptNav];
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
				if(nav.length() > 4)
				{
					nav = nav.substring( indexFin + 1);
				}
			}
		}
		return tabNav;
	}

	public static void navMaker(PrintWriter pw, String nav)
	{
		String[][] tabNav = tabNavMaker(nav);
		int cptT1 = 0, cptT2 = 0;
		pw.write("\t<nav>\n\t\t<ul>\n");
		if(tabNav[0][0] != null)
		{
			for(int i = 0; i < tabNav[0].length; i++)
			{
				if(tabNav[0][i].equals("T1:"))
				{
					if(cptT2 > 0)
					{
						pw.write("\t\t\t\t</ul>");
						cptT2 = 0;
					}
					if(cptT1 > 0)
					{
						pw.write("\t\t\t</li>");
					}
					pw.write("\t\t\t<li>\n\t\t\t\t" + tabNav[1][i] + "\n");
					cptT1++;
				}
				else
				{
					if(cptT2 == 0)
					{
						pw.write("\t\t\t\t<ul>\n");
					}
					pw.write("\t\t\t\t\t<li>\n\t\t\t\t\t\t" + tabNav[1][i] + "\n\t\t\t\t\t</li>\n");
					cptT2++;
				}
			}
		}
		if(cptT2 > 0)
		{
			pw.write("\t\t\t\t</ul>\n\t\t\t</li>\n");
		}
		pw.write("\t\t</ul>\n\t</nav>\n");
	}

	public static void fermetureHTML(PrintWriter pw, int cptPC, int cptPS, int cptDiapo, String nav)
	{
		if (cptPC != 0 || cptPS != 0)
		{
			cptPS=0;cptPC=0;
			pw.write("</p>\n");
		}
		nav = nav.substring(0, (nav.length() - 1));
		navMaker(pw, nav);
		pw.write("\t\t</article>\n"                 +
					"\t<footer>\n"                     +
					"\t\t<p><a href=\"sortie"+(cptDiapo-2)+".html\">x</a></p>\n" +
					"\t\t<h1>page "+cptDiapo+"/6</h1>\n"          +
					"\t\t<p><a href=\"sortie"+(cptDiapo)+".html\">⇢</a></p>\n" +
					"</footer>\n"                      +
			    "	</body>\n"                      +
			    "</html>\n"                         );
	}
}
