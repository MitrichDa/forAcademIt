package tk.dmitriikorenev.view;

import tk.dmitriikorenev.exceptions.InvalidTemperatureException;
import tk.dmitriikorenev.model.Model;
import tk.dmitriikorenev.model.converters.CelsiusTemperatureConverter;
import tk.dmitriikorenev.model.converters.FahrenheitTemperatureConverter;
import tk.dmitriikorenev.model.converters.KelvinTemperatureConverter;
import tk.dmitriikorenev.model.converters.TemperatureConverter;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionListener;

public class View {
    private final Model model;

    private JTextField inputField;
    private JTextField outputField;

    public View(Model model) {
        this.model = model;
    }

    public void init() {
        JFrame frame = new JFrame("Temperature");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(250, 220);
        frame.setLocationRelativeTo(null);

        JPanel inputPanel = getInputPanel();
        JPanel conversionTypesPanel = getConversionTypesPanel();
        JPanel resultPanel = getResultPanel();

        frame.getContentPane().add(BorderLayout.NORTH, inputPanel);
        frame.getContentPane().add(BorderLayout.CENTER, conversionTypesPanel);
        frame.getContentPane().add(BorderLayout.SOUTH, resultPanel);

        initModel();

        frame.setVisible(true);
    }

    private JPanel getInputPanel() {
        JLabel inputLabel = new JLabel("Значение");
        inputField = new JTextField(10);
        JPanel inputPanel = new JPanel();
        inputPanel.add(inputLabel);
        inputPanel.add(inputField);
        return inputPanel;
    }

    private JPanel getConversionTypesPanel() {
        JPanel conversionTypesPanel = new JPanel();

        JPanel fromScalePanel = getRadioPanel("Перевести из", true);
        JPanel toScalePanel = getRadioPanel("Перевести в", false);

        conversionTypesPanel.add(fromScalePanel);
        conversionTypesPanel.add(toScalePanel);

        return conversionTypesPanel;
    }

    private JPanel getRadioPanel(String title, boolean isFrom) {
        JRadioButton celsiusButton = new JRadioButton("C", true);
        JRadioButton fahrenheitButton = new JRadioButton("F");
        JRadioButton kelvinButton = new JRadioButton("K");

        celsiusButton.addActionListener(getRadioButtonListener(CelsiusTemperatureConverter.getInstance(), isFrom));
        fahrenheitButton.addActionListener(getRadioButtonListener(FahrenheitTemperatureConverter.getInstance(), isFrom));
        kelvinButton.addActionListener(getRadioButtonListener(KelvinTemperatureConverter.getInstance(), isFrom));

        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(celsiusButton);
        buttonGroup.add(fahrenheitButton);
        buttonGroup.add(kelvinButton);

        JPanel radioPanel = new JPanel();
        radioPanel.setBorder(new TitledBorder(title));

        BoxLayout boxLayout1 = new BoxLayout(radioPanel, BoxLayout.Y_AXIS);
        radioPanel.setPreferredSize(new Dimension(100, 100));
        radioPanel.setLayout(boxLayout1);
        radioPanel.add(fahrenheitButton);
        radioPanel.add(celsiusButton);
        radioPanel.add(kelvinButton);

        return radioPanel;
    }

    private JPanel getResultPanel() {
        JPanel resultPanel = new JPanel();
        JButton transfer = new JButton("перевести");

        transfer.addActionListener((event) -> {
            try {
                String result = model.convertTemperature(inputField.getText());
                outputField.setText(result);
            } catch (InvalidTemperatureException e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Введите корректное значение");
            }
        });

        outputField = new JTextField(10);
        outputField.setEditable(false);

        resultPanel.add(transfer);
        resultPanel.add(outputField);
        return resultPanel;
    }

    private void initModel() {
        model.setFromKelvinConverter(CelsiusTemperatureConverter.getInstance());
        model.setToKelvinConverter(CelsiusTemperatureConverter.getInstance());
        model.setValidator(CelsiusTemperatureConverter.getInstance());
    }

    private ActionListener getRadioButtonListener(TemperatureConverter converter, boolean isFrom) {
        if (isFrom) {
            return (event) -> {
                model.setToKelvinConverter(converter);
                model.setValidator(converter);
            };
        } else {
            return (event) -> model.setFromKelvinConverter(converter);
        }
    }
}
