package com.coffee.covidtrace.Ui;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.coffee.covidtrace.Data.ReportCase;
import com.coffee.covidtrace.Data.UserEntity;
import com.coffee.covidtrace.R;
import com.coffee.covidtrace.Repository.ReportCaseRepository;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.io.IOException;
import java.io.InputStream;

public class ReportCaseActivity extends AppCompatActivity {

    private Button btnPickDate, btn_open_camera;
    private TextInputEditText txShowDateButton;
    private static final int GALLERY_REQUEST_CODE = 100;
    private static final int CAMERA_REQUEST_CODE = 1;
    private ImageView selectedImageView;
    private EditText titleEditText;
    TextInputEditText new_case_title;
    ReportCase reportCase;
    UserEntity currentUser;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_case);

        // toolbar is defined in the layout file
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Report Rule-Breaking Case");
        setSupportActionBar(toolbar);

        // Get a support ActionBar corresponding to this toolbar
        ActionBar ab = getSupportActionBar();

        // Enable the Up button
        assert ab != null;
//        ab.setDisplayHomeAsUpEnabled(true);

        btnPickDate = findViewById(R.id.btn_pick_date);
        txShowDateButton = findViewById(R.id.tx_show_date);
        new_case_title = findViewById(R.id.new_case_title);
        selectedImageView = findViewById(R.id.new_selected_image);
        btn_open_camera  =findViewById(R.id.btn_open_camera);

        currentUser = (UserEntity) getIntent().getSerializableExtra("user");
        // now create instance of the material date picker
        // builder make sure to add the "datePicker" which
        // is normal material date picker which is the first
        // type of the date picker in material design date
        // picker
        MaterialDatePicker.Builder <Long> materialDateBuilder = MaterialDatePicker.Builder.datePicker();

        // now define the properties of the
        // materialDateBuilder that is title text as SELECT A DATE
        materialDateBuilder.setTitleText("SELECT A DATE");

        // now create the instance of the material date
        // picker
        final MaterialDatePicker <Long> materialDatePicker = materialDateBuilder.build();

        // handle select date button which opens the
        // material design date picker
        btnPickDate.setOnClickListener(
                v -> {
                    // getSupportFragmentManager() to
                    // interact with the fragments
                    // associated with the material design
                    // date picker tag is to get any error
                    // in logcat
                    materialDatePicker.show(getSupportFragmentManager(), "MATERIAL_DATE_PICKER");
                });

        // now handle the positive button click from the
        // material design date picker
        materialDatePicker.addOnPositiveButtonClickListener(
                selection -> {

                    // if the user clicks on the positive
                    // button that is ok button update the
                    // selected date
                    txShowDateButton.setText(materialDatePicker.getHeaderText());
                    // in the above statement, getHeaderText
                    // is the selected date preview from the
                    // dialog
                });
//        btn_open_camera.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                try {
//                    Intent intent = new Intent();
//                    intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
//                    startActivity(intent);
//                }catch (Exception e){
//                    e.printStackTrace();
//                }
//            }
//        });
    }

    public void openGallery(View view) {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), GALLERY_REQUEST_CODE);
    }

    public void openCamera(View view) {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, CAMERA_REQUEST_CODE);
        }else
        {
            Toast.makeText(
                    getApplicationContext(),
                    "Reopen camera open.",
                    Toast.LENGTH_LONG).show();
        }
    }

    public void cancel(View view) {
        finish();
    }

    public void save(View view) {
        Bitmap image = ((BitmapDrawable)selectedImageView.getDrawable()).getBitmap();
        reportCase = new ReportCase(new_case_title.getText().toString(), image, txShowDateButton.getText().toString(), currentUser.getId());
        if (!new_case_title.getText().toString().equals("") && !txShowDateButton.getText().toString().equals("") && image!=null)
        {
            new ReportCaseRepository(getApplication()).insert(reportCase);
            finish();

        } else
        {
            Toast.makeText(
                    getApplicationContext(),
                    "Please enter the information required",
                    Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && requestCode == GALLERY_REQUEST_CODE) {
            try {
                Uri selectedImage = data.getData();
                InputStream imageStream = getContentResolver().openInputStream(selectedImage);
                selectedImageView.setImageBitmap(BitmapFactory.decodeStream(imageStream));
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }

        if (requestCode == CAMERA_REQUEST_CODE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            selectedImageView.setImageBitmap(imageBitmap);
        }
    }
}