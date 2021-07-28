package com.queen.calendar.ui;

import androidx.appcompat.app.AppCompatActivity;
import com.queen.calendar.R;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;

public class ListActivity extends AppCompatActivity {

    Button mbtList;
    TextView tvO, tvSafe, tvCPP, tvFlo;
    PieChart pieChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        mbtList = (Button) findViewById(R.id.btLink);

        tvO = (TextView) findViewById(R.id.ovR);
        tvSafe = (TextView) findViewById(R.id.tvSafe);
        tvCPP = (TextView) findViewById(R.id.tvTime);
        tvFlo = (TextView) findViewById(R.id.tvAfter);
        pieChart = (PieChart) findViewById(R.id.piechart);
        setData();

        mbtList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListActivity.this, DashboardActivity.class);
                startActivity(intent);
            }
        });
    }

    private void setData() {

        // Set the percentage of language used
        tvO.setText(Integer.toString(8));
        tvSafe.setText(Integer.toString(9));
        tvCPP.setText(Integer.toString(5 ));
        tvFlo.setText(Integer.toString( 8));

        // Set the data and color to the pie chart
        pieChart.addPieSlice(
                new PieModel(
                        "Ovulatio period",
                        Integer.parseInt(tvO.getText().toString()),
                        Color.parseColor("#FFA726")));
        pieChart.addPieSlice(
                new PieModel(
                        "Safe Days",
                        Integer.parseInt(tvSafe.getText().toString()),
                        Color.parseColor("#66BB6A")));

        pieChart.addPieSlice(
                new PieModel(
                        "Python",
                        Integer.parseInt(tvSafe.getText().toString()),
                        Color.parseColor("#66BB6A")));
        pieChart.addPieSlice(
                new PieModel(
                        "Period Time",
                        Integer.parseInt(tvCPP.getText().toString()),
                        Color.parseColor("#EF5350")));
        pieChart.addPieSlice(
                new PieModel(
                        "AfterMath",
                        Integer.parseInt(tvFlo.getText().toString()),
                        Color.parseColor("#29B6F6")));

        // To animate the pie chart
        pieChart.startAnimation();
    }
}