package coordlab;

import java.util.Scanner;

public class CartToSph {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Введення кількості точок
        System.out.print("Введіть кількість точок: ");
        int n = scanner.nextInt();

        double[][] sphericalCoordinates = new double[n][3]; // [r, theta, phi]
        double[][] cartesianCoordinates = new double[n][3]; // [x, y, z]

        // Введення сферичних координат
        for (int i = 0; i < n; i++) {
            System.out.print("Введіть r для точки " + (i + 1) + ": ");
            sphericalCoordinates[i][0] = scanner.nextDouble();
            System.out.print("Введіть theta (в градусах) для точки " + (i + 1) + ": ");
            double thetaInDegrees = scanner.nextDouble();
            sphericalCoordinates[i][1] = Math.toRadians(thetaInDegrees); // Переведення в радіани
            System.out.print("Введіть phi (в градусах) для точки " + (i + 1) + ": ");
            double phiInDegrees = scanner.nextDouble();
            sphericalCoordinates[i][2] = Math.toRadians(phiInDegrees); // Переведення в радіани
        }

        // Переведення в декартову систему координат
        for (int i = 0; i < n; i++) {
            double r = sphericalCoordinates[i][0];
            double theta = sphericalCoordinates[i][1];
            double phi = sphericalCoordinates[i][2];

            cartesianCoordinates[i][0] = r * Math.sin(phi) * Math.cos(theta); // x
            cartesianCoordinates[i][1] = r * Math.sin(phi) * Math.sin(theta); // y
            cartesianCoordinates[i][2] = r * Math.cos(phi); // z

            System.out.printf("Точка A%d в декартовій системі: (%.2f, %.2f, %.2f)%n", (i + 1),
                    cartesianCoordinates[i][0], cartesianCoordinates[i][1], cartesianCoordinates[i][2]);
        }

        // Зворотнє переведення в сферичну систему координат
        for (int i = 0; i < n; i++) {
            double x = cartesianCoordinates[i][0];
            double y = cartesianCoordinates[i][1];
            double z = cartesianCoordinates[i][2];

            double r = Math.sqrt(x * x + y * y + z * z);
            double theta = Math.atan2(y, x); // Кут в радіанах
            double phi = Math.acos(z / r);

//            if (theta < 0) {
//                theta += 2 * Math.PI;
//            }

            // Переведення кутів в градуси для вивода
            double thetaInDegrees = Math.toDegrees(theta);
            double phiInDegrees = Math.toDegrees(phi);

            System.out.printf("Точка A%d в сферичній системі: (%.2f, %.2f, %.2f градусів)%n", (i + 1),
                    r, thetaInDegrees, phiInDegrees);
        }

        scanner.close();
    }
}
