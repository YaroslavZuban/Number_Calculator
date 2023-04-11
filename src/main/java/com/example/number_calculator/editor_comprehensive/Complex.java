package com.example.number_calculator.editor_comprehensive;

public class Complex {
    private double real;
    private double imaginary;

    public Complex(double real, double imaginary) {
        this.real = real;
        this.imaginary = imaginary;
    }

    public Complex add(Complex other) {
        return new Complex(real + other.real, imaginary + other.imaginary);
    }

    public Complex subtract(Complex other) {
        return new Complex(real - other.real, imaginary - other.imaginary);
    }

    public Complex multiply(Complex other) {
        double newReal = real * other.real - imaginary * other.imaginary;
        double newImaginary = real * other.imaginary + imaginary * other.real;

        return new Complex(newReal, newImaginary);
    }

    public Complex divide(Complex other) {
        double denominator = other.real * other.real + other.imaginary * other.imaginary;
        double newReal = (real * other.real + imaginary * other.imaginary) / denominator;
        double newImaginary = (imaginary * other.real - real * other.imaginary) / denominator;

        return new Complex(newReal, newImaginary);
    }

    public Complex power(int exponent) {
        Complex result = new Complex(1, 0);

        for (int i = 0; i < exponent; i++) {
            result = result.multiply(this);
        }

        return result;
    }

    public Complex power(double exponent) {
        double modulus = Math.pow(modulus(), exponent);
        double argument = exponent * Math.atan2(imaginary, real);
        double newReal = modulus * Math.cos(argument);
        double newImaginary = modulus * Math.sin(argument);

        return new Complex(newReal, newImaginary);
    }

    public double modulus() {
        return Math.sqrt(real * real + imaginary * imaginary);
    }

    public String toString() {
        if (imaginary >= 0) {
            return real + " + " + imaginary + "i";
        } else {
            return real + " - " + (-imaginary) + "i";
        }
    }


    public double argumentInDegrees() {
        return Math.toDegrees(Math.atan2(imaginary, real));
    }

    public double argumentInRadians() {
        return Math.atan2(imaginary, real);
    }

    public Complex[] roots(int n) {
        Complex[] roots = new Complex[n];

        double theta = argumentInRadians();
        double modulus = modulus();

        for (int i = 0; i < n; i++) {
            double newTheta = (theta + 2 * i * Math.PI) / n;
            double newReal = Math.pow(modulus, 1.0 / n) * Math.cos(newTheta);
            double newImaginary = Math.pow(modulus, 1.0 / n) * Math.sin(newTheta);
            roots[i] = new Complex(newReal, newImaginary);
        }

        return roots;
    }
}
