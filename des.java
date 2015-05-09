/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.Object;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
//import iaik.security.cipher.Twofish;

// KEY SPECIFICATIONS
import java.security.Provider; import java.security.Security;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.CipherSpi;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;
import com.mongodb.BasicDBObject;
import com.mongodb.Block;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoDatabase;

import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Sorts.ascending;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import javax.crypto.*;
import org.bson.Document;
/**
 *
 * @author funky
 */
public class des {
        public String encrypt(String str) {
            try {
                SecretKey key= KeyGenerator.getInstance("DES").generateKey();
                Cipher ecipher = Cipher.getInstance("DES");
                ecipher.init(Cipher.ENCRYPT_MODE, key);
                byte[] utf8 = str.getBytes("UTF8");
                byte[] enc = ecipher.doFinal(utf8);
                String s =new sun.misc.BASE64Encoder().encode(enc);
                crypter.count++;
                DBObject en = new BasicDBObject("id",crypter.count)
                        .append("string",s);
                crypter.collection.insert(en);
                crypter.vecd.add(key);
		crypter.indexd.add(crypter.count);
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
        
        public String decrypt(int i) throws InvalidKeyException, IOException {
            String str = null;
            SecretKey key = null;
		int j;
	int c=0;
            for (j=0;j<crypter.indexd.size();j++) {
                if(crypter.indexd.get(j)==i) {
			c++;
			break;
		}
            }
	if(c==0) {
	return new String("Wrong Algorithm chosen");
	}
            try {
                Cipher dcipher = Cipher.getInstance("DES");
                DBObject query = new BasicDBObject("id", i);
                DBCursor cursor= crypter.collection.find(query);
                str=(String)cursor.one().get("string");
                dcipher.init(Cipher.DECRYPT_MODE,crypter.vecd.get(j));
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
