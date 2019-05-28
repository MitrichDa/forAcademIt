package tk.dmitriikorenev.model.converters;

import tk.dmitriikorenev.exceptions.InvalidTemperatureException;

public class CelsiusTemperatureConverter implements TemperatureConverter {
    private static final double CELSIUS_TO_KELVIN = 273.15;

    private static CelsiusTemperatureConverter converter = null;

    private CelsiusTemperatureConverter() {
    }

    public static CelsiusTemperatureConverter getInstance() {
        if (converter == null) {
            converter = new CelsiusTemperatureConverter();
        }
        return converter;
    }

    @Override
    public double convertToKelvin(double degrees) {
        return degrees + CELSIUS_TO_KELVIN;
    }

    @Override
    public double convertFromKelvin(double degrees) {
        return degrees - CELSIUS_TO_KELVIN;
    }

    @Override
    public double validateInput(double inputValue) {
        if (inputValue < -CELSIUS_TO_KELVIN) {
            throw new InvalidTemperatureException("Неверная температура в градусах Цельсия");
        }
        return inputValue;
    }
}
