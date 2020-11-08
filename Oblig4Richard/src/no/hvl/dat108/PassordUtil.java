package no.hvl.dat108;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;
import java.util.Base64;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

/**
 * @author Lars-Petter Helland
 */
public class PassordUtil {
    
    /*
     * Man kunne godt gjort denne klassen mer fleksibel ved å bytte ut 
     * konstanter med konstruktørparametre. På den annen side: MAN MÅ BRUKE
     * SAMME OPPSETT OVERALT I EN APPLIKASJON.
     */
    
    private static final int SALT_LENGTH = 24;
    private static final int HASH_ITERATIONS = 1000;

    private static final int KEY_LENGTH = 256; // For PBEKeySpec
    
    //https://docs.oracle.com/javase/8/docs/api/index.html?javax/crypto/SecretKeyFactory.html
    private static final String KEY_FACTORY_ALGORITHM = "PBKDF2WithHmacSHA256";
    
    // ^.{10,}$ betyr at lengden må være minst 10 chars. Ikke noe annet.
//    private static final String VALID_PASSWORD_PATTERN = "^.{10,}$";
    private static final String VALID_PASSWORD_PATTERN = "^.{3,}$";
    
    /**
     * Denne metoden genererer en passordstreng for sikker lagring av passord.
     * Passordstrengen inneholder både saltet som ble brukt i hashingen og 
     * resultatet (digest-et) av hashingen.
     * 
     * Det er ikke noen spesiell grunn til at salt og digest bør slås sammen
     * til én streng. De kunne godt vært returnert som to verdier (som et par).
     * Det er gjort slik fordi det gjorde API-et enkelt å bruke, en verdi inn,
     * en verdi ut.
     * 
     * @param passord Passord som skal krypteres
     * @return (salt + digest) kodet som en base64-streng. 
     */
    public String krypterPassord(String passord) throws IllegalArgumentException {
    	if (passord == null || !passord.matches(VALID_PASSWORD_PATTERN)) {
    		throw new IllegalArgumentException("Ugyldig passord. Passordet må matche " + VALID_PASSWORD_PATTERN);
    	}
        byte[] salt = genererTilfeldigSalt();
        return krypterMedSalt(salt, passord);
    }

    /**
     * Denne metoden sjekker om angitt passord matcher kryptert passord 
     * (dvs. salt + digest) som tidligere er generert med {@link #krypterPassord(String)}.
     * 
     * @param passord Passord som skal sjekkes
     * @param kryptert (salt + digest) tidligere generert med {@link #krypterPassord(String)}
     * @return om passordet matcher det krypterte passordet
     */
    public boolean sjekkPassord(String passord, String kryptert) {
    	if (passord == null || !passord.matches(VALID_PASSWORD_PATTERN)) {
    		throw new IllegalArgumentException("Ugyldig passord. Passordet må matche " + VALID_PASSWORD_PATTERN);
    	}
    	if (kryptert == null) {
    		throw new IllegalArgumentException("Kryptert passord kan ikke være null");
    	}
        byte[] salt = hentUtSaltFraKryptertStreng(kryptert);
        return krypterMedSalt(salt, passord).equals(kryptert);
    }
    
    /*--- Private hjelpemetoder ---*/

	public String krypterMedSalt(byte[] salt, String password) {
		
		String kryptert = null;
		
		SecretKeyFactory skf;
		try {
			char[] passchars = password.toCharArray();
			
			PBEKeySpec pks = new PBEKeySpec(passchars, salt, HASH_ITERATIONS, KEY_LENGTH);
			skf = SecretKeyFactory.getInstance(KEY_FACTORY_ALGORITHM);
			byte[] keyhash = skf.generateSecret(pks).getEncoded();
			
	        byte[] saltPlusKeyHash = leggSammen(salt, keyhash);
	        kryptert = Base64.getEncoder().encodeToString(saltPlusKeyHash);
		
		} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
			e.printStackTrace();	
		}
        
        return kryptert;
		
	}

    public byte[] genererTilfeldigSalt() {
        byte[] salt = new byte[SALT_LENGTH];
        // https://docs.oracle.com/javase/8/docs/technotes/guides/security/StandardNames.html#SecureRandom
        // for evt. SecureRandom.getInstance("SHA1PRNG"); => m� catche ex
        new SecureRandom().nextBytes(salt);
//        System.out.println("Tilfeldig salt = " + Base64.getEncoder().encodeToString(salt));
        return salt;
    }

    private byte[] hentUtSaltFraKryptertStreng(String kryptert) {
        byte[] saltPlusDigest = Base64.getDecoder().decode(kryptert);
        byte[] salt = Arrays.copyOf(saltPlusDigest, SALT_LENGTH);
        return salt;
    }
    
    private byte[] leggSammen(byte[] tabell1, byte[] tabell2) {
        byte[] enOgTo = new byte[tabell1.length + tabell2.length];
        System.arraycopy(tabell1, 0, enOgTo, 0, tabell1.length);
        System.arraycopy(tabell2, 0, enOgTo, tabell1.length, tabell2.length);
        return enOgTo;
    }
}
