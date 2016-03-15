package lecteur_Grib;

import java.io.IOException;

import ucar.ma2.Array;
import ucar.ma2.InvalidRangeException;
import ucar.nc2.NCdumpW;
import ucar.nc2.NetcdfFile;
import ucar.nc2.Variable;
public class Grib_read{
	static String filename = "C:/Users/Loïck/Documents/Projet ILD/base_Projet/idl_projet_2016/Dossier Previsions/TETMsMsOToSYmtRzKDl0e75I4HAjqqDApv_.grb";
	  static NetcdfFile ncfile = null;
	  
	public static Array afficheValeur(){
		String varName = "v-component_of_wind_height_above_ground"; 
		 Variable v = ncfile.findVariable(varName);
		 Array data = null;
		  if (null == v) return null;
		 try {
		 data = v.read("0:4:1, 0:0:1, 0:30:1, 0:70:1");
		  NCdumpW.printArray(data, varName,
		  System.out, null);
		 } catch (IOException ioe) {
			 System.out.println("trying to open " + varName+" "+ioe);

		  } catch (InvalidRangeException e) {
			  System.out.println("trying to open " + varName+" "+e);
		  }
		 return data;
	}

	public static void main(String args[]){
		  try {
		    ncfile = NetcdfFile.open(filename);
		  } catch (IOException ioe) {
		    System.out.println("trying to open " + filename+" "+ioe);
		  } finally { 
		    if (null != ncfile) try {
		    	afficheValeur();
		      ncfile.close();
		    } catch (IOException ioe) {
		    	System.out.println("trying to open " + filename+" "+ioe);
		    }
		  }
	}
}