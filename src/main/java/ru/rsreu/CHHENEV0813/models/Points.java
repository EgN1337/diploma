package ru.rsreu.CHHENEV0813.models;

public class Points {

    private int mouthPoints;
    private int eyeBrowsPoints;
    private int nosePoints;
    private int eyePoints;
    private int hairPoints;

    public Points(){

    }

    public Points(int mouthPoints, int eyeBrowsPoints, int nosePoints, int eyePoints, int hairPoints) {
        this.mouthPoints = mouthPoints;
        this.eyeBrowsPoints = eyeBrowsPoints;
        this.nosePoints = nosePoints;
        this.eyePoints = eyePoints;
        this.hairPoints = hairPoints;
    }

    public int getMouthPoints() {
        return mouthPoints;
    }

    public void setMouthPoints(int mouthPoints) {
        this.mouthPoints = mouthPoints;
    }

    public int getEyeBrowsPoints() {
        return eyeBrowsPoints;
    }

    public void setEyeBrowsPoints(int eyeBrowsPoints) {
        this.eyeBrowsPoints = eyeBrowsPoints;
    }

    public int getNosePoints() {
        return nosePoints;
    }

    public void setNosePoints(int nosePoints) {
        this.nosePoints = nosePoints;
    }

    public int getEyePoints() {
        return eyePoints;
    }

    public void setEyePoints(int eyePoints) {
        this.eyePoints = eyePoints;
    }

    public int getHairPoints() {
        return hairPoints;
    }

    public void setHairPoints(int hairPoints) {
        this.hairPoints = hairPoints;
    }
}
