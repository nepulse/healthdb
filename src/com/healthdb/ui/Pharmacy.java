package com.healthdb.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.*;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableColumnModel;

import com.healthdb.dbconn.DatbaseConnector;

class Pharmacy extends JFrame implements ActionListener {
	private static final long serialVersionUID = -3164651464947903413L;
	DatbaseConnector dm;
	/*
	 * TableColumnModel colmodel; Doctor model; JTable viewTable;
	 */ JTextField ID;
	JTextField UserName;
	JTextField Password;
	JTextField MailID;

	JComboBox genderCombo;

	JComboBox MedicalStream;
	JComboBox Specialization;
	JTextField FullName;
	JTextField Degree;
	JTextField Designation;
	JTextField Services;
	JTextField Experience;
	JTextField ProfilePath;
	JComboBox SPONCERSHIP;
	JButton addButton;

	Pharmacy() {
		initMainUI();
		initDBConnection();
		createUIComponent();

		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				try {
					dm.closeConnection();
				} catch (SQLException se) {
					se.printStackTrace();
				}
				System.exit(0);
			}
		});
		setVisible(true);

	}

	/**
	 * 
	 */
	private void initDBConnection() {
		dm = DatbaseConnector.getInstance();
		// addSampleDataToDatabase();

		// create data model for a table component
		// model = new Doctor();
		// add column names to the model
		// model.setModelFieldName();
		// read sample data from the database and place them in the model
		// dm.getDoctorDataFromDB(model);
	}

	/**
	 * 
	 */
	private void initMainUI() {
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setTitle("Doctor");
		setSize(1000, 800);
		GridLayout parentLayout = new GridLayout(0,1);
		parentLayout.setVgap(5);
		setLayout(parentLayout);
	}

	private void createUIComponent() {
		
		final DefaultComboBoxModel gender = new DefaultComboBoxModel();
		gender.addElement("Male");
		gender.addElement("Female");
		gender.addElement("Others");

		final DefaultComboBoxModel medicalstream = new DefaultComboBoxModel();
		medicalstream.addElement("Allopathic");
		medicalstream.addElement("Ayurvedic");
		medicalstream.addElement("Homeopathy");

		final DefaultComboBoxModel specialization = new DefaultComboBoxModel();
		specialization.addElement("GeneralPhysician");
		specialization.addElement("Neurologist");
		specialization.addElement("ENT");
		specialization.addElement("Dermatologist");
		specialization.addElement("Gastroenterologist");
		specialization.addElement("GeneralSurgery");
		specialization.addElement("Psychiatrist");
		specialization.addElement("Physiotherapist");
		specialization.addElement("Orthopaedist");
		specialization.addElement("Dentist");
		specialization.addElement("Medicine");
		specialization.addElement("Urology");
		specialization.addElement("Gynaecologist");
		specialization.addElement("Endocrinologist");
		specialization.addElement("Dietician");
		specialization.addElement("Paediatrician");
		specialization.addElement("Cardiologist");

		final DefaultComboBoxModel sponcership = new DefaultComboBoxModel();
		sponcership.addElement("0");
		sponcership.addElement("1");
		sponcership.addElement("2");
		sponcership.addElement("3");
		sponcership.addElement("4");
		sponcership.addElement("5");

		ID = new JTextField(20);
		UserName = new JTextField(20);
		Password = new JPasswordField(20);
		MailID = new JTextField(20);
		FullName = new JTextField(20);
		Degree = new JTextField(10);
		Designation = new JTextField(10);
		Services = new JTextField(50);
		Experience = new JTextField(5);
		ProfilePath = new JTextField(50);

		genderCombo = new JComboBox(gender);
		genderCombo.setSelectedIndex(0);
		MedicalStream = new JComboBox(medicalstream);
		MedicalStream.setSelectedIndex(0);
		Specialization = new JComboBox(specialization);
		Specialization.setSelectedIndex(0);

		SPONCERSHIP = new JComboBox(sponcership);
		SPONCERSHIP.setSelectedIndex(0);

		addButton = new JButton("Add");
		addButton.setActionCommand("addNewDoctor");
		addButton.addActionListener(this);

		JPanel addUIPanel = new JPanel();
		addUIPanel.setLayout(new GridLayout(0,2));
		addUIPanel.add(new JLabel("User ID : "));
		addUIPanel.add(UserName);
		addUIPanel.add(new JLabel("Password : "));
		addUIPanel.add(Password);
		addUIPanel.add(new JLabel("MailID : "));
		addUIPanel.add(MailID);
		addUIPanel.add(new JLabel("Full Name"));
		addUIPanel.add(FullName);
		addUIPanel.add(new JLabel("Degree"));
		addUIPanel.add(Degree);
		addUIPanel.add(new JLabel("Designation"));
		addUIPanel.add(Designation);
		addUIPanel.add(new JLabel("Services"));
		addUIPanel.add(Services);
		addUIPanel.add(new JLabel("Expirience"));
		addUIPanel.add(Experience);
		addUIPanel.add(new JLabel("Profile Path"));
		addUIPanel.add(ProfilePath);
		addUIPanel.add(new JLabel("Gender"));
		addUIPanel.add(genderCombo);
		addUIPanel.add(new JLabel("Medical Stream"));
		addUIPanel.add(MedicalStream);
		addUIPanel.add(new JLabel("Specialization"));
		addUIPanel.add(Specialization);
		addUIPanel.add(new JLabel("Sponcership"));
		addUIPanel.add(SPONCERSHIP);
		
		addUIPanel.add(addButton);

		// create a table object
//		viewTable = new JTable(model);
//		JScrollPane scrollpane = new JScrollPane(viewTable);
//		add(scrollpane, BorderLayout.NORTH);
		add(addUIPanel, BorderLayout.NORTH);

	}

	public void actionPerformed(ActionEvent e) {

		String string = "ID:" + ID.getText() + " UserName:" + UserName.getText() + " Password:" + Password.getText()
				+ " FullName:" + FullName.getText() + " Medical Stream:" + MedicalStream.getSelectedItem();

		// Display the selected item
		System.out.println("Value selected = " + string);
		dm.insertSampleData(UserName.getText(), Password.getText(), FullName.getText());
		clearTextFields();

	}

	private void clearTextFields() {
		ID.setText(null);
		UserName.setText(null);
		Password.setText(null);
		FullName.setText(null);
		MedicalStream.setSelectedIndex(0);
	}


}
