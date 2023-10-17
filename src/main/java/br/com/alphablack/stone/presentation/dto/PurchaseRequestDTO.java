package br.com.alphablack.stone.presentation.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class PurchaseRequestDTO implements Serializable {

    @NotBlank(message = "Client ID is required")
    @JsonProperty("client_id")
    private String clientId;

    @NotBlank(message = "Client name is required")
    @JsonProperty("client_name")
    private String clientName;

    @NotNull(message = "Total to pay is required")
    @Positive(message = "Total to pay must be greater than zero")
    @JsonProperty("total_to_pay")
    private Integer totalToPay;

    @Valid
    @JsonProperty("credit_card")
    private CreditCardRequestDTO creditCard;
}
