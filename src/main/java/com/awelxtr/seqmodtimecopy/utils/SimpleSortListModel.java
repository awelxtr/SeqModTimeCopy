package com.awelxtr.seqmodtimecopy.utils;

import java.io.File;
import java.util.Collection;
import java.util.LinkedList;

import javax.swing.ListModel;
import javax.swing.event.ListDataListener;

public class SimpleSortListModel implements ListModel<String>{
	
	private FileList data;
	
	public SimpleSortListModel(Collection<File> data){
		this.data = new FileList(data);
	}
	@Override
	public int getSize() {
		// TODO Auto-generated method stub
		return data.size();
	}

	@Override
	public String getElementAt(int index) {
		// TODO Auto-generated method stub
		return ((File)data.get(index)).getName();
	}

	@Override
	public void addListDataListener(ListDataListener l) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeListDataListener(ListDataListener l) {
		// TODO Auto-generated method stub
		
	}
	
	public FileList getData(){
		return data;
	}
}
