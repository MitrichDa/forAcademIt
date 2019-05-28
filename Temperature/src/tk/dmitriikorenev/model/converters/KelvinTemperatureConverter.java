package tk.dmitriikorenev.model.converters;

import tk.dmitriikorenev.exceptions.InvalidTemperatureException;

public class KelvinTemperatureConverter implements TemperatureConverter {
    private static KelvinTemperatureConverter converter = null;

    private KelvinTemperatureConverter() {
    }

    public static KelvinTemperatureConverter getInstance() {
        if (converter == null) {
            converter = new KelvinTemperatureConverter();
        }
        return converter;
    }

    @Override
    public double convertToKelvin(double degrees) {
        return degrees;
    }

    @Override
    public double convertFromKelvin(double degrees) {
        return degrees;
    }

    @Override
    public double validateInput(double inputValue) {
        if (inputValue < 0) {
            throw new InvalidTemperatureException("Неверная температура в градусах Кельвина");
        }
        return inputValue;
    }
}
