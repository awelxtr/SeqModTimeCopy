package com.awelxtr.seqmodtimecopy.utils;

import java.io.File;
import java.util.Collection;
import java.util.LinkedList;

public class FileList extends LinkedList<File> {

	private static final long serialVersionUID = -8818754030956547879L;
	
	public FileList(){
	}
	public FileList(Collection<File> data) {
		// TODO Auto-generated constructor stub
		this.addAll(data);
	}

	public void raise(int i){
		if (i<(this.size()-1)){
			File tmp = this.get(i+1);
			this.set(i+1, this.get(i));
			this.set(i,tmp);
		}
	}
	
	public void deRaise(int i){
		if (i>0){
			File tmp = this.get(i-1);
			this.set(i-1, this.get(i));
			this.set(i,tmp);
		}
	}
	public void toTop(int i){
		this.addFirst(this.remove(i));
	}
	public void toBottom(int i){
		this.addLast(this.remove(i));
	}
}
