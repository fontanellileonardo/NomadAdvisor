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
    
    private ObservableList<PieChart.Data> setPieChartData(HashMap<String, Integer> slices) {
    	ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
    	for(Map.Entry<String, Integer> slice : slices.entrySet()) {
    		if(slice.getKey() == Utils.cityAttributes.get(Utils.cityNames.TEMPERATURE))
    			pieChartData.add(new PieChart.Data("Temperature", slice.getValue()));
    		if(slice.getKey() == Utils.cityAttributes.get(Utils.cityNames.AIR_QUALITY))
    			pieChartData.add(new PieChart.Data("Air quality", slice.getValue()));
    		if(slice.getKey() == Utils.cityAttributes.get(Utils.cityNames.COST))
    			pieChartData.add(new PieChart.Data("Cost", slice.getValue()));
    		if(slice.getKey() == Utils.cityAttributes.get(Utils.cityNames.SAFETY))
    			pieChartData.add(new PieChart.Data("Safety", slice.getValue()));
    		if(slice.getKey() == Utils.cityAttributes.get(Utils.cityNames.QUALITY_LIFE))
    			pieChartData.add(new PieChart.Data("Quality of life", slice.getValue()));
    		if(slice.getKey() == Utils.cityAttributes.get(Utils.cityNames.WALKABILITY))
    			pieChartData.add(new PieChart.Data("Walkability", slice.getValue()));
    		if(slice.getKey() == Utils.cityAttributes.get(Utils.cityNames.HEALTHCARE))
    			pieChartData.add(new PieChart.Data("Healthcare", slice.getValue()));
    		if(slice.getKey() == Utils.cityAttributes.get(Utils.cityNames.NIGHTLIFE))
    			pieChartData.add(new PieChart.Data("Nightlife", slice.getValue()));
    		if(slice.getKey() == Utils.cityAttributes.get(Utils.cityNames.WIFI))
    			pieChartData.add(new PieChart.Data("Free wifi", slice.getValue()));
    		if(slice.getKey() == Utils.cityAttributes.get(Utils.cityNames.FOREIGNERS))
    			pieChartData.add(new PieChart.Data("Friendly for foreigners", slice.getValue()));
    		if(slice.getKey() == Utils.cityAttributes.get(Utils.cityNames.ENGLISH))
    			pieChartData.add(new PieChart.Data("English speaking", slice.getValue()));
    	}
    	return pieChartData;
    }
    
    private void setCityPieChart(HashMap<String, Integer> slices) {
    	ObservableList<PieChart.Data> pieChartData = setPieChartData(slices);
    	citiesPieChart.setData(pieChartData);
    }
    
    private void setCustomerPieChart(HashMap<String, Integer> slices) {
    	ObservableList<PieChart.Data> pieChartData = setPieChartData(slices);
    	customerPieChart.setData(pieChartData);
    }
    
    public void initialize(HashMap<String, Integer> cities, HashMap<String, Integer> customers) {
    	this.setCityPieChart(cities);
    	this.setCustomerPieChart(customers);
    }

}
