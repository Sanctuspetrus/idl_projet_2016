package org.geotools.tutorial;

import java.io.File;
import java.io.IOException;

import org.geotools.coverage.grid.GridCoverage2D;
import org.geotools.coverage.io.netcdf.NetCDFReader;
import org.geotools.data.DataSourceException;
import org.geotools.geometry.DirectPosition2D;
import org.opengis.geometry.DirectPosition;

public class test {

	public static void main(String[] args) throws IllegalArgumentException, IOException {
		// Selection of the NetCDF file
        File file = new File("cartes/fichiertest.nc");

        // Creation of the NetCDF reader
        final NetCDFReader reader = new NetCDFReader(file, null);

        // It is better to surround this part of code with a try-finally construct
        // in order to avoid to leave the reader unclosed.
        try {
                // Getting the coverage names
                String[] names = reader.getGridCoverageNames();

                // Selection of the first coverage name
                String first = names[0];

                // Selection of the coverage associated to the name
                GridCoverage2D grid = reader.read(first, null);

                // Example: Get the value for the following position.
                float[] value = grid.evaluate((DirectPosition) new
                        DirectPosition2D(grid.getCoordinateReferenceSystem(), 5, 45 ), new float[1]);

        } finally {
                // Closure of the Reader
                if (reader != null) {
                        try {
                                reader.dispose();
                        } catch (Throwable t) {
                                // Log the exception
                        }
                }
        }

	}

}
