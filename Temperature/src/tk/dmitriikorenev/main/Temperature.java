package tk.dmitriikorenev.main;

import tk.dmitriikorenev.model.Model;
import tk.dmitriikorenev.view.View;

public class Temperature {
    public static void main(String[] args) {
        Model model = new Model();
        new View(model);
    }
}
