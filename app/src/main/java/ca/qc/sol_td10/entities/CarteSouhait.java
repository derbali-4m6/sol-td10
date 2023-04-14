package ca.qc.sol_td10.entities;

import android.os.Parcel;
import android.os.Parcelable;

public class CarteSouhait implements Parcelable {
    private String _id;
    private String url;
    private String categorie_id;

    public CarteSouhait(String _id, String url, String categorie_id) {
        this._id = _id;
        this.url = url;
        this.categorie_id = categorie_id;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCategorie_id() {
        return categorie_id;
    }

    public void setCategorie_id(String categorie_id) {
        this.categorie_id = categorie_id;
    }

    @Override
    public String toString() {
        return "CarteSouhait{" +
                "_id=" + _id +
                ", url='" + url + '\'' +
                ", categorie_id='" + categorie_id + '\'' +
                '}';
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this._id);
        dest.writeString(this.url);
        dest.writeString(this.categorie_id);
    }

    public void readFromParcel(Parcel source) {
        this._id = source.readString();
        this.url = source.readString();
        this.categorie_id = source.readString();
    }

    protected CarteSouhait(Parcel in) {
        this._id = in.readString();
        this.url = in.readString();
        this.categorie_id = in.readString();
    }

    public static final Parcelable.Creator<CarteSouhait> CREATOR = new Parcelable.Creator<CarteSouhait>() {
        @Override
        public CarteSouhait createFromParcel(Parcel source) {
            return new CarteSouhait(source);
        }

        @Override
        public CarteSouhait[] newArray(int size) {
            return new CarteSouhait[size];
        }
    };
}
