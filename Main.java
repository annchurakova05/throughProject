import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.*;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class Main {

    private static final String AES = "AES";
    private static final int KEY_SIZE = 256;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Main::createAndShowGUI);
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("File Processor");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 400);
        frame.setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 1));

        JLabel inputLabel = new JLabel("Выберите расширение входного файла:");
        panel.add(inputLabel);

        JCheckBox htmlInputCheck = new JCheckBox("HTML");
        JCheckBox xmlInputCheck = new JCheckBox("XML");
        JCheckBox yamlInputCheck = new JCheckBox("YAML");
        JCheckBox txtInputCheck = new JCheckBox("TXT");

        panel.add(htmlInputCheck);
        panel.add(xmlInputCheck);
        panel.add(yamlInputCheck);
        panel.add(txtInputCheck);

        JLabel outputLabel = new JLabel("Выберите расширение выходного файла:");
        panel.add(outputLabel);

        JCheckBox htmlOutputCheck = new JCheckBox("HTML");
        JCheckBox xmlOutputCheck = new JCheckBox("XML");
        JCheckBox yamlOutputCheck = new JCheckBox("YAML");
        JCheckBox txtOutputCheck = new JCheckBox("TXT");

        panel.add(htmlOutputCheck);
        panel.add(xmlOutputCheck);
        panel.add(yamlOutputCheck);
        panel.add(txtOutputCheck);

        JLabel archiveLabel = new JLabel("Выберите формат архивации:");
        panel.add(archiveLabel);

        JRadioButton noneButton = new JRadioButton("Не архивировать");
        JRadioButton zipButton = new JRadioButton("ZIP");
        JRadioButton rarButton = new JRadioButton("RAR");
        noneButton.setSelected(true);

        ButtonGroup archiveGroup = new ButtonGroup();
        archiveGroup.add(noneButton);
        archiveGroup.add(zipButton);
        archiveGroup.add(rarButton);

        panel.add(noneButton);
        panel.add(zipButton);
        panel.add(rarButton);

        JButton processButton = new JButton("Обработать файл");
        panel.add(processButton);

        // Добавляем кнопку разархивирования
        JButton unarchiveButton = new JButton("Разархивировать файл");
        panel.add(unarchiveButton);

        frame.add(panel, BorderLayout.CENTER);

        processButton.addActionListener(e -> {
            String inputExtension = getSelectedInputExtension(htmlInputCheck, xmlInputCheck, yamlInputCheck, txtInputCheck);
            String outputExtension = getSelectedOutputExtension(htmlOutputCheck, xmlOutputCheck, yamlOutputCheck, txtOutputCheck);

            if (inputExtension == null || outputExtension == null) {
                showMessage("Пожалуйста, выберите хотя бы одно расширение для входного и выходного файла.");
                return;
            }

            String archiveType;
            if (noneButton.isSelected()) {
                archiveType = "none";
            } else if (zipButton.isSelected()) {
                archiveType = "zip";
            } else {
                archiveType = "rar";
            }
            selectFilesAndProcess(inputExtension, outputExtension, archiveType);
        });

        unarchiveButton.addActionListener(e -> unzipSelectedFile());

        frame.setVisible(true);
    }

    private static String getSelectedInputExtension(JCheckBox... checkboxes) {
        for (JCheckBox checkbox : checkboxes) {
            if (checkbox.isSelected()) {
                return checkbox.getText().toLowerCase();
            }
        }
        return null;
    }

    private static String getSelectedOutputExtension(JCheckBox... checkboxes) {
        for (JCheckBox checkbox : checkboxes) {
            if (checkbox.isSelected()) {
                return checkbox.getText().toLowerCase();
            }
        }
        return null;
    }

    private static void selectFilesAndProcess(String inputExtension, String outputExtension, String archiveType) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Выберите входной файл");
        fileChooser.setFileFilter(new FileNameExtensionFilter("Файлы ." + inputExtension, inputExtension));

        int returnValue = fileChooser.showOpenDialog(null);
        if (returnValue != JFileChooser.APPROVE_OPTION) {
            showMessage("Входной файл не выбран.");
            return;
        }
        File inputFile = fileChooser.getSelectedFile();

        fileChooser.setDialogTitle("Выберите выходной файл");
        fileChooser.setFileFilter(new FileNameExtensionFilter("Файлы ." + outputExtension, outputExtension));
        returnValue = fileChooser.showSaveDialog(null);
        if (returnValue != JFileChooser.APPROVE_OPTION) {
            showMessage("Выходной файл не выбран.");
            return;
        }
        File outputFile = fileChooser.getSelectedFile();

        processFile(inputFile.getPath(), outputFile.getPath(), outputExtension, archiveType);
    }

    private static void unzipSelectedFile() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Выберите ZIP файл для разархивирования");
        fileChooser.setFileFilter(new FileNameExtensionFilter("ZIP файлы", "zip"));

        int returnValue = fileChooser.showOpenDialog(null);
        if (returnValue != JFileChooser.APPROVE_OPTION) {
            showMessage("Файл не выбран.");
            return;
        }
        File zipFile = fileChooser.getSelectedFile();

        // Предполагаем, что разархивированные файлы будут помещены в папку "output"
        unzip(zipFile.getPath(), "output");
        showMessage("Файл успешно разархивирован!");
    }

    private static void processFile(String inputFilePath, String outputFilePath, String fileExtension, String archiveType) {
        try {
            // Запрос переменных
            String variablesInput = JOptionPane.showInputDialog("Введите переменные (например, a 5 b 10):");
            Map<String, Double> variables = parseVariables(variablesInput);

            String inputContent = readFile(inputFilePath);

            // Заменяем переменные в содержимом
            for (Map.Entry<String, Double> entry : variables.entrySet()) {
                inputContent = inputContent.replace(entry.getKey(), String.valueOf(entry.getValue()));
            }

            String evaluatedContent = evaluateArithmeticExpressions(inputContent);

            SecretKey secretKey = generateKey();
            String encryptedContent = encrypt(evaluatedContent, secretKey);
            String decryptedContent = decrypt(encryptedContent, secretKey);

            String outputFilePathEnc = getOutputFileName(inputFilePath, ".enc");
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(outputFilePathEnc))) {
                bw.write(encryptedContent);
            }

            String outputFilePathDec = getOutputFileName(inputFilePath, ".dec");
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(outputFilePathDec))) {
                bw.write(decryptedContent);
            }

            switch (fileExtension) {
                case "html":
                    writeHtmlFile(outputFilePath, evaluatedContent, encryptedContent, decryptedContent);
                    break;
                case "xml":
                    writeXmlFile(outputFilePath, evaluatedContent, encryptedContent, decryptedContent);
                    break;
                case "yaml":
                    writeYamlFile(outputFilePath, evaluatedContent, encryptedContent, decryptedContent);
                    break;
                case "txt":
                    writeTxtFile(outputFilePath, evaluatedContent, encryptedContent, decryptedContent);
                    break;
            }

            if ("zip".equals(archiveType)) {
                archiveToZip("archive.zip", outputFilePath);
                unzip("archive.zip", "output");
            } else if ("rar".equals(archiveType)) {
                rar("archive.rar", outputFilePath);
                unrar("archive.rar", "output");
            }

            showMessage("Файл обработан и сохранен!");

        } catch (IOException | RuntimeException e) {
            e.printStackTrace();
            showMessage("Ошибка: " + e.getMessage());
        }
    }

    static Map<String, Double> parseVariables(String input) {
        Map<String, Double> variables = new HashMap<>();
        String[] tokens = input.split("\\s+");

        for (int i = 0; i < tokens.length; i += 2) {
            if (i + 1 < tokens.length) { // Проверяем, что существует пара
                String key = tokens[i].trim();
                double value = Double.parseDouble(tokens[i + 1].trim());
                variables.put(key, value);
            }
        }

        return variables;
    }

    private static void showMessage(String message) {
        JOptionPane.showMessageDialog(null, message);
    }

    static SecretKey generateKey() {
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance(AES);
            keyGenerator.init(KEY_SIZE);
            return keyGenerator.generateKey();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Ошибка генерации ключа: " + e);
        }
    }

    static String encrypt(String input, SecretKey key) {
        try {
            Cipher cipher = Cipher.getInstance(AES);
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] encryptedBytes = cipher.doFinal(input.getBytes());
            return Base64.getEncoder().encodeToString(encryptedBytes);
        } catch (Exception e) {
            throw new RuntimeException("Ошибка при шифровании: " + e);
        }
    }

    static String decrypt(String input, SecretKey key) {
        try {
            Cipher cipher = Cipher.getInstance(AES);
            cipher.init(Cipher.DECRYPT_MODE, key);
            byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(input));
            return new String(decryptedBytes);
        } catch (Exception e) {
            throw new RuntimeException("Ошибка при расшифровке: " + e);
        }
    }

    static String evaluateArithmeticExpressions(String content) {
        content = content.replaceAll("\\s+", " ");
        String regex = "-?\\d+(\\.\\d+)?([+\\-*/]-?\\d+(\\.\\d+)?)+";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(content);

        StringBuilder result = new StringBuilder(content);

        while (matcher.find()) {
            String expression = matcher.group();
            double evaluatedValue = evaluateExpression(expression);
            int start = matcher.start();
            int end = matcher.end();
            result.replace(start, end, String.valueOf(evaluatedValue));
            matcher = pattern.matcher(result);
        }
        return result.toString();
    }

    private static double evaluateExpression(String expression) {
        String[] tokens = expression.split("(?<=[-+*/])|(?=[-+*/])");
        double result = 0;

        List<Double> numbers = new ArrayList<>();
        List<String> operations = new ArrayList<>();

        for (String token : tokens) {
            if (token.matches("-?\\d+(\\.\\d+)?")) {
                double number = Double.parseDouble(token);
                if (!operations.isEmpty() && (operations.get(operations.size() - 1).equals("*") || operations.get(operations.size() - 1).equals("/"))) {
                    String operation = operations.remove(operations.size() - 1);
                    double leftOperand = numbers.remove(numbers.size() - 1);
                    number = operation.equals("*") ? leftOperand * number : leftOperand / number;
                }
                numbers.add(number);
            } else if (!token.trim().isEmpty()) {
                operations.add(token);
            }
        }

        result = numbers.get(0);
        int currentNumberIndex = 1;

        for (String operation : operations) {
            if (operation.equals("+")) {
                result += numbers.get(currentNumberIndex++);
            } else if (operation.equals("-")) {
                result -= numbers.get(currentNumberIndex++);
            }
        }

        return result;
    }

    static String readFile(String filePath) throws IOException {
        StringBuilder content = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                content.append(line).append(System.lineSeparator());
            }
        }
        return content.toString();
    }

    private static void writeHtmlFile(String filePath, String evaluated, String encrypted, String decrypted) throws IOException {
        String result = "<html><body><h1>Исходный текст:</h1><pre>" + evaluated + "</pre><h1>Зашифрованный текст:</h1><pre>" +
                encrypted + "</pre><h1>Расшифрованный текст:</h1><pre>" + decrypted + "</pre></body></html>";
        writeFile(filePath, result);
    }

    private static void writeXmlFile(String filePath, String evaluated, String encrypted, String decrypted) throws IOException {
        String xmlOutput = "<results><originalContent>" + evaluated + "</originalContent><encryptedContent>" +
                encrypted + "</encryptedContent><decryptedContent>" + decrypted + "</decryptedContent></results>";
        writeFile(filePath, xmlOutput);
    }

    private static void writeYamlFile(String filePath, String evaluated, String encrypted, String decrypted) throws IOException {
        String yamlOutput = "originalContent: |\n" + evaluated.replace("\n", "\n  ") +
                "\nencryptedContent: " + encrypted +
                "\ndecryptedContent: " + decrypted;
        writeFile(filePath, yamlOutput);
    }

    private static void writeTxtFile(String filePath, String evaluated, String encrypted, String decrypted) throws IOException {
        String result = "Исходный текст:\n" + evaluated +
                "\n\nЗашифрованный текст:\n" + encrypted +
                "\n\nРасшифрованный текст:\n" + decrypted;
        writeFile(filePath, result);
    }

    static void writeFile(String filePath, String content) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            bw.write(content);
        }
    }

    public static void archiveToZip(String zipArchiveName, String filename) {
        try (FileOutputStream fos = new FileOutputStream(zipArchiveName);
             ZipOutputStream zos = new ZipOutputStream(fos)) {

            File fileToZip = new File(filename);
            try (FileInputStream fis = new FileInputStream(fileToZip)) {
                ZipEntry zipEntry = new ZipEntry(fileToZip.getName());
                zos.putNextEntry(zipEntry);

                byte[] bytes = new byte[1024];
                int length;
                while ((length = fis.read(bytes)) >= 0) {
                    zos.write(bytes, 0, length);
                }
            }
        } catch (IOException e) {
            System.out.println("Ошибка при архивировании: " + e.getMessage());
        }
    }

    public static void unzip(String zipArchiveName, String outputDirectory) {
        try (FileInputStream fis = new FileInputStream(zipArchiveName);
             ZipInputStream zis = new ZipInputStream(fis)) {

            File outputFolder = new File(outputDirectory);
            if (!outputFolder.exists()) {
                outputFolder.mkdirs();
            }

            ZipEntry zipEntry = zis.getNextEntry();
            while (zipEntry != null) {
                File newFile = new File(outputDirectory, zipEntry.getName());

                try (FileOutputStream fos = new FileOutputStream(newFile)) {
                    byte[] buffer = new byte[1024];
                    int len;
                    while ((len = zis.read(buffer)) > 0) {
                        fos.write(buffer, 0, len);
                    }
                }

                zipEntry = zis.getNextEntry();
            }

            zis.closeEntry();

        } catch (IOException e) {
            System.out.println("Ошибка при деархивировании: " + e.getMessage());
        }
    }

    public static void rar(String rarArchiveName, String filename) {
        try {
            Runtime.getRuntime().exec(new String[]{"E:\\Win\\Rar.exe", "a", rarArchiveName, filename});
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void unrar(String rarArchiveName, String outputDirectory) {
        try {
            File outputFolder = new File(outputDirectory);
            if (!outputFolder.exists()) {
                outputFolder.mkdirs();
            }
            String currentDirectory = outputFolder.getCanonicalPath();
            Runtime.getRuntime().exec(new String[]{"E:\\Win\\UnRAR.exe", "e", rarArchiveName, currentDirectory});
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    static String getOutputFileName(String originalPath, String newExtension) {
        int dotIndex = originalPath.lastIndexOf('.');
        if (dotIndex == -1) {
            return originalPath + newExtension;
        }
        return originalPath.substring(0, dotIndex) + newExtension;
    }
}
