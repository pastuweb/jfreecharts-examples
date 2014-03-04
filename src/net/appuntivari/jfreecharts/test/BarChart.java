package net.appuntivari.jfreecharts.test;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Paint;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.PathIterator;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.CategoryItemLabelGenerator;
import org.jfree.chart.labels.CategoryToolTipGenerator;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.labels.StandardCategoryToolTipGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.DatasetRenderingOrder;
import org.jfree.chart.plot.IntervalMarker;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.Layer;
import org.jfree.ui.RectangleAnchor;
import org.jfree.ui.RectangleInsets;
import org.jfree.ui.RefineryUtilities;
import org.jfree.ui.TextAnchor;

/**
 * A simple demonstration application showing how to create a bar chart.
 *
 */
public class BarChart extends ApplicationFrame {

    public BarChart(final String title) {
        super(title);
        
        final CategoryDataset dataset = createDataset();
        final JFreeChart chart = createChart(dataset);
        final ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(800, 600));
        setContentPane(chartPanel);
    }

    private CategoryDataset createDataset() {
        
        // row keys...
        final String series1 = "Pressione Minima [mmHg]";
        final String series2 = "Pressione Massima [mmHg]";

        // column keys...
        final String category1 = "20-Ott";
       final String category2 = "21-Ott";
        final String category3 = "22-Ott";
        final String category4 = "23-Ott";
        final String category5 = "24-Ott";

        // create the dataset...
        final DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        dataset.addValue(40, series1, category1);
        dataset.addValue(60, series1, category2);
        dataset.addValue(70, series1, category3);
        dataset.addValue(75, series1, category4);
        dataset.addValue(80, series1, category5);

        dataset.addValue(95, series2, category1);
        dataset.addValue(97, series2, category2);
        dataset.addValue(110, series2, category3);
        dataset.addValue(130, series2, category4);
        dataset.addValue(110, series2, category5);
       
        return dataset;
    }
    

private CategoryDataset createDataset3() {
    
    // row keys...
    final String series3 = "Pulsazioni [num]";

    // column keys...
    final String category1 = "20-Ott";
    final String category2 = "21-Ott";
    final String category3 = "22-Ott";
    final String category4 = "23-Ott";
    final String category5 = "24-Ott";

    // create the dataset...
    final DefaultCategoryDataset dataset = new DefaultCategoryDataset();

    dataset.addValue(160, series3, category1);
    dataset.addValue(190, series3, category2);
    dataset.addValue(160, series3, category3);
    dataset.addValue(155, series3, category4);
    dataset.addValue(190, series3, category5);
    
    return dataset;
}
    
    @SuppressWarnings("deprecation")
	private JFreeChart createChart(final CategoryDataset dataset) {
        
        // create the chart...
        final JFreeChart chart = ChartFactory.createBarChart3D(
            "Grafico delle Pressioni",         // chart title
            "Tempo",               // domain axis label
            "Pressione [mmHg]",                  // range axis label
            dataset,                  // data
            PlotOrientation.VERTICAL, // orientation
            true,                     // include legend
            true,                     // tooltips?
            false                     // URLs?
        );
        chart.setBackgroundPaint(Color.white);

        final CategoryPlot plot = chart.getCategoryPlot();
        plot.setBackgroundPaint(Color.lightGray);
        plot.setDomainGridlinePaint(Color.white);
        plot.setRangeGridlinePaint(Color.white);
        plot.setForegroundAlpha(1.0f);
        plot.setDatasetRenderingOrder(DatasetRenderingOrder.FORWARD);
        

//Asse Y Pressione minima  e massima
        final NumberAxis rangeAxis1 = (NumberAxis) plot.getRangeAxis();
        rangeAxis1.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
//Asse Y Pulsazioni
        final NumberAxis rangeAxis2 = new NumberAxis("Pulsazioni [num]");
        rangeAxis2.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        rangeAxis2.setLabelPaint(Color.blue);
        plot.setRangeAxis(2, rangeAxis2);
        
//BarChart Pressione minima e massima
        final BarRenderer renderer = (BarRenderer) plot.getRenderer();
        plot.setRenderer(1, renderer);
        renderer.setSeriesPaint(0, Color.yellow);
        renderer.setSeriesItemLabelsVisible(0, true);
        renderer.setSeriesItemLabelGenerator(0, new StandardCategoryItemLabelGenerator());
        renderer.setSeriesItemLabelPaint(0, Color.black);
        renderer.setSeriesToolTipGenerator(0, new StandardCategoryToolTipGenerator());
        renderer.setSeriesPaint(1, Color.green);
        renderer.setSeriesItemLabelsVisible(1, true);
        renderer.setSeriesItemLabelGenerator(1, new StandardCategoryItemLabelGenerator());
        renderer.setSeriesItemLabelPaint(1, Color.black);
        renderer.setSeriesToolTipGenerator(1, new StandardCategoryToolTipGenerator());
        renderer.setItemMargin(0.0);
    //soglia 1
        final IntervalMarker target1 = new IntervalMarker(50, 90);
        target1.setLabel("soglia P.MIN");
        target1.setLabelFont(new Font("SansSerif", Font.ITALIC, 9));
        target1.setLabelAnchor(RectangleAnchor.TOP_LEFT);
        target1.setLabelTextAnchor(TextAnchor.TOP_LEFT);
        target1.setPaint(Color.red);
        target1.setAlpha(0.8f);
        plot.addRangeMarker(1,target1, Layer.BACKGROUND);

     //soglia 2
        final IntervalMarker target2 = new IntervalMarker(110, 130);
        target2.setLabel("soglia P.MAX");
        target2.setLabelFont(new Font("SansSerif", Font.ITALIC, 9));
        target2.setLabelAnchor(RectangleAnchor.TOP_LEFT);
        target2.setLabelTextAnchor(TextAnchor.TOP_LEFT);
        target2.setPaint(Color.red);
        target2.setAlpha(0.8f);
        plot.addRangeMarker(1,target2, Layer.BACKGROUND);
        
      //obiettivo
        final IntervalMarker target3 = new IntervalMarker(111, 112);
        target3.setLabel("obiettivo");
        target3.setLabelFont(new Font("SansSerif", Font.ITALIC, 9));
        target3.setLabelAnchor(RectangleAnchor.TOP_LEFT);
        target3.setLabelTextAnchor(TextAnchor.TOP_LEFT);
        target3.setPaint(Color.black);
        target3.setAlpha(0.8f);
        plot.addRangeMarker(1,target3, Layer.FOREGROUND);

//LineChart Pulsazioni
        final LineAndShapeRenderer  renderer3 = new LineAndShapeRenderer();
        DefaultCategoryDataset dataset3 = (DefaultCategoryDataset) createDataset3();
        plot.setDataset(3, dataset3);
        plot.setRenderer(3, renderer3);
        
        renderer3.setSeriesPaint(0, Color.blue); 
        renderer3.setShapesVisible(false);
        renderer3.setStroke(new BasicStroke(2.5F));
        renderer3.setSeriesToolTipGenerator(0, new StandardCategoryToolTipGenerator());
        plot.mapDatasetToRangeAxis(3, 2);
        
//asse X obliquo
        final CategoryAxis domainAxis = plot.getDomainAxis();
        domainAxis.setCategoryLabelPositions(
            CategoryLabelPositions.createUpRotationLabelPositions(Math.PI / 6.0)
        );
        
        return chart;
    }
    
    public static void main(final String[] args) {

        final BarChart demo = new BarChart("BarChart Demo");
        demo.pack();
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);
        
    }

}