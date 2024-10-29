package coordlab;

import java.util.Scanner;

public class DistanceCalculator {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Декартова система координат (2D)
        System.out.print("Введіть x1, y1 для першої точки: \n");
        double x1 = scanner.nextDouble();
        double y1 = scanner.nextDouble();
        System.out.println("Введіть x2, y2 для второй точки: ");
        double x2 = scanner.nextDouble();
        double y2 = scanner.nextDouble();

        double distance2D = Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
        System.out.printf("Відстань в 2D: %.2f%n", distance2D);

        // Декартова система координат (3D)
        System.out.println("\nВведіть z1 для першої точки: ");
        double z1 = scanner.nextDouble();
        System.out.println("Введіть z2 для другої точки: ");
        double z2 = scanner.nextDouble();

        double distance3D = Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2) + Math.pow(z2 - z1, 2));
        System.out.printf("Відстань в 3D: %.2f%n", distance3D);

        // Полярна система координат
        System.out.println("\nВведіть r1, theta1 (в градусах) для першої точки: ");
        double r1 = scanner.nextDouble();
        double theta1Degrees = scanner.nextDouble();
        System.out.println("Введіть r2, theta2 (в градусах) для другої точки: ");
        double r2 = scanner.nextDouble();
        double theta2Degrees = scanner.nextDouble();

        double theta1 = Math.toRadians(theta1Degrees);
        double theta2 = Math.toRadians(theta2Degrees);
        double polarDistance = Math.sqrt(Math.pow(r1, 2) + Math.pow(r2, 2) - 2 * r1 * r2 * Math.cos(theta2 - theta1));
        System.out.printf("Відстань в полярній системі: %.2f%n", polarDistance);

        // Сферична система координат
        System.out.println("\nВведіть r1, theta1 (в градусах), phi1 (в градусах) для першої точки: ");
        r1 = scanner.nextDouble();
        theta1Degrees = scanner.nextDouble();
        double phi1Degrees = scanner.nextDouble();
        System.out.println("Введіть r2, theta2 (в градусах), phi2 (в градусах) для другої точки: ");
        r2 = scanner.nextDouble();
        theta2Degrees = scanner.nextDouble();
        double phi2Degrees = scanner.nextDouble();

        // Переводжу в радіани
        double theta1Rad = Math.toRadians(theta1Degrees);
        double phi1Rad = Math.toRadians(phi1Degrees);
        double theta2Rad = Math.toRadians(theta2Degrees);
        double phi2Rad = Math.toRadians(phi2Degrees);

        // Метод 1: через об'єм сфери
        double x1_s = r1 * Math.sin(phi1Rad) * Math.cos(theta1Rad);
        double y1_s = r1 * Math.sin(phi1Rad) * Math.sin(theta1Rad);
        double z1_s = r1 * Math.cos(phi1Rad);

        double x2_s = r2 * Math.sin(phi2Rad) * Math.cos(theta2Rad);
        double y2_s = r2 * Math.sin(phi2Rad) * Math.sin(theta2Rad);
        double z2_s = r2 * Math.cos(phi2Rad);

        double distanceSphereVolume = Math.sqrt(Math.pow(x2_s - x1_s, 2) + Math.pow(y2_s - y1_s, 2) + Math.pow(z2_s - z1_s, 2));
        System.out.printf("Відстань через об'єм сфери: %.2f%n", distanceSphereVolume);

        // Метод 2: по поверхні сфери
        double deltaSigma = Math.acos(Math.sin(phi1Rad) * Math.sin(phi2Rad) +
                Math.cos(phi1Rad) * Math.cos(phi2Rad) * Math.cos(theta2Rad - theta1Rad));
        double distanceSphereSurface = r1 * deltaSigma; // Якщо r1 и r2 однакові
        System.out.printf("Відстань по поверхні сфери: %.2f%n", distanceSphereSurface);

        scanner.close();
    }
}
