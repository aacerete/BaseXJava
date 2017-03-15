import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

import org.basex.BaseXServer;
import org.basex.api.client.ClientSession;
/**
 * Created by aacerete on 15/03/17.
 */
public class BaseXJava {

    private static ClientSession clientSession = null;
    private static String factbook = "doc(\"Factbook.xml\")";

    //les dues ultimes cerques no es podien fer a factbook
    private static String mondial = "doc(\"mondial.xml\")";
    private static boolean fi = false;

    public static void main(String[] args) throws IOException {

        BaseXServer server = new BaseXServer();

        Scanner sc = new Scanner(System.in);
        int opcio = 0;

        while (!fi) {


            System.out.println("\nMenú Principal: \n");
            System.out.println("1. Nom dels països del fitxer");
            System.out.println("2. Nombre de països"  );
            System.out.println("3 .Informació sobre Alemanya");
            System.out.println("4. Població d'Uganda");
            System.out.println("5. Ciutats de Perú");
            System.out.println("6. Població de Shangai");
            System.out.println("7. Codi de la matricula de Xipre");
            System.out.println("0. Sortir");


            opcio = sc.nextInt();

            switch (opcio){
                //A FACTBOOK SERIA     (factbook+"/factbook/record/country")
                case 0:
                    fi = true;
                    System.out.println("Fi del programa");
                    break;
                case 1:
                    executeQueryAndOutPrintResult(factbook +"/factbook/record/country");
                    break;
                case 2:
                    executeQueryAndOutPrintResult(factbook +"/count(/factbook/record/country)");
                    break;
                case 3:
                    //executeQueryAndOutPrintResult(factbook +"/factbook/record/[country='Germany']//@background");
                    break;
                case 4:
                    //executeQueryAndOutPrintResult(factbook +"/factbook/record/country[name='Uganda']/@population");
                    break;
                case 5:
                    //executeQueryAndOutPrintResult(factbook +"/factbook/record/country[name='Peru']/province/city/name");
                    break;
                case 6:
                    executeQueryAndOutPrintResult(mondial +"/mondial/country/province/city[name='Shanghai']/population");
                    break;
                case 7:
                    executeQueryAndOutPrintResult(mondial +"/mondial/country[name='Cyprus']/@car_code");
                    break;
                default:
                    System.out.println("Opció invalida.");
                break;

            }
        }
    }

    public static void executeQueryAndOutPrintResult (String query) {

        try {

            clientSession = new ClientSession("localhost", 1984, "admin", "admin");
            System.out.println("Resultat de la cerca: \n"+clientSession.query(query).execute());

        }catch (IOException one) {
            one.printStackTrace();System.out.println("\nError al fer la consulta");

        }finally {

            if (clientSession!= null) {

                try {

                    clientSession.close();

                }catch (IOException two) {

                    System.out.println("\nError al tancar sessió");
                }
            }
        }
    }
}

