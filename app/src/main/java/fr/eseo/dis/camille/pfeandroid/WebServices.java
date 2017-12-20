package fr.eseo.dis.camille.pfeandroid;

import android.content.Context;
import android.net.wifi.hotspot2.pps.Credential;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;

import java.io.InputStream;
import java.security.KeyStore;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;

import errors.LoginError;
import fr.eseo.dis.camille.pfeandroid.webServiceBean.ListProjects;
import fr.eseo.dis.camille.pfeandroid.webServiceBean.Login;

/**
 * Created by Arthur on 20/12/2017.
 */

public class WebServices {

    private static String retrieve(Context context, String URI) {
        String output = null;
        try {
            //URL url = new URL(URI);
            //HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
            //conn.setRequestMethod("GET");
           // conn.setRequestProperty("Accept", "application/json");

            // Load CAs from an InputStream
            // (could be from a resource or ByteArrayInputStream or ...)
            CertificateFactory cf = CertificateFactory.getInstance("X.509");

            InputStream caInputChain = context.getResources().openRawResource(R.raw.chain);
            InputStream caInputInter = context.getResources().openRawResource(R.raw.inter);
            InputStream caInputRoot = context.getResources().openRawResource(R.raw.root);
            //InputStream caInputChain = new BufferedInputStream(new FileInputStream("chain.crt"));
            //InputStream caInputInter = new BufferedInputStream(new FileInputStream("android.resource://fr.eseo.dis.camille/raw/inter.crt"));
            //InputStream caInputRoot = new BufferedInputStream(new FileInputStream("android.resource://fr.eseo.dis.camille/raw/root.crt"));

            Certificate caChain;
            Certificate caInter;
            Certificate caRoot;

            try {
                caChain = cf.generateCertificate(caInputChain);
                caInter = cf.generateCertificate(caInputInter);
                caRoot = cf.generateCertificate(caInputRoot);
                System.out.println("caChain=" + ((X509Certificate) caChain).getSubjectDN());
                System.out.println("caInter=" + ((X509Certificate) caInter).getSubjectDN());
                System.out.println("caRoot=" + ((X509Certificate) caRoot).getSubjectDN());
            } finally {
                caInputChain.close();
                caInputInter.close();
                caInputRoot.close();
            }

            // Create a KeyStore containing our trusted CAs
            String keyStoreType = KeyStore.getDefaultType();
            KeyStore keyStore = KeyStore.getInstance(keyStoreType);
            keyStore.load(null, null);
            keyStore.setCertificateEntry("caChain", caChain);
            keyStore.setCertificateEntry("caInter", caInter);
            keyStore.setCertificateEntry("caRoot", caRoot);

            // Create a TrustManager that trusts the CAs in our KeyStore
            String tmfAlgorithm = TrustManagerFactory.getDefaultAlgorithm();
            TrustManagerFactory tmf = TrustManagerFactory.getInstance(tmfAlgorithm);
            tmf.init(keyStore);

            // Create an SSLContext that uses our TrustManager
            SSLContext contextSSL = SSLContext.getInstance("TLS");
            contextSSL.init(null, tmf.getTrustManagers(), null);

            //conn.setSSLSocketFactory(contextSSL.getSocketFactory());


            URL url = new URL(URI);
            System.out.println(URI);
            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
            conn.setSSLSocketFactory(contextSSL.getSocketFactory());
            conn.setRequestMethod("GET");
            // read the response
            InputStream in = new BufferedInputStream(conn.getInputStream());
            output = convertStreamToString(in);

            /*if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));
            String test;
            while ((test = br.readLine()) != null){
                output.append(test);
            }
            conn.disconnect();*/
        }
        catch (IOException | CertificateException | NoSuchAlgorithmException | KeyStoreException | KeyManagementException e){
            e.printStackTrace();
        }
        return output;

    }

    private static String convertStreamToString(InputStream is) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();

        String line;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line).append('\n');
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }

    public static Login login(Context context, String username, String password) throws LoginError{

        String json = retrieve(context, "https://192.168.4.10/www/pfe/webservice.php?q=LOGON&user="+username+"&pass="+password);


        ObjectMapper mapper = new ObjectMapper();

        HashMap<String,String> map = new HashMap<>();
        try {
            map = mapper.readValue(json, HashMap.class);
        } catch (IOException e) {
            e.printStackTrace();
            map.put("error", e.getMessage());
        }

        if(map.containsKey("error")) {
            throw new LoginError(map.get("error"));
        }

        Login log = null;

        try {
            log = mapper.readValue(json, Login.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return log;
    }

    public static ListProjects listAllProjects(Context context, String username, String token) throws Error{
        String json = retrieve(context, "https://192.168.4.10/www/pfe/webservice.php?q=LIPRJ&user="+username+"&token="+token);

        ObjectMapper mapper = new ObjectMapper();
        HashMap<String,String> map = new HashMap<>();
        try {
            map = mapper.readValue(json, HashMap.class);
        } catch (IOException e) {
            e.printStackTrace();
            map.put("error", e.getMessage());
        }

        if(map.containsKey("error")) {
            throw new Error(map.get("error"));
        }

        ListProjects list = null;
        try {
            list = mapper.readValue(json, ListProjects.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return list;
    }

}
