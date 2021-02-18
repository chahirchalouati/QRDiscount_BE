/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package QRDiscount.Entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Future;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Chahir Chalouati
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Discount implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Shop shop;

    @Size(max = 255, min = 8, message = "")
    @NotBlank(message = "Description can't be blank")
    private String descriptionDiscount;

    @NotBlank(message = " URL discount can't be blank")
    private String urlDiscount;

    @NotBlank(message = " Path discount can't be blank")
    private String pathDiscount;

    @FutureOrPresent(message = "date must be equal to present date or future date ")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(shape = JsonFormat.Shape.NUMBER)
    private Date validityFrom;// inzio di validita

    @Future(message = "date must be in future  ")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(shape = JsonFormat.Shape.NUMBER)
    private Date validityTo;// fine validita

    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(shape = JsonFormat.Shape.NUMBER)
    private Date createdAt;

    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(shape = JsonFormat.Shape.NUMBER)
    private Date modifiedAt;
}
