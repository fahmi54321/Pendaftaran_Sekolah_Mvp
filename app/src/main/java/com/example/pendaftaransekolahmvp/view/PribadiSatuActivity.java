package com.example.pendaftaransekolahmvp.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pendaftaransekolahmvp.R;
import com.example.pendaftaransekolahmvp.presenter.pribadisatu.PribadiSatuPresenter;
import com.example.pendaftaransekolahmvp.presenter.pribadisatu.PribadiSatuView;
import com.example.pendaftaransekolahmvp.utils.ImageAttachmentListener;
import com.example.pendaftaransekolahmvp.utils.ImageUtils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class PribadiSatuActivity extends AppCompatActivity implements ImageAttachmentListener, PribadiSatuView {

    //deklarasi animasi
    Animation top_to_bottom,bottom_to_top,left_to_right,right_to_left;

    TextView idUser,txtNamaPribadi,ketNamaPribadi,txtJenisKelamin,ketJenisKelamin,txtNisn,ketNisn,txtNik,ketNik,txtTempatLahir,ketTempatLahir,txtTglLahir,ketTglLahir;
    ProgressBar progressBar;
    ImageView imageViewNisn,imageViewNik,imgTgl;
    Button bttnFotoNisn,bttnFotoNik,bttnSimpan;
    EditText txtTanggal,edtNamaPribadi,edtNisn,edtNik,edtTempatLahir;
    RadioGroup rgJk;
    private ImageUtils imageutils;
    RadioButton rbLaki,rbPerempuan;
    private File fileImageNisn,fileImageNik;
    private DatePickerDialog datePickerDialog;
    private SimpleDateFormat dateFormatter;

    // todo 5 (pribadiSatu) deklarasi presenter
    private PribadiSatuPresenter pribadiSatuPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pribadi_satu);

        //inisialiasi
        init();

        Bundle bundle1 = getIntent().getExtras();
        idUser.setText(bundle1.getString("id"));

        //inisialiasi animasi
        left_to_right = AnimationUtils.loadAnimation(this,R.anim.left_to_right);
        bottom_to_top = AnimationUtils.loadAnimation(this,R.anim.bottom_to_top);

        //eksekusi animasi
        txtNamaPribadi.startAnimation(left_to_right);
        edtNamaPribadi.startAnimation(left_to_right);
        ketNamaPribadi.startAnimation(left_to_right);
        txtJenisKelamin.startAnimation(left_to_right);
        rbLaki.startAnimation(left_to_right);
        rbPerempuan.startAnimation(left_to_right);
        ketJenisKelamin.startAnimation(left_to_right);
        txtNisn.startAnimation(left_to_right);
        edtNisn.startAnimation(left_to_right);
        ketNisn.startAnimation(left_to_right);
        bttnFotoNisn.startAnimation(left_to_right);
        imageViewNisn.startAnimation(left_to_right);
        txtNik.startAnimation(left_to_right);
        edtNik.startAnimation(left_to_right);
        ketNik.startAnimation(left_to_right);
        bttnFotoNik.startAnimation(left_to_right);
        imageViewNik.startAnimation(left_to_right);
        txtTempatLahir.startAnimation(left_to_right);
        edtTempatLahir.startAnimation(left_to_right);
        ketTempatLahir.startAnimation(left_to_right);
        txtTglLahir.startAnimation(left_to_right);
        imgTgl.startAnimation(left_to_right);
        txtTglLahir.startAnimation(left_to_right);
        ketTglLahir.startAnimation(left_to_right);
        bttnSimpan.startAnimation(bottom_to_top);

        // inisialisasi datepicker
        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);

        imageutils = new ImageUtils(this);

        // todo 5 (pribadiSatu) inisialisasi presenter
        pribadiSatuPresenter = new PribadiSatuPresenter(this);

        if(Build.VERSION.SDK_INT>=23)
        {
            if(checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED ||
                    checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)
            {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA,Manifest.permission.READ_EXTERNAL_STORAGE},0);
            }
        }


        imgTgl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showTanggal();
            }
        });

        bttnFotoNik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageutils.imagepicker(1);
            }
        });

        bttnFotoNisn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageutils.imagepicker(2);
            }
        });

        bttnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // todo 1 (pribadiSatu) ambil view
                if (fileImageNik!=null && fileImageNisn!=null)
                {
                    bttnSimpan.setText("LOADING...");
                    bttnSimpan.setEnabled(false);
                    bttnSimpan.setBackgroundColor(Color.rgb(181, 76, 67));
                    progressBar.setVisibility(View.VISIBLE);

                    RequestBody requestBody = RequestBody.create(MediaType.parse("image/*"),fileImageNisn);
                    RequestBody requestBody1 = RequestBody.create(MediaType.parse("image/*"),fileImageNik);

                    //Id
                    RequestBody requestBodyId = requestBody.create(MediaType.parse("text/plain"),idUser.getText().toString());

                    //namaLengkap
                    RequestBody requestBodyNamaPribadi = requestBody.create(MediaType.parse("text/plain"),edtNamaPribadi.getText().toString());

                    //jenisKelamin
                    String jk="";
                    if (rbPerempuan.isChecked())
                    {
                        jk="P";
                    }
                    else if(rbLaki.isChecked())
                    {
                        jk="L";
                    }

                    RequestBody requestBodyJenisKelamin = requestBody.create(MediaType.parse("text/plain"),jk);

                    //NISN
                    RequestBody requestBodyNisn = requestBody.create(MediaType.parse("text/plain"),edtNisn.getText().toString());

                    //photo Nisn
                    MultipartBody.Part multipartBodyPhotoNisn =MultipartBody.Part.createFormData("foto_nisn",fileImageNisn.getName(),requestBody);

                    //NIK
                    RequestBody requestBodyNik = requestBody.create(MediaType.parse("text/plain"),edtNik.getText().toString());

                    //photo Nik
                    MultipartBody.Part multipartBodyPhotoNik =MultipartBody.Part.createFormData("foto_nik",fileImageNik.getName(),requestBody1);

                    //Tempat Lahir
                    RequestBody requestBodyTempatLahir = requestBody.create(MediaType.parse("text/plain"),edtTempatLahir.getText().toString());

                    //Tgl Lahir
                    RequestBody requestBodyTglLahir = requestBody.create(MediaType.parse("text/plain"),txtTanggal.getText().toString());

                    // todo 7 (pribadiSatu) eksekusi presenter
                    pribadiSatuPresenter.createPribadiSatu(requestBodyId,requestBodyNamaPribadi,requestBodyJenisKelamin,requestBodyNisn,multipartBodyPhotoNisn,requestBodyNik,multipartBodyPhotoNik,requestBodyTempatLahir,requestBodyTglLahir);
                }
                else
                {
                    toast("Foto Tidak boleh Kosong");
                }
            }
        });
    }

    private void showTanggal() {
        Calendar newCalendar = Calendar.getInstance();
        datePickerDialog = new DatePickerDialog(PribadiSatuActivity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                txtTanggal.setText(dateFormatter.format(newDate.getTime()));
            }
        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        imageutils.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        imageutils.request_permission_result(requestCode, permissions, grantResults);
    }

    @Override
    public void image_attachment(int from, String filename, Bitmap file, Uri uri) {

        if (from==1)
        {
            imageViewNik.setImageBitmap(file);

            String path = imageutils.getPath(uri);
            fileImageNik = new File(path);
        }
        if (from==2)
        {
            imageViewNisn.setImageBitmap(file);

            String path = imageutils.getPath(uri);
            fileImageNisn = new File(path);
        }
    }

    void toast(String message)
    {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    // todo 6 (pribadiSatu) implementasi presenter
    @Override
    public void onSuccess(String message) {
        toast("Berhasil");
        Intent intent = new Intent(PribadiSatuActivity.this,SuccessIsiFormActivity.class);
        intent.putExtra("idUser",idUser.getText().toString());
        startActivity(intent);
    }

    @Override
    public void onError(String message) {
        toast("Gagal");
    }

    @Override
    public void hideLoading() {
        bttnSimpan.setText("Simpan");
        bttnSimpan.setEnabled(true);
        bttnSimpan.setBackgroundColor(Color.rgb(245,112,100));

    }

    @Override
    public void hideProgres() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void inputKosong() {
        toast("Tidak boleh kosong");
    }

    void init()
    {
        imgTgl = findViewById(R.id.imgTanggal);
        idUser = findViewById(R.id.idUser);
        edtNamaPribadi = findViewById(R.id.edtNamaPribadi);
        edtNisn = findViewById(R.id.edtNisn);
        edtNik = findViewById(R.id.edtNik);
        edtTempatLahir = findViewById(R.id.edtTempatLahir);
        txtTanggal = findViewById(R.id.edtTanggalLahir);
        imageViewNisn = findViewById(R.id.imageNisn);
        bttnFotoNisn = findViewById(R.id.bttnFotoNisn);
        bttnFotoNik = findViewById(R.id.bttnFotoNik);
        imageViewNik = findViewById(R.id.imageNik);
        rgJk = findViewById(R.id.rgJk);
        rbLaki = findViewById(R.id.rbLaki);
        rbPerempuan = findViewById(R.id.rbPerempuan);
        imageViewNik = findViewById(R.id.imageNik);
        bttnSimpan = findViewById(R.id.bttnSimpan);
        progressBar = findViewById(R.id.progressBarPribadiSatu);
        txtNamaPribadi = findViewById(R.id.txtNamaPribadi);
        ketNamaPribadi = findViewById(R.id.ketNamaPribadi);
        txtJenisKelamin = findViewById(R.id.txtJenisKelamin);
        ketJenisKelamin = findViewById(R.id.ketJenisKelamin);
        txtNisn = findViewById(R.id.txtNisn);
        ketNisn = findViewById(R.id.ketNisn);
        txtNik = findViewById(R.id.txtNik);
        ketNik = findViewById(R.id.ketNik);
        txtTempatLahir = findViewById(R.id.txtTempatLahir);
        ketTempatLahir = findViewById(R.id.ketTempatLahir);
        txtTglLahir = findViewById(R.id.txtTglLahir);
        ketTglLahir = findViewById(R.id.ketTglLahir);
    }
}