package misc;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;



public class main_misc2 {
	
	
		
		
	
	
	public static void main(String[] args) {
	//(a+b)%k = ((a % k)+(b % k)) % k    || ||
	
	System.out.println(parens_combinations(10));
		
		
	}	
	
	//1) identify the "blocks" to operate;ex: s = "(())()" -->  ["(())","()"]
	
	private static ArrayList<String> identify_blocks(String s){
		
		ArrayList<String> blocks = new ArrayList<>();
		
		StringBuilder block_app = new StringBuilder();
		
		for(int i = 0;i < s.length();i++) {
			
			block_app.append(s.substring(i, i+1));
			if(i == s.length()-1){
				blocks.add(block_app.toString());
			}
			if(i+1 < s.length() && s.substring(i, i+1).equals(")") && s.substring(i+1, i+2).equals("(")){
				blocks.add(block_app.toString());
				block_app.delete(0, block_app.length());
			}
			
		}
		return blocks;
	}
	
	private static String blocks_to_string(ArrayList<String> blocks) {
		
		StringBuilder str = new StringBuilder();
		
		for(int i = 0;i < blocks.size();i++){
			
			str.append(blocks.get(i));
			
		}
		return str.toString();
	}
	
	private static ArrayList<String> operate_blocks(String s){
		
		ArrayList<String> op_blocks = new ArrayList<>();
		
		ArrayList<String> blocks = identify_blocks(s);
		
		
		
		op_blocks.add("("+s+")");
		
		if(blocks.size() == 1) {
			return op_blocks;
		}
		
		StringBuilder blocks_app = new StringBuilder();
		
		for(int i = 0;i < blocks.size();i++){
			
			ArrayList<String> temp = new ArrayList<>();
			temp.addAll(blocks);
			
			blocks_app.append("(");
			blocks_app.append(blocks.get(i));
			blocks_app.append(")");
			temp.remove(i);
			temp.add(i,blocks_app.toString());
			op_blocks.add(blocks_to_string(temp));
			blocks_app.delete(0, blocks_app.length());
			
			
		}
		return op_blocks;
	}
	
	
	private static ArrayList<String> parens_combinations(int n){
		
		ArrayList<String> all = new ArrayList<>();
		
		if(n == 0) {
			return all;
		}
		if(n == 1) {
			all.add("()");
			return all;
		}
		
		ArrayList<String> past = parens_combinations(n-1);
		
		for(int i = 0;i < past.size();i++) {
			String s = past.get(i);//s will be of the type ()()...()
			//()() --> ()()()
			if(i == 0) {
				all.add("()"+s);
			}
			//rest of possibilities:
			all.addAll(operate_blocks(s));
		}
		return all;
	}
	
	
	} 
	
