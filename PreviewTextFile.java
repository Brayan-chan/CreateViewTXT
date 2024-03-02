import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;

public class PreviewTextFile extends JFrame {
    
    private JTextArea textArea;

    public PreviewTextFile() {
        setTitle("Vista Previa de Archivo de Texto");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(720, 720);

        textArea = new JTextArea();
        JButton captureButton = new JButton("Capturar y Mostrar Vista Previa");

        captureButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    // Generar archivo de texto con la información del JTextArea
                    generateTextFile();

                    // Tomar captura de pantalla
                    Robot robot = new Robot();
                    Rectangle area = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
                    BufferedImage screenshot = robot.createScreenCapture(area);

                    // Mostrar captura de pantalla en una ventana
                    ImageIcon icon = new ImageIcon(screenshot);
                    JLabel label = new JLabel(icon);
                    JOptionPane.showMessageDialog(null, label);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(new JScrollPane(textArea), BorderLayout.CENTER);
        getContentPane().add(captureButton, BorderLayout.SOUTH);
    }

    private void generateTextFile() throws IOException {
        String content = textArea.getText();
        try (PrintWriter writer = new PrintWriter("archivo.txt")) {
            writer.println(content);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new PreviewTextFile().setVisible(true);
            }
        });
    }
}
