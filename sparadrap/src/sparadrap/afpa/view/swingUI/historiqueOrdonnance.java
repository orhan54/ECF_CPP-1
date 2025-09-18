package sparadrap.afpa.view.swingUI;

import sparadrap.afpa.exception.SaisieException;
import sparadrap.afpa.model.Medecin;
import sparadrap.afpa.model.Ordonnance;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.stream.Collectors;


public class historiqueOrdonnance extends JFrame {
    private JPanel contentPane;
    private JLabel titreMenu;
    private JComboBox comboBoxOrdoMedecin;
    private JTable tableOrdo;
    private JButton retourButton;
    private JButton quitterButton;
    private String selectedMedecin;

    private DefaultTableModel tableModelOrdo;

    public historiqueOrdonnance() throws SaisieException {
        ImageIcon imageIcon = new ImageIcon("C:\\Users\\User\\Desktop\\ECF-CPP1_CICEK_Orhan\\ECF-CPP-1\\sparadrap\\src\\sparadrap\\afpa\\image\\miniLogo.png");
        Dimension dimension = new Dimension(1600, 1000);

        this.setTitle("Sparadrap - Historique des Achats");
        this.setIconImage(imageIcon.getImage());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setPreferredSize(dimension);
        this.setResizable(false);
        this.setContentPane(contentPane);

        remplirComboBox();

        String[] colonnes = {"Date", "Nom du médecin", "Nom du patient", "Liste des médicaments"};
        tableModelOrdo = new DefaultTableModel(colonnes, 0);
        tableOrdo.setModel(tableModelOrdo);

        this.pack();
        this.setLocationRelativeTo(null);

        retourButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                retour();
            }
        });
        quitterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                quitter();
            }
        });
    }

    private void remplirComboBox() throws SaisieException {
        comboBoxOrdoMedecin.removeAllItems();
        comboBoxOrdoMedecin.addItem("Choisir le médecin");
        comboBoxOrdoMedecin.setSelectedItem(0);

        for(Medecin m : Medecin.getMedecins()) {
            comboBoxOrdoMedecin.addItem(m.getNom() + " " + m.getPrenom());
        }

        comboBoxOrdoMedecin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selected = (String)  comboBoxOrdoMedecin.getSelectedItem();
                selectedMedecin = selected;
                tableModelOrdo.setRowCount(0);

                for(Ordonnance ordo : Ordonnance.getOrdonnances()) {
                    if(selectedMedecin.equals(ordo.getNomMedecin())) {

                        String medicamentStr = ordo.getMedicaments().stream()
                                .map(m -> m.getNom())
                                .collect(Collectors.joining(", "));
                                // System.out.println(medicamentStr);

                        tableModelOrdo.addRow(new Object[]{
                                ordo.getDate(),
                                ordo.getNomMedecin(),
                                ordo.getNomPatient(),
                                medicamentStr // nom et quantités
                        });

                    }else if (false){
                        System.out.println("Le médecin " + ordo.getNomMedecin() + " n'a pas encore d'ordonnance !");
                    }
                }
            }
        });
    }

    private void retour() {
        this.dispose();
    }

    private void quitter() {
        int reponse = JOptionPane.showConfirmDialog(historiqueOrdonnance.this, "Voulez-vous quitter l'application ?", "Quitter", JOptionPane.YES_NO_OPTION);
        if (reponse == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }
}
