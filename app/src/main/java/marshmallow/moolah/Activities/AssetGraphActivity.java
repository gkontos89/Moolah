package marshmallow.moolah.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import org.w3c.dom.Text;

import java.util.HashMap;

import marshmallow.moolah.Activities.MoolahActivity;
import marshmallow.moolah.R;

/**
 * Created by George on 3/15/2018.
 */

public class AssetGraphActivity extends MoolahActivity {
    private HashMap<String, Integer> marketPrices = new HashMap<>();
    private HashMap<String, LineGraphSeries<DataPoint>> marketChartData = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asset_graph);

        Intent intent = getIntent();

        // Set the asset name
        String assetName = intent.getStringExtra("assetName");
        TextView textView = findViewById(R.id.assetName);
        textView.setText(assetName);

        // Set the current price
        TextView currentPrice = findViewById(R.id.price);
        LineGraphSeries<DataPoint> series;
        switch (assetName) {
            case "BTC":
                currentPrice.setText("$8,233");
                series = new LineGraphSeries<>(new DataPoint[] {
                        new DataPoint(0, 1),
                        new DataPoint(1, 5),
                        new DataPoint(2, 9),
                        new DataPoint(3, 3),
                        new DataPoint(4, 2)
                });
                break;
            case "ETH":
                currentPrice.setText("$1221");
                series = new LineGraphSeries<>(new DataPoint[] {
                        new DataPoint(0, 1),
                        new DataPoint(1, 2),
                        new DataPoint(2, 3),
                        new DataPoint(3, 3),
                        new DataPoint(4, 2)
                });
                break;
            case "XMR":
                currentPrice.setText("$324");
                series = new LineGraphSeries<>(new DataPoint[] {
                        new DataPoint(0, 2),
                        new DataPoint(1, 7),
                        new DataPoint(2, 6),
                        new DataPoint(3, 3),
                        new DataPoint(4, 2)
                });
                break;
            case "XRP":
            default:
                currentPrice.setText("$2.00");
                series = new LineGraphSeries<>(new DataPoint[] {
                        new DataPoint(0, 1),
                        new DataPoint(1, 9),
                        new DataPoint(2, 3),
                        new DataPoint(3, 1),
                        new DataPoint(4, 1)
                });
                break;
        }

        // Plot the graph
        GraphView graphView = findViewById(R.id.graph);
        graphView.addSeries(series);
    }
}
