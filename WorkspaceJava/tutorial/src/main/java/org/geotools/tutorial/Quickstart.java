package org.geotools.tutorial;

import java.io.File;

import org.geotools.coverage.GridSampleDimension;
import org.geotools.coverage.grid.GridCoverage2D;
import org.geotools.coverage.grid.io.AbstractGridCoverage2DReader;
import org.geotools.coverage.grid.io.AbstractGridFormat;
import org.geotools.coverage.grid.io.GridCoverage2DReader;
import org.geotools.coverage.grid.io.GridFormatFinder;
import org.geotools.data.FileDataStore;
import org.geotools.data.FileDataStoreFinder;
import org.geotools.data.Parameter;
import org.geotools.data.simple.SimpleFeatureSource;
import org.geotools.factory.CommonFactoryFinder;
import org.geotools.map.FeatureLayer;
import org.geotools.map.GridReaderLayer;
import org.geotools.map.Layer;
import org.geotools.map.MapContent;
import org.geotools.map.StyleLayer;
import org.geotools.styling.ChannelSelection;
import org.geotools.styling.ContrastEnhancement;
import org.geotools.styling.RasterSymbolizer;
import org.geotools.styling.SLD;
import org.geotools.styling.SelectedChannelType;
import org.geotools.styling.Style;
import org.geotools.styling.StyleFactory;
import org.geotools.swing.JMapFrame;
import org.geotools.swing.action.SafeAction;
import org.geotools.swing.data.JParameterListWizard;
import org.geotools.swing.wizard.JWizard;
import org.geotools.util.KVP;
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
		File file = new File("cartes/ne_110m_admin_0_sovereignty.shp");

		// Create a map content and add our shapefile to it
		MapContent map = new MapContent();
		map.setTitle("Quickstart");
		  FileDataStore store = FileDataStoreFinder.getDataStore(file);
	        SimpleFeatureSource featureSource = store.getFeatureSource();


		File barbulle = new File("cartes/barbulle.jpg");
		AbstractGridFormat format = GridFormatFinder.findFormat( barbulle );        
		reader = format.getReader(barbulle);
		Style rasterStyle = createGreyscaleStyle(1);

		// Connect to the shapefile
		FileDataStore dataStore = FileDataStoreFinder.getDataStore(file);
		SimpleFeatureSource shapefileSource = dataStore
				.getFeatureSource();
		 Layer rasterLayer = new GridReaderLayer(reader, rasterStyle);
		map.addLayer(rasterLayer);
		Style style = SLD.createSimpleStyle(featureSource.getSchema());
		
		Layer shpLayer = new FeatureLayer(shapefileSource, style);
        map.addLayer(shpLayer);
		map.addLayer(rasterLayer);

		// Now display the map
		JMapFrame.showMap(map);
	}

	
}