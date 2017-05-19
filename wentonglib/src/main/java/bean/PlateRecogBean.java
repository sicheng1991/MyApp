package bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by longwj on 2017/5/19.
 */

public class PlateRecogBean implements Parcelable{


    private String number;//车牌
    private String color;//颜色
    private String path;//车牌图片路径
    private int left;
    private int top;
    private int width;
    private int height;
    private String time;
    private boolean recogType;
    private boolean isManualInput;//是否手动输入车牌

    protected PlateRecogBean(Parcel in) {
        number = in.readString();
        color = in.readString();
        path = in.readString();
        left = in.readInt();
        top = in.readInt();
        width = in.readInt();
        height = in.readInt();
        time = in.readString();
        recogType = in.readByte() != 0;
        isManualInput = in.readByte() != 0;
    }

    public static final Creator<PlateRecogBean> CREATOR = new Creator<PlateRecogBean>() {
        @Override
        public PlateRecogBean createFromParcel(Parcel in) {
            return new PlateRecogBean(in);
        }

        @Override
        public PlateRecogBean[] newArray(int size) {
            return new PlateRecogBean[size];
        }
    };

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getLeft() {
        return left;
    }

    public void setLeft(int left) {
        this.left = left;
    }

    public int getTop() {
        return top;
    }

    public void setTop(int top) {
        this.top = top;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public boolean getRecogType() {
        return recogType;
    }

    public void setRecogType(boolean recogType) {
        this.recogType = recogType;
    }

    public boolean isManualInput() {
        return isManualInput;
    }

    public void setManualInput(boolean manualInput) {
        isManualInput = manualInput;
    }



    public PlateRecogBean(){}


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(number);
        dest.writeString(color);
        dest.writeString(path);
        dest.writeInt(left);
        dest.writeInt(top);
        dest.writeInt(width);
        dest.writeInt(height);
        dest.writeString(time);
        dest.writeByte((byte) (recogType ? 1 : 0));
        dest.writeByte((byte) (isManualInput ? 1 : 0));
    }
    @Override
    public String toString() {
        return "PlateRecogBean{" +
                "number='" + number + '\'' +
                ", color='" + color + '\'' +
                ", path='" + path + '\'' +
                ", left=" + left +
                ", top=" + top +
                ", width=" + width +
                ", height=" + height +
                ", time='" + time + '\'' +
                ", recogType=" + recogType +
                ", isManualInput=" + isManualInput +
                '}';
    }
}
