package refactor;

import java.util.ArrayList;

public class KMerData {
	private ArrayList<Integer> bucket;
	private String string;
	private char c;
	private char[] stray;
	private int[] nucleo;
	
	
	public KMerData (int size){
		setStray(new char[size]);
		setNucleo(new int[size]);
		setBucket();
	}

	public ArrayList<Integer> getBucket() {
		return bucket;
	}
	
	public void setBucket(){
		this.bucket = new ArrayList<Integer>();
	}


	public String getString() {
		return string;
	}


	public void setString(String string) {
		this.string = string;
	}


	public char getC() {
		return c;
	}


	public void setC(char c) {
		this.c = c;
	}


	public char[] getStray() {
		return stray;
	}
	
	public char getStrayElement(int index){
		return stray[index];
	}


	public void setStray(char[] stray) {
		this.stray = stray;
	}


	public int[] getNucleo() {
		return nucleo;
	}


	public void setNucleo(int[] nucleo) {
		this.nucleo = nucleo;
	}
	
	public void setNucleoElement(int index, int value){
		nucleo[index] = value;
	}


}
