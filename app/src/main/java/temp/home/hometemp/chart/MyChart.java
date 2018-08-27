package temp.home.hometemp.chart;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.Drawable;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindColor;
import butterknife.BindDrawable;
import butterknife.ButterKnife;
import temp.home.hometemp.R;

public class MyChart {
    @BindColor(R.color.windowBackground) int windowBackground;
    @BindColor(R.color.colorPrimary) int colorPrimary;
    @BindDrawable(R.drawable.fade_green) Drawable fade_green;

    private LineChart mChart;
    private List<Entry> yVals = new ArrayList<>();
    private LineDataSet set1;
    private int index;

    public MyChart(Activity activity, LineChart chart){
        mChart = chart;
        index = 0;
        ButterKnife.bind(this,activity);
        setUpDatasetInfo();
    }

    private void setUpDatasetInfo() {
        mChart.setViewPortOffsets(0, 0, 0, 0);
        mChart.setBackgroundColor(windowBackground);
        mChart.setDrawGridBackground(false);
        mChart.getAxisRight().setEnabled(true);
        mChart.getLegend().setEnabled(false);
        mChart.setPinchZoom(false);
        mChart.setDoubleTapToZoomEnabled(false);
        mChart.getDescription().setEnabled(false);

        XAxis x = mChart.getXAxis();
        x.setEnabled(false);

        YAxis y = mChart.getAxisLeft();
        y.setLabelCount(7, true);
        y.setTextColor(Color.rgb(255, 255, 240));
        y.setPosition(YAxis.YAxisLabelPosition.INSIDE_CHART);
        y.setDrawGridLines(false);
        y.setAxisLineColor(Color.WHITE);
        y.setDrawAxisLine(false);
        y.setYOffset(5f);
        y.setXOffset(10f);
        y.setAxisMaximum(60f);
        y.setAxisMinimum(0f);

        yVals.add(new Entry(1,0));
        set1 = new LineDataSet(yVals, "DataSet 1");
        set1.setMode(LineDataSet.Mode.HORIZONTAL_BEZIER);
        set1.setCubicIntensity(0.1f);
        set1.setDrawCircles(false);
        set1.setLineWidth(2f);
        set1.setCircleRadius(4f);
        set1.setColor(colorPrimary);
        set1.setHighlightEnabled(false);
        set1.setFillAlpha(65);
        set1.setDrawFilled(true);

        if (Utils.getSDKInt() >= 18) {
            Drawable drawable = fade_green;
            set1.setFillDrawable(drawable);
        }
        else {
            set1.setFillColor(colorPrimary);
        }

        LineData data = new LineData(set1);
        data.setDrawValues(false);
        mChart.setData(data);
    }


    public void addData(Float value){
        if (yVals.size()> 20 )
            yVals.remove(0);
        if (index > 2000 ) {
            index = 1;
            yVals.clear();
        }
        yVals.add(new Entry(index++,value));
        set1.setValues(yVals);
        mChart.getData().notifyDataChanged();
        mChart.notifyDataSetChanged();
        mChart.animateX(0);
    }

}
