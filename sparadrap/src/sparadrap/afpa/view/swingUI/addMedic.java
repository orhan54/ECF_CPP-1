package sparadrap.afpa.view.swingUI;

import sparadrap.afpa.model.Medicament;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class addMedic extends JFrame {

    private JPanel contentPane;
    private JLabel titreAddMedic;
    private JPanel footerAddMedic;
    private JPanel mainAddMedic;
    private JButton retourButton;
    private JButton quitterButton;
    private JComboBox comboBoxDetailsMedic;
    private JTable tableDetailsMedic;

    private DefaultTableModel tableModel;

    public addMedic() {

        ImageIcon imageIcon = new ImageIcon("C:\\Users\\User\\Desktop\\ECF-CPP1_CICEK_Orhan\\ECF-CPP-1\\sparadrap\\src\\sparadrap\\afpa\\image\\miniLogo.png");
        Dimension dimension = new Dimension(1600, 1000);

        //les attributs
        this.setTitle("Sparadrap");
        this.setIconImage(imageIcon.getImage());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setPreferredSize(dimension);
        this.setResizable(false);
        this.setContentPane(contentPane);

        String[] colonnes = {"Nom médicament", "Catégorie médicament", "Prix médicament", "Date mise en service", "Quantité médicament"};
        tableModel = new DefaultTableModel(colonnes, 0);
        tableDetailsMedic.setModel(tableModel);

        remplirComboBox();

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

    private void remplirComboBox() {
        comboBoxDetailsMedic.removeAllItems();
        comboBoxDetailsMedic.addItem("Voir le détails d'un médicament");
        comboBoxDetailsMedic.setSelectedIndex(0);

        for(Medicament medicament : Medicament.getMedicaments()) {
            comboBoxDetailsMedic.addItem(medicament.getNom());
        }

        comboBoxDetailsMedic.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                e.getSource();

                String selected = (String) comboBoxDetailsMedic.getSelectedItem();

                if(selected.equals(comboBoxDetailsMedic.getSelectedItem())) {
                    tableModel.setRowCount(0);

                    for(Medicament m : Medicament.getMedicaments()) {
                        if(m.getNom().equals(comboBoxDetailsMedic.getSelectedItem())) {
                            tableModel.addRow(new Object[] {
                                    m.getNom(),
                                    m.getCategorie(),
                                    m.getPrix() + "€",
                                    m.getDateMiseEnService(),
                                    m.getQuantite()
                            });
                        }
                    }
                }

            }
        });
    }

    private void retour() {
        this.dispose();
    }

    private void quitter() {
        int reponse = JOptionPane.showConfirmDialog(addMedic.this, "Voulez-vous quitter l'application ?", "Quitter", JOptionPane.YES_NO_OPTION);
        if (reponse == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }


}
