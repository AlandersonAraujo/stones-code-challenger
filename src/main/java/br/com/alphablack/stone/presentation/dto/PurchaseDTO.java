package br.com.alphablack.stone.presentation.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseDTO implements Serializable {

    @JsonProperty("client_id")
    private String clientId;
    @JsonProperty("purchase_id")
    private String purchaseId;
    private int value;
    private String date;
    @JsonProperty("card_number")
    private String cardNumber;

    public void setDate(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        this.date = dateFormat.format(date);
    }

    public void setCardNumber(String cardNumber) {
        int visibleDigits = 4;
        int totalDigits = cardNumber.length();
        StringBuilder mask = new StringBuilder("**** **** **** ");
        mask.append(cardNumber.substring(totalDigits - visibleDigits));

        this.cardNumber = mask.toString();
    }
}
