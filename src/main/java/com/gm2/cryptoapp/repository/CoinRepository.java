package com.gm2.cryptoapp.repository;

import com.gm2.cryptoapp.entity.Coin;
import jdk.jfr.Enabled;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CoinRepository {

    private static String INSERT = "insert into coin(name, price, quantity, datetime) values (?,?,?,?)";

    private JdbcTemplate jdbcTemplate;

    public CoinRepository(JdbcTemplate jdbcTemplate)
    {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Coin insert(Coin coin){
        Object[] attr = new Object[]{
                coin.getName(),
                coin.getPrice(),
                coin.getQuantity(),
                coin.getDatetime()

        };
        jdbcTemplate.update(INSERT, attr);
        return coin;
    }
}
