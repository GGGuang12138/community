package vip.gg.community.demo.config;

import io.jsonwebtoken.CompressionCodecs;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;

import java.util.Date;

/**
// * Creat by GG
 * Date on 2020/6/11  11:30 上午
 */
public class TokenManager {
    @Value("${token.expire}")
    private long tokenExpiration;

    @Value("${token.key}")
    private String tokenKey;

    public String createToken(String username) {
        String token = Jwts.builder().setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + tokenExpiration))
                .signWith(SignatureAlgorithm.HS512,tokenKey).compressWith(CompressionCodecs.GZIP).compact();
        return token;
    }
    public String getUserFromToken(String token) {
        String user = Jwts.parser().setSigningKey(tokenKey).parseClaimsJws(token).getBody().getSubject();
        return user;
    }
}
