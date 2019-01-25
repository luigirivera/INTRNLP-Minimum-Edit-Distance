
public class Main {
	public static void main(String[] args) throws Exception{
		
		fillArray("execution", "intention");
		

		
	}
	
	private static void display(String arr[][])
	{
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[i].length; j++) {
				System.out.print(arr[i][j] + " ");
			}
			System.out.println("");
		}
		
		System.out.println("Minimum Edit Distance: " + arr[0][arr[0].length-1] + " -> "+ MED(arr, 0, arr[0].length-1, Integer.parseInt(arr[0][arr[0].length-1])));
		
	}
	
	private static String MED(String arr[][], int row, int col, int sum)
	{
		
		Integer values[] = new Integer[3];
		Integer xs[] = new Integer[3];
		Integer ys[] = new Integer[3];
		
			try {
				values[0] = Integer.parseInt(arr[row][col-1]);
				xs[0] = row;
				ys[0] = col-1;
			}catch(Exception e)
			{ values[0] = null;
				xs[0] = null;
				ys[0] = null;}
			
			try {
				values[1] = Integer.parseInt(arr[row+1][col]);
				xs[1] = row+1;
				ys[1] = col;
			}catch(Exception e)
			{ values[1] = null; 
				xs[1] = null;
				ys[1] = null;}
			
			
			try {
				values[2] = Integer.parseInt(arr[row+1][col-1]);
				xs[2] = row+1;
				ys[2] = col-1;
			}catch(Exception e)
			{ values[2] = null; 
				xs[2] = null;
				ys[2] = null;}
			
			
			
			int index = min(values);
			
			if(arr[xs[index]+1][ys[index]].equals("#") && arr[xs[index]][ys[index]-1].equals("#"))
				return arr[xs[index]][ys[index]] + " = " + (sum + Integer.parseInt(arr[xs[index]][ys[index]]));
			else
				return arr[xs[index]][ys[index]]  + " -> " + MED(arr, xs[index], ys[index], sum + Integer.parseInt(arr[xs[index]][ys[index]]));

	}
	
	private static void fillArray(String source, String target)
	{
		String[][] arr  = new String[target.length()+2][source.length()+2];
		arr[target.length()+1][0] = " ";
		arr[target.length()+1][1] = "#";
		arr[target.length()][0] = "#";
		
		for(int i = 2 ; i < arr[target.length()+1].length; i++)
			arr[target.length()+1][i] = String.valueOf(source.charAt(i-2));
		
		String temp = "";
		
		for(int i = target.length() - 1; i>=0 ; i--)
			temp = temp + target.charAt(i);
		
		target = temp;
		
		for(int i = 0; i<target.length(); i++)
			arr[i][0] = String.valueOf(target.charAt(i));
		
		for(int i = 1; i < arr[target.length()].length; i++)
			arr[target.length()][i] = String.valueOf(i-1);
		
		for(int i = target.length(); i>=0; i--)
			arr[i][1] = String.valueOf(target.length() - i);
		
		editDistance(arr, target.length()-1,2, target.length(), source.length());
		
		display(arr);
	}
	
	private static void editDistance(String arr[][], int x, int y, int height, int width)
	{
		int[] values = new int[3];
		
		values[0] = Integer.parseInt(arr[x][y-1]) + 1;
		values[1] = Integer.parseInt(arr[x+1][y]) + 1;
		values[2] = Integer.parseInt(arr[x+1][y-1]);
		
		if(!arr[x][0].equalsIgnoreCase(arr[height+1][y]))
			values[2] += 2;
		
		arr[x][y] = String.valueOf(min(values));
		
		if(x != 0)
			editDistance(arr, x-1, y, height, width);
		else if(y != width+1)
			editDistance(arr, height-1, y+1, height, width);
	}
	
	private static int min(int values[])
	{
		if(values[0] <= values[1] && values[0] <= values[2]) return values[0];
		if(values[1] <= values[0] && values[1] <= values[2]) return values[1];
		
		return values[2];
	}
	
	private static int min(Integer values[])
	{
		if(values[1] == null && values[2] == null) return 0;
		if(values[0] == null && values[2] == null) return 1;
		if(values[0] != null && values[0] <= values[1] && values[0] <= values[2]) return 0;
		if(values[1] != null && values[1] <= values[0] && values[1] <= values[2]) return 1;
		
		return 2;
	}
}
