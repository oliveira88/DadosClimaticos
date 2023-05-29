package com.ufes.dadosclimaticos.view;

import com.ufes.dadosclimaticos.model.DadosClimaticos;
import com.ufes.dadosclimaticos.util.CustomLabelGenerator;
import java.awt.Color;
import java.awt.Font;
import javax.swing.BorderFactory;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.CategoryItemLabelGenerator;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.TextAnchor;

public class MaximasMinimasView extends javax.swing.JInternalFrame {
    private DadosClimaticos maxTemperatura;
    private DadosClimaticos minTemperatura;
    private DadosClimaticos maxUmidade;
    private DadosClimaticos minUmidade; 
    private DadosClimaticos maxPresao;
    private DadosClimaticos minPresao;
    private DefaultCategoryDataset dataset;
    BarRenderer renderer;
    public MaximasMinimasView() { }
    public void initUI() {

        this.dataset = createDataset();
        JFreeChart chart = createChart(dataset);

        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        chartPanel.setBackground(Color.white);
        this.renderer = (BarRenderer) chart.getCategoryPlot().getRenderer();
        chart.getCategoryPlot().getRangeAxis().setUpperMargin(0.5);
        
        CategoryItemLabelGenerator labelGenerator = new CustomLabelGenerator(maxTemperatura, minTemperatura, maxUmidade, minUmidade, maxPresao, minPresao);
        this.renderer.setBaseItemLabelGenerator(labelGenerator);
        this.renderer.setBaseItemLabelsVisible(true);
        this.renderer.setBaseItemLabelFont(new Font("Arial", Font.BOLD, 12));
        renderer.setBasePositiveItemLabelPosition(new ItemLabelPosition(ItemLabelAnchor.OUTSIDE12, TextAnchor.BOTTOM_CENTER));
        
        add(chartPanel);
        setLocation(700, 0);
        pack();
    }

    private DefaultCategoryDataset createDataset() {
        DefaultCategoryDataset dataset =  new DefaultCategoryDataset();
        dataset.setValue(maxTemperatura.getTemperatura(), "Max", "Temperatura");
        dataset.setValue(minTemperatura.getTemperatura(), "Min", "Temperatura");
        
        dataset.setValue(maxUmidade.getUmidade(), "Max", "Umidade");
        dataset.setValue(minUmidade.getUmidade(), "Min", "Umidade");
        
        dataset.setValue(maxPresao.getPresao(), "Max", "Pressão");
        dataset.setValue(minPresao.getPresao(), "Min", "Pressão");
        return dataset;
    }

    
    private JFreeChart createChart(CategoryDataset dataset) {
        return ChartFactory.createBarChart(
                "Máximas e Mínimas",
                "Umidade",
                null,
                dataset,
                PlotOrientation.VERTICAL,
                true,
                false,
                false
        );
    }

    public void atualizaDadosClimaticos() {
        this.dataset.setValue(maxTemperatura.getTemperatura(), "Max", "Temperatura");
        this.dataset.setValue(minTemperatura.getTemperatura(), "Min", "Temperatura");
        this.dataset.setValue(maxUmidade.getUmidade(), "Max", "Umidade");
        this.dataset.setValue(minUmidade.getUmidade(), "Min", "Umidade");
        this.dataset.setValue(maxPresao.getPresao(), "Max", "Pressão");
        this.dataset.setValue(minPresao.getPresao(), "Min", "Pressão");
        
        CategoryItemLabelGenerator labelGenerator = new CustomLabelGenerator(maxTemperatura, minTemperatura, maxUmidade, minUmidade, maxPresao, minPresao);
        this.renderer.setBaseItemLabelGenerator(labelGenerator);
    }

    public void setMaxTemperatura(DadosClimaticos maxTemperatura) {
        this.maxTemperatura = maxTemperatura;
    }

    public void setMinTemperatura(DadosClimaticos minTemperatura) {
        this.minTemperatura = minTemperatura;
    }

    public void setMaxUmidade(DadosClimaticos maxUmidade) {
        this.maxUmidade = maxUmidade;
    }

    public void setMinUmidade(DadosClimaticos minUmidade) {
        this.minUmidade = minUmidade;
        
    }

    public void setMaxPresao(DadosClimaticos maxPresao) {
        this.maxPresao = maxPresao;
    }

    public void setMinPresao(DadosClimaticos minPresao) {
        this.minPresao = minPresao;
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}