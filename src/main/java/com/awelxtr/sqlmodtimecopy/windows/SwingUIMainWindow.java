package com.awelxtr.sqlmodtimecopy.windows;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.awelxtr.seqmodtimecopy.utils.FileList;
import com.awelxtr.seqmodtimecopy.utils.SimpleSortListModel;

public class SwingUIMainWindow extends JFrame {

	private static final long serialVersionUID = 688717501798613673L;
	
	private JFileChooser dirChooser;
	
	private JPanel overlay;
	private JPanel filePathPane;
	private JPanel centerArea;
	private JPanel buttonHousing;
	private JScrollPane fileListScroll;
	private JTextField pathText;
	private JButton browse;
	private JList<String> fileList;
	private JButton top;
	private JButton up;
	private JButton down;
	private JButton bottom;
	private JPanel bottomPanel;
	private JButton set;
	
	private boolean modified = false;
	
	public final FileList fileData = new FileList();
	public final ArrayList<Integer> selectedIndexes = new ArrayList<Integer>();
	
	public SwingUIMainWindow(){
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Que feo eres pap\u00E1");
		overlay = new JPanel();
		this.setLayout(new BorderLayout());
		this.add(overlay,BorderLayout.CENTER);
		overlay.setLayout(new BorderLayout());
		
		filePathPane = new JPanel();
		filePathPane.setLayout(new BoxLayout(filePathPane,BoxLayout.LINE_AXIS));
		overlay.add(filePathPane,BorderLayout.NORTH);
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
				if (modified){
					//meeeec
					return;
				}
				if (dirChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){
					pathText.setText(dirChooser.getSelectedFile().getAbsolutePath());
					changeDir();
				}
			}
			
		});
		filePathPane.add(browse);
		
		centerArea = new JPanel();
		centerArea.setLayout(new BoxLayout(centerArea,BoxLayout.LINE_AXIS));
		
		fileList = new JList<String>();
		fileList.addListSelectionListener(new ListSelectionListener(){

			@Override
			public void valueChanged(ListSelectionEvent e) {
				// TODO Auto-generated method stub
				selectedIndexes.clear();
				for (int i: fileList.getSelectedIndices())
					selectedIndexes.add(i);
			}
			
		});
		//see changeDir()
		fileList.setModel(new SimpleSortListModel(fileData));
		fileListScroll = new JScrollPane(fileList);
		centerArea.add(fileListScroll);
		buttonHousing = new JPanel();
		buttonHousing.setLayout(new BoxLayout(buttonHousing,BoxLayout.PAGE_AXIS));
		buttonHousing.add(Box.createVerticalGlue());
		top = new JButton("\u25B2\n\u25B2");
		up = new JButton("\u25B2");
		down = new JButton("\u25BC");
		bottom = new JButton("\u25BC\n\u25BC");
		top.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				ListIterator<Integer> it = selectedIndexes.listIterator();
				while(it.hasNext())
					it.next();
				while(it.hasPrevious())
					fileData.toTop(it.previous());
			}
			
		});
		up.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				for (int i : selectedIndexes)
					fileData.raise(i);
				
			}
			
		});

		down.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				for (int i : selectedIndexes)
					fileData.raise(i);
				
			}
			
		});
		bottom.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				for (int i : selectedIndexes)
					fileData.toBottom(i);
				
			}
			
		});

		buttonHousing.add(top);
		buttonHousing.add(up);
		buttonHousing.add(down);
		buttonHousing.add(bottom);
		up.setSize(top.getSize());
		down.setSize(bottom.getSize());
		buttonHousing.add(Box.createVerticalGlue());
		centerArea.add(buttonHousing);
		overlay.add(centerArea,BorderLayout.CENTER);
		bottomPanel = new JPanel();
		bottomPanel.setLayout(new BoxLayout(bottomPanel,BoxLayout.PAGE_AXIS));
		bottomPanel.add(Box.createHorizontalGlue());
		set = new JButton("Grabar");
		set.setAlignmentX(0.5f);
		bottomPanel.add(set);
		bottomPanel.add(Box.createHorizontalGlue());
		overlay.add(bottomPanel,BorderLayout.SOUTH);
		this.setSize(300, 600);
		this.setVisible(true);
	}
	/*
	* w/ the path name the contents of the list are refreshed
	*/
	private void changeDir(){
		fileData.clear();
		fileData.addAll(Arrays.asList(new File(pathText.getText()).listFiles(new FileFilter(){

			@Override
			public boolean accept(File pathname) {
				// TODO Auto-generated method stub
				System.err.print(pathname.getName());
				return !pathname.isDirectory();
			}
			
		})));
	}
}
