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

    @Transactional
    public Coin insert(Coin coin){
        entityManager.persist(coin);
        return coin;
    }
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
//    public List<Coin> getByName(String name){
//
//        Object[] attr = new Object[] { name};
//        return  jdbcTemplate.query(SELECT_BY_NAME, new RowMapper<Coin>() {
//            @Override
//            public Coin mapRow(ResultSet rs, int rowNum) throws SQLException {
//               Coin coin = new Coin();
//               coin.setId(rs.getInt("id"));
//               coin.setName(rs.getString("name"));
//               coin.setPrice(rs.getBigDecimal("price"));
//               coin.setQuantity(rs.getBigDecimal("quantity"));
//               coin.setDatetime(rs.getTimestamp("datetime"));
//
//               return coin;
//
//            }
//        }, attr);
//    }
//
//    public int remove(int id){
//        return jdbcTemplate.update(DELETE, id);
//    }


}
