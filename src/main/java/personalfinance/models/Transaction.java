package personalfinance.models;

import java.math.BigDecimal;
import java.util.Date;

public class Transaction {
    protected int id;
    protected BigDecimal amount;
    protected boolean isIncome;
    protected String currency;
    protected Date date;
    protected int week;
    protected String transactionInfo;

    protected char plusMinus;

    public Transaction(int id, BigDecimal sum, Boolean isIncome, String currency, Date date, int week, String trancastionInfo) {
        this.id = id;
        this.amount = sum;
        this.isIncome = isIncome;
        this.currency = currency;
        this.date = date;
        this.week = week;
        this.transactionInfo = trancastionInfo;

        if (isIncome) {
            this.plusMinus = '+';
        } else if (!isIncome) {
            this.plusMinus = '-';
        }

    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public boolean getIsIncome() {
        return isIncome;
    }

    public String getCurrency() {
        return currency;
    }

    public Integer getWeek() {
        return week;
    }

    public String getTrancastionInfo() {
        return transactionInfo;
    }


    public Date getDate() {
        return date;
    }

    @Override
    public String toString() {
        return
                "id: " + id + ", " + plusMinus + amount +
                        " " + currency +
                        ", Info: " + transactionInfo +
                        ", Date: " + date + '\'' +
                        ", Week: " + week + "\n";
    }
}

