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
	
	public SimpleSortedMap<Integer, File> getData(){
		return data;
	}
	
	private class SimpleSortedMap<Integer, File> extends HashMap<Integer,File> implements SortedMap<Integer,File> {

		/**
		 * 
		 */
		private static final long serialVersionUID = 2728708487559869919L;

		public SimpleSortedMap(){
			super();
		}
		
		public SimpleSortedMap(Collection<File> values){
			super();
			int i = 0;
			for (File f: values)
				this.put((Integer)new java.lang.Integer(i++), (File)f);
				
		}
		
		@Override
		public Comparator<? super Integer> comparator() {
			// TODO Auto-generated method stub
			return new Comparator<Integer>(){

				@Override
				public int compare(Integer o1, Integer o2) {
					// TODO Auto-generated method stub
					return (int)o1-(int)o2;
				}
				
			};
		}

		@Override
		public SortedMap<Integer, File> subMap(Integer fromKey, Integer toKey) {
			// TODO Auto-generated method stub
			Set<Integer> keys = this.keySet();
			SimpleSortedMap ret = new SimpleSortedMap();
			for (int i = (int)fromKey;i<(int)toKey;i++){
				if(keys.contains(i)){
					ret.put(i, this.get(i));
				}
			}
			return ret;
		}

		@Override
		public SortedMap<Integer, File> headMap(Integer toKey) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public SortedMap<Integer, File> tailMap(Integer fromKey) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Integer firstKey() {
			// TODO Auto-generated method stub
			Iterator<Integer> it = this.keySet().iterator();
			Integer n = null;
			if (it.hasNext())
				return it.next();
			else
				return n;
			
		}

		@Override
		public Integer lastKey() {
			// TODO Auto-generated method stub
			Iterator<Integer> it = this.keySet().iterator();
			Integer i = null;
			while(it.hasNext())
				i = it.next();
			return i;
		}
	}

}
