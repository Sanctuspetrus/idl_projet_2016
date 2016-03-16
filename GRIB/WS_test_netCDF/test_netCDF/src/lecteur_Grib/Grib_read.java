package lecteur_Grib;

import ucar.nc2.dt.grid.*;

import java.io.IOException;

import ucar.ma2.Array;
import ucar.ma2.InvalidRangeException;
import ucar.nc2.NCdumpW;
import ucar.nc2.NetcdfFile;
import ucar.nc2.Variable;

public class Grib_read{
	static String filename = "C:/Users/Loïck/Documents/Projet ILD/base_Projet/Dossier Previsions/grib20160315060801331.grb";
	  static NetcdfFile ncfile = null;
	  
	public static Array afficheValeur(){
		String varNameU = "u-component_of_wind_height_above_ground";
		String varNameV = "v-component_of_wind_height_above_ground";  
		String varNameLon = "lon"; 
		String varNameLat = "lat"; 
		String varNameTime = "time"; 
		String varNamePrec = "v-component_of_wind_height_above_ground"; 
		 Variable u = ncfile.findVariable(varNameU);
		 Variable v = ncfile.findVariable(varNameV);
		 Variable lon = ncfile.findVariable(varNameLon);
		 Variable lat = ncfile.findVariable(varNameLat);
		 Variable time = ncfile.findVariable(varNameTime);
		 Variable prec = ncfile.findVariable(varNamePrec);
		 Array dataU = null;
		 Array dataV = null;
		 Array dataLon = null;
		 Array dataLat = null;
		 Array dataTime = null;
		 Array dataPrec = null;
		  if (null == v) return null;
		 try {
		 dataU = u.read("0:4:1, 0:0:1, 0:30:1, 0:70:1");
		 dataU = v.read("0:4:1, 0:0:1, 0:30:1, 0:70:1");
		 dataU = lon.read();
		 dataU = lat.read();
		 dataU = time.read();
		 dataU = prec.read("0:4:1, 0:0:1, 0:30:1, 0:70:1");
		 } catch (IOException ioe) {
			 System.out.println("trying to open " + varNameU+" "+ioe);

		  } catch (InvalidRangeException e) {
			  System.out.println("trying to open " + varNameU+" "+e);
		  }
		 return dataU;
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