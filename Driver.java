import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOError;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;

public class Driver {
    public static void main(String [] args) throws IOException {
    
    Polynomial p = new Polynomial();
    System.out.println(p.evaluate(3));
    double [] c1 = {6};
    int [] c3 = {0};
    Polynomial p1 = new Polynomial(c1, c3);
    double [] c2 = {-5};
    int [] c4 = {3};

    Polynomial p2 = new Polynomial(c2,c4);
    Polynomial s = p1.add(p2);
    System.out.println("s(0.1) = " + s.evaluate(0.1));
    
    if(s.hasRoot(1))
    System.out.println("1 is a root of s");
    else
    System.out.println("1 is not a root of s");

    Polynomial p8 = new Polynomial();
    Polynomial r = p8.add(s);
    System.out.println("r(0.1) = " + r.evaluate(0.1));
    if(r.hasRoot(1))
    System.out.println("1 is a root of r");
    else
    System.out.println("1 is not a root of r");
    
    double [] c5 = {1.0};
    int [] c6 = {1};
    Polynomial p3 = new Polynomial(c5,c6);
    Polynomial z = s.multiply(p3);
    for (double a : z.Coefficients)
            System.out.println(a);
    for (int a : z.Exponents)
            System.out.println(a);
    System.out.println("z(0.1) = " + z.evaluate(0.1));
    if(z.hasRoot(1))
    System.out.println("1 is a root of z");
    else
    System.out.println("1 is not a root of z");
    File file = new File("//Users//eddieyang//b07lab1//text");
    Polynomial h = new Polynomial(file);
    System.out.println("The Following are the Coefficients of h");
    for (double i : h.Coefficients) System.out.println(i);
     System.out.println("The Following are the Exponents of h");
    for (int i : h.Exponents) System.out.println(i);
    h.saveToFile("//Users//eddieyang//b07lab1//answer");



    }
    
    
    
    }