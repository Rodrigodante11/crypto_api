package com.gm2.cryptoapp.repository;

import com.gm2.cryptoapp.dto.CoinTransationDTO;
import com.gm2.cryptoapp.entity.Coin;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import jdk.jfr.Enabled;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
@EnableAutoConfiguration
public class CoinRepository {

    @Autowired
    private EntityManager entityManager;

    @Transactional // Transactional usado quando tem alteracao
    public Coin insert(Coin coin){
        entityManager.persist(coin);
        return coin;
    }

    // o MERGE se nao passar o ID ele faz o mesmo que um INSERT
    @Transactional
    public Coin update(Coin coin){
        entityManager.merge(coin);
        return coin;
    }

    @Transactional
    public List<CoinTransationDTO> getAll() {
        String jpql = "select new com.gm2.cryptoapp.dto.CoinTransationDTO(c.name, sum(c.quantity)) " +
                      "from Coin as c group by c.name";

        TypedQuery<CoinTransationDTO> query = entityManager.createQuery(jpql, CoinTransationDTO.class);
        return query.getResultList();

    }
    public List<Coin> getByName(String name){
        String jpql = "select c from Coin as c where c.name like :name";

        entityManager.createQuery(jpql, Coin.class);
        TypedQuery<Coin> query = entityManager.createQuery(jpql, Coin.class);
        query.setParameter("name", "%" + name + "%");
        return query.getResultList();

    }
//
//    public int remove(int id){
//        return jdbcTemplate.update(DELETE, id);
//    }


}
