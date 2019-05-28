package tk.dmitriikorenev.model;

import tk.dmitriikorenev.model.converters.Validator;
import tk.dmitriikorenev.model.converters.TemperatureConverter;

public class Model {
    private TemperatureConverter toKelvinConverter;
    private TemperatureConverter fromKelvinConverter;
    private Validator validator;

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

    public Validator getValidator() {
        return validator;
    }

    public void setValidator(Validator validator) {
        this.validator = validator;
    }

    public double convertTemperature(double inputValue) {
        double degrees = validator.validateInput(inputValue);
        degrees = toKelvinConverter.convertToKelvin(degrees);
        degrees = fromKelvinConverter.convertFromKelvin(degrees);
        return degrees;
    }
}
