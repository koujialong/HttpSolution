package com.example.administrator.httprequestdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.administrator.httprequestdemo.model.GetProvinceListModel;
import com.example.administrator.httprequestdemo.parser.IResultReceiver;
import com.example.administrator.httprequestdemo.parser.JsonParserFactory;
import com.example.administrator.httprequestdemo.utils.LogUtils;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    private TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text = (TextView) findViewById(R.id.text);
        getProvinceList();
    }

    /**
     * 获得省份数据
     * @date: 2016/2/19 10:39
     * @author: KJL
     * @param:
     * @return:
     */
    private void getProvinceList() {
        HttpTaskManager.startStringRequest(
                DataRequestUtils.getProvinceList(TAG,"ctrade1"),
                JsonParserFactory.parseBaseModel(GetProvinceListModel.class), new IResultReceiver() {

                    @Override
                    public void onReceiveResult(int resultCode, Object resultData) {
                        // TODO Auto-generated method stub
                        if (resultCode == ServerErrorCode.STATUS_SUCCESS) {
                            final GetProvinceListModel dataModel = (GetProvinceListModel) resultData;
                            StringBuffer sb=new StringBuffer();
                            if ((dataModel != null) && (dataModel.getItem() != null)) {
                                for (int i = 0; i <dataModel.getItem().getProvinceList().size() ; i++) {
                                    sb.append("  {"+dataModel.getItem().getProvinceList().get(i).getCodename()+"}  ");
                                }
                                text.setText(sb.toString());
                            }
                        } else {
                            LogUtils.d(TAG, "resultCode::: " + resultCode + "resultData ::: " + resultData);
                        }
                        LogUtils.d(TAG, "resultCode::: " + resultCode + "resultData ::: " + resultData);
                    }
                });
    }

}
