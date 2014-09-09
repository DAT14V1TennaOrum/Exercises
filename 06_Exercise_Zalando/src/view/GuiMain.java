package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.*;
import java.sql.SQLException;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import dataaccess.DBConnect;

public class GuiMain extends JFrame implements ActionListener
{
	private JFrame frame;
	private JPanel panel;
	private JPanel panel2;
	private JPanel panel3;
	private JButton btnSearch;
	private JLabel welcomeLabel;
	
	private JTextArea textArea = new JTextArea();
	
	DBConnect dbc = new DBConnect();
	
	public GuiMain()
	{
		frame = new JFrame("ZALANDO");
		frame.setBounds(700, 200, 500, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		
		panel = new JPanel();
		panel2 = new JPanel();
		panel3 = new JPanel();
		frame.add(panel, BorderLayout.NORTH);
		frame.add(panel2, BorderLayout.CENTER);
		frame.add(panel3, BorderLayout.SOUTH);
		
		welcomeLabel = new JLabel("Velkommen til Zalando");	
		ImageIcon picture = new ImageIcon("Zalando-logo.png");
		btnSearch = new JButton("SØG EFTER PRODUKT");
		btnSearch.addActionListener(this);

		panel.add(welcomeLabel);
		panel2.add(new JLabel(picture));
		panel3.add(btnSearch);
		
		frame.setVisible(true);
	}
	
	public void resultFrame()
	{
		JFrame frame2 = new JFrame("Søgningsresultat");
		frame2.setBounds(700, 200, 500, 300);
		frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame2.setLayout(new FlowLayout());
		
		JPanel panel4 = new JPanel();
		frame2.add(panel4);
		
		textArea.setBounds(89,148,244,28);
		panel4.add(textArea);
		
		frame2.setVisible(true);
	}
	
	
	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == btnSearch)
		{
			String searchWord = JOptionPane.showInputDialog("Hvad vil du søge efter? ");
			try
			{
				dbc.dbSearch(searchWord);
				textArea.setText(dbc.alToString());
				resultFrame();
				
			} 
			catch (SQLException e1)
			{
				e1.printStackTrace();
			}
		}
	}
	
}
