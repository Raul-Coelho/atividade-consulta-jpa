package persisterQuestao2;

import java.util.Arrays;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import mapeamento.questão2.Area;
import mapeamento.questão2.Escritor;
import mapeamento.questão2.PessoaDois;
import mapeamento.questão2.Publicacao;
import mapeamento.questão2.Revisor;

@Singleton
@Startup
public class App2 {

    @PersistenceContext
    EntityManager em;
    Persister2 persister = new Persister2();

    @PostConstruct
    private void init() {
        persister.initPersistence(em);
        a3(em);
//        b3(em);
//        c3(em);
//        d3(em);

//        a4(em);
//        b4(em);
//        c4(em);
//        d4(em);
//        a4_(em);

    }
//  O nome da pessoa, o título da publicação e o nome da área em que a pessoa tem o atributo id igual a 3.
    private void a3(EntityManager em) {
      String spql="select e.nome, p.titulo, a.nome from Escritor e join e.publicacoes p join p.areas a where e.id=3"
                + " union "
                + "select r.nome, p.titulo, a.nome from Revisor r join r.publicacoes p join p.areas a where r.id=3";
        em.createQuery(spql, Object[].class).getResultList().forEach(o -> System.out.println("\n"+o[0]+", "+o[1]+", "+o[2]));
    }
//  O título da publicação e o nome do revisor que tenham alguma publicação na área de indústria.
    private void b3(EntityManager em) {
        String jpql = "select p.titulo, p.revisor.nome from Publicacao p join p.areas a where a.nome like 'indústria'";
        Object[] o = em.createQuery(jpql, Object[].class).getSingleResult();
        System.out.println("\nTítulo da Publicação: " + o[0] + "\nNome do Revisor: " + o[1]);
    }
//    O nome dos Revisores que possuem alguma publicação começando com Java.
    private void c3(EntityManager em) {
        String jpql = "select p.revisor.nome from Publicacao p where p.titulo like 'Java%'";
        em.createQuery(jpql, String.class).getResultList().forEach(r -> System.out.println(r));
    }
//    O nome e a quantidade de Publicações escritas por Escritores com mais que três prêmios.
    private void d3(EntityManager em) {
//        String jpql = "select e.nome, count(e) from Escritor e left join e.publicacoes p group by e having e.premios>3";
        String jpql = "select e.nome, size(e.publicacoes) from Escritor e where e.premios>3";
        em.createQuery(jpql, Object[].class).getResultList().forEach(o -> System.out.println(o[0]+" : "+o[1]));
    }
//  O nome da pessoa, o título da publicação e o nome da área em que a pessoa tem o atributo id igual a 3.
    private void a4(EntityManager em) {
        List<Object[]> primeira = parte(Escritor.class, em);
        List<Object[]> segunda = parte(Revisor.class, em);
        primeira.addAll(segunda);
        primeira.forEach(o->System.out.println("\n"+o[0]+", "+o[1]+", "+o[2]));
    }
//  O título da publicação e o nome do revisor que tenham alguma publicação na área de indústria.
    private void b4(EntityManager em) {
//        String jpql = "select p.titulo, p.revisor.nome from Publicacao p join p.areas a where a.nome like 'indústria'";
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Object[]> criteria = builder.createQuery(Object[].class);
        Root<Publicacao> from = criteria.from(Publicacao.class);
        Join<Publicacao, Area> join = from.join("areas");
        Join<Publicacao, Revisor> join1 = from.join("revisor");
        criteria.multiselect(from.get("titulo"), join1.get("nome")).where(builder.like(join.get("nome"), "indústria"));
        Object[] o = em.createQuery(criteria).getSingleResult();
        System.out.println("\nTítulo da Publicação: " + o[0] + "\nNome do Revisor: " + o[1]);
        
    
    }
//    O nome dos Revisores que possuem alguma publicação começando com Java.
    private void c4(EntityManager em) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<String> criteria = builder.createQuery(String.class);
        Root<Publicacao> from = criteria.from(Publicacao.class);
        Join<Publicacao, Revisor> join = from.join("revisor");
        criteria.select(join.get("nome")).where(builder.like(from.get("titulo"), "Java%"));
        em.createQuery(criteria).getResultList().forEach(r -> System.out.println(r));
    }
//    O nome e a quantidade de Publicações escritas por Escritores com mais que três prêmios.
    private void d4(EntityManager em) {
//      String jpql = "select e.nome, size(e.publicacoes) from Escritor e where e.premios>3";
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Object[]> criteria = builder.createQuery(Object[].class);
        Root<Escritor> from = criteria.from(Escritor.class);
        
        criteria.multiselect(from.get("nome"), builder.size(from.get("publicacoes"))).where(builder.gt(from.get("premios"), 3));
        List<Object[]> o = em.createQuery(criteria).getResultList();
        o.forEach(a -> System.out.println(a[0] + " : " + a[1]));
    }
    
    //ajuda na consulta a4
    private List<Object[]> parte(Class c, EntityManager em){
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Object[]> criteria = builder.createQuery(Object[].class);
        Root<PessoaDois> from = criteria.from(c);
        Join<PessoaDois, Publicacao> join = from.join("publicacoes");
        Join<Publicacao, Area> join1 = join.join("areas");
        criteria.multiselect(from.get("nome"), join.get("titulo"), join1.get("nome")).where(builder.equal(from.get("id"), 3));
        return em.createQuery(criteria).getResultList();
        
    }
}