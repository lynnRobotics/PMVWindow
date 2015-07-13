import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.GridLayout;
import java.awt.GridBagLayout;

import javax.swing.JLabel;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.swing.JTextArea;
import javax.swing.JComboBox;
import javax.swing.JTextField;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.lang.Math;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.List;
import java.util.Collections;
import java.util.Comparator;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JButton;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JScrollPane;
import javax.swing.SwingConstants;



class PP<X, Y>{
	public final X x;
	public final Y y;
	public PP(X x, Y y){
		this.x = x;
		this.y = y;
	}
	
}



public class PMVtoTH extends JFrame {

	private JPanel contentPane;
	
	String currentTemperature = "18.0";
	String currentHumidity = "0";
	String currentAction = "sleep";
	String currentSeason = "summer";
	String content_PMVvalue;
	double[][] PMVtable;
	
	String PMVlower = "0.0";
	String PMVupper = "0.0";
	String action_ComfortZone = "sleep";
	String season_ComfortZone = "summer";
	String order_ComfortZone = "temperature";
	String content_ComfortZone;
	Map<Double,List<PP<Double,Integer>>> PMVmap;
	
	final static double T_lower = 15.0;
	final static double T_upper = 40.0;
	final static double T_interval = 0.5;
	final static int H_lower = 0;
	final static int H_upper = 100;
	final static int H_interval = 5;
	
	final static double T_comfort_lower = 18.0;
	final static double T_comfort_upper = 32.0;
	final static int H_comfort_lower = 50;
	final static int H_comfort_upper = 70;
	
