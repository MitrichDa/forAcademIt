package tk.dmitriikorenev.model.converters;

public interface TemperatureConverter extends Validator {
    double convertToKelvin(double degrees);

    double convertFromKelvin(double degrees);
}
