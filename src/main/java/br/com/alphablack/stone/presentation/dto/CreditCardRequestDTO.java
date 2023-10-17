package br.com.alphablack.stone.presentation.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreditCardRequestDTO implements Serializable {

    @NotBlank(message = "Card number is required")
    @JsonProperty("card_number")
    private String cardNumber;

    @NotNull(message = "Value is required")
    @Positive(message = "Value must be greater than zero")
    private Integer value;

    @NotNull(message = "CVV is required")
    private Integer cvv;

    @NotBlank(message = "Cardholder name is required")
    @JsonProperty("card_holder_name")
    private String cardHolderName;

    @Pattern(regexp = "\\d{2}/\\d{2}", message = "Expiration date must be in MM/YY format")
    @JsonProperty("exp_date")
    private String expDate;
}
