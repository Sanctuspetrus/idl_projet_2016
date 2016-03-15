package org.geotools.tutorial;

import java.awt.Color;
import java.io.File;
import java.io.IOException;

import javax.swing.JOptionPane;

import org.geotools.coverage.GridSampleDimension;
import org.geotools.coverage.grid.GridCoverage2D;
import org.geotools.coverage.grid.io.AbstractGridFormat;
import org.geotools.coverage.grid.io.GridCoverage2DReader;
import org.geotools.coverage.grid.io.GridFormatFinder;
import org.geotools.data.FileDataStore;
import org.geotools.data.FileDataStoreFinder;
import org.geotools.data.simple.SimpleFeatureSource;
import org.geotools.factory.CommonFactoryFinder;
import org.geotools.map.FeatureLayer;
import org.geotools.map.GridReaderLayer;
import org.geotools.map.Layer;
import org.geotools.map.MapContent;
import org.geotools.styling.ChannelSelection;
import org.geotools.styling.ContrastEnhancement;
import org.geotools.styling.RasterSymbolizer;
import org.geotools.styling.SLD;
import org.geotools.styling.SelectedChannelType;
import org.geotools.styling.Style;
import org.geotools.styling.StyleFactory;
import org.geotools.swing.JMapFrame;
import org.opengis.filter.FilterFactory2;
import org.opengis.style.ContrastMethod;

/**
 * Prompts the user for a shapefile and displays the contents on the screen in a map frame.
 * <p>
 * This is the GeoTools Quickstart application used in documentationa and tutorials. *
 */
public class Quickstart {
	private static StyleFactory sf = CommonFactoryFinder.getStyleFactory();
	private static FilterFactory2 ff = CommonFactoryFinder.getFilterFactory2();

	private JMapFrame frame;
	private static GridCoverage2DReader reader;

	 /**
     * Create a Style to display a selected band of the GeoTIFF image
     * as a greyscale layer
     *
     * @return a new Style instance to render the image in greyscale
     */
    private Style createGreyscaleStyle() {
        GridCoverage2D cov = null;
        try {
            cov = reader.read(null);
        } catch (IOException giveUp) {
            throw new RuntimeException(giveUp);
        }
        int numBands = cov.getNumSampleDimensions();
        Integer[] bandNumbers = new Integer[numBands];
        for (int i = 0; i < numBands; i++) { bandNumbers[i] = i+1; }
        Object selection = JOptionPane.showInputDialog(
                frame,
                "Band to use for greyscale display",
                "Select an image band",
                JOptionPane.QUESTION_MESSAGE,
                null,
                bandNumbers,
                1);
        if (selection != null) {
            int band = ((Number)selection).intValue();
            return createGreyscaleStyle(band);
        }
        return null;
    }


    /**
     * Create a Style to display the specified band of the GeoTIFF image
     * as a greyscale layer.
     * <p>
     * This method is a helper for createGreyScale() and is also called directly
     * by the displayLayers() method when the application first starts.
     *
     * @param band the image band to use for the greyscale display
     *
     * @return a new Style instance to render the image in greyscale
     */
    private static Style createGreyscaleStyle(int band) {
        ContrastEnhancement ce = sf.contrastEnhancement(ff.literal(1.0), ContrastMethod.NORMALIZE);
        SelectedChannelType sct = sf.createSelectedChannelType(String.valueOf(band), ce);

        RasterSymbolizer sym = sf.getDefaultRasterSymbolizer();
        ChannelSelection sel = sf.channelSelection(sct);
        sym.setChannelSelection(sel);

        return SLD.wrapSymbolizers(sym);
    }
   
	/**
	 * GeoTools Quickstart demo application. Prompts the user for a shapefile and displays its
	 * contents on the screen in a map frame
	 */
	public static void main(String[] args) throws Exception {
		// display a data store file chooser dialog for shapefiles
		File file = new File("cartes/countries.shp");
		File file2 = new File("cartes/timezone.shp");
		File barb = new File("cartes/barbulle.jpg");

		AbstractGridFormat format = GridFormatFinder.findFormat( barb );
        reader = format.getReader(barb);
        // Initially display the raster in greyscale using the
        // data from the first image band
        Style rasterStyle = createGreyscaleStyle(1);
        Layer rasterLayer = new GridReaderLayer(reader, rasterStyle);
        
        // Connect to the shapefile
        FileDataStore dataStore = FileDataStoreFinder.getDataStore(file);
        SimpleFeatureSource shapefileSource = dataStore.getFeatureSource();
    	// Create a basic style with yellow lines and no fill
        Style shpStyle = SLD.createPolygonStyle(Color.red, null, 0.0f);
        Layer shpLayer = new FeatureLayer(shapefileSource, shpStyle);

        
        // Connect to the shapefile
        FileDataStore dataStore2 = FileDataStoreFinder.getDataStore(file2);
        SimpleFeatureSource shapefileSource2 = dataStore2.getFeatureSource();
    	// Create a basic style with yellow lines and no fill
        Style shpStyle2 = SLD.createPolygonStyle(Color.black, null, 0.0f);
        Layer shpLayer2 = new FeatureLayer(shapefileSource2, shpStyle2);
       
        
        // Set up a MapContent with the two layers
        final MapContent map = new MapContent();
        map.setTitle("ImageLab");
        map.addLayer(rasterLayer);
        map.addLayer(shpLayer);
        map.addLayer(shpLayer2);

		// Now display the map
		JMapFrame.showMap(map);
	}


}