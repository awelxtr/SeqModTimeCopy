package com.awelxtr.seqmodtimecopy.utils;

import java.io.File;
import java.util.SortedMap;

import javax.swing.ListModel;
import javax.swing.event.ListDataListener;

public class SimpleSortListModel implements ListModel{
	
	private SortedMap<Integer,File> data;
	
	@Override
	public int getSize() {
		// TODO Auto-generated method stub
		return data.size();
	}

	@Override
	public Object getElementAt(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addListDataListener(ListDataListener l) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeListDataListener(ListDataListener l) {
		// TODO Auto-generated method stub
		
	}
	
	private class SimpleSortedMap<Integer, File> extends HashMap<Integer,File> implements SortedMap<Integer,File> {
		
	}

}
