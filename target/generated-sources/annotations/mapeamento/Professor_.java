package mapeamento;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import mapeamento.Telefone;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-08-01T16:31:29")
@StaticMetamodel(Professor.class)
public class Professor_ { 

    public static volatile SingularAttribute<Professor, Float> salario;
    public static volatile ListAttribute<Professor, Telefone> telefones;

}