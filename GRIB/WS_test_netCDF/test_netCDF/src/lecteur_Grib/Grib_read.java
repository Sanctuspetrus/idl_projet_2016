package lecteur_Grib;

import ucar.nc2.dt.grid.*;

import java.io.IOException;
import java.util.ArrayList;

import org.joda.time.DateTime;

import ucar.ma2.Array;
import ucar.ma2.InvalidRangeException;
import ucar.nc2.NCdumpW;
import ucar.nc2.NetcdfFile;
import ucar.nc2.Variable;

public class Grib_read{
	  NetcdfFile ncfile = null;
	  
	public ArrayList<Point> lectureFile(String filename, Float longitude, Float latitude){
		try {
		    ncfile = NetcdfFile.open(filename);
		  } catch (IOException ioe) {
		    System.out.println("trying to open " + filename+" "+ioe);
		  }
		
		ArrayList<Point> listPoints = new ArrayList<Point>();
		String varNameU = "u-component_of_wind_height_above_ground";
		String varNameV = "v-component_of_wind_height_above_ground";  
		String varNameLon = "lon"; 
		String varNameLat = "lat"; 
		String varNameTime = "time"; 
		String varNamePrec = "Pressure_reduced_to_MSL_msl"; 
		 Variable u = ncfile.findVariable(varNameU);
		 Variable v = ncfile.findVariable(varNameV);
		 Variable lon = ncfile.findVariable(varNameLon);
		 Variable lat = ncfile.findVariable(varNameLat);
		 Variable time = ncfile.findVariable(varNameTime);
		 Variable prec = ncfile.findVariable(varNamePrec);
		 Array dataLon = null;
		 Array dataLat = null;
		 Array dataTemps = null;
		 Array dataU = null;
		 Array dataV = null;
		 Array dataPrec = null;
		  if (null == u) return null;
		  if (null == v) return null;
		  if (null == lon) return null;
		  if (null == lat) return null;
		  if (null == time) return null;
		  if (null == prec) return null;
		 try {
		 dataLon = lon.read();
		 dataLat = lat.read();
		 dataTemps = time.read();
		 for(int i = 0; i < dataTemps.getSize(); i++){
			 for (int j = 0; j < dataLat.getSize(); j++){
				 for (int k = 0; k < dataLon.getSize(); k++){
					 Point tmp = new Point();
					 tmp.setTemps(dataTemps.getDouble(i));
					 if(longitude.equals( dataLon.getFloat(k))){
						 if(latitude.equals( dataLat.getFloat(j))){
							 dataPrec = prec.read(i+":"+i+":1, "+j+":"+j+":1, "+k+":"+k+":1");
							 dataU = u.read(i+":"+i+":1, 0:0:1, "+j+":"+j+":1, "+k+":"+k+":1");
							 dataV = v.read(i+":"+i+":1, 0:0:1, "+j+":"+j+":1, "+k+":"+k+":1");
							 System.out.println("temps : "+dataTemps.getDouble(i)+" longitude : "+dataLon.getFloat(k)+" Latitude : "+dataLat.getFloat(j)+" pression : "+dataPrec.getFloat(0)+" U : "+dataU.getFloat(0)+" V : "+dataV.getFloat(0));
						 }
					 }
					 /*tmp.setLongitude(dataLon.getFloat(k));
					 tmp.setLatitude(dataLat.getFloat(j));
					 dataPrec = prec.read(i+":"+i+":1, "+j+":"+j+":1, "+k+":"+k+":1");
					 dataU = u.read(i+":"+i+":1, 0:0:1, "+j+":"+j+":1, "+k+":"+k+":1");
					 dataV = v.read(i+":"+i+":1, 0:0:1, "+j+":"+j+":1, "+k+":"+k+":1");
					 tmp.setPression(dataPrec.getFloat(0));
					 tmp.setVentU(dataU.getFloat(0));
					 tmp.setVentV(dataV.getFloat(0));
					 //System.out.println("temps : "+dataTemps.getDouble(i)+" longitude : "+dataLon.getFloat(k)+" Latitude : "+dataLat.getFloat(j)+" pression : "+dataPrec.getFloat(0)+" U : "+dataU.getFloat(0)+" V : "+dataV.getFloat(0));
					 listPoints.add(tmp);*/
				 }
			 }
		 }

	      ncfile.close();
		 
		 
		 } catch (IOException ioe) {
			 System.out.println("trying to open " + varNameU+" "+ioe);

		  } catch (InvalidRangeException e) {
			  System.out.println("trying to open " + varNameU+" "+e);
		  }
		 return listPoints;
	}

	public static void main(String args[]){
		Grib_read grib = new Grib_read();
		ArrayList<Point> listPoints = grib.lectureFile("C:/Users/Loïck/Documents/Projet ILD/base_Projet/Dossier Previsions/grib20160315060801331.grb", -40.0f, 20.0f );
		System.out.println("coucou");
	}
}