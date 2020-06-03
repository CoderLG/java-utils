package lg.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.swagger.annotations.Api;
import lg.domain.User;

import java.util.Date;

/**
 * author: LG
 * date: 2019-09-09 09:39
 * desc:
 * user pass加密
 * token
 */
@Api(tags = "token")
public class JwtUtils {
    public static final String SUBJECT = "testToken";

    //过期时间 一个小时
    public static final long EXPIRA = 1000*60*60;

    //秘钥
    public static final String APPSECRET = "lgtest";

    public static String geneJsonWebToken(User tUser) {
        if (tUser == null || tUser.getUserId() == null || tUser.getName() == null) {
            return null;
        }
        String token = Jwts.builder().setSubject(SUBJECT)
                .claim("id", tUser.getUserId())
                .claim("name", tUser.getName())
                .setIssuedAt(new Date())               //发布时间
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRA))   //过期时间
                .signWith(SignatureAlgorithm.HS256, APPSECRET).compact();       //加密 压缩

        return token;
    }

    public static Claims checkJWT(String tocken) {

        try {
            Claims claims = Jwts.parser().setSigningKey(APPSECRET)
                    .parseClaimsJws(tocken)
                    .getBody();
            return claims;
        }catch (Exception e){}
        return null;
    }

}
