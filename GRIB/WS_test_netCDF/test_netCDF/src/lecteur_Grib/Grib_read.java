package lecteur_Grib;

import ucar.nc2.dt.grid.*;

import java.io.IOException;

import org.joda.time.DateTime;

import ucar.ma2.Array;
import ucar.ma2.InvalidRangeException;
import ucar.nc2.NCdumpW;
import ucar.nc2.NetcdfFile;
import ucar.nc2.Variable;

public class Grib_read{
	static String filename = "C:/Users/Loïck/Documents/Projet ILD/base_Projet/Dossier Previsions/20160316_100540_.grb";
	  static NetcdfFile ncfile = null;
	  
	public static Array afficheValeur(){
		
		String var = ncfile.getDetailInfo();
		System.out.println(var);
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
		 Array dataTemps = null;
		 Array dataPrec = null;
		  if (null == v) return null;
		 try {
		 dataLon = lon.read("0:216:1");
		 dataLat = lat.read("0:100:1");
		 dataTemps = time.read();
		 for(int i = 0; i < dataTemps.getSize(); i++){
			 for (int j = 0; j < dataLat.getSize(); j++){
				 for (int k = 0; k < dataLon.getSize(); k++){
					 dataPrec = prec.read(i+":"+i+":1, "+j+":"+j+":1, "+k+":"+k	+":1");
					 dataU = u.read(i+":"+i+":1, 0:0:1, "+j+":"+j+":1, "+k+":"+k	+":1");
					 dataV = v.read(i+":"+i+":1, 0:0:1, "+j+":"+j+":1, "+k+":"+k	+":1");
					 System.out.println("temps : "+dataTemps.getDouble(i)+"longitude : "+dataLon.getFloat(k)+"Latitude : "+dataLat.getFloat(j)+"pression : "+dataPrec.getFloat(0)+"U : "+dataU.getFloat(0)+"V : "+dataV.getFloat(0));
				 }
			 }
		 }
		 
		 
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