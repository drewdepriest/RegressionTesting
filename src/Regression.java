import java.util.ArrayList;
import java.util.List;

public class Regression {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// define data sets
		
		// x = OAT (X, the independent variable)
		List<Double> oat = new ArrayList<Double>();
		oat.add(0.00);
		oat.add(1.00);
		oat.add(2.00);
		oat.add(3.00);
		oat.add(4.00);
		oat.add(5.00);
		oat.add(6.00);
		
		// y = Energy (Y, the dependent variable)
		List<Double> energy = new ArrayList<Double>();
		energy.add(25.00);
		energy.add(38.00);
		energy.add(58.00);
		energy.add(89.00);
		energy.add(135.00);
		energy.add(206.00);
		energy.add(315.00);
		
		// N = size of data set
		int N = energy.size();
		
		// constant e:
		Double e = Math.E;
		
		// source: http://www.matrixlab-examples.com/exponential-regression.html
		// f(x) = ae^(bx)
		
		// Find sum of squares:
		// Calculate sum(X) & sum(X^2) & sum(Y) & sum(X*Y)
		// NOTE: Y denotes log(Y) for each case
		Double sumX = 0.00;
		Double sumX2 = 0.00;
		Double sumY = 0.00;
		Double sumY2 = 0.00;
		Double sumXY = 0.00;
		
		for(int i=0;i<N;i++)
		{
			sumX = sumX + oat.get(i);
			sumX2 = sumX2 + Math.pow(oat.get(i), 2);
		}
		
		for(int j=0;j<N;j++)
		{
			sumY = sumY + Math.log(energy.get(j));
			sumY2 = sumY2 + Math.pow(Math.log(energy.get(j)), 2);
		}
		
		for(int k=0;k<N;k++)
		{
			sumXY = sumXY + (oat.get(k)*(Math.log(energy.get(k))));
		}

		// Calculate regression coefficient 'b'
		Double b = 0.00;
		b = ((N*sumXY) - (sumX*sumY))/(N*sumX2 - (sumX*sumX));
		
		// Calculate regression coefficient 'a'
		Double a = 0.00;
		a = Math.pow(e, (sumY - (b*sumX))/N);
		
		// Calculate coefficient of determination (R^2)
		Double c = 0.00;	// numerator
		Double d = 0.00;	// denominator
		Double r = 0.00;	// coefficient of determination
		c = (b)*(sumXY - sumX*sumY/N);
		d = sumY2 - (sumY*sumY)/N;
		r = c/d;
		
		// Calculate coefficient of correlation
		Double p = 0.00;
		if(r > 0){
			p = Math.sqrt(r);
		} else {
			p = 0.00;
		}

		// Calculate standard error
		// equals (total variance - y variance)/(n-2)
		Double std_err = 0.00;
		std_err = Math.sqrt((d-c)/(N-2));
		
		// print the model
		System.out.println("Regression model for n equals " + N + ":");
		System.out.println("y = " + a + "*(e^(" + b + "*x))");
		
		System.out.println("R-square value equals " + r);
		System.out.println("Correlation equals " + p);
		System.out.println("Standard Error equals " + std_err);
		
	}
	
}
