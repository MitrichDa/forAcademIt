package tk.dmitriikorenev.model.converters;

public interface TemperatureConverter extends StringValidator {
    double convertToKelvin(double degrees);

    double convertFromKelvin(double degrees);
}
