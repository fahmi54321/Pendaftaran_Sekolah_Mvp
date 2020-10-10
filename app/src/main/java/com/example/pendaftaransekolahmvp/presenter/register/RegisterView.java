package com.example.pendaftaransekolahmvp.presenter.register;

//todo 2 (register) kerangka respon dari proses register
public interface RegisterView {
    void onSuccess(String message);
    void onError(String message);
    void inputKosong();
    void hideProgress();
    void hideLoading();
    void emailAda();
    void passwordTidakSama();
    void panjangPassword();
}
