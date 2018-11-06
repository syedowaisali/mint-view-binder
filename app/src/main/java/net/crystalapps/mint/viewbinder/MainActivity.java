package net.crystalapps.mint.viewbinder;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import net.crystalapps.mint.viewbinder.library.core.BindView;
import net.crystalapps.mint.viewbinder.library.core.OnClick;
import net.crystalapps.mint.viewbinder.library.core.ViewBinder;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.tvText)
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // set layout
        setContentView(R.layout.activity_main);

        // bind views & clicks
        ViewBinder.bind(this);
    }

    @OnClick(R.id.btnClickMe)
    private void clickMe() {
        textView.setText("Click Performed!");
    }
}