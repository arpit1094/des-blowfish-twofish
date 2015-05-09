/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import iaik.security.provider.IAIK;
import iaik.security.cipher.Twofish;
import com.mongodb.Block;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Sorts.ascending;
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
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import static java.util.Arrays.asList;
import java.util.Locale;
import java.util.Vector;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.CipherSpi;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import org.bson.Document;
import org.bson.Document;


public class crypter {
static int count=0;
static MongoClient mongoClient = new MongoClient();
static DB db = mongoClient.getDB("final");
static DBCollection collection = db.getCollection("data");
static Vector<SecretKey> vecb = new Vector<SecretKey>();
static Vector<SecretKey> vecd = new Vector<SecretKey>();
static Vector<SecretKeySpec> sks = new Vector<SecretKeySpec>();
static Vector<IvParameterSpec> iv = new Vector<IvParameterSpec>();
static byte[] ivBytes;
    static Vector<Integer> indexb = new Vector<Integer>();
    static Vector<Integer> indexd = new Vector<Integer>();
    static Vector<Integer> indext = new Vector<Integer>();
    /**
     * @param args the command line arguments
     */;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InvalidKeyException, IOException, NoSuchProviderException, InvalidAlgorithmParameterException {
        IAIK provider = new IAIK();
Security.addProvider(provider);
if(Security.getProvider("IAIK")!=null)
    System.out.println("imported");
java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new photo1().setVisible(true);
            }
        });
 	collection.drop();
    }
    
}

