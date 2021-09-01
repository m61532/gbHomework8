package ru.geebrains.lesson8;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GeekbrainsWindow extends JFrame {
    private int randomNumber;
    private JTextField textField;
    private int attemptCounter = 3;
    private boolean endOfGame = false;

    public GeekbrainsWindow() {
        this.randomNumber = (int) (Math.random() * 10) + 1; // [1, 10]

        setTitle("Игра: Угадай число");
        setBounds(600, 300, 600, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        //setLayout(new FlowLayout());
        setResizable(false);

        textField = new JTextField();
        textField.setText("Программа загадала число от 1 до 10");
        textField.setEditable(false);
        textField.setHorizontalAlignment(SwingConstants.CENTER);
        Font font = new Font("Arial", Font.PLAIN, 20);
        textField.setFont(font);
        add(textField, BorderLayout.NORTH);

        JPanel buttonsPanel = new JPanel(new GridLayout(1, 11));
        buttonsPanel.setBackground(Color.BLUE);
        add(buttonsPanel, BorderLayout.CENTER);

        for (int i = 1; i <= 10; i++) {
            JButton button = new JButton(String.valueOf(i));
            button.setFont(font);
            buttonsPanel.add(button);
            int buttonIndex = i;
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    tryToAnswer(buttonIndex);
                }
            });
        }

        JButton reset = new JButton("reset");
        buttonsPanel.add(reset);
        reset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reset();
            }
        });

        setVisible(true);
    }

    public void reset() {
        attemptCounter = 3;
        randomNumber = (int) (Math.random() * 10) + 1;
        endOfGame = false;
        textField.setText("Программа загадала новое число от 1 до 10");
    }

    public void tryToAnswer(int answer) {
        if (attemptCounter <= 0) {
            reset();
            return;
        }
        if(answer < randomNumber & !endOfGame) {
            if (attemptCounter > 1){
            textField.setText("Не угадали! Загаданное число больше! Осталось попыток (" + attemptCounter + ")");
            attemptCounter--;}
            else {
                textField.setText("Не угадали! Попыток не осталось. Нажмите reset");
//                attemptCounter--;
                endOfGame = true;
            }
            return;
        }
        if(answer > randomNumber & !endOfGame) {
            if (attemptCounter > 1) {
            textField.setText("Не угадали! Загаданное число меньше Осталось попыток (" + attemptCounter + ")");
            attemptCounter--; }
            else {
                textField.setText("Не угадали! Попыток не осталось. Нажмите reset");
                endOfGame = true;
//                attemptCounter--;
            }
            return;
        }
        if (answer == randomNumber & !endOfGame) {
            textField.setText("Вы угадали!!! Ответ: " + randomNumber + " Нажмите reset");
            endOfGame = true;
//            attemptCounter = 0;
        }
    }
}
