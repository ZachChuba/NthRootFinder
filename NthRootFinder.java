import java.util.Scanner;

public class NthRootFinder {
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);

		// Read n (for taking the nth root)
		int n = Integer.parseInt(sc.nextLine());

		// Read number to take the nth root of
		int number = Integer.parseInt(sc.nextLine());

		// Read the desired precision
		int precision = Integer.parseInt(sc.nextLine());

		// Print the answer
		System.out.println(findNthRoot(number, n, precision));
		

	}

	private static String findNthRoot(int number, int n, int precision) {
		 /*
		 * Given a int number, the number being rooted, int n, the power by which it is
		 * being rooted and int precision the number of decimals the root should be
		 * precise to Returns a string of the nth root of number to precision decimal
		 * places
		 */
		double upperBound = number;
		double lowerBound = 0.0;
		double midPoint;
		int numberIterations = 0;
		
		while (true) {
			numberIterations++;
			midPoint = (upperBound - lowerBound) / 2 + lowerBound;
			int numberOfCharsBeforeDecimalPoint = numberOfCharsBeforeDecimal(midPoint);
			double doubleValue = power(midPoint, n);
			doubleValue = Math.ceil((doubleValue*100)/100);
			System.out.println(Double.parseDouble(
					String.format("%." + Integer.toString(precision) + "f",
							power(midPoint, n))) + " " + midPoint);
							
			if (Double.parseDouble(
					String.format("%." + Integer.toString(precision) + "f",
							power(midPoint, n))) == number) { //if the midpoint^n == number, returns midpoint
				return Integer.toString(numberIterations);
						/*String.format("%." + Integer.toString(precision + numberOfCharsBeforeDecimalPoint) + "g",
						midPoint)*/
			} else if (power(midPoint, n) > number) {
				upperBound = midPoint;
			} else {
				lowerBound = midPoint;
			}
		}
	}

	private static double power(double n, int rootNumber) {
		/*
		 * Given int n, the number to be raised to a power And int rootNumber, the power
		 * it is to be raised to Returns number to that power
		 */
		double result = n;
		for (int i = 1; i < rootNumber; i++) {
			result *= n;
		}
		return result;
	}

	private static int numberOfCharsBeforeDecimal(double number) {
		/*
		 * Given a number with a decimal point
		 * Returns the number of digits in the number before the decimal point
		 */
		String numberConvertedToString = Double.toString(number);
		char[] numberAsCharArray = numberConvertedToString.toCharArray();
		int accumulateBeforeDecimal = 0;
		for (char digit : numberAsCharArray) {
			if (digit == '.') {
				return accumulateBeforeDecimal;
			}
			++accumulateBeforeDecimal;
		}
		return -1;
	}

	private static int[][] runTests() {
		
		int[][] attempts = new int[254][2];
		for (int i = 2; i < 256; i++) {
			attempts[i-2][0] = i;
			attempts[i-2][1] = Integer.parseInt(findNthRoot(i, 2, 1));
			//System.out.println("Finished one " + i);
		}
		return attempts;
	}
}
	// private static String findNthRoot(int number, int n, int precision) {
	// /*
	// * Given a int number, the number being rooted, int n, the power by which is
	// it
	// * being rooted and int precision the number of decimals the root should be
	// * precise to Returns a string of the nth root of number to precision decimal
	// * places
	// */
	// // Number = number to take nth root, n = nth root, precision = number of
	// // decimals accurate
	// // Approach is binary search
	// double upperBound = number;
	// double lowerBound = 0.0;
	// double midPoint = (upperBound - lowerBound) / 2;
	// while (true) {
	// String approximateValue = round(power(midPoint, n), precision);
	// double approximateValueDouble = Double.parseDouble(round(power(midPoint, n),
	// precision));
	// if (approximateValueDouble == number) {
	// return round(midPoint, precision);
	// }
	// if (approximateValueDouble > number) {
	// upperBound = midPoint;
	// } else {
	// lowerBound = midPoint;
	// }
	// midPoint = (upperBound - lowerBound) / 2.0 + lowerBound;
	// System.out.println(midPoint);
	// System.out.println("Value: " + approximateValueDouble);
	// }
	// }
	//
	//
	// private static String round(double number, int numPlaces) {
	// /*
	// * Given Integer number, the number to be rounded And int numPlaces, the
	// number
	// * of decimal places to round the integer to Returns the integer rounded to
	// that
	// * decimal place
	// */
	// String stringNumber = number + "";
	// char[] charNumber = stringNumber.toCharArray();
	// int placesCount = 0;
	// boolean afterDecimalState = false;
	// String stringOfNumber = "";
	// String result = "";
	// for (char charac : charNumber) {//identifies numbers too large and doesn't
	// round
	// if (charac == 'E') {
	// return stringNumber;
	// }
	// }
	//
	// for (int i = 0; i < charNumber.length; i++) {
	// char character = charNumber[i];
	//
	// stringOfNumber += character;
	// if (character == '.') {
	// afterDecimalState = true;
	// continue;
	// }
	// if (afterDecimalState) {
	// placesCount++;
	// }
	// if (placesCount == numPlaces + 1) { // includes number determining what it's
	// rounded to
	// break;
	// }
	// }
	// for (int i = placesCount; i < numPlaces + 1; i++) { // only executes if above
	// loop terminated too early.
	// // Includes determinant
	// stringOfNumber += "0";
	// }
	// char[] finalNumberDeterminant = stringOfNumber.toCharArray();
	// int lastCharacterToInt = Integer
	// .parseInt(Character.toString(finalNumberDeterminant[finalNumberDeterminant.length
	// - 1]));
	// int secondToLastCharacterToInt = Integer
	// .parseInt(Character.toString(finalNumberDeterminant[finalNumberDeterminant.length
	// - 2]));
	// if (lastCharacterToInt > 5 && secondToLastCharacterToInt < 9) {
	// finalNumberDeterminant[finalNumberDeterminant.length - 2] = (char) ((Integer
	// .parseInt(Character.toString(finalNumberDeterminant[finalNumberDeterminant.length
	// - 2])) + 49));
	// // 49 is added since apparently casting char to an integer actually returns
	// // start-of-heading unless you add the ascii value of the first char
	// } else if (lastCharacterToInt > 5 && secondToLastCharacterToInt == 9) {
	// //need to round the next number up if 9
	// int i = 2;
	// for (; i <= finalNumberDeterminant.length
	// && (finalNumberDeterminant[finalNumberDeterminant.length - i] == '9'
	// || finalNumberDeterminant[finalNumberDeterminant.length - i] == '.'); i++) {
	// if (finalNumberDeterminant[finalNumberDeterminant.length - i] == '9') {
	// finalNumberDeterminant[finalNumberDeterminant.length - i] = '0';
	// }
	// }
	// finalNumberDeterminant[finalNumberDeterminant.length - (i-1)] = (char)
	// ((Integer
	// .parseInt(Character.toString(finalNumberDeterminant[finalNumberDeterminant.length
	// - i])) + 49));
	// }
	// finalNumberDeterminant[finalNumberDeterminant.length - 1] = ' ';
	// for (char c : finalNumberDeterminant) { // converts the final value to a
	// returnable string
	// result += c;
	// }
	// return result.trim();
	// }
