package mapeamento;

import java.time.LocalDate;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import mapeamento.Autor;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-08-07T09:20:34")
@StaticMetamodel(Livro.class)
public class Livro_ { 

    public static volatile SingularAttribute<Livro, LocalDate> lancamento;
    public static volatile SingularAttribute<Livro, String> ISBN;
    public static volatile SingularAttribute<Livro, String> titulo;
    public static volatile ListAttribute<Livro, Autor> autores;

}