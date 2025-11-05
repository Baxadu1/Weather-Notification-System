import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class weatherapp {
    private weatherstation station;
    private JFrame mainFrame;
    private JPanel weatherPanel;
    private JTextArea weatherText;
    private JComboBox<String> strategyComboBox;
    private JTextField tempField, humidityField, windField, feelsLikeField;
    private JTextArea observersText;
    private JButton updateButton, addObserverButton, removeObserverButton;
    private DefaultListModel<String> observerListModel;
    private JList<String> observerList;

    public weatherapp() {
        station = new weatherstation();
        initializeGUI();
        setupObservers();
    }

    private void initializeGUI() {
        mainFrame = new JFrame("Weather Station Pro");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setLayout(new BorderLayout(10, 10));
        mainFrame.getContentPane().setBackground(new Color(240, 245, 255));

        createHeaderPanel();
        createWeatherPanel();
        createControlPanel();
        createObserverPanel();

        mainFrame.pack();
        mainFrame.setSize(900, 700);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setVisible(true);

        updateWeatherDisplay();
    }

    private void createHeaderPanel() {
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(70, 130, 180));
        headerPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        headerPanel.setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Weather Station Pro", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));

        headerPanel.add(titleLabel, BorderLayout.CENTER);
        mainFrame.add(headerPanel, BorderLayout.NORTH);
    }

    private void createWeatherPanel() {
        weatherPanel = new JPanel();
        weatherPanel.setLayout(new BorderLayout(10, 10));
        weatherPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 220, 240), 2),
                BorderFactory.createEmptyBorder(15, 15, 15, 15)
        ));
        weatherPanel.setBackground(Color.WHITE);

        JLabel currentWeatherLabel = new JLabel("Current Weather Conditions");
        currentWeatherLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        currentWeatherLabel.setForeground(new Color(70, 130, 180));

        weatherText = new JTextArea();
        weatherText.setFont(new Font("Consolas", Font.PLAIN, 14));
        weatherText.setBackground(new Color(248, 250, 255));
        weatherText.setBorder(BorderFactory.createLineBorder(new Color(220, 230, 240)));
        weatherText.setEditable(false);
        weatherText.setLineWrap(true);
        weatherText.setWrapStyleWord(true);

        JScrollPane scrollPane = new JScrollPane(weatherText);
        scrollPane.setPreferredSize(new Dimension(400, 150));

        weatherPanel.add(currentWeatherLabel, BorderLayout.NORTH);
        weatherPanel.add(scrollPane, BorderLayout.CENTER);
        mainFrame.add(weatherPanel, BorderLayout.CENTER);
    }

    private void createControlPanel() {
        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new GridLayout(3, 1, 10, 10));
        controlPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder("Control Panel"),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));
        controlPanel.setBackground(Color.WHITE);

        JPanel strategyPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        strategyPanel.setBackground(Color.WHITE);
        JLabel strategyLabel = new JLabel("Update Strategy:");
        strategyComboBox = new JComboBox<>(new String[]{"Real-time", "Scheduled", "Manual"});
        strategyComboBox.addActionListener(e -> onStrategyChanged());

        strategyPanel.add(strategyLabel);
        strategyPanel.add(strategyComboBox);

        JPanel manualPanel = new JPanel(new GridLayout(2, 4, 5, 5));
        manualPanel.setBackground(Color.WHITE);
        manualPanel.add(new JLabel("Temp (C):"));
        tempField = new JTextField("22.0");
        manualPanel.add(tempField);
        manualPanel.add(new JLabel("Humidity (%):"));
        humidityField = new JTextField("65.0");
        manualPanel.add(humidityField);
        manualPanel.add(new JLabel("Wind (m/s):"));
        windField = new JTextField("5.5");
        manualPanel.add(windField);
        manualPanel.add(new JLabel("Feels Like:"));
        feelsLikeField = new JTextField("23.0");
        manualPanel.add(feelsLikeField);

        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.setBackground(Color.WHITE);
        updateButton = new JButton("Update Weather");
        updateButton.setBackground(new Color(70, 130, 180));
        updateButton.setForeground(Color.WHITE);
        updateButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        updateButton.addActionListener(e -> updateWeather());

        buttonPanel.add(updateButton);

        controlPanel.add(strategyPanel);
        controlPanel.add(manualPanel);
        controlPanel.add(buttonPanel);
        mainFrame.add(controlPanel, BorderLayout.SOUTH);
    }

    private void createObserverPanel() {
        JPanel observerPanel = new JPanel();
        observerPanel.setLayout(new BorderLayout(10, 10));
        observerPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder("Weather Observers"),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));
        observerPanel.setBackground(Color.WHITE);
        observerPanel.setPreferredSize(new Dimension(300, 0));

        observerListModel = new DefaultListModel<>();
        observerList = new JList<>(observerListModel);
        observerList.setBackground(new Color(248, 250, 255));
        observerList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JScrollPane listScrollPane = new JScrollPane(observerList);
        listScrollPane.setPreferredSize(new Dimension(280, 200));

        JPanel observerButtonPanel = new JPanel(new GridLayout(2, 1, 5, 5));
        observerButtonPanel.setBackground(Color.WHITE);

        addObserverButton = new JButton("Add Observer");
        addObserverButton.setBackground(new Color(60, 179, 113));
        addObserverButton.setForeground(Color.WHITE);
        addObserverButton.addActionListener(e -> addObserver());

        removeObserverButton = new JButton("Remove Observer");
        removeObserverButton.setBackground(new Color(220, 20, 60));
        removeObserverButton.setForeground(Color.WHITE);
        removeObserverButton.addActionListener(e -> removeObserver());

        observerButtonPanel.add(addObserverButton);
        observerButtonPanel.add(removeObserverButton);

        observersText = new JTextArea();
        observersText.setFont(new Font("Consolas", Font.PLAIN, 11));
        observersText.setBackground(new Color(248, 250, 255));
        observersText.setBorder(BorderFactory.createLineBorder(new Color(220, 230, 240)));
        observersText.setEditable(false);
        JScrollPane textScrollPane = new JScrollPane(observersText);

        observerPanel.add(listScrollPane, BorderLayout.NORTH);
        observerPanel.add(observerButtonPanel, BorderLayout.CENTER);
        observerPanel.add(textScrollPane, BorderLayout.SOUTH);
        mainFrame.add(observerPanel, BorderLayout.EAST);
    }

    private void setupObservers() {
        station.addObserver(new weatherdisplay("Main Display"));
        station.addObserver(new mobileapp("Weather Pro"));
        station.addObserver(new weatherdisplay("Outdoor Display"));
        updateObserverList();
    }

    private void onStrategyChanged() {
        String selected = (String) strategyComboBox.getSelectedItem();
        switch (selected) {
            case "Real-time":
                station.setUpdateStrategy(new realtimeupdatestrategy());
                break;
            case "Scheduled":
                station.setUpdateStrategy(new scheduledupdatestrategy());
                break;
            case "Manual":
                station.setUpdateStrategy(new manualupdatestrategy());
                break;
        }
    }

    private void updateWeather() {
        String selectedStrategy = (String) strategyComboBox.getSelectedItem();

        if ("Manual".equals(selectedStrategy)) {
            try {
                double temp = Double.parseDouble(tempField.getText());
                double humidity = Double.parseDouble(humidityField.getText());
                double wind = Double.parseDouble(windField.getText());
                double feelsLike = Double.parseDouble(feelsLikeField.getText());

                manualupdatestrategy manual = new manualupdatestrategy();
                manual.setManualData(temp, humidity, wind, feelsLike);
                station.setUpdateStrategy(manual);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(mainFrame, "Please enter valid numbers!", "Input Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }

        station.updateWeatherData();
        updateWeatherDisplay();
        updateObserverText();
    }

    private void updateWeatherDisplay() {
        weatherdata data = station.getCurrentWeather();
        if (data != null) {
            StringBuilder sb = new StringBuilder();
            sb.append("Temperature: ").append(data.getTemperature()).append("C\n");
            sb.append("Humidity: ").append(data.getHumidity()).append("%\n");
            sb.append("Wind Speed: ").append(data.getWindSpeed()).append(" m/s\n");
            sb.append("Feels Like: ").append(data.getFeelsLike()).append("C\n\n");

            if (data.getTemperature() > 30) {
                sb.append("Hot weather alert!\n");
            }
            if (data.getWindSpeed() > 15) {
                sb.append("Strong wind warning!\n");
            }
            if (data.getHumidity() > 80) {
                sb.append("High humidity notice\n");
            }
            if (Math.abs(data.getTemperature() - data.getFeelsLike()) > 3) {
                sb.append("Temperature feels different than actual!\n");
            }

            weatherText.setText(sb.toString());
        }
    }

    private void addObserver() {
        String[] options = {"Main Display", "Outdoor Display", "Mobile App", "Tablet App", "Web Dashboard"};
        String choice = (String) JOptionPane.showInputDialog(mainFrame,
                "Select observer type:", "Add Observer",
                JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

        if (choice != null) {
            weatherobserver observer;
            String name = JOptionPane.showInputDialog(mainFrame, "Enter observer name:",
                    "Observer Name", JOptionPane.QUESTION_MESSAGE);

            if (name == null || name.trim().isEmpty()) {
                name = choice;
            }

            if (choice.contains("Mobile") || choice.contains("Tablet") || choice.contains("Web")) {
                observer = new mobileapp(name);
            } else {
                observer = new weatherdisplay(name);
            }

            station.addObserver(observer);
            updateObserverList();
            JOptionPane.showMessageDialog(mainFrame, "Observer added successfully!");
        }
    }

    private void removeObserver() {
        int selectedIndex = observerList.getSelectedIndex();
        if (selectedIndex != -1) {
            station.removeObserver(selectedIndex);
            updateObserverList();
        } else {
            JOptionPane.showMessageDialog(mainFrame, "Please select an observer to remove!");
        }
    }

    private void updateObserverList() {
        observerListModel.clear();
        java.util.List<weatherobserver> observers = station.getObservers();
        for (int i = 0; i < observers.size(); i++) {
            weatherobserver observer = observers.get(i);
            String type = observer instanceof mobileapp ? "Mobile App" : "Weather Display";
            observerListModel.addElement((i + 1) + ". " + type + " - " + getObserverName(observer));
        }
    }

    private String getObserverName(weatherobserver observer) {
        if (observer instanceof mobileapp) {
            return ((mobileapp) observer).appName;
        } else if (observer instanceof weatherdisplay) {
            return ((weatherdisplay) observer).name;
        }
        return "Unknown Observer";
    }

    private void updateObserverText() {
        observersText.setText(station.getLastUpdateLog());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }
            new weatherapp();
        });
    }
}