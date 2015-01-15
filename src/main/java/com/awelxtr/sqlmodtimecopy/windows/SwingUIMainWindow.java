package com.awelxtr.sqlmodtimecopy.windows;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileFilter;
import java.util.Arrays;
import java.util.LinkedList;
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
import javax.swing.UIManager;

import com.awelxtr.seqmodtimecopy.utils.SimpleSortListModel;

public class SwingUIMainWindow extends JFrame {
	
//	private static final Logger logger = LogManager.getLogger(SwingUIMainWindow.class);

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
	
	private SwingUIMainWindow mainWindow;
	
	private boolean modified = false;
	
	public LinkedList<File> fileData = new LinkedList<File>();
	
	public SwingUIMainWindow(){
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		try{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}catch(Exception e){
			e.printStackTrace();
		}
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
		
		fileList = new JList<String>(new SimpleSortListModel(fileData));
		changeDir();
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
				modified = true;
				for (int i = fileList.getSelectedIndices().length-1;i>=0;i--)
					((SimpleSortListModel) fileList.getModel()).toTop(fileList.getSelectedIndices()[i]);
				int amount = fileList.getSelectedIndices().length;
				fileList.clearSelection();
				for (int i =0;i<amount;i++)
					fileList.setSelectedIndex(i);
				fileList.doLayout();
			}
			
		});
		up.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				modified = true;
				for (int i = fileList.getSelectedIndices().length-1;i>=0;i--){
					((SimpleSortListModel) fileList.getModel()).raise(fileList.getSelectedIndices()[i]);
					int[] si = fileList.getSelectedIndices();
					if (si[i]>0)si[i]--;
					fileList.setSelectedIndices(si);
				}
			}
			
		});

		down.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				modified = true;
				for (int i = fileList.getSelectedIndices().length-1;i>=0;i--){
					((SimpleSortListModel) fileList.getModel()).deRaise(fileList.getSelectedIndices()[i]);
					int[] si = fileList.getSelectedIndices();
					if (si[i] <fileList.getModel().getSize()-1)si[i]++;
					fileList.setSelectedIndices(si);
				}
				fileList.doLayout();
				
			}
			
		});
		bottom.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				modified = true;
				for (int i : fileList.getSelectedIndices())
					((SimpleSortListModel) fileList.getModel()).toBottom(i);
				
				int amount = fileList.getSelectedIndices().length;
				fileList.clearSelection();
				fileList.setSelectionInterval(fileList.getModel().getSize()-amount, fileList.getModel().getSize()-1);
				fileList.doLayout();
				fileList.requestFocusInWindow();
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
		set.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				ListIterator<File> it = fileData.listIterator(fileData.indexOf(fileData.getLast()));
				it.next();
				long time = System.currentTimeMillis();
				for(;it.hasPrevious();time-=1000)
					it.previous().setLastModified(time);
				modified = false;
			}
			
		});
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
//				logger.debug(pathname);
				return !pathname.isDirectory();
			}
			
		})));
		modified = false;
		if (fileList.getModel().getSize()>0)
			((SimpleSortListModel)fileList.getModel()).refresh();
		fileList.doLayout();
	}
}
