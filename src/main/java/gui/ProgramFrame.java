package gui;

import domain.generators.PasswordGenerator;
import domain.generators.PasswordGeneratorImpl;
import domain.listeners.ListenerOverlayKey;
import domain.models.Alphabet;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Класс приложения
 *
 * @autor Pycukvsu
 */
public class ProgramFrame extends JFrame {
    private JPanel mainPanel;
    private JPanel panel2;
    private JButton button;
    private JTextField tfPhrase;
    private JTextArea taPassword1;
    private JTextArea taKeyHold;
    private JCheckBox cbUpperCase;
    private JCheckBox cbSpecialSigns;
    private JCheckBox cbNumbers;
    private JTextField thPasswordLength;
    private JButton jButton2;
    private final PasswordGenerator passwordGenerator = new PasswordGeneratorImpl();
    private Alphabet alphabet = new Alphabet();

    public void showKeyHold(String averageKeyHold) {
        taKeyHold.setText(averageKeyHold);
    }

    public ProgramFrame() {

        ListenerOverlayKey listenerKeyHold = new ListenerOverlayKey(ProgramFrame.this);
        tfPhrase.addKeyListener(listenerKeyHold);
        setContentPane(mainPanel);
        setTitle("Password Generator");
        setSize(320, 450);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String passwordLength;

                // Если длина пароля не указана, то по дефолту она будет равна 10
                if ((passwordLength = thPasswordLength.getText()).equals("")) {
                    passwordLength = "10";
                }

                alphabet.addNumbers(cbNumbers.isSelected());
                alphabet.addUpperCase(cbUpperCase.isSelected());
                alphabet.addPunctuationMarks(cbSpecialSigns.isSelected());
                taPassword1.setText(passwordGenerator.generatePassword(Integer.parseInt(passwordLength), alphabet));

                alphabet = new Alphabet();
            }
        });
        jButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // Обнуление счетчика
                listenerKeyHold.resetTheCounter();
                tfPhrase.setText("");
                taKeyHold.setText("");
            }
        });

    }
}
