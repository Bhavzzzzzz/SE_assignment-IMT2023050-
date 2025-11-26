public class AppTest {
    public static void main(String[] args) {
        System.out.println("------------------------------------------------");
        System.out.println("Running Automated Tests for Jenkins...");
        System.out.println("------------------------------------------------");

        int passed = 0;
        int failed = 0;

        // Test 1: Addition
        if (assertTest(10, 5, '+', 15.0)) passed++; else failed++;

        // Test 2: Subtraction
        if (assertTest(20, 3, '-', 17.0)) passed++; else failed++;

        // Test 3: Multiplication
        if (assertTest(5, 5, '*', 25.0)) passed++; else failed++;

        // Test 4: Division
        if (assertTest(100, 10, '/', 10.0)) passed++; else failed++;

        // Test 5: Division by Zero Logic
        // IMPORTANT: Ensure this calls Calculator.calculate
        double resultDivZero = Calculator.calculate(10, 0, '/');
        if (Double.isNaN(resultDivZero)) {
            System.out.println("[PASS] Division by zero correctly returned NaN");
            passed++;
        } else {
            System.out.println("[FAIL] Division by zero. Expected: NaN, Got: " + resultDivZero);
            failed++;
        }

        System.out.println("------------------------------------------------");
        System.out.println("Result: " + passed + " Passed, " + failed + " Failed");

        if (failed > 0) {
            System.out.println("BUILD FAILURE");
            System.exit(1);
        } else {
            System.out.println("BUILD SUCCESS");
            System.exit(0);
        }
    }

    public static boolean assertTest(double n1, double n2, char op, double expected) {
        // IMPORTANT: Ensure this calls Calculator.calculate
        double result = Calculator.calculate(n1, n2, op);
        
        if (Math.abs(result - expected) < 0.0001) {
            System.out.printf("[PASS] %.2f %c %.2f = %.2f%n", n1, op, n2, result);
            return true;
        } else {
            System.out.printf("[FAIL] %.2f %c %.2f. Expected: %.2f, Got: %.2f%n", n1, op, n2, expected, result);
            return false;
        }
    }
}