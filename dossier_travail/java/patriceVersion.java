import java.util.Scanner;
import java.io.FileInputStream;

import java.io.OutputStreamWriter;
import java.io.FileOutputStream;
import java.io.PrintWriter;



public class Exemple2
{
	public static void main(String[] a)
	{
		String ligne;

		Scanner     scIn;
		PrintWriter pw;
		int cptDP = 0;
		int cptPS = 0;
		int cptPC = 0;

		try
		{
			scIn = new Scanner ( new FileInputStream ( "exemple.data"), "utf-8"  );
			pw = new PrintWriter ( new OutputStreamWriter ( new FileOutputStream("sortie2.html"), "utf-8" ) );

			pw.write("<!DOCTYPE html>\n"                                                               +
                     "<html>\n"                                                                        +
    				"     <head>\n"                                                                    +
            		"          <title></title>\n"                                                      +
            		"          <meta charset=\"UTF-8\">\n"                                             +
            		"          <link rel=\"stylesheet\" type=\"text/css\" href=\"css/style.css\">\n"   +
					"          <link rel=\"icon\" type=\"image/png\" href=\"images/maxi_logo.png\">\n" +
            		"     </head>\n"                                                                   +
            		"     <body>\n"                                                                     );

			while ( scIn.hasNextLine() )
			{
				ligne = scIn.nextLine();

				if (!(ligne.substring(0).equals(""))) {
					switch (ligne.substring(0,3)){
					case "TP:": pw.println ("\t<header><img class=\"logo\" src=\"images/maxi_logo.png\" alt=\"logo\">\n" +
											ligne.substring(3)                                                         +
											"\n<img class=\"logo\" src=\"images/maxi_logo.png\" alt=\"logo\">\n</header>"   ); cptPS = cptPC = 0;break;
					case "T1:": if (cptPC != 0 || cptPS != 0) {cptPS=0;cptPC=0;pw.println("</p>");}pw.println ("\t\t<h1>"+ ligne.substring(3)+"</h1>"); cptPS = cptPC = 0;break;
					case "T2:": if (cptPC != 0 || cptPS != 0) {cptPS=0;cptPC=0;pw.println("</p>");}pw.println ("\t\t<h2>"+ ligne.substring(3)+"</h2>"); cptPS = cptPC = 0;break;
					case "t2:": if (cptPC != 0 || cptPS != 0) {cptPS=0;cptPC=0;pw.println("</p>");}pw.println ("\t\t<h3>"+ ligne.substring(3)+"</h3>"); cptPS = cptPC = 0;break;
					case "DP:": if (cptPC != 0 || cptPS != 0) {cptPS=0;cptPC=0;pw.println("</p>");}if(cptDP==0){pw.println ("\t<article>");}else{pw.println ("\t</article>\n\t<article>");}cptDP++; cptPS = cptPC = 0; break;

					case "PS:": if (cptPC != 0) {cptPC=0;pw.println("</p>");}if(cptPS==0){pw.println ("\t<p>"+  ligne.substring(3));}else{pw.println (ligne.substring(3));}cptPS++; break;
					case "PC:": if (cptPS != 0) {cptPS=0;pw.println("</p>");}if(cptPC==0){pw.println ("\t<p class=\"encadrer\">"+  ligne.substring(3));}else{pw.println (ligne.substring(3));}cptPC++; break;
				}

					System.out.println (ligne.substring(3));
				}
			}
			pw.write("		</article>\n"+
					 "	</body>\n"       +
					 "</html>\n"          );
			pw.close();
		}
		catch(Exception e){ e.printStackTrace(); }
	}
}

import java.util.Scanner;
import java.io.FileInputStream;

import java.io.OutputStreamWriter;
import java.io.FileOutputStream;
import java.io.PrintWriter;



public class Exemple2
{
	public static void main(String[] a)
	{
		String ligne;

		Scanner     scIn;
		PrintWriter pw;
		int cptDP = 0;
		int cptPS = 0;
		int cptPC = 0;

		try
		{
			scIn = new Scanner ( new FileInputStream ( "exemple.data"), "utf-8"  );
			pw = new PrintWriter ( new OutputStreamWriter ( new FileOutputStream("sortie2.html"), "utf-8" ) );

			pw.write("<!DOCTYPE html>\n"                                                               +
                     "<html>\n"                                                                        +
    				"     <head>\n"                                                                    +
            		"          <title></title>\n"                                                      +
            		"          <meta charset=\"UTF-8\">\n"                                             +
            		"          <link rel=\"stylesheet\" type=\"text/css\" href=\"css/style.css\">\n"   +
					"          <link rel=\"icon\" type=\"image/png\" href=\"images/maxi_logo.png\">\n" +
            		"     </head>\n"                                                                   +
            		"     <body>\n"                                                                     );

			while ( scIn.hasNextLine() )
			{
				ligne = scIn.nextLine();

				if (!(ligne.substring(0).equals(""))) {
					switch (ligne.substring(0,3)){
					case "TP:": pw.println ("\t<header><img class=\"logo\" src=\"images/maxi_logo.png\" alt=\"logo\">\n" +
											ligne.substring(3)                                                         +
											"\n<img class=\"logo\" src=\"images/maxi_logo.png\" alt=\"logo\">\n</header>"   ); cptPS = cptPC = 0;break;
					case "T1:": if (cptPC != 0 || cptPS != 0) {cptPS=0;cptPC=0;pw.println("</p>");}pw.println ("\t\t<h1>"+ ligne.substring(3)+"</h1>"); cptPS = cptPC = 0;break;
					case "T2:": if (cptPC != 0 || cptPS != 0) {cptPS=0;cptPC=0;pw.println("</p>");}pw.println ("\t\t<h2>"+ ligne.substring(3)+"</h2>"); cptPS = cptPC = 0;break;
					case "t2:": if (cptPC != 0 || cptPS != 0) {cptPS=0;cptPC=0;pw.println("</p>");}pw.println ("\t\t<h3>"+ ligne.substring(3)+"</h3>"); cptPS = cptPC = 0;break;
					case "DP:": if (cptPC != 0 || cptPS != 0) {cptPS=0;cptPC=0;pw.println("</p>");}if(cptDP==0){pw.println ("\t<article>");}else{pw.println ("\t</article>\n\t<article>");}cptDP++; cptPS = cptPC = 0; break;

					case "PS:": if (cptPC != 0) {cptPC=0;pw.println("</p>");}if(cptPS==0){pw.println ("\t<p>"+  ligne.substring(3));}else{pw.println (ligne.substring(3));}cptPS++; break;
					case "PC:": if (cptPS != 0) {cptPS=0;pw.println("</p>");}if(cptPC==0){pw.println ("\t<p class=\"encadrer\">"+  ligne.substring(3));}else{pw.println (ligne.substring(3));}cptPC++; break;
				}

					System.out.println (ligne.substring(3));
				}
			}
			pw.write("		</article>\n"+
					 "	</body>\n"       +
					 "</html>\n"          );
			pw.close();
		}
		catch(Exception e){ e.printStackTrace(); }
	}
}