	final static int dimT = (int)((T_upper-T_lower)/T_interval)+1;
	final static int dimH = (H_upper-H_lower)/H_interval + 1;
	//String PMVvalue = "0.0";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PMVtoTH frame = new PMVtoTH();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				
				
				
			}
		});
	}
	
	/**
	 * Create the frame.
	 */
	public PMVtoTH() {
		
		//PMV 2-dim array;
		double[][] PMVSleepSummer=CalPMVArray("sleep","summer");
		double[][] PMVSitSummer=CalPMVArray("sit","summer");
		double[][] PMVStandSummer=CalPMVArray("stand","summer");
		double[][] PMVSleepWinter=CalPMVArray("sleep","winter");
		double[][] PMVSitWinter=CalPMVArray("sit","winter");
		double[][] PMVStandWinter=CalPMVArray("stand","winter");
		//PMV Sort by temperature
		Map<Double,List<PP<Double,Integer>>> SleepSummerbyTemperature=SortPMVArray("temperature",PMVSleepSummer);
		Map<Double,List<PP<Double,Integer>>> SitSummerbyTemperature=SortPMVArray("temperature",PMVSitSummer);
		Map<Double,List<PP<Double,Integer>>> StandSummerbyTemperature=SortPMVArray("temperature",PMVStandSummer);
		Map<Double,List<PP<Double,Integer>>> SleepWinterbyTemperature=SortPMVArray("temperature",PMVSleepWinter);
		Map<Double,List<PP<Double,Integer>>> SitWinterbyTemperature=SortPMVArray("temperature",PMVSitWinter);
		Map<Double,List<PP<Double,Integer>>> StandWinterbyTemperature=SortPMVArray("temperature",PMVStandWinter);
		//PMV Sort by humidity
		Map<Double,List<PP<Double,Integer>>> SleepSummerbyHumidity=SortPMVArray("humidity",PMVSleepSummer);
		Map<Double,List<PP<Double,Integer>>> SitSummerbyHumidity=SortPMVArray("humidity",PMVSitSummer);
		Map<Double,List<PP<Double,Integer>>> StandSummerbyHumidity=SortPMVArray("humidity",PMVStandSummer);
		Map<Double,List<PP<Double,Integer>>> SleepWinterbyHumidity=SortPMVArray("humidity",PMVSleepWinter);
		Map<Double,List<PP<Double,Integer>>> SitWinterbyHumidity=SortPMVArray("humidity",PMVSitWinter);
		Map<Double,List<PP<Double,Integer>>> StandWinterbyHumidity=SortPMVArray("humidity",PMVStandWinter);
		
		System.out.println("Calculation Done");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 503, 545);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0, 0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JTextArea textArea_PMVResult = new JTextArea();
		GridBagConstraints gbc_textArea_PMVResult = new GridBagConstraints();
		gbc_textArea_PMVResult.insets = new Insets(0, 0, 5, 0);
		gbc_textArea_PMVResult.fill = GridBagConstraints.BOTH;
		gbc_textArea_PMVResult.gridx = 2;
		gbc_textArea_PMVResult.gridy = 6;
		contentPane.add(textArea_PMVResult, gbc_textArea_PMVResult);
		
		JLabel lblPmvQuery = new JLabel("PMV Query");
		GridBagConstraints gbc_lblPmvQuery = new GridBagConstraints();
		gbc_lblPmvQuery.gridwidth = 3;
		gbc_lblPmvQuery.insets = new Insets(0, 0, 5, 0);
		gbc_lblPmvQuery.gridx = 0;
		gbc_lblPmvQuery.gridy = 0;
		contentPane.add(lblPmvQuery, gbc_lblPmvQuery);
		
		JLabel lblTemperatureInput = new JLabel("Temperature");
		GridBagConstraints gbc_lblTemperatureInput = new GridBagConstraints();
		gbc_lblTemperatureInput.gridwidth = 2;
		gbc_lblTemperatureInput.anchor = GridBagConstraints.EAST;
		gbc_lblTemperatureInput.insets = new Insets(0, 0, 5, 5);
		gbc_lblTemperatureInput.gridx = 0;
		gbc_lblTemperatureInput.gridy = 1;
		contentPane.add(lblTemperatureInput, gbc_lblTemperatureInput);
		
		String[] temperatures = {																					"15.0", "15.5", "16.0", "16.5", "17.0", "17.5", "18.0", "18.5", "19.0", "19.5",
									"20.0", "20.5", "21.0", "21.5", "22.0", "22.5", "23.0", "23.5", "24.0", "24.5", "25.0", "25.5", "26.0", "26.5", "27.0", "27.5", "28.0", "28.5", "29.0", "29.5",
									"30.0", "30.5", "31.0", "31.5", "32.0", "32.5", "33.0", "33.5", "34.0", "34.5", "35.0", "35.5", "36.0", "36.5", "37.0", "37.5", "38.0", "38.5", "39.0", "39.5",
									"40.0" };
		JComboBox comboBox_TemperatureQuery = new JComboBox(temperatures);
		comboBox_TemperatureQuery.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentTemperature = (String)comboBox_TemperatureQuery.getSelectedItem();
			}
		});
		GridBagConstraints gbc_comboBox_TemperatureQuery = new GridBagConstraints();
		gbc_comboBox_TemperatureQuery.insets = new Insets(0, 0, 5, 0);
		gbc_comboBox_TemperatureQuery.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox_TemperatureQuery.gridx = 2;
		gbc_comboBox_TemperatureQuery.gridy = 1;
		contentPane.add(comboBox_TemperatureQuery, gbc_comboBox_TemperatureQuery);
		
		JLabel lblHumidityInput = new JLabel("Humidity");
		GridBagConstraints gbc_lblHumidityInput = new GridBagConstraints();
		gbc_lblHumidityInput.anchor = GridBagConstraints.EAST;
		gbc_lblHumidityInput.gridwidth = 2;
		gbc_lblHumidityInput.insets = new Insets(0, 0, 5, 5);
		gbc_lblHumidityInput.gridx = 0;
		gbc_lblHumidityInput.gridy = 2;
		contentPane.add(lblHumidityInput, gbc_lblHumidityInput);
		
		String[] humidities = { "0", "5", "10", "15", "20", "25", "30", "35", "40", "45", "50", "55", "60", "65", "70", "75", "80", "85", "90", "95", "100"};
		JComboBox comboBox_HumidityQuery = new JComboBox(humidities);
		comboBox_HumidityQuery.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentHumidity = (String)comboBox_HumidityQuery.getSelectedItem();
			}
		});
		GridBagConstraints gbc_comboBox_HumidityQuery = new GridBagConstraints();
		gbc_comboBox_HumidityQuery.insets = new Insets(0, 0, 5, 0);
		gbc_comboBox_HumidityQuery.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox_HumidityQuery.gridx = 2;
		gbc_comboBox_HumidityQuery.gridy = 2;
		contentPane.add(comboBox_HumidityQuery, gbc_comboBox_HumidityQuery);
		
		JLabel lblActionInput = new JLabel("Action");
		GridBagConstraints gbc_lblActionInput = new GridBagConstraints();
		gbc_lblActionInput.anchor = GridBagConstraints.EAST;
		gbc_lblActionInput.gridwidth = 2;
		gbc_lblActionInput.insets = new Insets(0, 0, 5, 5);
		gbc_lblActionInput.gridx = 0;
		gbc_lblActionInput.gridy = 3;
		contentPane.add(lblActionInput, gbc_lblActionInput);
		
		String[] actions = {"sleep", "sit", "stand"};
		JComboBox comboBox_ActionQuery = new JComboBox(actions);
		comboBox_ActionQuery.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentAction = (String)comboBox_ActionQuery.getSelectedItem();
			}
		});
		GridBagConstraints gbc_comboBox_ActionQuery = new GridBagConstraints();
		gbc_comboBox_ActionQuery.insets = new Insets(0, 0, 5, 0);
		gbc_comboBox_ActionQuery.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox_ActionQuery.gridx = 2;
		gbc_comboBox_ActionQuery.gridy = 3;
		contentPane.add(comboBox_ActionQuery, gbc_comboBox_ActionQuery);
		
		JLabel lblSeasonInput = new JLabel("Season");
		GridBagConstraints gbc_lblSeasonInput = new GridBagConstraints();
		gbc_lblSeasonInput.anchor = GridBagConstraints.EAST;
		gbc_lblSeasonInput.gridwidth = 2;
		gbc_lblSeasonInput.insets = new Insets(0, 0, 5, 5);
		gbc_lblSeasonInput.gridx = 0;
		gbc_lblSeasonInput.gridy = 4;
		contentPane.add(lblSeasonInput, gbc_lblSeasonInput);
		
		String[] seasons = {"summer", "winter"};
		JComboBox comboBox_SeasonQuery = new JComboBox(seasons);
		comboBox_SeasonQuery.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentSeason = (String)comboBox_SeasonQuery.getSelectedItem();
			}
		});
		GridBagConstraints gbc_comboBox_SeasonQuery = new GridBagConstraints();
		gbc_comboBox_SeasonQuery.insets = new Insets(0, 0, 5, 0);
		gbc_comboBox_SeasonQuery.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox_SeasonQuery.gridx = 2;
		gbc_comboBox_SeasonQuery.gridy = 4;
		contentPane.add(comboBox_SeasonQuery, gbc_comboBox_SeasonQuery);
		
		JButton btnCalculate_PMVQuery = new JButton("Calculate");
		btnCalculate_PMVQuery.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(currentAction.equals("sleep") && currentSeason.equals("summer") )
				{
					PMVtable = PMVSleepSummer;
				}
				else if(currentAction.equals("sit") && currentSeason.equals("summer") )
				{
					PMVtable = PMVSitSummer;
				}
				else if(currentAction.equals("stand") && currentSeason.equals("summer") )
				{
					PMVtable = PMVStandSummer;
				}
				else if(currentAction.equals("sleep") && currentSeason.equals("winter") )
				{
					PMVtable = PMVSleepWinter;
				}
				else if(currentAction.equals("sit") && currentSeason.equals("winter") )
				{
					PMVtable = PMVSitWinter;
				}
				else if(currentAction.equals("stand") && currentSeason.equals("winter") )
				{
					PMVtable = PMVStandWinter;
				}
				
				int indexT = (int)((Double.parseDouble(currentTemperature)-T_lower)/T_interval);
				int indexH = (int)((Double.parseDouble(currentHumidity)-H_lower)/H_interval);
				
				content_PMVvalue = Double.toString(PMVtable[indexT][indexH]);
				textArea_PMVResult.setText(content_PMVvalue);
			}
		});
		GridBagConstraints gbc_btnCalculate_PMVQuery = new GridBagConstraints();
		gbc_btnCalculate_PMVQuery.anchor = GridBagConstraints.EAST;
		gbc_btnCalculate_PMVQuery.insets = new Insets(0, 0, 5, 0);
		gbc_btnCalculate_PMVQuery.gridx = 2;
		gbc_btnCalculate_PMVQuery.gridy = 5;
		contentPane.add(btnCalculate_PMVQuery, gbc_btnCalculate_PMVQuery);
		
		
		
		JLabel lblComfortZoneCalculation = new JLabel("Comfort Zone Calculation");
		GridBagConstraints gbc_lblComfortZoneCalculation = new GridBagConstraints();
		gbc_lblComfortZoneCalculation.gridwidth = 3;
		gbc_lblComfortZoneCalculation.insets = new Insets(0, 0, 5, 0);
		gbc_lblComfortZoneCalculation.gridx = 0;
		gbc_lblComfortZoneCalculation.gridy = 7;
		contentPane.add(lblComfortZoneCalculation, gbc_lblComfortZoneCalculation);
		
		JLabel lblPmvRange = new JLabel("PMV Range");
		GridBagConstraints gbc_lblPmvRange = new GridBagConstraints();
		gbc_lblPmvRange.anchor = GridBagConstraints.EAST;
		gbc_lblPmvRange.insets = new Insets(0, 0, 5, 5);
		gbc_lblPmvRange.gridx = 0;
		gbc_lblPmvRange.gridy = 8;
		contentPane.add(lblPmvRange, gbc_lblPmvRange);
		
		JLabel lblFrom = new JLabel("From");
		GridBagConstraints gbc_lblFrom = new GridBagConstraints();
		gbc_lblFrom.insets = new Insets(0, 0, 5, 5);
		gbc_lblFrom.anchor = GridBagConstraints.EAST;
		gbc_lblFrom.gridx = 1;
		gbc_lblFrom.gridy = 8;
		contentPane.add(lblFrom, gbc_lblFrom);
		
		String[] PMVlowerRange = {	"-3.0", "-2.9", "-2.8", "-2.7", "-2.6", "-2.5", "-2.4", "-2.3", "-2.2", "-2.1",
									"-2.0", "-1.9", "-1.8", "-1.7", "-1.6", "-1.5", "-1.4", "-1.3", "-1.2", "-1.1",
									"-1.0", "-0.9", "-0.8", "-0.7", "-0.6", "-0.5", "-0.4", "-0.3", "-0.2", "-0.1", 
									"0.0", 
									"0.1", "0.2", "0.3", "0.4", "0.5", "0.6", "0.7", "0.8", "0.9", "1.0",
									"1.1", "1.2", "1.3", "1.4", "1.5", "1.6", "1.7", "1.8", "1.9", "2.0",
									"2.1", "2.2", "2.3", "2.4", "2.5", "2.6", "2.7", "2.8", "2.9", "3.0" };
		JComboBox comboBox_PMVlower = new JComboBox(PMVlowerRange);
		comboBox_PMVlower.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PMVlower = (String)comboBox_PMVlower.getSelectedItem();
			}
		});
		GridBagConstraints gbc_comboBox_PMVlower = new GridBagConstraints();
		gbc_comboBox_PMVlower.insets = new Insets(0, 0, 5, 0);
		gbc_comboBox_PMVlower.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox_PMVlower.gridx = 2;
		gbc_comboBox_PMVlower.gridy = 8;
		contentPane.add(comboBox_PMVlower, gbc_comboBox_PMVlower);
		
		JLabel lblTo = new JLabel("To");
		GridBagConstraints gbc_lblTo = new GridBagConstraints();
		gbc_lblTo.anchor = GridBagConstraints.EAST;
		gbc_lblTo.insets = new Insets(0, 0, 5, 5);
		gbc_lblTo.gridx = 1;
		gbc_lblTo.gridy = 9;
		contentPane.add(lblTo, gbc_lblTo);
		
		String[] PMVupperRange = {	"-3.0", "-2.9", "-2.8", "-2.7", "-2.6", "-2.5", "-2.4", "-2.3", "-2.2", "-2.1",
				"-2.0", "-1.9", "-1.8", "-1.7", "-1.6", "-1.5", "-1.4", "-1.3", "-1.2", "-1.1",
				"-1.0", "-0.9", "-0.8", "-0.7", "-0.6", "-0.5", "-0.4", "-0.3", "-0.2", "-0.1", 
				"0.0", 
				"0.1", "0.2", "0.3", "0.4", "0.5", "0.6", "0.7", "0.8", "0.9", "1.0",
				"1.1", "1.2", "1.3", "1.4", "1.5", "1.6", "1.7", "1.8", "1.9", "2.0",
				"2.1", "2.2", "2.3", "2.4", "2.5", "2.6", "2.7", "2.8", "2.9", "3.0" };
		JComboBox comboBox_PMVupper = new JComboBox(PMVupperRange);
		comboBox_PMVupper.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PMVupper = (String)comboBox_PMVupper.getSelectedItem();
			}
		});
		GridBagConstraints gbc_comboBox_PMVupper = new GridBagConstraints();
		gbc_comboBox_PMVupper.insets = new Insets(0, 0, 5, 0);
		gbc_comboBox_PMVupper.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox_PMVupper.gridx = 2;
		gbc_comboBox_PMVupper.gridy = 9;
		contentPane.add(comboBox_PMVupper, gbc_comboBox_PMVupper);
		
		JLabel lblAction = new JLabel("Action");
		GridBagConstraints gbc_lblAction = new GridBagConstraints();
		gbc_lblAction.gridwidth = 2;
		gbc_lblAction.anchor = GridBagConstraints.EAST;
		gbc_lblAction.insets = new Insets(0, 0, 5, 5);
		gbc_lblAction.gridx = 0;
		gbc_lblAction.gridy = 10;
		contentPane.add(lblAction, gbc_lblAction);
		
		JComboBox comboBox_Action = new JComboBox(actions);
		comboBox_Action.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				action_ComfortZone = (String)comboBox_Action.getSelectedItem();
			}
		});
		comboBox_Action.setToolTipText("");
		GridBagConstraints gbc_comboBox_Action = new GridBagConstraints();
		gbc_comboBox_Action.insets = new Insets(0, 0, 5, 0);
		gbc_comboBox_Action.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox_Action.gridx = 2;
		gbc_comboBox_Action.gridy = 10;
		contentPane.add(comboBox_Action, gbc_comboBox_Action);
		
		JLabel lblSeason = new JLabel("Season");
		GridBagConstraints gbc_lblSeason = new GridBagConstraints();
		gbc_lblSeason.gridwidth = 2;
		gbc_lblSeason.anchor = GridBagConstraints.EAST;
		gbc_lblSeason.insets = new Insets(0, 0, 5, 5);
		gbc_lblSeason.gridx = 0;
		gbc_lblSeason.gridy = 11;
		contentPane.add(lblSeason, gbc_lblSeason);
		
		
		JComboBox comboBox_Season = new JComboBox(seasons);
		comboBox_Season.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				season_ComfortZone = (String)comboBox_Season.getSelectedItem();
			}
		});
		comboBox_Season.setToolTipText("");
		GridBagConstraints gbc_comboBox_Season = new GridBagConstraints();
		gbc_comboBox_Season.insets = new Insets(0, 0, 5, 0);
		gbc_comboBox_Season.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox_Season.gridx = 2;
		gbc_comboBox_Season.gridy = 11;
		contentPane.add(comboBox_Season, gbc_comboBox_Season);
		
		JLabel lblSortBy = new JLabel("Sort by");
		GridBagConstraints gbc_lblSortBy = new GridBagConstraints();
		gbc_lblSortBy.gridwidth = 2;
		gbc_lblSortBy.anchor = GridBagConstraints.EAST;
		gbc_lblSortBy.insets = new Insets(0, 0, 5, 5);
		gbc_lblSortBy.gridx = 0;
		gbc_lblSortBy.gridy = 12;
		contentPane.add(lblSortBy, gbc_lblSortBy);
		
		String[] orders = {"temperature", "humidity"};
		JComboBox comboBox_Sorting = new JComboBox(orders);
		comboBox_Sorting.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				order_ComfortZone = (String)comboBox_Sorting.getSelectedItem();
			}
		});
		comboBox_Sorting.setToolTipText("");
		GridBagConstraints gbc_comboBox_Sorting = new GridBagConstraints();
		gbc_comboBox_Sorting.insets = new Insets(0, 0, 5, 0);
		gbc_comboBox_Sorting.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox_Sorting.gridx = 2;
		gbc_comboBox_Sorting.gridy = 12;
		contentPane.add(comboBox_Sorting, gbc_comboBox_Sorting);
		
		JLabel lblResult = new JLabel("Result (T,H)");
		GridBagConstraints gbc_lblResult = new GridBagConstraints();
		gbc_lblResult.anchor = GridBagConstraints.NORTH;
		gbc_lblResult.insets = new Insets(0, 0, 5, 5);
		gbc_lblResult.gridx = 0;
		gbc_lblResult.gridy = 14;
		contentPane.add(lblResult, gbc_lblResult);
		
		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 2;
		gbc_scrollPane.gridy = 14;
		contentPane.add(scrollPane, gbc_scrollPane);
		
		JTextArea textArea_ComfortZoneResult = new JTextArea();
		scrollPane.setViewportView(textArea_ComfortZoneResult);
		textArea_ComfortZoneResult.setLineWrap(true);
		
		JButton btnCalculate_ComfortZone = new JButton("Calculate");
		btnCalculate_ComfortZone.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//System.out.println("action");
				//System.out.println(action);
				//System.out.println("season");
				//System.out.println(season);
				//System.out.println("order");
				//System.out.println(order);
				//System.out.println("==========================");
				
				if(action_ComfortZone.equals("sleep") && season_ComfortZone.equals("summer") && order_ComfortZone.equals("temperature") )
				{
					PMVmap = SleepSummerbyTemperature;
					System.out.println("1");
				}
				else if(action_ComfortZone.equals("sit") && season_ComfortZone.equals("summer") && order_ComfortZone.equals("temperature") )
				{
					PMVmap = SitSummerbyTemperature;
					System.out.println("2");
				}
				else if(action_ComfortZone.equals("stand") && season_ComfortZone.equals("summer") && order_ComfortZone.equals("temperature") )
				{
					PMVmap = StandSummerbyTemperature;
					System.out.println("3");
				}
				else if(action_ComfortZone.equals("sleep") && season_ComfortZone.equals("winter") && order_ComfortZone.equals("temperature") )
				{
					PMVmap = SleepWinterbyTemperature;
					System.out.println("4");
				}
				else if(action_ComfortZone.equals("sit") && season_ComfortZone.equals("winter") && order_ComfortZone.equals("temperature") )
				{
					PMVmap = SitWinterbyTemperature;
					System.out.println("5");
				}
				else if(action_ComfortZone.equals("stand") && season_ComfortZone.equals("winter") && order_ComfortZone.equals("temperature") )
				{
					PMVmap = StandWinterbyTemperature;
					System.out.println("6");
				}
				else if(action_ComfortZone.equals("sleep") && season_ComfortZone.equals("summer") && order_ComfortZone.equals("humidity") )
				{
					PMVmap = SleepSummerbyHumidity;
					System.out.println("7");
				}
				else if(action_ComfortZone.equals("sit") && season_ComfortZone.equals("summer") && order_ComfortZone.equals("humidity") )
				{
					PMVmap = SitSummerbyHumidity;
					System.out.println("8");
				}
				else if(action_ComfortZone.equals("stand") && season_ComfortZone.equals("summer") && order_ComfortZone.equals("humidity") )
				{
					PMVmap = StandSummerbyHumidity;
					System.out.println("9");
				}
				else if(action_ComfortZone.equals("sleep") && season_ComfortZone.equals("winter") && order_ComfortZone.equals("humidity") )
				{
					PMVmap = SleepWinterbyHumidity;
					System.out.println("10");
				}
				else if(action_ComfortZone.equals("sit") && season_ComfortZone.equals("winter") && order_ComfortZone.equals("humidity") )
				{
					PMVmap = SitWinterbyHumidity;
					System.out.println("11");
				}
				else if(action_ComfortZone.equals("stand") && season_ComfortZone.equals("winter") && order_ComfortZone.equals("humidity") )
				{
					PMVmap = StandWinterbyHumidity;
					System.out.println("12");
				}
				
				double PMVlower_value = roundToDec(Double.parseDouble(PMVlower),1);
				double PMVupper_value = roundToDec(Double.parseDouble(PMVupper),1);
				if(PMVlower_value>PMVupper_value)
				{
					content_ComfortZone = "PMV range is invalid!";
				}
				else{
					content_ComfortZone = "";
					for (double PMVvalue=PMVlower_value;PMVvalue<PMVupper_value+0.1;PMVvalue+=0.1)
					{
						double key = roundToDec(PMVvalue,1);
						System.out.println("key"+key);
						if(PMVmap.containsKey(key))
						{
							for(int index=0;index < PMVmap.get(key).size();++index)
							{
								content_ComfortZone=content_ComfortZone+"("+Double.toString(roundToDec(PMVmap.get(key).get(index).x,1))+","+Integer.toString(PMVmap.get(key).get(index).y)+") ";
							}
							content_ComfortZone=content_ComfortZone+"\n";
						}
					}
					if(content_ComfortZone.contentEquals(""))
					{
						content_ComfortZone="PMV value is out of range!";
					}
				}
				textArea_ComfortZoneResult.setText(content_ComfortZone);
			}
		});
		
		GridBagConstraints gbc_btnCalculate_ComfortZone = new GridBagConstraints();
		gbc_btnCalculate_ComfortZone.anchor = GridBagConstraints.EAST;
		gbc_btnCalculate_ComfortZone.insets = new Insets(0, 0, 5, 0);
		gbc_btnCalculate_ComfortZone.gridx = 2;
		gbc_btnCalculate_ComfortZone.gridy = 13;
		contentPane.add(btnCalculate_ComfortZone, gbc_btnCalculate_ComfortZone);
	}
	
	
	double GetPMVFromArray(double temp, int humid, double[][] PMVarray)
	{
		int i = (int)(Math.round(temp*10)-200);
		int j = humid;
		return PMVarray[i][j];
	}
	
	static Map<Double,List<PP<Double,Integer>>> SortPMVArray(String priority, double[][] PMVarray)
	{
		Map<Double,List<PP<Double,Integer>>> PMVmap = ArraytoMap(PMVarray);
		
		Iterator it = PMVmap.entrySet().iterator();
		while(it.hasNext())
		{
			Map.Entry pair = (Map.Entry)it.next();
			Double key=(Double) pair.getKey();
			List<PP<Double,Integer>> oldList= new ArrayList<PP<Double,Integer>>( (List<PP<Double,Integer>>)pair.getValue() );
			List<PP<Double,Integer>> sortedList;
			if(priority.contentEquals("temperature")){
				sortedList = SortListbyFirstEntry(oldList);
			}
			else if(priority.contentEquals("humidity")){
				sortedList = SortListbySecondEntry(oldList);
			}
			else
			{
				System.out.print("undefined order");
				return null;
			}
			PMVmap.put(key,sortedList);
		}
		it.remove();
		return PMVmap;
	}
	
	static List<PP<Double,Integer>> SortListbyFirstEntry(List<PP<Double,Integer>> list)
	{
		Collections.sort(list, new Comparator<PP<Double,Integer>>()
				{
					public int compare(PP<Double,Integer> p1, PP<Double,Integer> p2)
					{
						if (p1.x > p2.x) return 1;
						else if (p1.x < p2.x) return -1;
						else if (p1.y > p2.y) return 1;
						else if (p1.y < p2.y) return -1;
						else return 0;
					}
				});
		return list;
	}
	
	static List<PP<Double,Integer>> SortListbySecondEntry(List<PP<Double,Integer>> list)
	{
		Collections.sort(list, new Comparator<PP<Double,Integer>>()
				{
					public int compare(PP<Double,Integer> p1, PP<Double,Integer> p2)
					{
						if (p1.y > p2.y) return 1;
						else if (p1.y < p2.y) return -1;
						else if (p1.x > p2.x) return 1;
						else if (p1.x < p2.x) return -1;
						else return 0;
					}
				});
		return list;
	}
	
	static Map<Double,List<PP<Double,Integer>>> ArraytoMap(double[][] array)
	{
		Map<Double,List<PP<Double,Integer>>> map = new HashMap<Double,List<PP<Double,Integer>>>();
		
		for(int rh=H_lower;rh<H_upper+H_interval;rh+=H_interval){
			for(double t=T_lower;t<T_upper+T_interval;t+=T_interval){
				int i = (int)Math.round((t-T_lower)/T_interval);
				int j = (rh-H_lower)/H_interval;
				
				double entry1 = array[i][j];
				
				if(map.containsKey(entry1)){
					map.get(entry1).add(new PP(t,rh));
				}
				else
				{
					List<PP<Double,Integer>> list=new ArrayList<PP<Double,Integer>>();
					list.add(new PP(t,rh));
					map.put(entry1, list);
				}
			}
		}
		return map;
	}
	
	static double[][] CalPMVArray(String action, String season)
	{
		double[][] PMVarray = new double[dimT][dimH];
		
		double t; // air temperature & mean radiant temperature (°C), from 20.0 to 40.0
		double vel = 0.1; // wind velocity (m/s), from 0.0 to 2.0
		double rh; // relative humidity (%), from 0 to 100
		double met; // metabolic rate (met), sleep-0.7, sit=1.0, stand=1.2
		double clo; // clothing (clo), summer=0.5, winter=1.0
		
		if(action.contentEquals("sleep"))
			met=0.7;
		else if (action.contentEquals("sit"))
			met=1.0;
		else if (action.contentEquals("stand"))
			met=1.2;
		else
		{
			System.out.print("Action undefined");
			return null;
		}
		
		if(season.contentEquals("summer"))
			clo=0.5;
		else if(season.contentEquals("winter"))
			clo=1.0;
		else
		{
			System.out.print("Season undefined");
			return null;
		}
		
		System.out.println("action: "+action);
		System.out.println("season: "+season);
		
		int i = 0; // x index, t
		int j = 0; // y index, rh
		for(rh=H_lower;rh<H_upper+H_interval;rh+=H_interval){
			for(t=T_lower;t<T_upper+T_interval;t+=T_interval){
				double pmv=calPMV(t,t,vel,rh,met,clo,0.0);
				pmv=roundToDec(pmv,1);
				if (season.equals("summer")&&rh>H_comfort_upper) pmv=3.0;
				if (rh<H_comfort_lower) pmv=-3.0;
				if (t>T_comfort_upper) pmv=3.0;
				if (t<T_comfort_lower) pmv=-3.0;
				
				PMVarray[i][j]=pmv;
				i++;
				System.out.print(pmv+" ");
			}
			System.out.println(" ");
			j++;
			i=0;
		}
		return PMVarray;
	}
	
	static double calPMV(double ta, double tr, double vel, double rh, double met, double clo, double wme) {
	    // returns [pmv, ppd]
	    // ta, air temperature (?�°C)
	    // tr, mean radiant temperature (?�°C)
	    // vel, relative air velocity (m/s)
	    // rh, relative humidity (%) Used only this way to input humidity level
	    // met, metabolic rate (met)
	    // clo, clothing (clo)
	    // wme, external work, normally around 0 (met)

	    double pa, icl, m, w, mw, fcl, hcf, taa, tra, tcla, p1, p2, p3, p4,
	    p5, xn, xf, eps, hcn, hc = 1, tcl, hl1, hl2, hl3, hl4, hl5, hl6,
	    ts, pmv, ppd, n;

	    pa = rh * 10 * Math.exp(16.6536 - 4030.183 / (ta + 235));

	    icl = 0.155 * clo; //thermal insulation of the clothing in M2K/W
	    m = met * 58.15; //metabolic rate in W/M2
	    w = wme * 58.15; //external work in W/M2
	    mw = m - w; //internal heat production in the human body
	    if (icl <= 0.078) fcl = 1 + (1.29 * icl);
	    else fcl = 1.05 + (0.645 * icl);

	    //heat transf. coeff. by forced convection
	    hcf = 12.1 * Math.sqrt(vel);
	    taa = ta + 273;
	    tra = tr + 273;
	    tcla = taa + (35.5 - ta) / (3.5 * icl + 0.1);

	    p1 = icl * fcl;
	    p2 = p1 * 3.96;
	    p3 = p1 * 100;
	    p4 = p1 * taa;
	    p5 = 308.7 - 0.028 * mw + p2 * Math.pow(tra / 100, 4);
	    xn = tcla / 100;
	    xf = tcla / 50;
	    eps = 0.00015;

	    n = 0;
	    while (Math.abs(xn - xf) > eps) {
	        xf = (xf + xn) / 2;
	        hcn = 2.38 * Math.pow(Math.abs(100.0 * xf - taa), 0.25);
	        if(hcf > hcn)
	        	hc = hcf;
	        else
	        	hc = hcn;
	        xn = (p5 + p4 * hc - p2 * Math.pow(xf, 4)) / (100 + p3 * hc);
	        ++n;
	        if (n > 150) {
	            System.out.print("Max iterations exceeded");
	            return -1000;
	        }
	    }

	    tcl = 100 * xn - 273;

	    // heat loss diff. through skin 
	    hl1 = 3.05 * 0.001 * (5733 - (6.99 * mw) - pa);
	    // heat loss by sweating
	    if (mw > 58.15) hl2 = 0.42 * (mw - 58.15);
	    else hl2 = 0;
	    // latent respiration heat loss 
	    hl3 = 1.7 * 0.00001 * m * (5867 - pa);
	    // dry respiration heat loss
	    hl4 = 0.0014 * m * (34 - ta);
	    // heat loss by radiation  
	    hl5 = 3.96 * fcl * (Math.pow(xn, 4) - Math.pow(tra / 100, 4));
	    // heat loss by convection
	    hl6 = fcl * hc * (tcl - ta);

	    ts = 0.303 * Math.exp(-0.036 * m) + 0.028;
	    pmv = ts * (mw - hl1 - hl2 - hl3 - hl4 - hl5 - hl6);
	    ppd = 100.0 - 95.0 * Math.exp(-0.03353 * Math.pow(pmv, 4.0) - 0.2179 * Math.pow(pmv, 2.0));

	    return pmv;
	}
	
	static double roundToDec(double num,int dec){
		// dec is how many digits after the point num to be rounded
		return Math.round(num*Math.pow(10, dec))/Math.pow(10, dec);
	}	
	

}
