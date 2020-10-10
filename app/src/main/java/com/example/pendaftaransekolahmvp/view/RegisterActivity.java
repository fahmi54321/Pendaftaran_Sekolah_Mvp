package com.example.pendaftaransekolahmvp.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.pendaftaransekolahmvp.R;
import com.example.pendaftaransekolahmvp.presenter.register.RegisterPresenter;
import com.example.pendaftaransekolahmvp.presenter.register.RegisterView;
import com.example.pendaftaransekolahmvp.utils.ImageAttachmentListener;
import com.example.pendaftaransekolahmvp.utils.ImageUtils;

import java.io.File;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class RegisterActivity extends AppCompatActivity implements ImageAttachmentListener, RegisterView {

    //todo 6 (register) deklarasi presenter
    private RegisterPresenter registerPresenter;
    File photo;
    private ImageUtils imageutils;
    CircleImageView imgProfile;
    ImageView imgPlus;
    EditText namaLengkap, noHp, alamat, emailAddress, password, confirmPassword;
    Button bttnSignup;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //todo 7 (register) inisialisasi presenter
        registerPresenter = new RegisterPresenter(this);

        imgProfile = findViewById(R.id.imgProfile);
        imgPlus = findViewById(R.id.imgPlus);
        namaLengkap = findViewById(R.id.edtNamaLengkap);
        noHp = findViewById(R.id.edtNoHp);
        emailAddress = findViewById(R.id.edtemailAddress);
        password = findViewById(R.id.edtpassword);
        alamat = findViewById(R.id.edtAlamat);
        bttnSignup = findViewById(R.id.bttnSignup);
        confirmPassword = findViewById(R.id.edtConfirmPassword);
        progressBar = findViewById(R.id.progressBar);

        imageutils = new ImageUtils(this);

        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED ||
                    checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE}, 0);
            }
        }

        imgPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageutils.imagepicker(1);
            }
        });

        bttnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                bttnSignup.setText("LOADING...");
                bttnSignup.setEnabled(false);
                bttnSignup.setBackgroundColor(Color.rgb(181, 76, 67));
                namaLengkap.setEnabled(false);
                noHp.setEnabled(false);
                alamat.setEnabled(false);
                emailAddress.setEnabled(false);
                password.setEnabled(false);
                confirmPassword.setEnabled(false);
                imgPlus.setVisibility(View.GONE);
                progressBar.setVisibility(View.VISIBLE);

                //todo 1(register) ambil view
                if (photo != null) {
                    RequestBody requestBody = RequestBody.create(MediaType.parse("image/*"), photo);

                    //namaLengkap
                    RequestBody requestBodyNamaLengkap = requestBody.create(MediaType.parse("text/plain"), namaLengkap.getText().toString());

                    //No Hp
                    RequestBody requestBodyNohp = requestBody.create(MediaType.parse("text/plain"), noHp.getText().toString());

                    //alamat
                    RequestBody requestBodyAlamat = requestBody.create(MediaType.parse("text/plain"), alamat.getText().toString());

                    //emailAddress
                    RequestBody requestBodyEmailAddress = requestBody.create(MediaType.parse("text/plain"), emailAddress.getText().toString());

                    //password
                    RequestBody requestBodyPassword = requestBody.create(MediaType.parse("text/plain"), password.getText().toString());

                    //confirmPassword
                    RequestBody requestBodyConfirmPassword = requestBody.create(MediaType.parse("text/plain"), confirmPassword.getText().toString());

                    //photo
                    MultipartBody.Part multipartBodyPhoto = MultipartBody.Part.createFormData("foto", photo.getName(), requestBody);


                    //todo 9 (register) eksekusi presenter
                    registerPresenter.register(requestBodyNamaLengkap, requestBodyNohp, requestBodyAlamat, requestBodyEmailAddress, requestBodyPassword, requestBodyConfirmPassword, multipartBodyPhoto);
                } else {
                    toast("Photo tidak boleh kosong");
                    bttnSignup.setText("Sign Up");
                    bttnSignup.setEnabled(true);
                    bttnSignup.setBackgroundColor(Color.rgb(245, 112, 100));
                    namaLengkap.setEnabled(true);
                    noHp.setEnabled(true);
                    alamat.setEnabled(true);
                    emailAddress.setEnabled(true);
                    password.setEnabled(true);
                    confirmPassword.setEnabled(true);
                    imgPlus.setVisibility(View.VISIBLE);
                    progressBar.setVisibility(View.GONE);
                }
            }
        });
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
        imgProfile.setImageBitmap(file);

        String path = imageutils.getPath(uri);
        photo = new File(path);
    }

    void toast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }


    //todo 8 (register) implementasi presenter
    @Override
    public void onSuccess(String message) {
        toast("Berhasil");
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    @Override
    public void onError(String message) {
        toast("Gagal");
    }

    @Override
    public void inputKosong() {
        toast("Tidak boleh kosong");
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void hideLoading() {
        bttnSignup.setText("Sign Up");
        bttnSignup.setEnabled(true);
        bttnSignup.setBackgroundColor(Color.rgb(245, 112, 100));
        namaLengkap.setEnabled(true);
        noHp.setEnabled(true);
        alamat.setEnabled(true);
        emailAddress.setEnabled(true);
        password.setEnabled(true);
        confirmPassword.setEnabled(true);
        imgPlus.setVisibility(View.VISIBLE);
    }

    @Override
    public void emailAda() {
        toast("Email sudah ada");
    }

    @Override
    public void passwordTidakSama() {
        toast("Password Tidak Sama");
    }

    @Override
    public void panjangPassword() {
        toast("Panjang Password harus lebih dari 6 karakter");
    }
}