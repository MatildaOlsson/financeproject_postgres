package personalfinance.models;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

public class Transaction {
    protected BigDecimal amount;
    protected boolean isIncome;
    protected String currency;


    protected LocalDate date;
    protected int week;

//    protected String day;
//    protected String month;
//    protected String year;
//    protected String week;

    public Transaction(BigDecimal sum, Boolean isIncome, String currency, LocalDate date, int week) {
        this.amount = sum;
        this.isIncome = isIncome;
        this.currency = currency;
        this.date = date;
        this.week = week;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public boolean getIsIncome() { return isIncome; }

    public String getCurrency() {return currency; }

    public Integer getWeek() {return week;}

    public Date convertDateToSql(LocalDate date) {
        return java.sql.Timestamp.valueOf(date.atStartOfDay());
    }


    public LocalDate getDate() {return date;}



//    public String getDay() {
//        return day;
//    }
//
//
//    public String getYear() {
//        return year;
//    }
//
//    public String getMonth() {
//        return month;
//    }
//
//    public String getWeek() {
//        return week;
//    }



    @Override
    public String toString() {
        return amount +
                " " + currency +
                ", isIncome: '" + isIncome + '\'' +
                ", Date: " + date + '\'' +
                ", Week: " + week;
    }
}

