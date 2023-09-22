package basic.android.myfirstapp;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import basic.android.myfirstapp.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    private List<CheckBox> checkBoxList;
    private int attempts;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        attempts = 0;

        binding.errorMessage.setVisibility(View.GONE);
        binding.yesRB.setChecked(true);

        checkBoxList = new ArrayList<>();
        checkBoxList.add(binding.cbx1);
        checkBoxList.add(binding.cbx2);
        checkBoxList.add(binding.cbx3);
        checkBoxList.add(binding.cbx4);
        checkBoxList.add(binding.cbx5);
        checkBoxList.add(binding.cbx6);

        binding.btnClear.setOnClickListener(v -> {
            binding.textNames.setText("");
            binding.textLastNames.setText("");
            binding.textDateSelected.setText(R.string.EmptyDate);

            binding.yesRB.setChecked(true);
            binding.NoRB.setChecked(false);

            binding.spnGender.setSelection(0);

            for (CheckBox checkBox : checkBoxList) {
                checkBox.setChecked(false);
                checkBox.setEnabled(true);
            }
        });

        binding.radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == R.id.yesRB) {
                for (CheckBox checkBox : checkBoxList) {
                    checkBox.setEnabled(true);
                }

            } else if (checkedId == R.id.NoRB) {
                for (CheckBox checkBox : checkBoxList) {
                    checkBox.setChecked(false);
                    checkBox.setEnabled(false);
                }
            }
        });

        binding.btnDate.setOnClickListener(v -> {
            final Calendar c = Calendar.getInstance();

            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(
                    MainActivity.this,
                    (view, yearSelected, monthOfYear, dayOfMonth) -> {
                        String dateFormat = getString(R.string.date_format);
                        String formattedDate = String.format(dateFormat, dayOfMonth, (monthOfYear + 1), yearSelected);
                        binding.textDateSelected.setText(formattedDate);
                    },
                    year, month, day);

            datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());

            datePickerDialog.show();
        });

        binding.btnSend.setOnClickListener(v -> {
            attempts += 1;

            if (validateFields()) {
                binding.errorMessage.setVisibility(View.GONE);
                Intent intent = new Intent(MainActivity.this, DisplayData.class);

                String name = binding.textNames.getText().toString() + " " + binding.textLastNames.getText().toString();
                intent.putExtra("name", name);

                String gender;
                if (binding.spnGender.getSelectedItemPosition() == 3) {
                    gender = "Genero: Prefirió no especificar genero";
                } else {
                    gender = "Genero: " + binding.spnGender.getSelectedItem().toString();
                }
                intent.putExtra("gender", gender);

                String inputDate = binding.textDateSelected.getText().toString();
                String emptyDate = getString(R.string.EmptyDate);
                String birthdate;
                if (inputDate.equals(emptyDate)) {
                    birthdate = "Fecha de nacimiento: No seleccionada";
                } else {
                    birthdate = "Fecha de nacimiento: " + inputDate;
                }
                intent.putExtra("birthdate", birthdate);

                String coder, languages;
                if (binding.yesRB.isChecked()) {
                    coder = "Me gusta programar!";
                    languages = "Mis lenguajes favoritos son: \n";
                    for (CheckBox checkBox : checkBoxList) {
                        if (checkBox.isChecked()) {
                            String checkedLanguage = checkBox.getText().toString();
                            languages += "\t\u2022 " + checkedLanguage + "\n";
                        }
                    }
                } else {
                    coder = "No me gusta programar.";
                    languages = "";
                }
                intent.putExtra("coder", coder);
                intent.putExtra("languages", languages);

                String tries = "Le tomo ("+ attempts +") intentos para ingresar la data correctamente.";
                intent.putExtra("attempts", tries);

                startActivity(intent);
            } else {
                binding.errorMessage.setVisibility(View.VISIBLE);
                binding.errorMessage.setText(R.string.errorMissingFields);
            }
        });
    }

    private boolean validateFields() {
        String name = binding.textNames.getText().toString();
        String lastName = binding.textLastNames.getText().toString();
        int genderIndex = binding.spnGender.getSelectedItemPosition();

        if (name.isEmpty() || lastName.isEmpty() || genderIndex == 0) {
            showMessage("Debe ingresar un nombre, un apellido y seleccionar un género.");
            return false;
        }

        if (binding.yesRB.isChecked() && !validateCbx()) {
            showMessage("Debe seleccionar al menos un lenguaje.");
            return false;
        }

        return true;
    }

    private boolean validateCbx() {
        for (CheckBox checkBox : checkBoxList) {
            if (checkBox.isChecked()) {
                return true;
            }
        }
        return false;
    }

    private void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}