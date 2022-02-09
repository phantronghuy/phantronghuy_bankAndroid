package com.example.tronghuy_b18_bank;

import android.graphics.Bitmap;

public class TyGia {
    private String type;
    private String imageUrl;
    private Bitmap bitmap;
    private String muaTienMat;
    private String muaCk;
    private String banTienMat;
    private String banCk;

    public TyGia(){

    }
    public TyGia(String type, String imageUrl, Bitmap bitmap, String muaTienMat, String muaCk, String banTienMat, String banCk) {
        this.type = type;
        this.imageUrl = imageUrl;
        this.bitmap = bitmap;
        this.muaTienMat = muaTienMat;
        this.muaCk = muaCk;
        this.banTienMat = banTienMat;
        this.banCk = banCk;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public String getMuaTienMat() {
        return muaTienMat;
    }

    public void setMuaTienMat(String muaTienMat) {
        this.muaTienMat = muaTienMat;
    }

    public String getMuaCk() {
        return muaCk;
    }

    public void setMuaCk(String muaCk) {
        this.muaCk = muaCk;
    }

    public String getBanTienMat() {
        return banTienMat;
    }

    public void setBanTienMat(String banTienMat) {
        this.banTienMat = banTienMat;
    }

    public String getBanCk() {
        return banCk;
    }

    public void setBanCk(String banCk) {
        this.banCk = banCk;
    }
}
