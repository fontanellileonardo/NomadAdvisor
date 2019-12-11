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
    		pieChartData.add(new PieChart.Data(slice.getValue() + "\t" + slice.getKey(), slice.getValue()));
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
