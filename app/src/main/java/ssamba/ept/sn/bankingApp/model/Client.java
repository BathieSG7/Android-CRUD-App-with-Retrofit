package ssamba.ept.sn.bankingApp.model;

import com.google.gson.annotations.Expose;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


/*
public class User {
   // @SerializedName("id")
    //@Expose
    private int id;

    //@SerializedName("name")
    //@Expose
    private String name;
}

package ssamba.ept.sn.BankerApp.model;

        import java.time.LocalDate;
        import java.util.ArrayList;
        import java.util.List;

        import lombok.*;

*/

@Getter
@Setter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Client {
    @Expose
    private int id;
    @Expose
    private String nom;
    @Expose
    private String prenom;

    //@JsonAdapter(DateConverter.class)
    @Expose
    private Date dateNaissance;

    @Expose
    private List<Compte> comptes ;



}
