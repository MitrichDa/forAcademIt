package tk.dmitriikorenev.model;

import tk.dmitriikorenev.model.converters.StringValidator;
import tk.dmitriikorenev.model.converters.TemperatureConverter;

public class Model {
    private TemperatureConverter toKelvinConverter;
    private TemperatureConverter fromKelvinConverter;
    private StringValidator validator;

    public TemperatureConverter getToKelvinConverter() {
        return toKelvinConverter;
    }

    public void setToKelvinConverter(TemperatureConverter toKelvinConverter) {
        this.toKelvinConverter = toKelvinConverter;
    }

    public TemperatureConverter getFromKelvinConverter() {
        return fromKelvinConverter;
    }

    public void setFromKelvinConverter(TemperatureConverter fromKelvinConverter) {
        this.fromKelvinConverter = fromKelvinConverter;
    }

    public StringValidator getValidator() {
        return validator;
    }

    public void setValidator(StringValidator validator) {
        this.validator = validator;
    }

    public String convertTemperature(String inputValue) {
        double degrees = validator.validateInput(inputValue);
        degrees = toKelvinConverter.convertToKelvin(degrees);
        degrees = fromKelvinConverter.convertFromKelvin(degrees);
        return String.format("%.2f", degrees);

    }
}
