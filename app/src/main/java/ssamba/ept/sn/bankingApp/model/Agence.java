package ssamba.ept.sn.bankingApp.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
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
public class Agence {

    @SerializedName("code")
    @Expose
    private int id;

    @Expose
    private String nom;

    @Expose
    private String adresse;

    @Expose
    private String telephone;

    @Expose
    private List<Compte> comptes = new ArrayList<>();


}
