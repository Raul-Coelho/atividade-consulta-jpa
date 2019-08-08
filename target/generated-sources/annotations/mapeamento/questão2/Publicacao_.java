package mapeamento.questão2;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import mapeamento.questão2.Area;
import mapeamento.questão2.Escritor;
import mapeamento.questão2.Revisor;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-08-07T09:20:34")
@StaticMetamodel(Publicacao.class)
public class Publicacao_ { 

    public static volatile SingularAttribute<Publicacao, Revisor> revisor;
    public static volatile SingularAttribute<Publicacao, Escritor> escritor;
    public static volatile SingularAttribute<Publicacao, Integer> codPublicacao;
    public static volatile SingularAttribute<Publicacao, String> titulo;
    public static volatile ListAttribute<Publicacao, Area> areas;

}