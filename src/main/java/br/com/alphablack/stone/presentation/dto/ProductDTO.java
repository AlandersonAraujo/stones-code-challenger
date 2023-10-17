package br.com.alphablack.stone.presentation.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductDTO implements Serializable {

    @NotBlank(message = "Title is required")
    @Length(max = 255, message = "Title must be up to 255 characters")
    private String title;

    @Min(value = 0, message = "Price must be a positive value")
    private int price;

    @NotBlank(message = "Zipcode is required")
    private String zipcode;

    @NotBlank(message = "Seller is required")
    private String seller;

    @NotBlank(message = "Thumbnail URL is required")
    private String thumbnailHd;

    @NotNull(message = "Date is required")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", timezone = "UTC")
    private String date;

    public void setDate(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        this.date = dateFormat.format(date);
    }
}
