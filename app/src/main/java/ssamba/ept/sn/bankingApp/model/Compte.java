package ssamba.ept.sn.bankingApp.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Compte {

    @Expose
    private int id;
    @Expose
    private double solde;
    @Expose
    private double decouvert;

    @Expose
    private double client ;

    @Expose
    private double agence ;

/*
    //@SerializedName("compte")
    //@Expose(serialize = false)
    private int compteId;

    //@SerializedName("client")
    //@Expose(serialize = false)
    private int clientId;

    @Expose(serialize = false)
    private int clientId ;
    @Expose(serialize = false)
    private int agenceId ;

 */

}
