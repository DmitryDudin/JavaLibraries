import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.*;
import org.apache.commons.collections.MapUtils;
import org.junit.Test;

import java.io.IOException;
import java.util.Base64;
import java.util.Date;
import java.util.Map;

public class TokenParserTest {

    //https://github.com/jwtk/jjwt#04

    @Test
    public void shouldParseTokenWithSecret1() {
        String authToken = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbkBnbWFpbC5jb20iLCJhdXRoIjoiUk9MRV9BRE1JTiIsImV4cCI6MTUzMzgxODE4M30.F8xYM3vOyehgNNIK-sR5xw5-i1mkTJxAFhj1xc438gj8i5zzLBQ16F3QOUeEb2tfn1HoM5G3YgkPnguQNwDfzQ";
        String[] parts = authToken.split("\\."); // Splitting header, payload and signature

        Base64.Decoder decoder = Base64.getUrlDecoder();
        System.out.println("Headers: " + new String(decoder.decode(parts[0]))); // Header
        System.out.println("Payload: " + new String(decoder.decode(parts[1]))); // Payload

        Base64.Decoder decoder2 = Base64.getDecoder();
        System.out.println("Headers: " + new String(decoder2.decode(parts[0]))); // Header
        System.out.println("Payload: " + new String(decoder2.decode(parts[1]))); // Payload

        System.out.println("Headers: " + new String(org.apache.commons.codec.binary.Base64.decodeBase64(parts[0])));
        System.out.println("Payload: " + new String(org.apache.commons.codec.binary.Base64.decodeBase64(parts[1])));

        //json string to Map
        String json = new String(org.apache.commons.codec.binary.Base64.decodeBase64(parts[1]));
        ObjectMapper objectMapper = new ObjectMapper();
        Map map = null;
        try {
            map = objectMapper.readValue(json, Map.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(map);
        MapUtils.debugPrint(System.out, "Print claims", map);

        //{"alg":"HS512"}
        //{"sub":"admin@gmail.com","auth":"ROLE_ADMIN","exp":1533818183}
    }

    //    @Ignore
    @Test
    public void shouldParseTokenWithSecret2() {
        String authToken = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbkBnbWFpbC5jb20iLCJhdXRoIjoiUk9MRV9BRE1JTiIsImV4cCI6MTUzMzgxODE4M30.F8xYM3vOyehgNNIK-sR5xw5-i1mkTJxAFhj1xc438gj8i5zzLBQ16F3QOUeEb2tfn1HoM5G3YgkPnguQNwDfzQ";

//        Jwts.parser().setSigningKeyResolver(new SigningKeyResolverAdapter()).parseClaimsJws(authToken);

//        Jwt<Header, Claims> exp = Jwts.parser().parseClaimsJwt("exp");
//        Claims body = exp.getBody();
//        Header header = exp.getHeader();

//        SigningKeyResolver

        Jws<Claims> jws = Jwts.parser()
                .setSigningKeyResolver(new SigningKeyResolverAdapter() {
                    @Override
                    public byte[] resolveSigningKeyBytes(JwsHeader header, Claims claims) {
                        // inspect anything you want in the header or claims object.  This is
                        // called after parsing, but *before* validation assertions.

                        //inspect the header or claims, lookup and return the signing key
//                        header.getAlgorithm()
                        return null;//will throught exceptin
//                        return getSigningKeyBytes(header, claims); //implement me
                    }
                })
                .parseClaimsJws(authToken);
        System.out.println(jws.getSignature());
    }

    @Test
    public void shouldParseClaims() {//!!!!!!!!!!!!!!!!!!!!!
        String authToken = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbkBnbWFpbC5jb20iLCJhdXRoIjoiUk9MRV9BRE1JTiIsImV4cCI6MTUzMzgxODE4M30.F8xYM3vOyehgNNIK-sR5xw5-i1mkTJxAFhj1xc438gj8i5zzLBQ16F3QOUeEb2tfn1HoM5G3YgkPnguQNwDfzQ";

        int i = authToken.lastIndexOf('.');
        String withoutSignature = authToken.substring(0, i + 1);

        Jwt<Header, Claims> untrusted = Jwts.parser().parseClaimsJwt(withoutSignature);
        System.out.println("untrusted= " + untrusted);

        Integer exp = (Integer) untrusted.getBody().get("exp");
        System.out.println("exp= " + exp);

        Date expiration = untrusted.getBody().getExpiration();
        System.out.println("expiration= " + expiration);

        Date expiration2 = untrusted.getBody().get("exp", Date.class);
        System.out.println("expiration2= " + expiration2);
    }


}
