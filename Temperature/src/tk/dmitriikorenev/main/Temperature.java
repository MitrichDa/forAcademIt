package tk.dmitriikorenev.main;

import tk.dmitriikorenev.model.Model;
import tk.dmitriikorenev.view.View;

import javax.swing.*;

public class Temperature {
    public static void main(String[] args) {
        Model model = new Model();
        SwingUtilities.invokeLater(() -> {
            View view = new View(model);
            view.init();
        });
    }
}
