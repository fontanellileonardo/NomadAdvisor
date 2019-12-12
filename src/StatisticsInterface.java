import java.util.HashMap;
import java.util.Map;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;

public class StatisticsInterface {

    @FXML
    private PieChart citiesPieChart;

    @FXML
    private PieChart customerPieChart;
    
    public ObservableList<PieChart.Data> setPieChartData(HashMap<String, Integer> slices) {
    	ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
    	for(Map.Entry<String, Integer> slice : slices.entrySet()) {
    		switch(slice.getKey()) {
    			case Utils.TEMPERATURE:
    				pieChartData.add(new PieChart.Data("Temperature", slice.getValue()));
    				break;
    			case Utils.AIR_QUALITY:
    				pieChartData.add(new PieChart.Data("Air quality", slice.getValue()));
    				break;
    			case Utils.COST:
    				pieChartData.add(new PieChart.Data("Cost", slice.getValue()));
    				break;
    			case Utils.SAFETY:
    				pieChartData.add(new PieChart.Data("Safety", slice.getValue()));
    				break;
    			case Utils.QUALITY_LIFE:
    				pieChartData.add(new PieChart.Data("Quality of life", slice.getValue()));
    				break;
    			case Utils.WALKABILITY:
    				pieChartData.add(new PieChart.Data("Walkability", slice.getValue()));
    				break;
    			case Utils.HEALTHCARE:
    				pieChartData.add(new PieChart.Data("Healthcare", slice.getValue()));
    				break;
    			case Utils.NIGHTLIFE:
    				pieChartData.add(new PieChart.Data("Nightlife", slice.getValue()));
    				break;
    			case Utils.WIFI:
    				pieChartData.add(new PieChart.Data("Free wifi", slice.getValue()));
    				break;
    			case Utils.FOREIGNERS:
    				pieChartData.add(new PieChart.Data("Friendly for foreigners", slice.getValue()));
    				break;
    			case Utils.ENGLISH:
    				pieChartData.add(new PieChart.Data("English speaking", slice.getValue()));
    				break;
    		}
    	}
    	return pieChartData;
    }
    
    public void setCityPieChart(HashMap<String, Integer> slices) {
    	ObservableList<PieChart.Data> pieChartData = setPieChartData(slices);
    	citiesPieChart.setData(pieChartData);
    }
    
    public void setCustomerPieChart(HashMap<String, Integer> slices) {
    	ObservableList<PieChart.Data> pieChartData = setPieChartData(slices);
    	customerPieChart.setData(pieChartData);
    }

}
