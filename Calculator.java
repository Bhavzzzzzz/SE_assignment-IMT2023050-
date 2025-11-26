import java.util.Scanner;

public class Calculator {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean continueCalculating = true;

        System.out.println("=== Java CLI Calculator ===");

        while (continueCalculating) {
            try {
                System.out.println("\nEnter first number: ");
                double num1 = scanner.nextDouble();

                System.out.println("Enter operator (+, -, *, /): ");
                char operator = scanner.next().charAt(0);

                System.out.println("Enter second number: ");
                double num2 = scanner.nextDouble();

                // Call the helper method (This connects main to the logic)
                double result = calculate(num1, num2, operator);

                if (Double.isNaN(result)) {
                    System.out.println("Error: Invalid operation (e.g., Division by Zero).");
                } else {
                    System.out.printf("Result: %.2f %c %.2f = %.2f%n", num1, operator, num2, result);
                }

            } catch (Exception e) {
                System.out.println("Error: Invalid input.");
                scanner.nextLine(); 
            }

            System.out.println("\nCalculate again? (y/n): ");
            char choice = scanner.next().toLowerCase().charAt(0);
            if (choice != 'y') continueCalculating = false;
        }
        scanner.close();
    }

    // =========================================================
    // THIS IS THE METHOD YOU WERE MISSING
    // The AppTest needs this specific method to exist.
    // =========================================================
    public static double calculate(double num1, double num2, char operator) {
        switch (operator) {
            case '+': return num1 + num2;
            case '-': return num1 - num2;
            case '*': return num1 * num2;
            case '/': 
                if (num2 == 0) return Double.NaN; 
                return num1 / num2;
            default: return Double.NaN;
        }
    }

    // End of class
}