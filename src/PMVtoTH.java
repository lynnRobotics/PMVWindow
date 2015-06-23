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
	private JTextField textField_PMVValue;
	
	String action = "sleep";
	String season = "summer";
	String order = "temperature";
	String PMVvalue = "0.0";

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
		
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JLabel lblPmvValue = new JLabel("PMV Value");
		GridBagConstraints gbc_lblPmvValue = new GridBagConstraints();
		gbc_lblPmvValue.anchor = GridBagConstraints.EAST;
		gbc_lblPmvValue.insets = new Insets(0, 0, 5, 5);
		gbc_lblPmvValue.gridx = 0;
		gbc_lblPmvValue.gridy = 0;
		contentPane.add(lblPmvValue, gbc_lblPmvValue);
		
		textField_PMVValue = new JTextField();
		textField_PMVValue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PMVvalue = textField_PMVValue.getText();
			}
		});
		GridBagConstraints gbc_textField_PMVValue = new GridBagConstraints();
		gbc_textField_PMVValue.insets = new Insets(0, 0, 5, 0);
		gbc_textField_PMVValue.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_PMVValue.gridx = 1;
		gbc_textField_PMVValue.gridy = 0;
		contentPane.add(textField_PMVValue, gbc_textField_PMVValue);
		textField_PMVValue.setColumns(10);
		
		JLabel lblAction = new JLabel("Action");
		GridBagConstraints gbc_lblAction = new GridBagConstraints();
		gbc_lblAction.anchor = GridBagConstraints.EAST;
		gbc_lblAction.insets = new Insets(0, 0, 5, 5);
		gbc_lblAction.gridx = 0;
		gbc_lblAction.gridy = 1;
		contentPane.add(lblAction, gbc_lblAction);
		
		String[] actions = {"sleep", "sit", "stand"};
		
		JComboBox comboBox_Action = new JComboBox(actions);
		comboBox_Action.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				action = (String)comboBox_Action.getSelectedItem();
				System.out.println(action);
			}
		});
		comboBox_Action.setToolTipText("");
		GridBagConstraints gbc_comboBox_Action = new GridBagConstraints();
		gbc_comboBox_Action.insets = new Insets(0, 0, 5, 0);
		gbc_comboBox_Action.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox_Action.gridx = 1;
		gbc_comboBox_Action.gridy = 1;
		contentPane.add(comboBox_Action, gbc_comboBox_Action);
		
		JLabel lblSeason = new JLabel("Season");
		GridBagConstraints gbc_lblSeason = new GridBagConstraints();
		gbc_lblSeason.anchor = GridBagConstraints.EAST;
		gbc_lblSeason.insets = new Insets(0, 0, 5, 5);
		gbc_lblSeason.gridx = 0;
		gbc_lblSeason.gridy = 2;
		contentPane.add(lblSeason, gbc_lblSeason);
		
		String[] seasons = {"summer", "winter"};
		
		JComboBox comboBox_Season = new JComboBox(seasons);
		comboBox_Season.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				season = (String)comboBox_Season.getSelectedItem();
			}
		});
		comboBox_Season.setToolTipText("");
		GridBagConstraints gbc_comboBox_Season = new GridBagConstraints();
		gbc_comboBox_Season.insets = new Insets(0, 0, 5, 0);
		gbc_comboBox_Season.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox_Season.gridx = 1;
		gbc_comboBox_Season.gridy = 2;
		contentPane.add(comboBox_Season, gbc_comboBox_Season);
		
		JLabel lblSortBy = new JLabel("Sort by");
		GridBagConstraints gbc_lblSortBy = new GridBagConstraints();
		gbc_lblSortBy.anchor = GridBagConstraints.EAST;
		gbc_lblSortBy.insets = new Insets(0, 0, 5, 5);
		gbc_lblSortBy.gridx = 0;
		gbc_lblSortBy.gridy = 3;
		contentPane.add(lblSortBy, gbc_lblSortBy);
		
		String[] orders = {"temperature", "humidity"};
		
		JComboBox comboBox_Sorting = new JComboBox(orders);
		comboBox_Sorting.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				order = (String)comboBox_Sorting.getSelectedItem();
			}
		});
		comboBox_Sorting.setToolTipText("");
		GridBagConstraints gbc_comboBox_Sorting = new GridBagConstraints();
		gbc_comboBox_Sorting.insets = new Insets(0, 0, 5, 0);
		gbc_comboBox_Sorting.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox_Sorting.gridx = 1;
		gbc_comboBox_Sorting.gridy = 3;
		contentPane.add(comboBox_Sorting, gbc_comboBox_Sorting);
		
		JLabel lblResult = new JLabel("Result (T,H)");
		GridBagConstraints gbc_lblResult = new GridBagConstraints();
		gbc_lblResult.anchor = GridBagConstraints.NORTH;
		gbc_lblResult.insets = new Insets(0, 0, 5, 5);
		gbc_lblResult.gridx = 0;
		gbc_lblResult.gridy = 5;
		contentPane.add(lblResult, gbc_lblResult);
		
		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 1;
		gbc_scrollPane.gridy = 5;
		contentPane.add(scrollPane, gbc_scrollPane);
		
		JTextArea textArea_Result = new JTextArea();
		scrollPane.setViewportView(textArea_Result);
		textArea_Result.setLineWrap(true);
		
		JButton btnCalculate1 = new JButton("Calculate");
		btnCalculate1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double[][] PMVarr=CalPMVArray(action,season);
				Map<Double,List<PP<Double,Integer>>> PMVmap = SortPMVArray(order,PMVarr);
				if(!PMVmap.containsKey(Double.parseDouble(PMVvalue)))
				{
					content = "PMV Value out of range!";
					textArea_Result.setText(content);
				}
				else{
					content = "";
					for(int index=0;index < PMVmap.get(Double.parseDouble(PMVvalue)).size();++index)
					{
						content=content+"("+Double.toString(roundToDec(PMVmap.get(Double.parseDouble(PMVvalue)).get(index).x,1))+","+Integer.toString(PMVmap.get(Double.parseDouble(PMVvalue)).get(index).y)+")\n";
						textArea_Result.setText(content);
					}
				}
			}
		});
		
		GridBagConstraints gbc_btnCalculate = new GridBagConstraints();
		gbc_btnCalculate.anchor = GridBagConstraints.EAST;
		gbc_btnCalculate.insets = new Insets(0, 0, 5, 0);
		gbc_btnCalculate.gridx = 1;
		gbc_btnCalculate.gridy = 4;
		contentPane.add(btnCalculate1, gbc_btnCalculate);
	}
	
	static String content;
	
	final static int dimT = 20*10 + 1;
	final static int dimH = 100/1 + 1; 
	
	double GetPMVFromArray(double temp, int humid, double[][] PMVarray)
	{
		int i = (int)(Math.round(temp*10)-200);
		int j = humid;
		return PMVarray[i][j];
	}
	
	Map<Double,List<PP<Double,Integer>>> SortPMVArray(String priority, double[][] PMVarray)
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
	
	List<PP<Double,Integer>> SortListbyFirstEntry(List<PP<Double,Integer>> list)
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
	
	List<PP<Double,Integer>> SortListbySecondEntry(List<PP<Double,Integer>> list)
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
	
	Map<Double,List<PP<Double,Integer>>> ArraytoMap(double[][] array)
	{
		Map<Double,List<PP<Double,Integer>>> map = new HashMap<Double,List<PP<Double,Integer>>>();
		
		for(int rh=0;rh<=100;++rh){
			for(double t=20.0;t<40.1;t+=0.1){
				int i = (int)(Math.round(t*10)-200);
				int j = rh;
				
				double entry1 = array[i][j];
				
				if(map.containsKey(entry1)){
					map.get(entry1).add(new PP(t,j));
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
	
	double[][] CalPMVArray(String action, String season)
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
		
		int i = 0; // x index, t
		int j = 0; // y index, rh
		for(rh=0;rh<=100;++rh){
			for(t=20.0;t<40.1;t+=0.1){
				double pmv=calPMV(t,t,vel,rh,met,clo,0.0);
				pmv=roundToDec(pmv,1);
				PMVarray[i][j]=pmv;
				i++;
			}
			j++;
			i=0;
		}
		return PMVarray;
	}
	
	static void WritePMVtoFile(String action, String season) throws IOException // varies with action and season
	{
		double t; // air temperature & mean radiant temperature (°C), from 20.0 to 40.0
		double vel; // wind velocity (m/s), from 0.0 to 2.0
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
			return;
		}
		
		if(season.contentEquals("summer"))
			clo=0.5;
		else if(season.contentEquals("winter"))
			clo=1.0;
		else
		{
			System.out.print("Season undefined");
			return;
		}
		

		for(vel=0.0;vel<2.1;vel+=0.1){
			String filename = new String(action+"_"+season+"_WindVel_"+Double.toString(roundToDec(vel,1)));
			File fout= new File(filename+".txt");
			FileOutputStream fos = new FileOutputStream(fout);
			OutputStreamWriter osw= new OutputStreamWriter(fos);
			osw.write(filename);
			osw.write("\n");
			osw.write("HumidTemp ");
			for (t=20.0;t<40.1;t+=0.1)
				osw.write(Double.toString(roundToDec(t,1))+" ");
			osw.write("\n");
			for(rh=0;rh<=100;++rh){
				//System.out.print(Double.toString(rh));
				osw.write(Double.toString(rh)+" ");
				for(t=20.0;t<40.1;t+=0.1){
					double pmv=calPMV(t,t,vel,rh,met,clo,0.0);
					pmv=roundToDec(pmv,1);
					osw.write(pmv+" ");
					//System.out.print(pmv+" ");
				}
				osw.write("\n");
				//System.out.println(" ");
			}
			osw.close();
		}	
			
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
