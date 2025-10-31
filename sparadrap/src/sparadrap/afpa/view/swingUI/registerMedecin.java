package sparadrap.afpa.view.swingUI;

import sparadrap.afpa.exception.SaisieException;
import sparadrap.afpa.model.Lieu;
import sparadrap.afpa.model.Medecin;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static java.lang.Integer.parseInt;

public class registerMedecin extends JFrame {
    private JPanel contentPane;
    private JPanel titreRegisterMedecin;
    private JPanel mainRegisterMedecin;
    private JPanel footerRegisterMedecin;
    private JTextField textFieldVille;
    private JTextField textFieldTelephone;
    private JTextField textFieldEmail;
    private JTextField textFieldNumAgre;
    private JTextField textFieldNom;
    private JTextField textFieldPrenom;
    private JTextField textFieldAdresse;
    private JTextField textFieldCodePostal;
    private JButton buttonRetourRegisterMedecin;
    private JButton buttonValideRegisterMedecin;
    private JButton buttonQuitter;
    private JFrame previousFrame;

    // Médecin en cours ou update
    private Medecin currentMedecin;

    public registerMedecin(JFrame previousFrame) {
        this.previousFrame = previousFrame;
        initUI();

        // Actions boutons
        buttonRetourRegisterMedecin.addActionListener(e -> retour());
        buttonValideRegisterMedecin.addActionListener(e -> {
            try {
                valider();
            } catch (SaisieException ex) {
                JOptionPane.showMessageDialog(this, "Erreur : " + ex.getMessage());
            }
        });
        buttonQuitter.addActionListener(e -> quitter());
    }

    public registerMedecin(Medecin medecin) {
        initUI();

        this.currentMedecin = medecin;

        if(medecin != null) {
            textFieldNom.setText(medecin.getNom());
            textFieldPrenom.setText(medecin.getPrenom());
            textFieldAdresse.setText(medecin.getLieu().getAdresse());
            textFieldCodePostal.setText(String.valueOf(medecin.getLieu().getCodePostal()));
            textFieldVille.setText(medecin.getLieu().getVille());
            textFieldTelephone.setText(medecin.getLieu().getTelephone());
            textFieldEmail.setText(medecin.getLieu().getEmail());
            textFieldNumAgre.setText(medecin.getNumeroAgreement());
        }

        // Actions boutons
        buttonRetourRegisterMedecin.addActionListener(e -> retour());
        buttonValideRegisterMedecin.addActionListener(e -> {
            try {
                valider();
            } catch (SaisieException ex) {
                JOptionPane.showMessageDialog(this,
                        "Erreur sur la validation de la mise a jour médecin : "
                                + ex.getMessage());
            }
        });
        buttonQuitter.addActionListener(e -> quitter());

    }

    /**
     * Initialisation de la fenêtre
     */
    private void initUI() {
        ImageIcon imageIcon = new ImageIcon("C:\\Users\\User\\Desktop\\ECF-CPP1_CICEK_Orhan\\ECF-CPP-1\\sparadrap\\src\\sparadrap\\afpa\\image\\miniLogo.png");
        Dimension dimension = new Dimension(1600, 1000);

        this.setTitle("Sparadrap");
        this.setIconImage(imageIcon.getImage());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setPreferredSize(dimension);
        this.setResizable(false);
        this.setContentPane(contentPane);

        this.pack();
        this.setLocationRelativeTo(null);
    }

    private void retour() {
        if (previousFrame != null) {
            previousFrame.setVisible(true); // réaffiche la fenêtre précédente
        }
        this.dispose(); // ferme la fenêtre actuelle
    }

    private void valider() throws SaisieException {
        try{
            String nom = textFieldNom.getText();
            String prenom = textFieldPrenom.getText();
            String adresse = textFieldAdresse.getText();
            int codePostal = parseInt(textFieldCodePostal.getText());
            String ville = textFieldVille.getText();
            String telephone = textFieldTelephone.getText();
            String email = textFieldEmail.getText();
            String numAgre = textFieldNumAgre.getText();

            if(currentMedecin != null) {
                // -------- MODE UPDATE --------
                currentMedecin.setNom(nom);
                currentMedecin.setPrenom(prenom);
                currentMedecin.setNumeroAgreement(numAgre);

                Lieu lieu = currentMedecin.getLieu();
                lieu.setAdresse(adresse);
                lieu.setCodePostal(codePostal);
                lieu.setVille(ville);
                lieu.setTelephone(telephone);
                lieu.setEmail(email);

                JOptionPane.showMessageDialog(this,
                        "Médecin mis à jour avec succès !",
                        "Succès",
                        JOptionPane.INFORMATION_MESSAGE);
            }else{
                // -------- MODE CREATION --------
                Lieu lieu = new Lieu(adresse, email, telephone, ville, codePostal);
                Medecin medecin = new Medecin("Dr "+nom, prenom, numAgre, lieu);
                medecin.setNumeroAgreement(numAgre);
                Medecin.getMedecins().add(medecin);

                JOptionPane.showMessageDialog(this,
                        "Nouveau médecin ajouté avec succès !",
                        "Succès",
                        JOptionPane.INFORMATION_MESSAGE);
            }

            // Retour vers le consulterMédecin
            consulterMedecin consulterMedecin = new consulterMedecin(this);
            consulterMedecin.setVisible(true);
            this.dispose();

        }catch(NullPointerException e){
            throw new SaisieException("Code postal ou numéro agreement invalide !");
        }
    }

    /**
     * Quitter l'application
     */
    private void quitter() {
        int reponse = JOptionPane.showConfirmDialog(registerMedecin.this,
                "Voulez-vous quitter l'application ?", "Quitter",
                JOptionPane.YES_NO_OPTION);
        if (reponse == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }
}
