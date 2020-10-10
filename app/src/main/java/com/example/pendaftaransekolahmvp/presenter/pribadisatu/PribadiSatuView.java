package com.example.pendaftaransekolahmvp.presenter.pribadisatu;

// todo 2 (pribadiSatu) kerangka respon
public interface PribadiSatuView {
    void onSuccess(String message);
    void onError(String message);
    void hideLoading();
    void hideProgres();
    void inputKosong();
}
