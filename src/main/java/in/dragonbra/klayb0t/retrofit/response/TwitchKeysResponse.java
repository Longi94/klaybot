package in.dragonbra.klayb0t.retrofit.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * @author lngtr
 * @since 2018-01-01
 */
public class TwitchKeysResponse {

    @SerializedName("keys")
    @Expose
    private List<Key> keys = null;

    public List<Key> getKeys() {
        return keys;
    }

    public void setKeys(List<Key> keys) {
        this.keys = keys;
    }

    public static class Key {

        @SerializedName("use")
        @Expose
        private String use;

        @SerializedName("alg")
        @Expose
        private String alg;

        @SerializedName("n")
        @Expose
        private String n;

        @SerializedName("e")
        @Expose
        private String e;

        @SerializedName("kty")
        @Expose
        private String kty;

        @SerializedName("kid")
        @Expose
        private String kid;

        public String getUse() {
            return use;
        }

        public void setUse(String use) {
            this.use = use;
        }

        public String getAlg() {
            return alg;
        }

        public void setAlg(String alg) {
            this.alg = alg;
        }

        public String getN() {
            return n;
        }

        public void setN(String n) {
            this.n = n;
        }

        public String getE() {
            return e;
        }

        public void setE(String e) {
            this.e = e;
        }

        public String getKty() {
            return kty;
        }

        public void setKty(String kty) {
            this.kty = kty;
        }

        public String getKid() {
            return kid;
        }

        public void setKid(String kid) {
            this.kid = kid;
        }

    }
}
