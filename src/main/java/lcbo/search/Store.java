package lcbo.search;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Java object representation of the JSON store portion of the search results returned from lcboapi.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Store {

    private String name;
    private String address_line_1;
    private String address_line_2;
    private String city;
    private String postal_code;
    private String telephone;
    private int sunday_open;
    private int sunday_close;
    private int monday_open;
    private int monday_close;
    private int tuesday_open;
    private int tuesday_close;
    private int wednesday_open;
    private int wednesday_close;
    private int thursday_open;
    private int thursday_close;
    private int friday_open;
    private int friday_close;
    private int saturday_open;
    private int saturday_close;

    public Store() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress_line_1() {
        return address_line_1;
    }

    public void setAddress_line_1(String address_line_1) {
        this.address_line_1 = address_line_1;
    }

    public String getAddress_line_2() {
        return address_line_2;
    }

    public void setAddress_line_2(String address_line_2) {
        this.address_line_2 = address_line_2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostal_code() {
        return postal_code;
    }

    public void setPostal_code(String postal_code) {
        this.postal_code = postal_code;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getSunday_open() {
        return msmTo12Hour(sunday_open);
    }

    public void setSunday_open(int sunday_open) {
        this.sunday_open = sunday_open;
    }

    public String getSunday_close() {
        return msmTo12Hour(sunday_close);
    }

    public void setSunday_close(int sunday_close) {
        this.sunday_close = sunday_close;
    }

    public String getMonday_open() {
        return msmTo12Hour(monday_open);
    }

    public void setMonday_open(int monday_open) {
        this.monday_open = monday_open;
    }

    public String getMonday_close() {
        return msmTo12Hour(monday_close);
    }

    public void setMonday_close(int monday_close) {
        this.monday_close = monday_close;
    }

    public String getTuesday_open() {
        return msmTo12Hour(tuesday_open);
    }

    public void setTuesday_open(int tuesday_open) {
        this.tuesday_open = tuesday_open;
    }

    public String getTuesday_close() {
        return msmTo12Hour(tuesday_close);
    }

    public void setTuesday_close(int tuesday_close) {
        this.tuesday_close = tuesday_close;
    }

    public String getWednesday_open() {
        return msmTo12Hour(wednesday_open);
    }

    public void setWednesday_open(int wednesday_open) {
        this.wednesday_open = wednesday_open;
    }

    public String getWednesday_close() {
        return msmTo12Hour(wednesday_close);
    }

    public void setWednesday_close(int wednesday_close) {
        this.wednesday_close = wednesday_close;
    }

    public String getThursday_open() {
        return msmTo12Hour(thursday_open);
    }

    public void setThursday_open(int thursday_open) {
        this.thursday_open = thursday_open;
    }

    public String getThursday_close() {
        return msmTo12Hour(thursday_close);
    }

    public void setThursday_close(int thursday_close) {
        this.thursday_close = thursday_close;
    }

    public String getFriday_open() {
        return msmTo12Hour(friday_open);
    }

    public void setFriday_open(int friday_open) {
        this.friday_open = friday_open;
    }

    public String getFriday_close() {
        return msmTo12Hour(friday_close);
    }

    public void setFriday_close(int friday_close) {
        this.friday_close = friday_close;
    }

    public String getSaturday_open() {
        return msmTo12Hour(saturday_open);
    }

    public void setSaturday_open(int saturday_open) {
        this.saturday_open = saturday_open;
    }

    public String getSaturday_close() {
        return msmTo12Hour(saturday_close);
    }

    public void setSaturday_close(int saturday_close) {
        this.saturday_close = saturday_close;
    }

    @Override
    public String toString() {
        return "Store{" +
                "name='" + name + '\'' +
                ", address_line_1='" + address_line_1 + '\'' +
                ", address_line_2='" + address_line_2 + '\'' +
                ", city='" + city + '\'' +
                ", postal_code='" + postal_code + '\'' +
                ", telephone='" + telephone + '\'' +
                ", sunday_open='" + sunday_open + '\'' +
                ", sunday_close='" + sunday_close + '\'' +
                ", monday_open='" + monday_open + '\'' +
                ", monday_close='" + monday_close + '\'' +
                ", tuesday_open='" + tuesday_open + '\'' +
                ", tuesday_close='" + tuesday_close + '\'' +
                ", wednesday_open='" + wednesday_open + '\'' +
                ", wednesday_close='" + wednesday_close + '\'' +
                ", thursday_open='" + thursday_open + '\'' +
                ", thursday_close='" + thursday_close + '\'' +
                ", friday_open='" + friday_open + '\'' +
                ", friday_close='" + friday_close + '\'' +
                ", saturday_open='" + saturday_open + '\'' +
                ", saturday_close='" + saturday_close + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Store store = (Store) o;

        if (sunday_open != store.sunday_open) return false;
        if (sunday_close != store.sunday_close) return false;
        if (monday_open != store.monday_open) return false;
        if (monday_close != store.monday_close) return false;
        if (tuesday_open != store.tuesday_open) return false;
        if (tuesday_close != store.tuesday_close) return false;
        if (wednesday_open != store.wednesday_open) return false;
        if (wednesday_close != store.wednesday_close) return false;
        if (thursday_open != store.thursday_open) return false;
        if (thursday_close != store.thursday_close) return false;
        if (friday_open != store.friday_open) return false;
        if (friday_close != store.friday_close) return false;
        if (saturday_open != store.saturday_open) return false;
        if (saturday_close != store.saturday_close) return false;
        if (!name.equals(store.name)) return false;
        if (address_line_1 != null ? !address_line_1.equals(store.address_line_1) : store.address_line_1 != null)
            return false;
        if (address_line_2 != null ? !address_line_2.equals(store.address_line_2) : store.address_line_2 != null)
            return false;
        if (city != null ? !city.equals(store.city) : store.city != null) return false;
        if (postal_code != null ? !postal_code.equals(store.postal_code) : store.postal_code != null) return false;
        return telephone != null ? telephone.equals(store.telephone) : store.telephone == null;
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + (address_line_1 != null ? address_line_1.hashCode() : 0);
        result = 31 * result + (address_line_2 != null ? address_line_2.hashCode() : 0);
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (postal_code != null ? postal_code.hashCode() : 0);
        result = 31 * result + (telephone != null ? telephone.hashCode() : 0);
        result = 31 * result + sunday_open;
        result = 31 * result + sunday_close;
        result = 31 * result + monday_open;
        result = 31 * result + monday_close;
        result = 31 * result + tuesday_open;
        result = 31 * result + tuesday_close;
        result = 31 * result + wednesday_open;
        result = 31 * result + wednesday_close;
        result = 31 * result + thursday_open;
        result = 31 * result + thursday_close;
        result = 31 * result + friday_open;
        result = 31 * result + friday_close;
        result = 31 * result + saturday_open;
        result = 31 * result + saturday_close;
        return result;
    }

    /**
     * Converts time from the supplied "minutes since midnight" format to 12 hour format.
      */
    private String msmTo12Hour(int msm) {

        int min = msm % 60;
        int h24 = msm / 60;
        int h12 = (0 == h24 ? 12 : (h24 > 12 ? (h24 - 10) - 2 : h24));

        return h12 + ":" + (min == 0 ? "00" : min) + (h24 >= 12 ? " PM" : " AM");
    }

}
