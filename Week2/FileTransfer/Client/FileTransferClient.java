import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.Socket;

public class FileTransferClient extends JFrame {

    private JTextField filePathField;
    private JButton browseButton;
    private JButton sendButton;

    public FileTransferClient() {
        setTitle("File Transfer Client");
        setSize(400, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        filePathField = new JTextField(20);
        browseButton = new JButton("Browse");
        sendButton = new JButton("Send");

        browseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int returnValue = fileChooser.showOpenDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    filePathField.setText(selectedFile.getAbsolutePath());
                }
            }
        });

        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String filePath = filePathField.getText();
                if (!filePath.isEmpty()) {
                    sendFile(filePath);
                } else {
                    JOptionPane.showMessageDialog(null, "Please select a file to send.");
                }
            }
        });

        setLayout(new FlowLayout());
        add(new JLabel("File:"));
        add(filePathField);
        add(browseButton);
        add(sendButton);
    }

    private void sendFile(String filePath) {
        try (Socket socket = new Socket("localhost", 900);
                FileInputStream fileInputStream = new FileInputStream(filePath);
                DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream())) {

            File file = new File(filePath);
            dataOutputStream.writeLong(file.length());

            byte[] buffer = new byte[4 * 1024];
            int bytes;
            while ((bytes = fileInputStream.read(buffer)) != -1) {
                dataOutputStream.write(buffer, 0, bytes);
            }

            JOptionPane.showMessageDialog(null, "File sent successfully.");
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error sending file: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new FileTransferClient().setVisible(true);
            }
        });
    }
}