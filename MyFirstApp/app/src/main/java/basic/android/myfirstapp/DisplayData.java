package basic.android.myfirstapp;

import android.app.ActionBar;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import basic.android.myfirstapp.databinding.ActivityDisplayDataBinding;

public class DisplayData extends AppCompatActivity {

    ActivityDisplayDataBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDisplayDataBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = getIntent();

        String name = intent.getStringExtra("name");
        binding.nameTxt.setText(name);

        String gender = intent.getStringExtra("gender");
        binding.genderTxt.setText(gender);

        String birthdate = intent.getStringExtra("birthdate");
        binding.dateTxt.setText(birthdate);

        String coder = intent.getStringExtra("coder");
        binding.coderTxt.setText(coder);

        String languages = intent.getStringExtra("languages");
        binding.languagesTxt.setText(languages);

        String attempts = intent.getStringExtra("attempts");
        binding.attemptsTxt.setText(attempts);
    }
}