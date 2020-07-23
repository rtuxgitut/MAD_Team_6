package sg.edu.np.madteam6.assignment;

import java.io.Serializable;

public class ViewPurchaseModel implements Serializable {
    private String bbt_name;
    private String bbt_price;
    private String bbt_date;

    public ViewPurchaseModel() {}

    public ViewPurchaseModel(String bbtName, String bbtPrice, String bbtDate) {
        this.bbt_name = bbtName;
        this.bbt_price = bbtPrice;
        this.bbt_date = bbtDate;
    }

    public String getBbt_name() {
        return bbt_name;
    }

    public String getBbt_price() {
        return bbt_price;
    }

    public String getBbt_date() {
        return bbt_date;
    }
}
