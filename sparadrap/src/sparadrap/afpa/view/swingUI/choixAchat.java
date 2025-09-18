package sparadrap.afpa.view.swingUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class choixAchat extends JFrame {
    private JPanel contentPane;
    private JPanel logoChoixAchat;
    private JPanel logoMainAchat;
    private JPanel logoFooterAchat;
    private JButton achatDirect;
    private JButton achatOrdonnance;
    private JButton buttonQuitterChoix;
    private JButton retourChoixAchat;
    private JLabel titreChoixAchat;

    /**
     * Instantiates a new Choix achat.
     */
    public choixAchat() {
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

        achatDirect.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                typeAchatDirect();
            }
        });
        achatOrdonnance.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                typeAchatOrdonnance();
            }
        });
        retourChoixAchat.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                back();
            }
        });
        buttonQuitterChoix.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                quitter();
            }
        });
    }

    private void typeAchatDirect() {
        validationAchat validationAchat = new validationAchat("direct");
        validationAchat.setVisible(true);
    }

    private void typeAchatOrdonnance() {
        validationAchat validationAchat = new validationAchat("ordonnance");
        validationAchat.setVisible(true);
    }

    private void back() {
        this.dispose();
    }

    private void quitter() {
        int reponse = JOptionPane.showConfirmDialog(choixAchat.this, "Voulez-vous quitter l'application ?", "Quitter", JOptionPane.YES_NO_OPTION);
        if (reponse == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }
}
