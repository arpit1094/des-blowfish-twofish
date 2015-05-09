/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import iaik.security.cipher.Twofish;
import iaik.security.provider.IAIK;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 *
 * @author funky
 */
public class twofishc {
    public String encrypt(String str) throws NoSuchProviderException {
            try {
                SecretKey key= KeyGenerator.getInstance("Twofish", "IAIK").generateKey();
                Cipher ecipher = Cipher.getInstance("Twofish/CBC/PKCS5Padding", "IAIK");
                ecipher.init(Cipher.ENCRYPT_MODE, key);
                byte[] keyBytes = key.getEncoded();
	 	SecretKeySpec secretKeySpec = new SecretKeySpec(keyBytes, "Twofish");

                byte[] utf8 = str.getBytes("UTF8");
                byte[] enc = ecipher.doFinal(utf8);
                crypter.ivBytes = ecipher.getIV();
                IvParameterSpec ivt = new IvParameterSpec(crypter.ivBytes);
                String s =new sun.misc.BASE64Encoder().encode(enc);

                crypter.count++;
                DBObject en = new BasicDBObject("id",crypter.count)
                        .append("string",s);
                crypter.collection.insert(en);
                //crypter.vec.add(key);
                crypter.sks.add(secretKeySpec);
                crypter.iv.add(ivt);
		crypter.indext.add(crypter.count);
                return s;
            } catch (BadPaddingException e) {
            } catch (IllegalBlockSizeException e) {
            } catch (UnsupportedEncodingException e) {
            } catch (NoSuchAlgorithmException e) {
            } catch (InvalidKeyException e) {
            } catch (NoSuchPaddingException e) {
            }
            return null;
        }
        
        public String decrypt(int i) throws InvalidKeyException, IOException, NoSuchProviderException, InvalidAlgorithmParameterException {
            String str = null;
            SecretKey key = null;
		int j;
	int c=0;
            for (j=0;j<crypter.indext.size();j++) {
                if(crypter.indext.get(j)==i) {
			c++;
			break;
		}
            }
	if(c==0) {
	return new String("Wrong Algorithm chosen");
	}
            try {
                Cipher dcipher = Cipher.getInstance("Twofish/CBC/PKCS5Padding", "IAIK");
                DBObject query = new BasicDBObject("id", i);
                DBCursor cursor= crypter.collection.find(query);
                str=(String)cursor.one().get("string");
                dcipher.init(Cipher.DECRYPT_MODE, crypter.sks.get(j), crypter.iv.get(j));
                byte[] dec = new sun.misc.BASE64Decoder().decodeBuffer(str);
                byte[] utf8 = dcipher.doFinal(dec);
                return new String(utf8, "UTF8");
            }  catch (BadPaddingException e) {
            } catch (IllegalBlockSizeException e) {
            } catch (UnsupportedEncodingException e) {
            } catch (NoSuchAlgorithmException e) {
            } catch (NoSuchPaddingException e) {
            }
            return null;
        }
}

