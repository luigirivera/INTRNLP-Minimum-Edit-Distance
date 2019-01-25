
public class Main {
	public static void main(String[] args) throws Exception{
		
		String[][] arr = null;
		
		arr = initArray(arr, "execution", "intention");
		display(arr);

		
	}
	
	private static void display(String arr[][])
	{
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[i].length; j++) {
				System.out.print(arr[i][j] + " ");
			}
			System.out.println("");
		}
	}
	
	private static String[][] initArray(String arr[][], String source, String target)
	{
		arr  = new String[target.length()+2][source.length()+2];
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
		
		return arr;
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
}
