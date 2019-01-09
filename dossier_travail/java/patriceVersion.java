import java.util.Scanner;
import java.io.FileInputStream;

import java.io.OutputStreamWriter;
import java.io.FileOutputStream;
import java.io.PrintWriter;



public class patriceVersion
{
	String source;
	String racine;
	int cptDiapo;

	public static void main(String[] args)
	{
		source = args[0];
		racine = args[1];
		cptDiapo = 0;
		initialiserDiapo();
	}

	public static void initialiserDiapo()
	{
		String ligne;
		Scanner     scIn;
		PrintWriter pw;
		int cptDP = 0;
		int cptPS = 0;
		int cptPC = 0;
		int cptT1 = 0;
		int cptT2 = 1;
		String fichierDestination = racine + "/sortie" + cptDiapo + ".html";
		String nav = "";

		try
		{
			scIn = new Scanner ( new FileInputStream ( "exemple.data"), "utf-8"  );
			pw = new PrintWriter ( new OutputStreamWriter ( new FileOutputStream(fichierDestination), "utf-8" ) );

			while ( scIn.hasNextLine() )
			{
				ligne = scIn.nextLine();

				if (!(ligne.substring(0).equals(""))) {
					switch (ligne.substring(0,3)){
						case "TP:":
							fermetureBalisesP(pw, cptPC, cptPS);
							pw.write ("\t<header><img class=\"logo\" src=\"images/maxi_logo.png\" alt=\"logo\">\n" + ligne.substring(3) + "\n<img class=\"logo\" src=\"images/maxi_logo.png\" alt=\"logo\">\n</header>\n"   );
							cptPS = cptPC = 0;
							break;
						case "T1:":
							fermetureBalisesP(pw, cptPC, cptPS);
							cptT1++;
							nav = ligne + "#";
							pw.write ("\t\t<h1>" + cptT1 + " " + ligne.substring(3)+"</h1>\n");
							cptPS = cptPC = 0;
							break;
						case "T2:":
							fermetureBalisesP(pw, cptPC, cptPS);
							cptT2++;
							nav = ligne + "#";
							pw.write ("\t\t<h2>"+ cptT1 + "." + cptT2 + " " + ligne.substring(3)+"</h2>\n");
							cptPS = cptPC = 0;
							break;
						case "t2:":
							fermetureBalisesP(pw, cptPC, cptPS);
							pw.write ("\t\t<h3>"+ ligne.substring(3)+"</h3>\n");
							cptPS = cptPC = 0;
							break;
						case "DP:":
							fermetureBalisesP(pw, cptPC, cptPS);
							if(cptDP==0)
							{
								pw.write ("\t<article>\n");
							}
							else
							{
								pw.write ("\t</article>\n\t<article>\n");
							}
							cptDP++;
							cptPS = cptPC = 0;
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
			if (cptPC != 0 || cptPS != 0)
			{
				cptPS=0;cptPC=0;
				pw.write("</p>\n");
			}
			pw.write("\t\t</article>\n"                 +
					     "\t<footer>\n"                     +
					     "\t\t<p><a href=\"#\">x</a></p>\n" +
					     "\t\t<h1>page 1/6</h1>\n"          +
					     "\t\t<p><a href=\"#\">⇢</a></p>\n" +
					     "</footer>\n"                      +
			         "	</body>\n"                      +
			         "</html>\n"                         );
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

	public static void initalisationHTML()
	{
		pw.write("<!DOCTYPE html>\n"                                                                +
			    "<html>\n"                                                                         +
			    "     <head>\n"                                                                    +
			    "          <title></title>\n"                                                      +
			    "          <meta charset=\"UTF-8\">\n"                                             +
			    "          <link rel=\"stylesheet\" type=\"text/css\" href=\"css/style.css\">\n"   +
			    "          <link rel=\"icon\" type=\"image/png\" href=\"images/maxi_logo.png\">\n" +
			    "     </head>\n"                                                                   +
			    "     <body>\n"                                                                     );
	}

	public static String[][] tabNavMaker( String nav )
	{
		int cptNav = 1;
		for (int i = 0; i < nav.length() ; i++ )
		{
			if(nav.charAt(i) = '#') cptNav++;
		}

		String[][] tabNav = new String[2][cptNav];
		for ( int j = 0; j < cptNav; j++)
		{
			int indexFin = nav.indexOf("#");
			String tempString = nav.substring( 0, ( indexFin - 1 ) );
			nav = nav.substring( indexFin + 1 );
			tabNav[0][j] = tempString.substring(0,3);
			tabNav[1][j] = tempString.substring(4);
		}

		return tabNav;
	}
}
