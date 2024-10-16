package com.example.server.util;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import com.example.server.Server;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.sql.*;
import java.util.*;

@Component
public class DataImporter {

    private static final String CSV_FILE_PATH = "premier-player-23-24.csv";
    private static final double MIN_PRICE = 4;
    private static final double MAX_PRICE = 15;
    private static final int NUM_BINS = 100;
    private static final Map<String, Map<String, Double>> POSITION_WEIGHTS_RATINGS = new HashMap<>();
    private static final Map<String, Double> COMMON_WEIGHTS_SCORES = new HashMap<>();
    private static final Map<String, Map<String, Double>> POSITION_WEIGHTS_SCORES = new HashMap<>();

    static {
        // Initialize POSITION_WEIGHTS_RATINGS
        POSITION_WEIGHTS_RATINGS.put("FW", new HashMap<>() {{
            put("Gls", 0.7);
            put("Ast", 0.3);
            put("npxG", 0.1);
            put("xAG", 0.2);
            put("Min", 0.1);
            put("CrdY", -0.2);
            put("CrdR", -0.5);
        }});
        POSITION_WEIGHTS_RATINGS.put("MF", new HashMap<>() {{
            put("PrgP", 0.4);
            put("Ast", 0.3);
            put("xAG", 0.2);
            put("npxG", 0.1);
            put("Min", 0.1);
            put("CrdY", -0.2);
            put("CrdR", -0.5);
        }});
        POSITION_WEIGHTS_RATINGS.put("DF", new HashMap<>() {{
            put("PrgC", 0.4);
            put("PrgP", 0.3);
            put("Min", 0.1);
            put("CrdY", -0.2);
            put("CrdR", -0.5);
        }});
        POSITION_WEIGHTS_RATINGS.put("GK", new HashMap<>() {{
            put("PrgC", 0.4);
            put("PrgP", 0.3);
            put("Min", 0.1);
        }});
        // Initialize COMMON_WEIGHTS_SCORES
        COMMON_WEIGHTS_SCORES.put("Min", 2.0 / 90);
        COMMON_WEIGHTS_SCORES.put("Ast", 3.0);
        COMMON_WEIGHTS_SCORES.put("CrdY", -1.0);
        COMMON_WEIGHTS_SCORES.put("CrdR", -3.0);
        // Initialize POSITION_WEIGHTS_SCORES
        POSITION_WEIGHTS_SCORES.put("FW", new HashMap<>() {{
            put("Gls", 5.0);
        }});
        POSITION_WEIGHTS_SCORES.put("MF", new HashMap<>() {{
            put("Gls", 4.0);
        }});
        POSITION_WEIGHTS_SCORES.put("DF", new HashMap<>() {{
            put("Gls", 3.0);
        }});
        POSITION_WEIGHTS_SCORES.put("GK", new HashMap<>() {{
            put("Gls", 2.0);
            put("PrgC", 3.0);
            put("PrgP", 2.0);
        }});
    }

