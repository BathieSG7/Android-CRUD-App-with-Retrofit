package ssamba.ept.sn.bankingApp.model;


import com.google.gson.annotations.Expose;

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


    //@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    //@JsonIdentityReference(alwaysAsId = true)
    @Expose(deserialize = false)
    private Client client ;



    //@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "code")
    //@JsonIdentityReference(alwaysAsId = true)
    @Expose(deserialize = false)
    private Agence agence ;
/*
    @Expose(serialize = false)
    private int clientId ;
    @Expose(serialize = false)
    private int agenceId ;

 */

}
