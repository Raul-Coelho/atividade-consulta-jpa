package mapeamento.questão2;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import mapeamento.questão2.Publicacao;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-08-07T09:20:34")
@StaticMetamodel(Escritor.class)
public class Escritor_ extends PessoaDois_ {

    public static volatile ListAttribute<Escritor, Publicacao> publicacoes;
    public static volatile SingularAttribute<Escritor, Integer> premios;

}