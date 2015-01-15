package com.awelxtr.seqmodtimecopy.utils;

import java.io.File;
import java.util.LinkedList;

import javax.swing.AbstractListModel;
import javax.swing.event.ListDataListener;

public class SimpleSortListModel extends AbstractListModel<String>{
	
	private static final long serialVersionUID = -4061150128626526875L;
	private LinkedList<File> data;
	
	public SimpleSortListModel(LinkedList<File> data){
		this.data = data;
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
		super.addListDataListener(l);
	}

	@Override
	public void removeListDataListener(ListDataListener l) {
		// TODO Auto-generated method stub
		super.removeListDataListener(l);
	}
	
	public LinkedList<File> getData(){
		return data;
	}
	
	public void setData(LinkedList<File> data){
		this.data = data;
	}
	
	/**
	 * Raising is reducing the index as the list is ascending
	 * @param i
	 */
	public void raise(int i){
		if (i>0){
			File tmp = data.get(i-1);
			data.set(i-1, data.get(i));
			data.set(i,tmp);
		}
		fireContentsChanged(this, 0, data.size()-1);
	}
	
	/**
	 * deRaising is reducing the index as the list is ascending
	 * @param i
	 */
	public void deRaise(int i){
		if (i<(data.size()-1)){
			File tmp = data.get(i+1);
			data.set(i+1, data.get(i));
			data.set(i,tmp);
		}
		fireContentsChanged(this, 0, data.size()-1);
	}
	public void toTop(int i){
		data.addFirst(data.remove(i));
		fireContentsChanged(this, 0, data.size()-1);
	}
	public void toBottom(int i){
		data.addLast(data.remove(i));
		fireContentsChanged(this, 0, data.size()-1);
	}
}
