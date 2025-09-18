package sparadrap.afpa.view.swingUI;

import sparadrap.afpa.exception.SaisieException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu extends JFrame {
    private JPanel contentPane;
    private JPanel logoMenu;
    private JPanel mainMenu;
    private JPanel footerMenu;
    private JButton buttonAchatMenu;
    private JButton buttonHistoriqueMenu;
    private JButton buttonAddMedecinMenu;
    private JButton buttonAddClientMenu;
    private JLabel titreMenu;
    private JButton buttonQuitterMenu;
    private JButton buttonHistoriqueOrdo;
    private JButton buttonAddMedic;

    public Menu() {

        ImageIcon imageIcon = new ImageIcon("C:\\Users\\User\\Desktop\\ECF-CPP1_CICEK_Orhan\\ECF-CPP-1\\sparadrap\\src\\sparadrap\\afpa\\image\\miniLogo.png");
        Dimension dimension = new Dimension(1600, 1000);

        //les attributs
        this.setTitle("Sparadrap");
        this.setIconImage(imageIcon.getImage());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setPreferredSize(dimension);
        this.setResizable(false);
        this.setContentPane(contentPane);

        this.pack();
        this.setLocationRelativeTo(null);

        buttonAchatMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayTypeAchat();
            }
        });

        buttonHistoriqueMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayHistoriqueAchat();
            }
        });

        buttonAddMedecinMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displaymedecin();
            }
        });

        buttonAddClientMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    displayAddClient();
                } catch (SaisieException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        buttonQuitterMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                quitter();
            }
        });

        buttonHistoriqueOrdo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                historiqueOrdonnance historiqueOrdonnance = null;
                try {
                    historiqueOrdonnance = new historiqueOrdonnance();
                } catch (SaisieException ex) {
                    throw new RuntimeException(ex);
                }
                historiqueOrdonnance.setVisible(true);
            }
        });
        buttonAddMedic.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    displayAddMedic();
                } catch (SaisieException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }

    private void displayTypeAchat() {
        choixAchat choixAchat = new choixAchat();
        choixAchat.setVisible(true);
    }

    private void displayHistoriqueAchat() {
        historiqueAchat historiqueAchat = new historiqueAchat();
        historiqueAchat.setVisible(true);
    }

    private void displaymedecin() {
        consulterMedecin consulterMedecin = new consulterMedecin();
        consulterMedecin.setVisible(true);
    }

    private void displayAddClient() throws SaisieException {
        consulterClient consulterClient = new consulterClient();
        consulterClient.setVisible(true);
    }

    private void displayAddMedic() throws SaisieException {
        addMedic addMedic = new addMedic();
        addMedic.setVisible(true);
    }

    private void quitter() {
        int reponse = JOptionPane.showConfirmDialog(Menu.this, "Voulez-vous quitter l'application ?", "Quitter", JOptionPane.YES_NO_OPTION);
        if (reponse == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }
}
