package marshmallow.moolah.Activities;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.jjoe64.graphview.GraphView;

import java.lang.reflect.Array;
import java.util.ArrayList;

import marshmallow.moolah.R;

public class HomeActivity extends MoolahActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Set the user banner
        TextView userBanner = findViewById(R.id.userBanner);
        String banner = username + "'s Portfolio";
        userBanner.setText(banner);

        // Attach scrollable list
        final ArrayList<AssetInfo> assetInfos = new ArrayList<>();
        AssetAdapter assetAdapter = new AssetAdapter(this, assetInfos);
        final ListView assetListView = findViewById(R.id.assetScrollable);
        assetListView.setAdapter(assetAdapter);

        // Update scrollable list
        assetAdapter.add(new AssetInfo("BTC", 10, 80000));
        assetInfos.add(new AssetInfo("XMR", 5, 1500));
        assetInfos.add(new AssetInfo("XRP", 3, 3));
        assetInfos.add(new AssetInfo("ETH", 23, 23000));

        // Set action for selecting a list view item
        assetListView.setClickable(true);
        assetListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Object selectedAsset = assetListView.getItemAtPosition(position);
                AssetInfo assetInfo = (AssetInfo) selectedAsset;
                Intent intent = new Intent(getApplicationContext(), AssetGraphActivity.class);
                intent.putExtra("assetName", assetInfo.name);
                startActivity(intent);
            }
        });
        //GraphView graph = (GraphView) findViewById(R.id.graph);



        // Update bought for
        TextView boughtFor = findViewById(R.id.boughtForText);
        boughtFor.setText("$5435.78");

        // Update sold for
        TextView soldFor = findViewById(R.id.soldForText);
        soldFor.setText("$25,689.90");

        // Update ROI
        TextView roi = findViewById(R.id.roiText);
        roi.setTextColor(Color.parseColor("#006400"));
        roi.setText("372.61%");
    }

    class AssetInfo {
        public String name;
        public int quantity;
        public int cashValue;

        AssetInfo(String name, int quantity, int cashValue) {
            this.name = name;
            this.quantity = quantity;
            this.cashValue = cashValue;
        }
    }

    public class AssetAdapter extends ArrayAdapter<AssetInfo> {
        public AssetAdapter(Context context, ArrayList<AssetInfo> assetInfoArrayList) {
            super(context, 0, assetInfoArrayList);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // Get the data item for this position
            AssetInfo assetInfo = getItem(position);
            // Check if an existing view is being reused, otherwise inflate the view
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.portfolio_item, parent, false);
            }
            // Lookup view for data population
            TextView asset = convertView.findViewById(R.id.assetId);
            TextView quantity  = convertView.findViewById(R.id.quantity);
            TextView cashValue = convertView.findViewById(R.id.value);

            // Populate the data into the template view using the data object
            asset.setText(assetInfo.name);
            quantity.setText(Integer.toString(assetInfo.quantity));
            cashValue.setText(Integer.toString(assetInfo.cashValue));

            // Return the completed view to render on screen
            return convertView;
        }
    }
}
