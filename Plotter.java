public class Plotter 
{
  public double[] getherData(int size)
  {
    var arr = new double[size];
    for(int i = 0; i < size; i++)
    {
      arr[i] = Math.random(); // you can replace any function here
    }
    return arr;
  }

  public double[] findRange(double[] arr)
  {
    if(arr.length == 0) return new double[]{0, 0};
    double minimum = arr[0];
    double maximum = arr[0];
    for(int i = 0; i < arr.length; i++)
    {
      if(arr[i] < minimum) minimum = arr[i];
      if(arr[i] > maximum) maximum = arr[i];
    }
    return new double[]{minimum, maximum};
  }

  public double[] plot(int bins, double[] arr)
  {
    var arr_histo = new double[bins]; // by default its all have zeros
    var arr_range = findRange(arr);
    var minimum = arr_range[0];
    var maximum = arr_range[1];
    var bin_length = (maximum - minimum) / bins;

    for(int i = 0; i < arr.length; i++)
    {
      var value = arr[i];
      var bin_min = minimum;
      var bin_max = minimum + bin_length;
      for(int j = 0; j < bins; j++)
      {
        if(value >= bin_min && value < bin_max) 
        {
          arr_histo[j] = arr_histo[j] + 1;
          break;
        }
        else
        {
          bin_min = bin_max;
          bin_max = bin_min + bin_length;
        }
      }
    }
    return arr_histo;
  }

  public void printPlot(double[] histo)
  {
    int x = histo.length; 
    var range = findRange(histo);
    int y = (int)range[1];

    // printing area (matrix) is x * y 
    // x and y are not needed but used for clarity

    System.out.println();
    for(int i = 0; i < y; i++)
    {
      for(int j = 0; j < x; j++)
      {
        int value = (int)histo[j];
        int diff = (y - i) - (value); 
        if(diff <= 0) 
          System.out.print("** ");
        else
          System.out.print("   ");
      }
      System.out.println();
    }
  }

  public static void printPlotTest()
  {
    double[] histo = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    var plotter = new Plotter();
    plotter.printPlot(histo);
  }

  public static void main(String[] args) 
  {
    //printPlotTest();
    int size = 100;
    var plotter = new Plotter();
    var data = plotter.getherData(size);
    var histo = plotter.plot(10, data);
    for(int i = 0; i < histo.length; i++)
      System.out.println(histo[i]);
    plotter.printPlot(histo);
  }
}
