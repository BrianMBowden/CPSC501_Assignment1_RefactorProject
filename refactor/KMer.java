package refactor;

import java.io.BufferedReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class KMer extends DNA{
	
	private int size;
	private int count;
	
	public int hashsize;	
	public int key;
	
	public ArrayDeque<Character> lump;
	public HashMap<Integer, ArrayList<Integer>> DNA;
	public int position = 0;
	
	private Scanner scan;
	private KMerData data;
	
	private long start;
	private long end;
	
	private String string;
	

	public KMer (BufferedReader fIn){
		setSize(0);
		setHashSize(0);
		setDNA(new HashMap<Integer, ArrayList<Integer>>());
		setLump(new ArrayDeque<Character>());
		setScan(new Scanner(fIn));
		
	}

	public void solve(){
		
		//parses the fasta file
		
		int asksize = 0;
		int sequence = 0;
		char c;
		int j = 0;

		initialize();
		initialScan();
		
		while (j < size){						//takes care of initial size queue
			lump.offer(data.getStrayElement(j));
			data.setNucleoElement(j, decision(data.getStrayElement(j)));
			j++;
		}
		
		
		data.getBucket().add(position);
		sequence = Sequencer(data.getNucleo(), size);
		key = sequence % hashsize;

		getDNA().put(key, data.getBucket());
		
		lump.poll();
		lump.offer(data.getStrayElement(position + size));

		position++;
		
		while (position + size < count){
			asksize = 0;
			while (asksize < size){
				c = lump.poll();
				data.setNucleoElement(asksize, decision(c));
				lump.offer(c);
				asksize++;
			}
			sequence = Sequencer(data.getNucleo(), size);
			
			lump.offer(data.getStrayElement(position + size));
			key = sequence % hashsize;
			if (DNA.containsKey(key)){			//checks the Map before colliding
				DNA.get(key).add(position);
			}
			else{
				data.setBucket();
				data.getBucket().add(position);
				getDNA().put(key, data.getBucket());
			}
			position++;
		}
		
		tearDown();
			
	}	
	
	public void inquireSize(Scanner input){
		System.out.println("Please enter the length of the nucleotide string: ");
		setSize(input.nextInt());
	}
	
	private void initialize(){
		data = new KMerData(size);
		
		hashsize = (int) Math.pow(5, size);

		System.out.println(hashsize);
		start = System.currentTimeMillis();
	}
	
	private void initialScan(){
		string = scan.nextLine();

		System.out.println(string);
		string = scan.nextLine();
		
		while (scan.hasNextLine()){
			string += scan.nextLine();		//takes care of newline characters?
		}
		
		count = string.length();
		System.out.println("count is: " + count);
		data.setStray(string.toCharArray());
	}
	
	private void tearDown(){
		end  = System.currentTimeMillis() - start;
		System.out.println("time to parse file = " + end + '\n');
		scan.close();	
	}
	
	public int getSize(){
		return size;
	}
	
	public int getCount(){
		return count;
	}
	
	public int getHashSize(){
		return hashsize;
	}
	
	public void setSize(int s){
		this.size = s;
	}
	
	public void setCount(int c){
		this.count = c;
	}
	
	private void setHashSize(int i) {
		this.hashsize = i;
	}
	
	public void setScan(Scanner scan) {
		this.scan = scan.useDelimiter("\n");
	}
	
	public void setDNA(HashMap<Integer, ArrayList<Integer>> dNA) {
		DNA = dNA;
	}
	
	public HashMap<Integer, ArrayList<Integer>> getDNA() {
		return DNA;
	}
	private void setLump(ArrayDeque<Character> arrayDeque) {
		this.lump = arrayDeque;
		
	}
}
