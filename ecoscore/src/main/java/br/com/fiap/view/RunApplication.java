package br.com.fiap.view;

import br.com.fiap.view.gui.GUIMainView;

import javax.swing.*;
import java.awt.*;

public class RunApplication {
    public static void main(String[] args) {
        GUIMainView frame = new GUIMainView();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension tela = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation((tela.width - frame.getSize().width) / 2,
                (tela.height - frame.getSize().height) / 2);
        frame.setVisible(true);
    }
}
