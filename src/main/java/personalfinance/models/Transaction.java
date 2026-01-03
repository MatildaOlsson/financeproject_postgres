package personalfinance.models;

import java.math.BigDecimal;

public class Transaction {
    protected BigDecimal amount;
    protected boolean isIncome;
    protected String currency;
    protected String transactionInfo;

    protected String day;
    protected String month;
    protected String year;
    protected String week;

    public Transaction(BigDecimal sum, Boolean isIncome, String currency, String year, String month, String day, String week) {
        this.amount = sum;
        this.isIncome = isIncome;
        this.currency = currency;
        this.year = year;
        this.month = month;
        this.day = day;
        this.week = week;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public boolean getIsIncome(){
        return isIncome;
    }

    public String getDay() {
        return day;
    }


    public String getYear() {
        return year;
    }

    public String getMonth() {
        return month;
    }

    public String getWeek() {
        return week;
    }

    public String getCurrency() {
        return currency;
    }

    @Override
    public String toString() {
        return amount +
                " " + currency +
                ", Type: '" + type + '\'' +
                ", Date: " + day + "/" + month + "/" + year +
                ", Week: " + week;
    }
}

