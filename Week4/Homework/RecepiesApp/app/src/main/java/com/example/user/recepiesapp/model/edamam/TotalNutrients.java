
package com.example.user.recepiesapp.model.edamam;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TotalNutrients implements Parcelable {

    @SerializedName("ENERC_KCAL")
    @Expose
    private ENERCKCAL eNERCKCAL;
    @SerializedName("FAT")
    @Expose
    private FAT fAT;
    @SerializedName("FASAT")
    @Expose
    private FASAT fASAT;
    @SerializedName("FATRN")
    @Expose
    private FATRN fATRN;
    @SerializedName("FAMS")
    @Expose
    private FAMS fAMS;
    @SerializedName("FAPU")
    @Expose
    private FAPU fAPU;
    @SerializedName("CHOCDF")
    @Expose
    private CHOCDF cHOCDF;
    @SerializedName("FIBTG")
    @Expose
    private FIBTG fIBTG;
    @SerializedName("SUGAR")
    @Expose
    private SUGAR sUGAR;
    @SerializedName("PROCNT")
    @Expose
    private PROCNT pROCNT;
    @SerializedName("CHOLE")
    @Expose
    private CHOLE cHOLE;
    @SerializedName("NA")
    @Expose
    private NA nA;
    @SerializedName("CA")
    @Expose
    private CA cA;
    @SerializedName("MG")
    @Expose
    private MG mG;
    @SerializedName("K")
    @Expose
    private K k;
    @SerializedName("FE")
    @Expose
    private FE fE;
    @SerializedName("ZN")
    @Expose
    private ZN zN;
    @SerializedName("P")
    @Expose
    private P p;
    @SerializedName("VITA_RAE")
    @Expose
    private VITARAE vITARAE;
    @SerializedName("VITC")
    @Expose
    private VITC vITC;
    @SerializedName("THIA")
    @Expose
    private THIA tHIA;
    @SerializedName("RIBF")
    @Expose
    private RIBF rIBF;
    @SerializedName("NIA")
    @Expose
    private NIA nIA;
    @SerializedName("VITB6A")
    @Expose
    private VITB6A vITB6A;
    @SerializedName("FOLDFE")
    @Expose
    private FOLDFE fOLDFE;
    @SerializedName("FOLFD")
    @Expose
    private FOLFD fOLFD;
    @SerializedName("VITB12")
    @Expose
    private VITB12 vITB12;
    @SerializedName("VITD")
    @Expose
    private VITD vITD;
    @SerializedName("TOCPHA")
    @Expose
    private TOCPHA tOCPHA;
    @SerializedName("VITK1")
    @Expose
    private VITK1 vITK1;

    protected TotalNutrients(Parcel in) {
        eNERCKCAL = in.readParcelable(ENERCKCAL.class.getClassLoader());
        fAT = in.readParcelable(FAT.class.getClassLoader());
        fASAT = in.readParcelable(FASAT.class.getClassLoader());
        fATRN = in.readParcelable(FATRN.class.getClassLoader());
        fAMS = in.readParcelable(FAMS.class.getClassLoader());
        fAPU = in.readParcelable(FAPU.class.getClassLoader());
        cHOCDF = in.readParcelable(CHOCDF.class.getClassLoader());
        fIBTG = in.readParcelable(FIBTG.class.getClassLoader());
        sUGAR = in.readParcelable(SUGAR.class.getClassLoader());
        pROCNT = in.readParcelable(PROCNT.class.getClassLoader());
        cHOLE = in.readParcelable(CHOLE.class.getClassLoader());
        nA = in.readParcelable(NA.class.getClassLoader());
        cA = in.readParcelable(CA.class.getClassLoader());
        mG = in.readParcelable(MG.class.getClassLoader());
        k = in.readParcelable(K.class.getClassLoader());
        fE = in.readParcelable(FE.class.getClassLoader());
        p = in.readParcelable(P.class.getClassLoader());
        tHIA = in.readParcelable(THIA.class.getClassLoader());
        rIBF = in.readParcelable(RIBF.class.getClassLoader());
        nIA = in.readParcelable(NIA.class.getClassLoader());
        fOLDFE = in.readParcelable(FOLDFE.class.getClassLoader());
        fOLFD = in.readParcelable(FOLFD.class.getClassLoader());
        tOCPHA = in.readParcelable(TOCPHA.class.getClassLoader());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(eNERCKCAL, flags);
        dest.writeParcelable(fAT, flags);
        dest.writeParcelable(fASAT, flags);
        dest.writeParcelable(fATRN, flags);
        dest.writeParcelable(fAMS, flags);
        dest.writeParcelable(fAPU, flags);
        dest.writeParcelable(cHOCDF, flags);
        dest.writeParcelable(fIBTG, flags);
        dest.writeParcelable(sUGAR, flags);
        dest.writeParcelable(pROCNT, flags);
        dest.writeParcelable(cHOLE, flags);
        dest.writeParcelable(nA, flags);
        dest.writeParcelable(cA, flags);
        dest.writeParcelable(mG, flags);
        dest.writeParcelable(k, flags);
        dest.writeParcelable(fE, flags);
        dest.writeParcelable(p, flags);
        dest.writeParcelable(tHIA, flags);
        dest.writeParcelable(rIBF, flags);
        dest.writeParcelable(nIA, flags);
        dest.writeParcelable(fOLDFE, flags);
        dest.writeParcelable(fOLFD, flags);
        dest.writeParcelable(tOCPHA, flags);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<TotalNutrients> CREATOR = new Creator<TotalNutrients>() {
        @Override
        public TotalNutrients createFromParcel(Parcel in) {
            return new TotalNutrients(in);
        }

        @Override
        public TotalNutrients[] newArray(int size) {
            return new TotalNutrients[size];
        }
    };

    public ENERCKCAL getENERCKCAL() {
        return eNERCKCAL;
    }

    public void setENERCKCAL(ENERCKCAL eNERCKCAL) {
        this.eNERCKCAL = eNERCKCAL;
    }

    public FAT getFAT() {
        return fAT;
    }

    public void setFAT(FAT fAT) {
        this.fAT = fAT;
    }

    public FASAT getFASAT() {
        return fASAT;
    }

    public void setFASAT(FASAT fASAT) {
        this.fASAT = fASAT;
    }

    public FATRN getFATRN() {
        return fATRN;
    }

    public void setFATRN(FATRN fATRN) {
        this.fATRN = fATRN;
    }

    public FAMS getFAMS() {
        return fAMS;
    }

    public void setFAMS(FAMS fAMS) {
        this.fAMS = fAMS;
    }

    public FAPU getFAPU() {
        return fAPU;
    }

    public void setFAPU(FAPU fAPU) {
        this.fAPU = fAPU;
    }

    public CHOCDF getCHOCDF() {
        return cHOCDF;
    }

    public void setCHOCDF(CHOCDF cHOCDF) {
        this.cHOCDF = cHOCDF;
    }

    public FIBTG getFIBTG() {
        return fIBTG;
    }

    public void setFIBTG(FIBTG fIBTG) {
        this.fIBTG = fIBTG;
    }

    public SUGAR getSUGAR() {
        return sUGAR;
    }

    public void setSUGAR(SUGAR sUGAR) {
        this.sUGAR = sUGAR;
    }

    public PROCNT getPROCNT() {
        return pROCNT;
    }

    public void setPROCNT(PROCNT pROCNT) {
        this.pROCNT = pROCNT;
    }

    public CHOLE getCHOLE() {
        return cHOLE;
    }

    public void setCHOLE(CHOLE cHOLE) {
        this.cHOLE = cHOLE;
    }

    public NA getNA() {
        return nA;
    }

    public void setNA(NA nA) {
        this.nA = nA;
    }

    public CA getCA() {
        return cA;
    }

    public void setCA(CA cA) {
        this.cA = cA;
    }

    public MG getMG() {
        return mG;
    }

    public void setMG(MG mG) {
        this.mG = mG;
    }

    public K getK() {
        return k;
    }

    public void setK(K k) {
        this.k = k;
    }

    public FE getFE() {
        return fE;
    }

    public void setFE(FE fE) {
        this.fE = fE;
    }

    public ZN getZN() {
        return zN;
    }

    public void setZN(ZN zN) {
        this.zN = zN;
    }

    public P getP() {
        return p;
    }

    public void setP(P p) {
        this.p = p;
    }

    public VITARAE getVITARAE() {
        return vITARAE;
    }

    public void setVITARAE(VITARAE vITARAE) {
        this.vITARAE = vITARAE;
    }

    public VITC getVITC() {
        return vITC;
    }

    public void setVITC(VITC vITC) {
        this.vITC = vITC;
    }

    public THIA getTHIA() {
        return tHIA;
    }

    public void setTHIA(THIA tHIA) {
        this.tHIA = tHIA;
    }

    public RIBF getRIBF() {
        return rIBF;
    }

    public void setRIBF(RIBF rIBF) {
        this.rIBF = rIBF;
    }

    public NIA getNIA() {
        return nIA;
    }

    public void setNIA(NIA nIA) {
        this.nIA = nIA;
    }

    public VITB6A getVITB6A() {
        return vITB6A;
    }

    public void setVITB6A(VITB6A vITB6A) {
        this.vITB6A = vITB6A;
    }

    public FOLDFE getFOLDFE() {
        return fOLDFE;
    }

    public void setFOLDFE(FOLDFE fOLDFE) {
        this.fOLDFE = fOLDFE;
    }

    public FOLFD getFOLFD() {
        return fOLFD;
    }

    public void setFOLFD(FOLFD fOLFD) {
        this.fOLFD = fOLFD;
    }

    public VITB12 getVITB12() {
        return vITB12;
    }

    public void setVITB12(VITB12 vITB12) {
        this.vITB12 = vITB12;
    }

    public VITD getVITD() {
        return vITD;
    }

    public void setVITD(VITD vITD) {
        this.vITD = vITD;
    }

    public TOCPHA getTOCPHA() {
        return tOCPHA;
    }

    public void setTOCPHA(TOCPHA tOCPHA) {
        this.tOCPHA = tOCPHA;
    }

    public VITK1 getVITK1() {
        return vITK1;
    }

    public void setVITK1(VITK1 vITK1) {
        this.vITK1 = vITK1;
    }

}
