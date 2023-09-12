

public class Polynomial{
    double[] Coefficients;

    public Polynomial(){
        Coefficients = new double[] {0.0};
    }

    public Polynomial(double[] a ){
        Coefficients = new double[a.length];
        for(int i = 0; i<a.length;i++){
            Coefficients[i]=a[i];
        }

    }

    public Polynomial add(Polynomial a){
        Polynomial c = new Polynomial();
        int len;
        int shortlen;
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
        return c;

    }

    public double evaluate(double x){
        double sum = 0;
        double multi;
        for(int i = 0; i<Coefficients.length; i++){
            multi = 1;
            for(int j = 0; j<i; j++){
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
        

    


}

