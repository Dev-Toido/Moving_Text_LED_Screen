package LedScreen;

import java.util.ArrayList;
import java.util.LinkedList;

public class Screen {
	int rows;
	int cols;
	LED [][]board;
	public Screen(int rows, int cols){
		this.rows=rows;
		this.cols=cols;
		board = new LED[rows][cols];
		for(int i=0;i<rows;i++){
			for(int j=0;j<cols;j++){
				board[i][j] = new LED();
			}
		}
	}
	public void rowOn(int row){
		for(int j=0;j<cols;j++){
			board[row][j].setI(true);
		}
	}

	public void rowOn(int row, LinkedList<Integer> rowData){
		for(int j=0;j<cols;j++){
			if(rowData.get(j)==1)board[row][j].on();
		}
	}
	public void rowOff(int row){
		for(int j=0;j<cols;j++){
			board[row][j].setI(false);
		}
	}
	public void colOn(int col){
		for(int j=0;j<rows;j++){
			board[j][col].setJ(true);
		}
	}
	public void colOff(int col){
		for(int j=0;j<rows;j++){
			board[j][col].setJ(false);
		}
	}
	public void allRowOn(){
		for(int i=0;i<rows;i++){
			for(int j=0;j<cols;j++){
				board[i][j].setI(true);
			}
		}
	}
	public void allRowOff(){
		for(int i=0;i<rows;i++){
			for(int j=0;j<cols;j++){
				board[i][j].setI(false);
			}
		}
	}
	public void allColOn(){
		for(int i=0;i<rows;i++){
			for(int j=0;j<cols;j++){
				board[i][j].setJ(true);
			}
		}
	}
	public void allColOff(){
		for(int i=0;i<rows;i++){
			for(int j=0;j<cols;j++){
				board[i][j].setJ(false);
			}
		}
	}
	public StringBuilder displayStringBuilder(){
		StringBuilder sb = new StringBuilder();
		StringBuilder topBottomBorder = new StringBuilder();
		char border = '#';
		for(int i=0;i<cols+2;i++){
			topBottomBorder.append(border).append(" ");
		}
		topBottomBorder.append('\n');
		sb.append(topBottomBorder);
		for(int i=0;i<rows;i++){
			sb.append(border).append(" ");
			for(int j=0;j<cols;j++){
				sb.append((board[i][j].isOn()) ? "*" : " ").append(" ");
			}
			sb.append(border).append("\n");
		}
		sb.append(topBottomBorder);
		return sb;
	}
	public void wholeScreenOn(){
		for(int i=0;i<rows;i++){
			for(int j=0;j<cols;j++){
				board[i][j].on();
			}
		}
	}
	public void clearScreen(){
		for(int i=0;i<rows;i++){
			for(int j=0;j<cols;j++){
				board[i][j].off();
			}
		}
	}

}
