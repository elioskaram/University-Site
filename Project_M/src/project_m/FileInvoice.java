package project_m;
import java.io.*;
import java.util.*;
import java.text.*;
/**
 *
 * @author Elios
 */
public class FileInvoice {
    public static void writeInvoice(String invoice){
        try
	    {
        	PrintWriter outFileStream = new PrintWriter(new File("Invoice.txt"));
		outFileStream.println(invoice);
		outFileStream.close();
	    }
	catch(FileNotFoundException e)
	    {
		System.out.println(e.getMessage());
		System.out.println("Can't open Invoice.txt");
		System.exit(-1);
		}
	catch(IOException e)
	    {
		System.out.println(e.getMessage());
		System.out.println("Error writing Invoice.txt");
		System.exit(-1);
	    }


	File outFile = new File("../FileIO/Invoice.txt");
	System.out.println(outFile.exists());
	System.out.println(outFile.length());
	System.out.println(outFile.getName());
	System.out.println(outFile.getPath());
    }
}
