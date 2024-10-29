package coordlab;

import java.util.Scanner;

public class PolToCart {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Введення полярних координат
        System.out.print("Введіть кількість точок: ");
        int n = scanner.nextInt();

        double[][] polarCoordinates = new double[n][2]; // [r, theta]

        for (int i = 0; i < n; i++) {
            System.out.print("Введіть r для точки " + (i + 1) + ": ");
            polarCoordinates[i][0] = scanner.nextDouble();
            System.out.print("Введіть theta (в градусах) для точки " + (i + 1) + ": ");
            double thetaInDegrees = scanner.nextDouble();
            // Переводжу градуси в радіани
            polarCoordinates[i][1] = Math.toRadians(thetaInDegrees);

        }

        // Переводжу в декартову систему координат
        double[][] cartesianCoordinates = new double[n][2]; // [x, y]

        for (int i = 0; i < n; i++) {
            double r = polarCoordinates[i][0];
            double theta = polarCoordinates[i][1];
            cartesianCoordinates[i][0] = r * Math.cos(theta); // x
            cartesianCoordinates[i][1] = r * Math.sin(theta); // y
            System.out.printf("Точка A%d в декартовій системі: (%.2f, %.2f)%n", (i + 1), cartesianCoordinates[i][0], cartesianCoordinates[i][1]);
        }

        // Зворотнє переведення в полярну систему координат
        for (int i = 0; i < n; i++) {
            double x = cartesianCoordinates[i][0];
            double y = cartesianCoordinates[i][1];
            double r = Math.sqrt(x * x + y * y);
            double theta = Math.atan2(y, x);

//            if (theta < 0) {
//                theta += 2 * Math.PI; // Переводжу кут у додатний стан
//            }

            // Переводжу радіани в градуси для виводу
            double thetaInDegrees = Math.toDegrees(theta);
            System.out.printf("Точка A%d в полярній системі: (%.2f, %.2f градусів)%n", (i + 1), r, thetaInDegrees);
        }

        scanner.close();
    }
}
