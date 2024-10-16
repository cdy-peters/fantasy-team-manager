package com.example.server.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DataImporterTest {

    private DataImporter dataImporter;

    @Mock
    private Connection mockConnection;

    @Mock
    private PreparedStatement mockPreparedStatement;

    @Mock
    private ResultSet mockResultSet;

    @Mock
    private ResultSetMetaData mockMetaData;

    @Mock
    private DatabaseMetaData mockDatabaseMetaData;

    @BeforeEach
    void setUp() throws SQLException {
        MockitoAnnotations.openMocks(this);
        dataImporter = new DataImporter();
        
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        when(mockConnection.getMetaData()).thenReturn(mockDatabaseMetaData);
        when(mockDatabaseMetaData.getColumns(any(), any(), eq("player_statistics"), any())).thenReturn(mockResultSet);
        when(mockResultSet.getMetaData()).thenReturn(mockMetaData);
    }

    @Test
    void testReadCSV() throws IOException {
        List<String[]> data = dataImporter.readCSV("test-data.csv");
        assertNotNull(data);
        assertFalse(data.isEmpty());
        assertEquals(34, data.get(0).length);
        assertEquals("Rodri", data.get(0)[0]);
        assertEquals("es ESP", data.get(0)[1]);
    }

    @Test
    void testExpandMultiPositionPlayers() {
        List<String[]> testData = new ArrayList<>();
        testData.add(new String[]{"Player1", "Country1", "FW,MF", "25"});
        testData.add(new String[]{"Player2", "Country2", "DF", "28"});

        List<String[]> expandedData = dataImporter.expandMultiPositionPlayers(testData);
        
        assertEquals(2, expandedData.size());
        assertEquals("FW,MF", expandedData.get(0)[2]);
        assertEquals("DF", expandedData.get(1)[2]);
    }

    @Test
    void testCalculateRatings() {
        List<String[]> testData = new ArrayList<>();
        testData.add(new String[]{"Player1", "Country1", "FW", "25", "10", "9", "800", "8.89", "5", "3", "8", "5", "0", "0", "2", "0", "4.5", "4.5", "2.3", "6.8", "20", "50", "30", "0.56", "0.34", "0.90", "0.56", "0.90", "0.51", "0.26", "0.77", "0.51", "0.77", "Team A"});
        testData.add(new String[]{"Player2", "Country2", "MF", "28", "12", "12", "1080", "12", "2", "6", "8", "2", "0", "0", "1", "0", "2.3", "2.3", "5.1", "7.4", "30", "80", "40", "0.17", "0.50", "0.67", "0.17", "0.67", "0.19", "0.43", "0.62", "0.19", "0.62", "Team B"});

        dataImporter.calculateRatings(testData);

        assertEquals(35, testData.get(0).length);
        assertEquals(35, testData.get(1).length);
        assertNotNull(testData.get(0)[34]);
        assertNotNull(testData.get(1)[34]);
    }

    @Test
    void testCalculatePrices() {
        List<String[]> testData = new ArrayList<>();
        testData.add(new String[]{"Player1", "Country1", "FW", "25", "10", "9", "800", "8.89", "5", "3", "8", "5", "0", "0", "2", "0", "4.5", "4.5", "2.3", "6.8", "20", "50", "30", "0.56", "0.34", "0.90", "0.56", "0.90", "0.51", "0.26", "0.77", "0.51", "0.77", "Team A", "1.5"});
        testData.add(new String[]{"Player2", "Country2", "MF", "28", "12", "12", "1080", "12", "2", "6", "8", "2", "0", "0", "1", "0", "2.3", "2.3", "5.1", "7.4", "30", "80", "40", "0.17", "0.50", "0.67", "0.17", "0.67", "0.19", "0.43", "0.62", "0.19", "0.62", "Team B", "0.5"});

        dataImporter.calculatePrices(testData);

        assertEquals(36, testData.get(0).length);
        assertEquals(36, testData.get(1).length);
        assertNotNull(testData.get(0)[35]);
        assertNotNull(testData.get(1)[35]);
    }

    @Test
    void testCalculateScores() {
        List<String[]> testData = new ArrayList<>();
        testData.add(new String[]{"Player1", "Country1", "FW", "25", "10", "9", "800", "8.89", "5", "3", "8", "5", "0", "0", "2", "0", "4.5", "4.5", "2.3", "6.8", "20", "50", "30", "0.56", "0.34", "0.90", "0.56", "0.90", "0.51", "0.26", "0.77", "0.51", "0.77", "Team A", "1.5", "10.5"});
        testData.add(new String[]{"Player2", "Country2", "MF", "28", "12", "12", "1080", "12", "2", "6", "8", "2", "0", "0", "1", "0", "2.3", "2.3", "5.1", "7.4", "30", "80", "40", "0.17", "0.50", "0.67", "0.17", "0.67", "0.19", "0.43", "0.62", "0.19", "0.62", "Team B", "0.5", "8.5"});

        dataImporter.calculateScores(testData);

        assertEquals(37, testData.get(0).length);
        assertEquals(37, testData.get(1).length);
        assertNotNull(testData.get(0)[36]);
        assertNotNull(testData.get(1)[36]);
    }

}