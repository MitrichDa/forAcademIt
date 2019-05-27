package tk.dmitriikorenev.model.converters;

import tk.dmitriikorenev.exceptions.InvalidTemperatureException;

public class FahrenheitTemperatureConverter implements TemperatureConverter {
    private static final double KELVIN_TO_FAHRENHEIT1 = 459.67;
    private static final double KELVIN_TO_FAHRENHEIT2 = 1.8;

    private static FahrenheitTemperatureConverter converter = null;

    private FahrenheitTemperatureConverter() {
    }

    public static FahrenheitTemperatureConverter getInstance() {
        if (converter == null)
            converter = new FahrenheitTemperatureConverter();

        return converter;
    }

    @Override
    public double convertToKelvin(double degrees) {
        return (degrees + KELVIN_TO_FAHRENHEIT1) / KELVIN_TO_FAHRENHEIT2;
    }

    @Override
    public double convertFromKelvin(double degrees) {
        return degrees * KELVIN_TO_FAHRENHEIT2 - KELVIN_TO_FAHRENHEIT1;
    }

    @Override
    public double validateInput(String stringValue) {
        double value = Double.parseDouble(stringValue);
        if (value < -KELVIN_TO_FAHRENHEIT1) {
            throw new InvalidTemperatureException("Неверная температура в градусах Фаренгейта");
        }
        return value;
    }
}
