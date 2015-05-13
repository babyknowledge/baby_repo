package cn.hpapa.bkl.study.array;

public class D2Array {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int a[][] = new int[4][6];
		for(int i = 0; i < 4; i++){
			for(int j = 0; j < 6; j++){
				if(i == 1 && j == 2)
					a[i][j] = 1;
				else if(i == 2 && j == 1)
					a[i][j] = 2;
				else if(i == 2 && j == 3)
					a[i][j] = 3;
				else a[i][j] = 0;
				
			}
		}
		
		for(int i = 0; i < 4; i++){
			for(int j = 0; j < 6; j++){
				System.out.print(a[i][j] + " ");
			}
			System.out.println();
		}
	}

}
