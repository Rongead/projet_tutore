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
		int cptL1 = 0;
		int cptL2 = 0;
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
							fermetureBalisesL( pw, cptL1, cptL2, cptPC, cptPS );
							fermetureBalisesP(pw, cptPC, cptPS, cptL1, cptL2 );
							header = "\t<header><img class=\"logo\" src=\"images/maxi_logo.png\" alt=\"logo\">\n" + ligne.substring(3) + "\n<img class=\"logo\" src=\"images/maxi_logo.png\" alt=\"logo\">\n</header>\n<article>\n";
							pw.write (header);
							cptL1 = cptL2 = 0;
							cptPS = cptPC = 0;
							break;
						case "T1:":
							fermetureBalisesL( pw, cptL1, cptL2, cptPC, cptPS );
							fermetureBalisesP(pw, cptPC, cptPS, cptL1, cptL2 );
							cptT1++;
							nav = ligne + "#";
							pw.write ("\t\t<h1>" + cptT1 + " " + ligne.substring(3)+"</h1>\n");
							cptL1 = cptL2 = 0;
							cptPS = cptPC = 0;
							break;
						case "T2:":
							fermetureBalisesL( pw, cptL1, cptL2, cptPC, cptPS );
							fermetureBalisesP(pw, cptPC, cptPS, cptL1, cptL2 );
							cptT2++;
							nav = ligne + "#";
							pw.write ("\t\t<h2>"+ cptT1 + "." + cptT2 + " " + ligne.substring(3)+"</h2>\n");
							cptL1 = cptL2 = 0;
							cptPS = cptPC = 0;
							break;
						case "t2:":
							fermetureBalisesL( pw, cptL1, cptL2, cptPC, cptPS );
							fermetureBalisesP(pw, cptPC, cptPS, cptL1, cptL2 );
							pw.write ("\t\t<h3>"+ ligne.substring(3)+"</h3>\n");
							cptL1 = cptL2 = 0;
							cptPS = cptPC = 0;
							break;
						case "DP:":
							if(cptDiapo > 0)
							{
								fermetureBalisesL( pw, cptL1, cptL2, cptPC, cptPS );
								fermetureBalisesP(pw, cptPC, cptPS, cptL1, cptL2 );
								fermetureHTML(pw, cptPC, cptPS, cptDiapo);
								pw.close();
								fichierDestination = racine + "/sortie" + cptDiapo + ".html";
								pw = new PrintWriter ( new OutputStreamWriter ( new FileOutputStream(fichierDestination), "utf-8" ) );
								initalisationHTML(pw, header);
							}
							cptL1 = cptL2 = 0;
							cptPS = cptPC = 0;
							cptDiapo++;
							break;
						case "L1:":
							if (cptL1 == 0)
							{
								pw.write("\t\t\t<ul>\n"+"\t\t\t<li>"+ligne.substring(3)+"</li>\n");
							}
							else
							{
								pw.write("\t\t\t<li>"+ligne.substring(3)+"</li>\n");
							}
							cptPS = cptPC = 0;
							cptL1++;
							break;
						case "L2:":
							if (cptL1 != 0 && cptL2 == 0)
							{
								pw.write("\t\t\t<ul>\n"+"\t\t\t<li>"+ligne.substring(3)+"</li>\n");
							}
							else
							{
								if ( cptL1 != 0 )
								{
									pw.write("\t\t\t<li>"+ligne.substring(3)+"</li>\n");
								}
							}
							cptPS = cptPC = 0;
							cptL2++;
							break;
						case "PS:":
							fermetureBalisesL( pw, cptL1, cptL2, cptPC, cptPS );
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
							cptL1 = cptL2 = 0;
							cptPS++;
							break;
						case "PC:":
							fermetureBalisesL( pw, cptL1, cptL2, cptPC, cptPS );
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
							cptL1 = cptL2 = 0;
							cptPC++;
							break;
							case "IM:":fermetureBalisesP(pw, cptPC, cptPS, cptL1, cptL2 );  AfficherImage(pw, ligne);break;
					}
					System.out.println (ligne.substring(3));
				}
			}
			String[][] navTab = tabNavMaker(nav);
			fermetureHTML(pw, cptPC, cptPS, cptDiapo);
			pw.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	public static void fermetureBalisesP(PrintWriter pw, int cptPC, int cptPS, int cptL1, int cptL2 )
	{
		if (cptPC != 0 || cptPS != 0)
		{
			pw.write("</p>\n");
		}
	}

	public static void fermetureBalisesL(PrintWriter pw, int cptL1, int cptL2, int cptPC, int cptPS )
	{
		if ( cptL1 != 0 )
		{
			if ( cptL2 != 0 ) pw.write("</ul>\n");
			pw.write("</ul>\n");
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
			if ( indexFin - 1 > 0 ) tempString = nav.substring( 0, ( indexFin - 1 ) );
			if ( nav.length() > 3 )
			{
				nav = nav.substring( indexFin + 1 );
				tabNav[0][j] = tempString.substring(0,3);
				tabNav[1][j] = tempString.substring(4);
			}
		}

		return tabNav;
	}

	public static void fermetureHTML(PrintWriter pw, int cptPC, int cptPS, int cptDiapo)
	{
		if (cptPC != 0 || cptPS != 0)
		{
			cptPS=0;cptPC=0;
			pw.write("</p>\n");
		}
		pw.write("\t\t</article>\n"                 +
					"\t<footer>\n"                     +
					"\t\t<p><a href=\"sortie"+(cptDiapo-2)+".html\">⨯</a></p>\n" +
					"\t\t<h1>page "+cptDiapo+"/6</h1>\n"          +
					"\t\t<p><a href=\"sortie"+(cptDiapo)+".html\">⇢</a></p>\n" +
					"</footer>\n"                      +
			    "	</body>\n"                      +
			    "</html>\n"                         );
	}

	public static void AfficherImage(PrintWriter pw, String ligne)
	{
		int posPoint;
		posPoint = ligne.indexOf ( ':', 3 );
		pw.write("<img src=images/"+ligne.substring(3,posPoint)+" alt="+ligne.substring(posPoint+1)+">");
	}
}
