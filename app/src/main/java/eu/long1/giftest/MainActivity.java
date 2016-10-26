package eu.long1.giftest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText gifTextView = (EditText) findViewById(R.id.textView);

        SpannableStringBuilder sb = new SpannableStringBuilder();
        sb.append("Text followed by animated gif: ");
        String dummyText = "dummy";
        sb.append(dummyText);
        try {
            sb.setSpan(new AnimatedImageSpan(new AnimatedGifDrawable(getAssets().open("agif.gif"), new AnimatedGifDrawable.UpdateListener() {
                @Override
                public void update() {
                    gifTextView.invalidate();
                }
            })), sb.length() - dummyText.length(), sb.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        } catch (IOException e) {
            e.printStackTrace();
        }
        gifTextView.setText(sb);
    }
}