    public void importData() {
        try {
            List<String[]> data = readCSV(CSV_FILE_PATH);
            List<String[]> expandedData = expandMultiPositionPlayers(data);
            calculateRatings(expandedData);
            calculatePrices(expandedData);
            calculateScores(expandedData);

            // Validate that each row has 37 columns after calculations
            for (String[] row : expandedData) {
                if (row.length != 37) {
                    System.err.println("Error: Row does not have correct number of columns after calculation: " + Arrays.toString(row));
                }
            }

            Connection connection = Server.conn;
            int rowsProcessed = insertData(connection, expandedData);
            System.out.println("Processed " + rowsProcessed + " rows of player data.");
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }

    public List<String[]> readCSV(String filePath) throws IOException {        List<String[]> data = new ArrayList<>();
        ClassPathResource resource = new ClassPathResource(filePath);
        try (BufferedReader br = new BufferedReader(new InputStreamReader(resource.getInputStream()))) {
            String line;
            br.readLine(); // Skip header
            while ((line = br.readLine()) != null) {
                if (!line.isEmpty()) {
                    List<String> values = new ArrayList<>();
                    Matcher m = Pattern.compile("(?:^|,)(\"(?:[^\"]+|\"\")*\"|[^,]*)").matcher(line);
                    while (m.find()) {
                        String value = m.group(1);
                        value = value.replaceAll("^\"|\"$", "").replace("\"\"", "\"");
                        values.add(value);
                    }
                    if (values.size() >= 34) {  // Change column count based on data structure
                        data.add(values.toArray(new String[0]));
                    } else {
                        System.err.println("Warning: Row has missing data. Length: " + values.size() + ", Data: " + values);
                    }
                }
            }
        }
        return data;
    }

    protected List<String[]> expandMultiPositionPlayers(List<String[]> data) {        List<String[]> expandedData = new ArrayList<>();
        for (String[] row : data) {
            String positions = row[2].replace("\"", "").trim();
            String[] newRow = Arrays.copyOf(row, row.length);
            newRow[2] = positions;  // Keep the original multi-position string
            expandedData.add(newRow);
        }
        return expandedData;
    }

    protected void calculateRatings(List<String[]> data) {
        Map<String, List<Double>> statValues = new HashMap<>();
        for (String[] row : data) {
            String position = row[2];
            String[] positions = position.split(",");
            Map<String, Double> weights = POSITION_WEIGHTS_RATINGS.get(positions[0].trim());
            if (weights != null) {
                for (String stat : weights.keySet()) {
                    int statIndex = getStatIndex(stat);
                    if (statIndex != -1 && statIndex < row.length) {
                        try {
                            double value = Double.parseDouble(row[statIndex]);
                            statValues.computeIfAbsent(stat, k -> new ArrayList<>()).add(value);
                        } catch (NumberFormatException e) {
                            // Skip this stat if it's not a valid number
                        }
                    }
                }
            }
        }
        // Calculate z-scores
        Map<String, double[]> zScores = new HashMap<>();
        for (Map.Entry<String, List<Double>> entry : statValues.entrySet()) {
            zScores.put(entry.getKey(), calculateZScores(entry.getValue()));
        }
        // Second pass: calculate ratings
        for (int i = 0; i < data.size(); i++) {
            String[] row = data.get(i);
            String position = row[2];
            String[] positions = position.split(",");
            Map<String, Double> weights = POSITION_WEIGHTS_RATINGS.get(positions[0].trim());
            if (weights != null) {
                double rating = 0;
                for (Map.Entry<String, Double> weightEntry : weights.entrySet()) {
                    String stat = weightEntry.getKey();
                    int statIndex = getStatIndex(stat);
                    if (statIndex != -1 && statIndex < row.length) {
                        try {
                            rating += zScores.get(stat)[i] * weightEntry.getValue();
                        } catch (IndexOutOfBoundsException e) {
                            // Skip this stat if there's no z-score available
                        }
                    }
                }
                // Add rating as a new column
                String[] newRow = Arrays.copyOf(row, row.length + 1);
                newRow[row.length] = String.valueOf(Math.round(rating * 100.0) / 100.0);
                data.set(i, newRow);
            }
        }
    }

    protected void calculatePrices(List<String[]> data) {        double[] ratings = data.stream()
                .mapToDouble(row -> {
                    try {
                        return Double.parseDouble(row[row.length - 1]);
                    } catch (NumberFormatException e) {
                        return 0.0;
                    }
                })
                .toArray();
        double[] prices = calculatePriceBins(ratings);
        for (int i = 0; i < data.size(); i++) {
            String[] row = data.get(i);
            String[] newRow = Arrays.copyOf(row, row.length + 1);
            newRow[row.length] = String.valueOf(Math.round(prices[i] * 100.0) / 100.0);
            data.set(i, newRow);
        }
    }

    protected void calculateScores(List<String[]> data) {        for (int i = 0; i < data.size(); i++) {
            String[] row = data.get(i);
            String[] positions = row[2].split(",");
            double totalScore = 0;
            for (String position : positions) {
                position = position.trim();
                double score = calculatePositionScore(row, position);
                totalScore += score;
            }
            double avgScore = totalScore / positions.length;
            String[] newRow = Arrays.copyOf(row, row.length + 1);
            newRow[row.length] = String.valueOf(Math.max(0.0, Math.round(avgScore * 100.0) / 100.0));
            data.set(i, newRow);
        }
    }

    private double calculatePositionScore(String[] row, String position) {
        double score = 0;
        for (Map.Entry<String, Double> entry : COMMON_WEIGHTS_SCORES.entrySet()) {
            int statIndex = getStatIndex(entry.getKey());
            if (statIndex != -1 && statIndex < row.length) {
                try {
                    score += Double.parseDouble(row[statIndex]) * entry.getValue();
                } catch (NumberFormatException e) {
                    // Skip this stat if it's not a valid number
                }
            }
        }
        Map<String, Double> positionWeights = POSITION_WEIGHTS_SCORES.get(position);
        if (positionWeights != null) {
            for (Map.Entry<String, Double> entry : positionWeights.entrySet()) {
                int statIndex = getStatIndex(entry.getKey());
                if (statIndex != -1 && statIndex < row.length) {
                    try {
                        score += Double.parseDouble(row[statIndex]) * entry.getValue();
                    } catch (NumberFormatException e) {
                        // Skip this stat if it's not a valid number
                    }
                }
            }
        }
        return score;
    }

    private int getStatIndex(String statName) {
        switch (statName) {
            case "Gls":
                return 8;
            case "Ast":
                return 9;
            case "PrgP":
                return 21;
            case "PrgC":
                return 20;
            case "npxG":
                return 17;
            case "xAG":
                return 18;
            case "Min":
                return 6;
            case "CrdY":
                return 14;
            case "CrdR":
                return 15;
            default:
                return -1;
        }
    }

    private double[] calculateZScores(List<Double> values) {
        double mean = values.stream().mapToDouble(Double::doubleValue).average().orElse(0.0);
        double stdDev = Math.sqrt(values.stream().mapToDouble(v -> Math.pow(v - mean, 2)).average().orElse(0.0));
        return values.stream().mapToDouble(v -> (v - mean) / stdDev).toArray();
    }

    private double[] calculatePriceBins(double[] ratings) {
        double minRating = Arrays.stream(ratings).min().orElse(0);
        double maxRating = Arrays.stream(ratings).max().orElse(0);
        double binSize = (maxRating - minRating) / NUM_BINS;
        return Arrays.stream(ratings)
                .map(rating -> {
                    int bin = (int) ((rating - minRating) / binSize);
                    return MIN_PRICE + (MAX_PRICE - MIN_PRICE) * bin / (NUM_BINS - 1);
                })
                .toArray();
    }

    protected int insertData(Connection connection, List<String[]> data) throws SQLException {
        // Get column information from the database
        Map<String, Integer> columnTypes = getColumnInfo(connection, "player_statistics");
        
        // Build the SQL query dynamically based on the actual columns
        StringBuilder columns = new StringBuilder();
        StringBuilder placeholders = new StringBuilder();
        StringBuilder updateClauses = new StringBuilder();
        
        for (String column : columnTypes.keySet()) {
            if (columns.length() > 0) {
                columns.append(", ");
                placeholders.append(", ");
                updateClauses.append(", ");
            }
            columns.append("`").append(column).append("`");
            placeholders.append("?");
            updateClauses.append("`").append(column).append("`=VALUES(`").append(column).append("`)");
        }
        
        String upsertQuery = String.format(
            "INSERT INTO player_statistics (%s) VALUES (%s) ON DUPLICATE KEY UPDATE %s",
            columns, placeholders, updateClauses
        );
    
        System.out.println("Upsert Query: " + upsertQuery);  // Debug output
    
        int idCounter = 3000;
        int rowsProcessed = 0;
    
        connection.setAutoCommit(false);  // Start transaction
    
        try (PreparedStatement upsertStmt = connection.prepareStatement(upsertQuery)) {
            for (String[] row : data) {
                if (row.length != columnTypes.size() - 1) {  // -1 because 'id' is auto-generated
                    System.err.println("Error: Data row length incorrect: " + Arrays.toString(row));
                    continue;
                }
    
                String playerName = row[0];
                String nation = row[1];
                String position = row[2];
    
                try {
                    int parameterIndex = 1;
                    upsertStmt.setInt(parameterIndex++, idCounter++);
                    upsertStmt.setString(parameterIndex++, playerName);
                    upsertStmt.setString(parameterIndex++, nation);
                    upsertStmt.setString(parameterIndex++, position);
                    
                    for (int i = 3; i < row.length; i++) {
                        String columnName = (String) columnTypes.keySet().toArray()[i + 1];  // +1 to account for id
                        int sqlType = columnTypes.get(columnName);
                        
                        setParameterSafely(upsertStmt, parameterIndex++, row[i], sqlType, columnName);
                    }
    
                    // Execute update
                    int affectedRows = upsertStmt.executeUpdate();
                    System.out.println("Affected rows for " + playerName + ": " + affectedRows);  // Debug output
                    rowsProcessed += affectedRows;
    
                } catch (SQLException e) {
                    System.err.println("SQL error parsing row for player: " + playerName + ", position: " + position);
                    System.err.println("Error: " + e.getMessage());
                    System.err.println("Data: " + Arrays.toString(row));
                }
            }
            connection.commit();  // Commit transaction
        } catch (SQLException e) {
            connection.rollback();  // Rollback transaction on error
            throw e;
        } finally {
            connection.setAutoCommit(true);  // Reset auto-commit
        }
        System.out.println("Total rows processed: " + rowsProcessed);  // Debug output
        return rowsProcessed;
    }

    private Map<String, Integer> getColumnInfo(Connection connection, String tableName) throws SQLException {
        Map<String, Integer> columnInfo = new LinkedHashMap<>();
        try (ResultSet rs = connection.getMetaData().getColumns(null, null, tableName, null)) {
            while (rs.next()) {
                columnInfo.put(rs.getString("COLUMN_NAME"), rs.getInt("DATA_TYPE"));
            }
        }
        return columnInfo;
    }

    private void setParameterSafely(PreparedStatement stmt, int index, String value, int sqlType, String columnName) throws SQLException {        if (value == null || value.trim().isEmpty()) {
            stmt.setNull(index, sqlType);
        } else {
            try {
                switch (sqlType) {
                    case Types.INTEGER:
                        stmt.setInt(index, (int) Math.round(Double.parseDouble(value)));
                        break;
                    case Types.DOUBLE:
                    case Types.DECIMAL:
                    case Types.NUMERIC:
                    case Types.FLOAT:
                        double doubleValue = Double.parseDouble(value);
                        if (columnName.equals("90s")) {
                            // Assuming '90s' is stored as an integer representing the number of 90-minute periods
                            stmt.setInt(index, (int) Math.round(doubleValue));
                        } else {
                            stmt.setDouble(index, doubleValue);
                        }
                        break;
                    case Types.BOOLEAN:
                        stmt.setBoolean(index, Boolean.parseBoolean(value));
                        break;
                    default:
                        stmt.setString(index, value);
                }
            } catch (NumberFormatException e) {
                System.err.println("Warning: Could not parse value '" + value + "' for column '" + columnName + "'. Setting to null.");
                stmt.setNull(index, sqlType);
            }
        }
    }


}
