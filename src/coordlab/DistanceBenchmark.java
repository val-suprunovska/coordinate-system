package coordlab;

import java.util.Random;

public class DistanceBenchmark {

    public static void main(String[] args) {
        int numberOfPoints = 100000; // Розмір масиву
        double[][] cartesianPoints = generateCartesianPoints(numberOfPoints);
        double[][] polarPoints = generatePolarPoints(numberOfPoints);
        double[][] sphericalPoints = generateSphericalPoints(numberOfPoints);

        // Бенчмаркінг для декартової системи
        long startTime = System.nanoTime();
        calculateCartesianDistances(cartesianPoints);
        long duration = System.nanoTime() - startTime;
        System.out.printf("Час виконання для декартової системи: %.2f мс%n", duration / 1_000_000.0);

        // Бенчмаркінг для полярної системи
        startTime = System.nanoTime();
        calculatePolarDistances(polarPoints);
        duration = System.nanoTime() - startTime;
        System.out.printf("Час виконання для полярної системи: %.2f мс%n", duration / 1_000_000.0);

        // Бенчмаркінг для сферичної системи
        startTime = System.nanoTime();
        calculateSphericalDistances(sphericalPoints);
        duration = System.nanoTime() - startTime;
        System.out.printf("Час виконання для сферичної системи: %.2f мс%n", duration / 1_000_000.0);
    }

    private static double[][] generateCartesianPoints(int n) {
        Random rand = new Random();
        double[][] points = new double[n][3];
        for (int i = 0; i < n; i++) {
            points[i][0] = rand.nextDouble() * 100; // x
            points[i][1] = rand.nextDouble() * 100; // y
            points[i][2] = rand.nextDouble() * 100; // z
        }
        return points;
    }

    private static double[][] generatePolarPoints(int n) {
        Random rand = new Random();
        double[][] points = new double[n][2];
        for (int i = 0; i < n; i++) {
            points[i][0] = rand.nextDouble() * 100; // r
            points[i][1] = rand.nextDouble() * 360; // theta в градусах
        }
        return points;
    }

    private static double[][] generateSphericalPoints(int n) {
        Random rand = new Random();
        double[][] points = new double[n][3];
        for (int i = 0; i < n; i++) {
            points[i][0] = rand.nextDouble() * 100; // r
            points[i][1] = rand.nextDouble() * 360; // theta в градусах
            points[i][2] = rand.nextDouble() * 180; // phi в градусах
        }
        return points;
    }

    private static void calculateCartesianDistances(double[][] points) {
        double totalDistance = 0;
        for (int i = 0; i < points.length - 1; i++) {
            double x1 = points[i][0];
            double y1 = points[i][1];
            double z1 = points[i][2];

            double x2 = points[i + 1][0];
            double y2 = points[i + 1][1];
            double z2 = points[i + 1][2];

            double distance = Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2) + Math.pow(z2 - z1, 2));
            totalDistance += distance;
        }
    }

    private static void calculatePolarDistances(double[][] points) {
        double totalDistance = 0;
        for (int i = 0; i < points.length - 1; i++) {
            double r1 = points[i][0];
            double theta1 = Math.toRadians(points[i][1]);

            double r2 = points[i + 1][0];
            double theta2 = Math.toRadians(points[i + 1][1]);

            double distance = Math.sqrt(Math.pow(r1, 2) + Math.pow(r2, 2) - 2 * r1 * r2 * Math.cos(theta2 - theta1));
            totalDistance += distance;
        }
    }

    private static void calculateSphericalDistances(double[][] points) {
        double totalDistance = 0;
        for (int i = 0; i < points.length - 1; i++) {
            double r1 = points[i][0];
            double theta1 = Math.toRadians(points[i][1]);
            double phi1 = Math.toRadians(points[i][2]);

            double r2 = points[i + 1][0];
            double theta2 = Math.toRadians(points[i + 1][1]);
            double phi2 = Math.toRadians(points[i + 1][2]);

            // Метод 1: через об'єм сфери
            double x1 = r1 * Math.sin(phi1) * Math.cos(theta1);
            double y1 = r1 * Math.sin(phi1) * Math.sin(theta1);
            double z1 = r1 * Math.cos(phi1);

            double x2 = r2 * Math.sin(phi2) * Math.cos(theta2);
            double y2 = r2 * Math.sin(phi2) * Math.sin(theta2);
            double z2 = r2 * Math.cos(phi2);

            double distanceVolume = Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2) + Math.pow(z2 - z1, 2));
            totalDistance += distanceVolume;
        }
    }
}
