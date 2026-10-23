import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.File;

public class LeafTriageFrontend extends JFrame {

    private JLabel imageLabel;
    private JLabel diseaseLabel;
    private JLabel confidenceLabel;
    private JLabel statusLabel;

    public LeafTriageFrontend() {

        setTitle("LeafTriage - Offline Crop Disease Triage");
        setSize(500, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(new EmptyBorder(20, 30, 20, 30));

        // Title
        JLabel title = new JLabel("LeafTriage");
        title.setFont(new Font("Arial", Font.BOLD, 30));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel subtitle =
                new JLabel("Offline Crop Disease Triage");
        subtitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        mainPanel.add(title);
        mainPanel.add(subtitle);
        mainPanel.add(Box.createVerticalStrut(20));

        // Language Selection
        JLabel languageLabel = new JLabel("Select Language:");

        String[] languages = {
                "English",
                "Tamil",
                "Hindi",
                "Telugu"
        };

        JComboBox<String> languageBox =
                new JComboBox<>(languages);

        mainPanel.add(languageLabel);
        mainPanel.add(languageBox);

        mainPanel.add(Box.createVerticalStrut(20));

        // Image Preview
        imageLabel = new JLabel(
                "No Leaf Image Selected",
                SwingConstants.CENTER
        );

        imageLabel.setPreferredSize(
                new Dimension(300, 180)
        );

        imageLabel.setMaximumSize(
                new Dimension(300, 180)
        );

        imageLabel.setBorder(
                BorderFactory.createLineBorder(Color.GRAY)
        );

        imageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        mainPanel.add(imageLabel);

        mainPanel.add(Box.createVerticalStrut(10));

        // Upload Button
        JButton uploadButton =
                new JButton("Upload Leaf Image");

        uploadButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        mainPanel.add(uploadButton);

        // Analyze Button
        JButton analyzeButton =
                new JButton("Analyze Leaf");

        analyzeButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        mainPanel.add(Box.createVerticalStrut(10));
        mainPanel.add(analyzeButton);

        mainPanel.add(Box.createVerticalStrut(20));

        // Result
        diseaseLabel =
                new JLabel("Disease: Not Analyzed");

        confidenceLabel =
                new JLabel("Confidence: --");

        statusLabel =
                new JLabel("Status: Waiting for image");

        diseaseLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        confidenceLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        statusLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        mainPanel.add(diseaseLabel);
        mainPanel.add(confidenceLabel);
        mainPanel.add(statusLabel);

        mainPanel.add(Box.createVerticalStrut(15));

        // Guidance Button
        JButton guidanceButton =
                new JButton("Listen to Guidance");

        guidanceButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        mainPanel.add(guidanceButton);

        mainPanel.add(Box.createVerticalStrut(10));

        // Expert Button
        JButton expertButton =
                new JButton("Save / Send to Expert");

        expertButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        mainPanel.add(expertButton);

        // -----------------------------
        // Upload Image
        // -----------------------------

        uploadButton.addActionListener(e -> {

            JFileChooser chooser = new JFileChooser();

            int result = chooser.showOpenDialog(this);

            if (result == JFileChooser.APPROVE_OPTION) {

                File file = chooser.getSelectedFile();

                ImageIcon icon =
                        new ImageIcon(file.getAbsolutePath());

                Image image =
                        icon.getImage().getScaledInstance(
                                280,
                                170,
                                Image.SCALE_SMOOTH
                        );

                imageLabel.setIcon(
                        new ImageIcon(image)
                );

                imageLabel.setText("");

                statusLabel.setText(
                        "Status: Leaf image selected"
                );
            }
        });

        // -----------------------------
        // Analyze Demo
        // -----------------------------

        analyzeButton.addActionListener(e -> {

            /*
             * Demo result only.
             *
             * Later CNN / Hybrid AI model
             * can be connected here.
             */

            double confidence = 85.0;

            diseaseLabel.setText(
                    "Disease: Early Blight"
            );

            confidenceLabel.setText(
                    "Confidence: " + confidence + "%"
            );

            if (confidence >= 70) {

                statusLabel.setText(
                        "Status: High Confidence"
                );

            } else {

                statusLabel.setText(
                        "Status: Expert Review Required"
                );
            }
        });

        // -----------------------------
        // Guidance
        // -----------------------------

        guidanceButton.addActionListener(e -> {

            String language =
                    (String) languageBox.getSelectedItem();

            JOptionPane.showMessageDialog(
                    this,
                    "Selected Language: " + language +
                    "\n\nDisease: Early Blight" +
                    "\nPlease follow verified agricultural guidance." +
                    "\nSource: Agricultural Expert Database",
                    "LeafTriage Guidance",
                    JOptionPane.INFORMATION_MESSAGE
            );
        });

        // -----------------------------
        // Expert Referral
        // -----------------------------

        expertButton.addActionListener(e -> {

            JOptionPane.showMessageDialog(
                    this,
                    "Case saved offline.\n" +
                    "It can be sent to an expert when internet is available.",
                    "Expert Referral",
                    JOptionPane.INFORMATION_MESSAGE
            );
        });

        add(new JScrollPane(mainPanel));

        setVisible(true);
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() ->
                new LeafTriageFrontend()
        );
    }
}