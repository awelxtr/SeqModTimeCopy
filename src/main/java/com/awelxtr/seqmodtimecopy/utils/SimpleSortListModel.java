package com.awelxtr.seqmodtimecopy.utils;

import java.io.File;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.SortedMap;

import javax.swing.ListModel;
import javax.swing.event.ListDataListener;

public class SimpleSortListModel implements ListModel<String>{
	
	private SimpleSortedMap data;
	public SimpleSortListModel(Collection<File> data){
		this.data = new SimpleSortedMap(data);
	}
	@Override
	public int getSize() {
		// TODO Auto-generated method stub
		return data.size();
	}

	@Override
	public String getElementAt(int index) {
		// TODO Auto-generated method stub
		return data.get(index).toString();
	}

	@Override
	public void addListDataListener(ListDataListener l) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeListDataListener(ListDataListener l) {
		// TODO Auto-generated method stub
		
	}
	
	public SimpleSortedMap getData(){
		return data;
	}
}
