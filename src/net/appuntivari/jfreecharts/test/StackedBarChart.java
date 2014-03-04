package net.appuntivari.jfreecharts.test;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Paint;

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
import org.jfree.chart.renderer.category.GroupedStackedBarRenderer;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.KeyToGroupMap;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.Layer;
import org.jfree.ui.RectangleAnchor;
import org.jfree.ui.RefineryUtilities;
import org.jfree.ui.TextAnchor;

/*
 * Problema: asse Y non coerente con i valori delle 2 pressioni, viene fatta una somma.
 * 
 * */
public class StackedBarChart extends ApplicationFrame {

    public StackedBarChart(final String title) {
        super(title);

        final CategoryDataset dataset = createDataset();
        final JFreeChart chart = createChart(dataset);
        final ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(600, 400));
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
    

    
    private JFreeChart createChart(final CategoryDataset dataset) {
        
        // create the chart...
        final JFreeChart chart = ChartFactory.createStackedBarChart3D(
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
        plot.setForegroundAlpha(0.4f);
        plot.setDatasetRenderingOrder(DatasetRenderingOrder.REVERSE);
        

//Asse Y Pressione minima  e massima
        final NumberAxis rangeAxis1 = (NumberAxis) plot.getRangeAxis();
        rangeAxis1.setStandardTickUnits(NumberAxis.createIntegerTickUnits());

        
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

     //soglia 1
        final IntervalMarker target1 = new IntervalMarker(50, 90);
        target1.setLabel("Valori IDEALI di Pressione Minima");
        target1.setLabelFont(new Font("SansSerif", Font.ITALIC, 11));
        target1.setLabelAnchor(RectangleAnchor.LEFT);
        target1.setLabelTextAnchor(TextAnchor.CENTER_LEFT);
        target1.setPaint(Color.red);
        target1.setAlpha(0.3f);
        plot.addRangeMarker(1,target1, Layer.FOREGROUND);
  
     //soglia 2
        final IntervalMarker target2 = new IntervalMarker(110, 130);
        target2.setLabel("Valori IDEALI di Pressione Massima");
        target2.setLabelFont(new Font("SansSerif", Font.ITALIC, 11));
        target2.setLabelAnchor(RectangleAnchor.LEFT);
        target2.setLabelTextAnchor(TextAnchor.CENTER_LEFT);
        target2.setPaint(Color.red);
        target2.setAlpha(0.3f);
        plot.addRangeMarker(1,target2, Layer.FOREGROUND);
        
//asse X obliquo
        final CategoryAxis domainAxis = plot.getDomainAxis();
        domainAxis.setCategoryLabelPositions(
            CategoryLabelPositions.createUpRotationLabelPositions(Math.PI / 6.0)
        );
        
        return chart;
    }
    
    public static void main(final String[] args) {

        final StackedBarChart demo = new StackedBarChart("StackedBarChart Demo");
        demo.pack();
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);

    }

}