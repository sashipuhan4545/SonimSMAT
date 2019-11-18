package AllGMSTC;

import java.util.Arrays;

public class EXP3 {


	public static boolean  anagram(String val1,String val2) {
		
		char[] str1=val1.toLowerCase().toCharArray();
		char[] str2=val2.toLowerCase().toCharArray();
		boolean status = false;
		
		Arrays.sort(str1);
		Arrays.sort(str2);
		
		if(Arrays.equals(str1, str2)) {
			
			status=true;
			
		}
		return status;
		



	}


	public static void main(String args[]) {


   System.out.println(anagram("madam", "Mad"));






	}
}