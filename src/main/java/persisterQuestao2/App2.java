package persisterQuestao2;

import mapeamento.questão2.Area;
import mapeamento.questão2.Escritor;
import mapeamento.questão2.Publicacao;
import mapeamento.questão2.Revisor;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;

@Singleton
@Startup
public class App2 {
    
    @PersistenceContext
    EntityManager em;
    Persister2 persister = new Persister2();
    @PostConstruct
    private void init(){
    	persister.initPersistence(em);

//    	  consultaAJPQL(em);
//        consultaBJPQL(em);
//        consultaCJPQL(em);
//        consultaDJPQL(em);

//    	  consultaACRITERIA(em);
//        consultaBCRITERIA(em);
//        consultaCCRITERIA(em);
//        consultaDCRITERIA(em);
    }


    //  O nome da pessoa, o título da publicação e o nome da área em que a pessoa tem o atributo id igual a 3.
    private void consultaAJPQL(EntityManager em) {
        String jpql="SELECT e.nome, p.titulo, a.nome FROM Escritor e, IN(e.publicacoes) p, IN(p.areas) a WHERE e.id=3";
        TypedQuery<Object[]> query = em.createQuery(jpql,Object[].class);
        query.getResultList().forEach( p-> System.out.println("Nome: "+p[0]+"\n Publicação: "+p[1]+"\n Area: "+p[2]+"\n"));
    }

    private void consultaACRITERIA(EntityManager em) {

        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Object[]> criteria = builder.createQuery(Object[].class);
        Root<Escritor> root = criteria.from(Escritor.class);

        Join<Escritor, Publicacao> join = root.join("publicacoes");
        Join<Publicacao, Area> publicacaoAreaJoin = join.join("areas");

        Predicate condicaoId = builder.equal(root.get("id"),3);

        criteria.where(condicaoId).multiselect(root.get("nome"),join.get("titulo"),
                publicacaoAreaJoin.get("nome"));

        em.createQuery(criteria).getResultList().forEach(
                p-> System.out.println("Nome: "+p[0]+"\n Publicação: "+p[1]+"\n Area: "+p[2]+"\n")
        );
    }



    //  O título da publicação e o nome do revisor que tenham alguma publicação na área de indústria.
    private void consultaBJPQL(EntityManager em) {

        String jpql = "SELECT p.titulo, p.revisor.nome FROM Publicacao p JOIN p.areas a WHERE a.nome LIKE 'indústria'";
        Object[] o = em.createQuery(jpql, Object[].class).getSingleResult();
        System.out.println("Publicação: " + o[0] + "    Revisor: " + o[1]);
    }

    private void consultaBCRITERIA(EntityManager em) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Object[]> criteria = builder.createQuery(Object[].class);
        Root<Publicacao> from = criteria.from(Publicacao.class);

        Join<Publicacao, Area> joinAREA = from.join("areas");
        Join<Publicacao, Revisor> joinREVISOR = from.join("revisor");

        criteria.multiselect(from.get("titulo"), joinREVISOR.get("nome")).where(builder.like(joinAREA.get("nome"), "indústria"));

        Object[] o = em.createQuery(criteria).getSingleResult();

        System.out.println("Publicação: " + o[0] + "\n Revisor: " + o[1]);

    }

    //    O nome dos Revisores que possuem alguma publicação começando com Java.
    private void consultaCJPQL(EntityManager em) {

        String jpql = "SELECT p.revisor.nome FROM Publicacao p WHERE p.titulo LIKE 'Java%'";
        em.createQuery(jpql, String.class).getResultList().forEach(r -> System.out.println(r));
    }

    private void consultaCCRITERIA(EntityManager em) {

        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<String> criteria = builder.createQuery(String.class);
        Root<Publicacao> from = criteria.from(Publicacao.class);

        Join<Publicacao, Revisor> joinREVISOR = from.join("revisor");

        criteria.select(joinREVISOR.get("nome")).where(builder.like(from.get("titulo"), "Java%"));

        em.createQuery(criteria).getResultList().forEach(r -> System.out.println(r));
    }

    //    O nome e a quantidade de Publicações escritas por Escritores com mais que três prêmios.
    private void consultaDJPQL(EntityManager em) {
        String jpql = "SELECT e.nome, COUNT(e) FROM Escritor e LEFT JOIN e.publicacoes p GROUP BY e HAVING e.premios>3";
        em.createQuery(jpql, Object[].class).getResultList().forEach(o -> System.out.println(o[0]+" : "+o[1]));
    }

    private void consultaDCRITERIA(EntityManager em) {

        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Object[]> criteria = builder.createQuery(Object[].class);
        Root<Escritor> root = criteria.from(Escritor.class);

        Join<Escritor,Publicacao> joinPUBLI = root.join("publicacoes");

        Predicate condicao3 = builder.gt(root.get("premios"),3);

        criteria.multiselect(root.get("nome"),builder.count(joinPUBLI)).where(condicao3).groupBy(root.get("nome"));

        em.createQuery(criteria).getResultList().forEach(r-> System.out.println("Escritor: "+r[0]+" "+"\n Quantidade de Publicações: "+r[1])
        );

    }
}
