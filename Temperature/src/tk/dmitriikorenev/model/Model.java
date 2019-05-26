package tk.dmitriikorenev.model;

import tk.dmitriikorenev.enums.DegreesType;
import tk.dmitriikorenev.exceptions.InvalidTemperatureException;

public class Model {
    private static final double CELSIUS_TO_KELVIN = 273.15;
    private static final double KELVIN_TO_FAHRENHEIT1 = 459.67;
    private static final double KELVIN_TO_FAHRENHEIT2 = 1.8;

    public String convertTemperature(String stringValue, DegreesType fromType, DegreesType toType) {
        double degrees = validateInput(stringValue, fromType);
        degrees = convertToKelvin(degrees, fromType);
        degrees = convertFromKelvin(degrees, toType);
        return String.format("%.2f", degrees);
    }

    private double validateInput(String stringValue, DegreesType fromType) {
        double value = Double.parseDouble(stringValue);
        if (fromType == DegreesType.KELVIN) {
            if (value < 0) {
                throw new InvalidTemperatureException("Неверная температура в градусах Кельвина");
            }
        } else if (fromType == DegreesType.CELSIUS) {
            if (value < -CELSIUS_TO_KELVIN) {
                throw new InvalidTemperatureException("Неверная температура в градусах Цельсия");
            }
        } else if (value < -KELVIN_TO_FAHRENHEIT1) {
            throw new InvalidTemperatureException("Неверная температура в градусах Фаренгейта");
        }

        return value;
    }

    private double convertToKelvin(double degrees, DegreesType fromType) {
        if (fromType == DegreesType.CELSIUS) {
            return degrees + CELSIUS_TO_KELVIN;
        }
        if (fromType == DegreesType.FAHRENHEIT) {
            return (degrees + KELVIN_TO_FAHRENHEIT1) / KELVIN_TO_FAHRENHEIT2;
        }
        return degrees;
    }

    private double convertFromKelvin(double degrees, DegreesType toType) {
        if (toType == DegreesType.CELSIUS) {
            return degrees - CELSIUS_TO_KELVIN;
        }
        if (toType == DegreesType.FAHRENHEIT) {
            return degrees * KELVIN_TO_FAHRENHEIT2 - KELVIN_TO_FAHRENHEIT1;
        }
        return degrees;
    }
}
