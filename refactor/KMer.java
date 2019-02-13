package refactor;

import java.io.BufferedReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class KMer{
	
	private int size = 0;
	private int count;
	public int hashsize;	
	public int[] nucleo;
	public int key;
	public ArrayDeque<Character> lump = new ArrayDeque<Character>();
	public HashMap<Integer, ArrayList<Integer>> DNA = new HashMap<Integer, ArrayList<Integer>>();
	public int position = 0;

	public KMer (BufferedReader fIn){
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

		inquireSize();

		stray = new char[size];
		nucleo = new int[size];
		hashsize = (int) Math.pow(5, size);
		
		Scanner two = new Scanner(fIn);	//delimiter may not be needed
		start = System.currentTimeMillis();
		String s = two.nextLine(); // gets the header of the fasta file

		System.out.println(s);
		ch = two.nextLine();
		
		while (two.hasNextLine()){
			ch += two.nextLine();	//The bulk of the file
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
		two.close();		
	}	
	
	public void inquireSize(){
		@SuppressWarnings("resource")
		Scanner help = new Scanner(System.in);
		String size = "Please enter the length of the nucleotide string: ";
		System.out.println(size);
		this.size = help.nextInt();
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
	
	public static int Sequencer(int[] n, int s){
		
		int j = 0;
		int r = 0;
		for (int i = 0; i < s; i++){
			r = (int) Math.pow(10, (s-i) - 1);
			j += n[i] * r;
		}
		return j;
	}

	
	public static int decision(char c){
		int decider = 0;
		if (c == 'A')
			decider = 1;
		else if (c == 'G')
			decider = 2;
		else if (c == 'C')
			decider = 3;
		else if (c == 'T')
			decider = 4;
		return decider;
	}
}
