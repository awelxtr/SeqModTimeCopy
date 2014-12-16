package com.awelxtr.sqlmodtimecopy.windows;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.plaf.basic.BasicDirectoryModel;

public class SwingUIMainWindow extends JFrame {

	private static final long serialVersionUID = 688717501798613673L;
	
	private JFileChooser dirChooser;
	
	private JPanel overlay;
	private JPanel filePathPane;
	private JPanel lowerArea;
	private JPanel buttonHousing;
	private JScrollPane fileListScroll;
	private JTextField pathText;
	private JButton browse;
	private JList fileList;
	private JButton top;
	private JButton up;
	private JButton down;
	private JButton bottom;
	
	public SwingUIMainWindow(){
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Que feo eres papá");
		overlay = new JPanel();
		this.setLayout(new BorderLayout());
		this.add(overlay,BorderLayout.CENTER);
		overlay.setLayout(new BoxLayout(overlay,BoxLayout.PAGE_AXIS));
		
		filePathPane = new JPanel();
		filePathPane.setLayout(new BoxLayout(filePathPane,BoxLayout.LINE_AXIS));
		overlay.add(filePathPane);
		pathText = new JTextField("C:\\");
		pathText.addKeyListener(new KeyAdapter(){
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				if (e.getKeyChar() == KeyEvent.VK_ENTER)
					changeDir();
			}
			
		});
		dirChooser = new JFileChooser();
		dirChooser.setCurrentDirectory(new File(pathText.getText()));
		dirChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		filePathPane.add(pathText);
		browse = new JButton("Buscar...");
		browse.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dirChooser.showOpenDialog(null);
				if (dirChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){
					pathText.setText(dirChooser.getSelectedFile().getAbsolutePath());
					changeDir();
				}
			}
			
		});
		filePathPane.add(browse);
		
		lowerArea = new JPanel();
		lowerArea.setLayout(new BoxLayout(lowerArea,BoxLayout.LINE_AXIS));
		fileListScroll = new JScrollPane();
		fileList = new JList();
		fileListScroll.add(fileList);
		lowerArea.add(fileListScroll);
		buttonHousing = new JPanel();
		buttonHousing.setLayout(new BoxLayout(buttonHousing,BoxLayout.PAGE_AXIS));
		buttonHousing.add(Box.createVerticalGlue());
		top = new JButton("\u0332\n\u25B2");
		up = new JButton("\u25B2");
		down = new JButton("\u25BC");
		bottom = new JButton("\u25BC\n\u0332");
		buttonHousing.add(top);
		buttonHousing.add(up);
		buttonHousing.add(down);
		buttonHousing.add(bottom);
		up.setSize(top.getSize());
		down.setSize(bottom.getSize());
		buttonHousing.add(Box.createVerticalGlue());
		lowerArea.add(buttonHousing);
		overlay.add(lowerArea);
		this.setSize(300, 600);
		this.setVisible(true);
	}
	private void changeDir(){
		
	}
}
