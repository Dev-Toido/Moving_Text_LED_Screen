package TextToBigMatrix;

import java.util.ArrayList;
import java.util.LinkedList;

//This class is to make the whole system of the big letters matrix to feed into the screen
public class BigLetters {
    // for now only supports the capital letters only
    Letters[] let ;
    String originalString;
    int length;
    int scale;
    int space;

    //Construct Zone
    public BigLetters(String str, int scale,int space) {
        originalString = str;
        length = str.trim().length();
        str=str.toUpperCase();
        this.scale =scale;
        this.space=space;
        let = new Letters[length];
        for(int i = 0; i < let.length; i++){
            let[i] = new Letters(str.charAt(i));
        }
    }

    //Returning functions
    public LinkedList<Integer>[] wholeRowWiseMat(){
        LinkedList<Integer>[] res = new LinkedList[5*scale];
        for(int i = 0; i < res.length; i++){
            res[i] = new LinkedList<>();
        }
        for(int i = 0; i < length; i++){
            int[][] temp = let[i].getLetterScaledMat(scale);
            for(int j = 0; j < 5*scale; j++){
                for(int k = 0; k < 5*scale; k++){
                    res[j].add(temp[j][k]);
                }
	            if (i<length-1) {
		            res[j].add(0);
		            res[j].add(0);
	            }
            }
        }
		return res;
    }

    public void display(){
        LinkedList<Integer>[] dis = wholeRowWiseMat();
	    for (LinkedList<Integer> di : dis) {
		    for (int j : di) {
                System.out.print((j==1)?"# ":". ");
		    }
            System.out.println();
	    }
    }
}
