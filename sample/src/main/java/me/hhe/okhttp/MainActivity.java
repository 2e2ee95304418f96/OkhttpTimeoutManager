package me.hhe.okhttp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private TextView tvTest;
    private TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvTest = findViewById(R.id.tv_test);
        tvResult = findViewById(R.id.tv_result);

        findViewById(R.id.tv_test).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                testRetrofit(1);
            }
        });


        findViewById(R.id.tv_all).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                testRetrofit(2);
            }
        });


        findViewById(R.id.tv_apart).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                testRetrofit(3);
            }
        });


    }

    private void testRetrofit(int type) {
        long startTime = System.currentTimeMillis();
        tvResult.setText("requesting...");
        Api api = HttpUtils.getInstance().getApi();
        Call<String> call = null;
        switch (type) {
            case 1:
                call = api.getTestContent();
                break;
            case 2:
                call = api.timeoutAll();
                break;
            case 3:
                call = api.timeoutApart();
                break;
            default:
                break;
        }

        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                long diff = System.currentTimeMillis() - startTime;
                String string = new StringBuilder()
                        .append("network spend time:")
                        .append(diff)
                        .append("\n")
                        .append(response.body())
                        .toString();
                tvResult.setText(string);
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                long diff = System.currentTimeMillis() - startTime;
                String text = new StringBuilder()
                        .append("network spend time:")
                        .append(diff)
                        .append("\n")
                        .append("failed\n")
                        .append(t.getClass().getName())
                        .append("\n")
                        .append(t.getMessage())
                        .toString();
                tvResult.setText(text);
            }
        });
    }
}
