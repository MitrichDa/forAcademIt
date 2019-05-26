package tk.dmitriikorenev.view;

import tk.dmitriikorenev.enums.DegreesType;
import tk.dmitriikorenev.exceptions.InvalidTemperatureException;
import tk.dmitriikorenev.model.Model;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionListener;

public class View {
    private final Model model;

    private JTextField inputField;
    private JTextField outputField;
    private DegreesType fromType = DegreesType.CELSIUS;
    private DegreesType toType = DegreesType.CELSIUS;

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

        ActionListener radioButtonListener = getRadioButtonListener(celsiusButton, kelvinButton, fahrenheitButton, isFrom);

        celsiusButton.addActionListener(radioButtonListener);
        fahrenheitButton.addActionListener(radioButtonListener);
        kelvinButton.addActionListener(radioButtonListener);

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
                String result = model.convertTemperature(inputField.getText(), fromType, toType);
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

    private ActionListener getRadioButtonListener(JRadioButton celsiusButton, JRadioButton kelvinButton, JRadioButton fahrenheitButton, boolean isFrom) {
        if (isFrom) {
            return (event) -> {
                JRadioButton button = (JRadioButton) event.getSource();
                if (button == celsiusButton) {
                    fromType = DegreesType.CELSIUS;
                } else if (button == kelvinButton) {
                    fromType = DegreesType.KELVIN;
                } else if (button == fahrenheitButton) {
                    fromType = DegreesType.FAHRENHEIT;
                }
            };
        } else {
            return (event) -> {
                JRadioButton button = (JRadioButton) event.getSource();
                if (button == celsiusButton) {
                    toType = DegreesType.CELSIUS;
                } else if (button == kelvinButton) {
                    toType = DegreesType.KELVIN;
                } else if (button == fahrenheitButton) {
                    toType = DegreesType.FAHRENHEIT;
                }
            };
        }
    }
}
