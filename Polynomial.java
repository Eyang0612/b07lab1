import java.security.cert.PolicyQualifierInfo;
import java.util.Arrays;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOError;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;
import java.util.function.IntFunction;
import java.util.*;
import java.util.ArrayList;

public class Polynomial{
    double[] Coefficients;
    int[] Exponents;

    public Polynomial(){
        Coefficients = new double[] {0.0};
        Exponents = new int[] {0};
    }

    public Polynomial(double[] a, int[] b){
        Coefficients = new double[a.length];
        Exponents = new int[b.length];;
        for(int i = 0; i<a.length;i++){
            Coefficients[i]=a[i];
        }
        for(int i = 0; i<b.length;i++){
            Exponents[i]=b[i];
        }

    }
    
    

    public Polynomial add(Polynomial a){
        Polynomial c = new Polynomial();
        int len = 0;
        Polynomial d = new Polynomial(a.Coefficients,a.Exponents);
        Polynomial e = new Polynomial(Coefficients, Exponents);
        for(int i = 0; i<e.Exponents.length; i++){
            for(int j = 0; j<d.Exponents.length; j++){
                if(e.Exponents[i] == d.Exponents[j]){
                    d.Exponents[j] = -1;
                    len = len + 1;
                    e.Coefficients[i] = e.Coefficients[i] + d.Coefficients[j];
                }
            }
        }
        for(int i = 0; i<e.Coefficients.length;i++){
            if(e.Coefficients[i] == 0.0){
                        e.Exponents[i] = -1;
                        len = len +1;
                    }
        }
        if((e.Exponents.length + d.Exponents.length - len) ==0){
            return c;
        }
        c.Coefficients = new double[(e.Exponents.length + d.Exponents.length - len)];
        c.Exponents = new int[(e.Coefficients.length + d.Coefficients.length - len)];
        int digit = 0;
        for(int i = 0; i<e.Exponents.length; i++){
            if(e.Exponents[i] != -1){
            c.Exponents[digit] = e.Exponents[i];
            c.Coefficients[digit] = e.Coefficients[i];
            digit = digit + 1;
            }
        }
        for(int i = 0; i<d.Exponents.length; i++){
            if(d.Exponents[i] != -1){
                c.Exponents[digit] = d.Exponents[i];
                c.Coefficients[digit] = d.Coefficients[i];
                digit = digit + 1; 
            }
        }
        return c;

        


        /*int shortlen;
        boolean model;
        if(a.Coefficients.length >= Coefficients.length){
            len = a.Coefficients.length;
            shortlen = Coefficients.length;
            model = true;
        }else{
            len = Coefficients.length;
            shortlen = a.Coefficients.length;
            model = false;
        }
        c.Coefficients = new double[len];
        for(int i=0; i<shortlen; i++){
            c.Coefficients[i] = a.Coefficients[i] + Coefficients[i];

        }
        for(int i=shortlen; i<len; i++){
            if(model){
                c.Coefficients[i] = a.Coefficients[i];

            }else{
                c.Coefficients[i] = Coefficients[i];

            }

        }
        return c;*/
        

    }
    public Polynomial(File a) throws IOException{
        Scanner scan = new Scanner(a);
        String line = scan.nextLine();
        line = line.replaceAll("-","+-"); 
        String[] content = line.split("\\+",line.length());
    
        Polynomial p = new Polynomial();
        Polynomial q = new Polynomial();
        String[] given;
        double[] set_1;
        int[] set_2;
        for(int i = 0; i<content.length; i++){
            if (content[i] == ""){
                
            }else{
                given = content[i].split("x",2);
                
                if(given.length == 1){

                    set_1 = new double[] {Double.parseDouble(given[0])};
                    set_2 = new int[] {0};
                    q = new Polynomial(set_1,set_2);
                    
                }else{
                    if(given[0].isEmpty()){
                        set_1 = new double[] {1};
                    }else{
                        set_1 = new double[] {Double.parseDouble(given[0])};
                    }
                    if(given[1].isEmpty()){
                        set_2 = new int[] {1};
                    }else{
                        set_2 = new int[] {Integer.parseInt(given[1])};
                    }
                    q = new Polynomial(set_1,set_2);

                }
            
                
                p = p.add(q);
            }
        }
        Coefficients = p.Coefficients;
        Exponents = p.Exponents;
        scan.close();



    }

    public double evaluate(double x){
        double sum = 0;
        double multi;
        for(int i = 0; i<Coefficients.length; i++){
            multi = 1;
            for(int j = 0; j<Exponents[i]; j++){
                multi = multi * x;
            }
            sum = sum + (Coefficients[i] * multi);

        }
        return sum;
    }

    public boolean hasRoot(double x){
        double root = evaluate(x);
        if(root == 0.0){
            return true;
        }else{
            return false;
        }


    }
    public Polynomial multiply(Polynomial a){
        Polynomial c = new Polynomial();
        Polynomial d = new Polynomial(new double[Coefficients.length], new int[Exponents.length]);
        for(int j = 0; j<a.Exponents.length; j++){
            d = new Polynomial(Coefficients, Exponents);
            for(int i = 0; i<Exponents.length; i++){
                d.Exponents[i] = d.Exponents[i] + a.Exponents[j];
                d.Coefficients[i] = d.Coefficients[i] * a.Coefficients[j];
            }
            

            c = c.add(d);
        

        }
        return c;
    }
    public void saveToFile(String a) throws IOException {
        String line = "";
        FileWriter fw = new FileWriter(a);
        BufferedWriter bw = new BufferedWriter(fw);
        line = line.concat(String.valueOf(Coefficients[0]));
            if(Exponents[0] != 0){
                line = line.concat("x");
                if(Exponents[0] != 1){
                    line = line.concat(String.valueOf(Exponents[0]));
                }
            }
        for(int i = 1; i<Coefficients.length; i++){
            line = line.concat("+");
            line = line.concat(String.valueOf(Coefficients[i]));
            if(Exponents[i] != 0){
                line = line.concat("x");
                if(Exponents[i] != 1){
                    line = line.concat(String.valueOf(Exponents[i]));
                }
            }


        }
        line = line.replaceAll("\\+-","-"); 
        
        
        bw.write(line);
        bw.close();




    }

        

    


}

