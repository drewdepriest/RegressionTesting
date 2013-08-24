import java.util.ArrayList;
import java.util.List;

public class Regression {

	/**
	 * @param args
	 * 2013.AUG.23 - Class now calculates both exponential and linear regression
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
		
		/* sample data & equations 
		 * exponential sourced from: http://www.matrixlab-examples.com/exponential-regression.html
		 * f(x) = ae^(bx) for exponential
		 * f(x) = a + bx for linear
		 */
		
		/* Find sum of squares:
		 * Calculate sum(X) & sum(X^2) & sum(Y) & sum(Y^2) & sum(X*Y)
		 * NOTE: Y denotes log(Y) for each case when calculating exponential regression
		 */
		Double sumX = 0.00;
		Double sumX2 = 0.00;
		Double sumY = 0.00;
		Double sumYlin = 0.00;
		Double sumY2 = 0.00;
		Double sumY2lin = 0.00;
		Double sumXY = 0.00;
		Double sumXYlin = 0.00;
		
		for(int i=0;i<N;i++)
		{
			sumX = sumX + oat.get(i);
			sumX2 = sumX2 + Math.pow(oat.get(i), 2);
			
			// exponential
			sumY = sumY + Math.log(energy.get(i));
			sumY2 = sumY2 + Math.pow(Math.log(energy.get(i)), 2);
			sumXY = sumXY + (oat.get(i)*(Math.log(energy.get(i))));
			
			// linear
			sumYlin = sumYlin + energy.get(i);
			sumY2lin = sumY2lin + Math.pow(energy.get(i),2);
			sumXYlin = sumXYlin + oat.get(i)*energy.get(i);
		}
		
		/* Calculate regression coefficient 'b' */
		// exponential
		Double b = 0.00;
		b = ((N*sumXY) - (sumX*sumY))/(N*sumX2 - (sumX*sumX));
		
		// linear
		Double bLin = 0.00;
		bLin = ((N*sumXYlin) - (sumX*sumYlin))/(N*sumX2 - (sumX*sumX));
		
		/* Calculate regression coefficient 'a' */
		// exponential
		Double a = 0.00;
		a = Math.pow(e, (sumY - (b*sumX))/N);
		
		// linear
		Double aLin = 0.00;
		aLin = (sumYlin*sumX2 - sumX*sumXYlin)/(N*sumX2 - sumX*sumX);
		
		/* Calculate coefficient of determination (R^2) */
		// exponential
		Double c = 0.00;	// numerator
		Double d = 0.00;	// denominator
		Double r = 0.00;	// coefficient of determination
		c = (b)*(sumXY - sumX*sumY/N);
		d = sumY2 - (sumY*sumY)/N;
		r = c/d;
		
		// linear
		Double cLin = 0.00;	// numerator
		Double dLin = 0.00;	// denominator
		Double rLin = 0.00;	// coefficient of determination
		cLin = (bLin)*(sumXYlin - sumX*sumYlin/N);
		dLin = sumY2lin - (sumYlin*sumYlin)/N;
		rLin = cLin/dLin;
		
		// Calculate coefficient of correlation
		// exponential
		Double p = 0.00;
		if(r > 0){
			p = Math.sqrt(r);
		} else {
			p = 0.00;
		}
		
		// linear
		Double pLin = 0.00;
		if(rLin > 0){
			pLin = Math.sqrt(rLin);
		} else {
			pLin = 0.00;
		}

		/* Calculate standard error
		 * equals (total variance - y variance)/(n-2)
		 */
		// exponential
		Double std_err = 0.00;
		std_err = Math.sqrt((d-c)/(N-2));
		
		// linear
		Double std_errLin = 0.00;
		std_errLin = Math.sqrt((dLin-cLin)/(N-2));
		
		/* print the model */
		// exponential
		System.out.println("Exponential regression model for n equals " + N + ":");
		System.out.println("y = " + a + "*(e^(" + b + "*x))");
		System.out.println("R-square value equals " + r);
		System.out.println("Correlation equals " + p);
		System.out.println("Standard Error equals " + std_err);
		System.out.println("");
		
		// linear
		System.out.println("Linear regression model for n equals " + N + ":");
		System.out.println("y = " + bLin + "x + " + aLin);
		System.out.println("R-square value equals " + rLin);
		System.out.println("Correlation equals " + pLin);
		System.out.println("Standard Error equals " + std_errLin);
		
	}
	
}
