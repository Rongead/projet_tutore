/**
 * @author Sebastien PRUNIER && Patrice MAISONNEUUVE
 * date: 07/01/2019
 */

import iut.algo.*;
import java.io.*;
import java.util.*;

public class
{
     public static void main(String[] args)
     {
          String source, racine;

          System.out.print("Entrez la source du fichier html : ");
          source = Clavier.lireString();
          System.out.print("Entrez la répertoire de destination : ");
          racine = Clavier.lireString();

          try
          {
               Scanner sc = new Scanner( new FileReader( source ) );
               PrintWriter pw = new PrintWriter ( new FileWriter (racine + "/sortie.html") );
               pw.write("<!DOCTYPE html>");
               pw.write(<html>);
               pw.write(     <head>);
               pw.write(     </head>);
               pw.write(     <body>);
               while( sc.hasNext() )
               {

               }
               sc.close();
          }
          catch ( Exception e )
          {
               e.printStackTrace();
          }
     }
}
