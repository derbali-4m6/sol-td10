package ca.qc.sol_td10.entities;

import android.os.Parcel;
import android.os.Parcelable;

public class Categorie implements Parcelable {
    private String _id;
    private String nom;

    public Categorie(String _id, String nom) {
        this._id = _id;
        this.nom = nom;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this._id);
        dest.writeString(this.nom);
    }

    public void readFromParcel(Parcel source) {
        this._id = source.readString();
        this.nom = source.readString();
    }

    protected Categorie(Parcel in) {
        this._id = in.readString();
        this.nom = in.readString();
    }

    public static final Parcelable.Creator<Categorie> CREATOR = new Parcelable.Creator<Categorie>() {
        @Override
        public Categorie createFromParcel(Parcel source) {
            return new Categorie(source);
        }

        @Override
        public Categorie[] newArray(int size) {
            return new Categorie[size];
        }
    };

    @Override
    public String toString() {
        return "Categorie{" +
                "_id='" + _id + '\'' +
                ", nom='" + nom + '\'' +
                '}';
    }
}
