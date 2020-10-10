package com.example.pendaftaransekolahmvp.presenter.register;

import com.example.pendaftaransekolahmvp.model.java.ResponseRegister;
import com.example.pendaftaransekolahmvp.network.java.ApiService;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterPresenter {

    //todo 5 (register) construct interface
    RegisterView registerView;

    public RegisterPresenter(RegisterView registerView) {
        this.registerView = registerView;
    }

    //todo 3 (register) fungsi register
    public void register(RequestBody namaLengkap, RequestBody noHp, RequestBody alamat, RequestBody emailAddress, RequestBody password, RequestBody confirmPassword, MultipartBody.Part photo)
    {
        //todo 4 (register) konfigurasi retrofit
        if (namaLengkap!=null && noHp!=null && alamat!=null && alamat!=null && emailAddress!=null && password!=null && confirmPassword!=null)
        {
            if (password.equals(confirmPassword))
            {
                registerView.passwordTidakSama();
                registerView.hideLoading();
                registerView.hideProgress();
            }
            else
            {
                Call<ResponseRegister> call = ApiService.Factory.create().signup(namaLengkap, noHp, alamat, emailAddress, password, confirmPassword, photo);
                call.enqueue(new Callback<ResponseRegister>() {
                    @Override
                    public void onResponse(Call<ResponseRegister> call, Response<ResponseRegister> response) {
                        if (response.body().getMessage().equals("Email address sudah ada"))
                        {
                            registerView.emailAda();
                            registerView.hideLoading();
                            registerView.hideProgress();
                        }
                        else
                        {
                            registerView.onSuccess(response.message());
                            registerView.hideLoading();
                            registerView.hideProgress();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseRegister> call, Throwable t) {
                        registerView.onError(t.getLocalizedMessage());
                        registerView.hideLoading();
                        registerView.hideProgress();
                    }
                });
            }
        }
        else
        {
            registerView.inputKosong();
            registerView.hideLoading();
            registerView.hideProgress();
        }
    }
}
