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
	public int[] nucleo;
	public int key;
	
	public ArrayDeque<Character> lump;
	public HashMap<Integer, ArrayList<Integer>> DNA;
	public int position = 0;
	
	private Scanner scan;
	

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
		String ch;
		char[] stray; // KMer chain in char array
		int j = 0;
		ArrayList<Integer> buck;
		long start;
		long end;

		stray = new char[size];
		nucleo = new int[size];
		hashsize = (int) Math.pow(5, size);
		
		start = System.currentTimeMillis();
		String s = scan.nextLine(); // gets the header of the fasta file

		System.out.println(s);
		ch = scan.nextLine();
		
		while (scan.hasNextLine()){
			ch += scan.nextLine();	//The bulk of the file
		}
		
		count = ch.length();
		System.out.println("count is: " + count);
		stray = ch.toCharArray();
		buck = new ArrayList<Integer>(); // buckets of the hashmap
		
		while (j < size){						//takes care of initial size queue
			lump.offer(stray[j]);
			nucleo[j] = decision(stray[j]); // the nucleotide being searched for
			j++;
		}
		
		buck.add(position);
		sequence = Sequencer(nucleo, size);
		key = sequence % hashsize;

		DNA.put(key, buck);
		
		lump.poll();
		lump.offer(stray[position + size]);

		position++;
		
		while (position + size < count){
			asksize = 0;
			while (asksize < size){
				c = lump.poll();
				nucleo[asksize] = decision(c);
				lump.offer(c);
				asksize++;
			}
			sequence = Sequencer(nucleo, size);
			
			lump.offer(stray[position + size]);
			key = sequence % hashsize;
			if (DNA.containsKey(key)){			//checks the Map before colliding
				DNA.get(key).add(position);
			}
			else{
				buck = new ArrayList<Integer>();
				buck.add(position);
				DNA.put(key, buck);
			}
			position++;
		}
		
		end  = System.currentTimeMillis() - start;
		System.out.println("time to parse file = " + end + '\n');
		scan.close();		
	}	
	
	public void inquireSize(Scanner input){
		System.out.println("Please enter the length of the nucleotide string: ");
		setSize(input.nextInt());
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
	
	private void setLump(ArrayDeque<Character> arrayDeque) {
		this.lump = arrayDeque;
		
	}
}
