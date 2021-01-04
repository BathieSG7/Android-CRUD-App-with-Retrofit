package ssamba.ept.sn.bankingApp.model;


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
    private int code;

    private String nom;

    private String adresse;

    private String telephone;

    private List<Compte> comptes = new ArrayList<>();

}
