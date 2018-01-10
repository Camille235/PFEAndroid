package fr.eseo.dis.camille.pfeandroid;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.HashMap;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;

import errors.LoginError;
import fr.eseo.dis.camille.pfeandroid.dto.juries.JuryInfo;
import fr.eseo.dis.camille.pfeandroid.dto.juries.ListJuries;
import fr.eseo.dis.camille.pfeandroid.dto.projects.ListProjects;
import fr.eseo.dis.camille.pfeandroid.dto.login.Login;
import fr.eseo.dis.camille.pfeandroid.dto.note.NoteInfo;

/**
 * Created by Arthur on 20/12/2017.
 */

public class WebServices {

    private static String retrieve(Context context, String URI) {
        String output = null;
        try {
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
        }
        catch (IOException | CertificateException | NoSuchAlgorithmException | KeyStoreException | KeyManagementException e){
            e.printStackTrace();
        }
        return output;
    }


    private static InputStream retrieveImage(Context context, String URI) {
        InputStream in = null;
        try {
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
            in = new BufferedInputStream(conn.getInputStream());
        }
        catch (IOException | CertificateException | NoSuchAlgorithmException | KeyStoreException | KeyManagementException e){
            e.printStackTrace();
        }
        return in;
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
        errorHandling(json);
        ObjectMapper mapper = new ObjectMapper();
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
        errorHandling(json);
        ObjectMapper mapper = new ObjectMapper();
        ListProjects list = null;
        try {
            list = mapper.readValue(json, ListProjects.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return list;
    }

    public static ListProjects listMyProjects(Context context, String username, String token) throws Error{
        String json = retrieve(context, "https://192.168.4.10/www/pfe/webservice.php?q=MYPRJ&user="+username+"&token="+token);
        errorHandling(json);
        ObjectMapper mapper = new ObjectMapper();
        ListProjects list = null;
        try {
            list = mapper.readValue(json, ListProjects.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return list;
    }

    public static ListJuries listAllJuries(Context context, String username, String token) throws Error{
        String json = retrieve(context, "https://192.168.4.10/www/pfe/webservice.php?q=LIJUR&user="+username+"&token="+token);
        errorHandling(json);
        ObjectMapper mapper = new ObjectMapper();
        ListJuries list = null;
        try {
            list = mapper.readValue(json, ListJuries.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return list;
    }

    public static ListJuries listMyJuries(Context context, String username, String token) throws Error{
        String json = retrieve(context, "https://192.168.4.10/www/pfe/webservice.php?q=MYJUR&user="+username+"&token="+token);
        errorHandling(json);
        ObjectMapper mapper = new ObjectMapper();
        ListJuries list = null;
        try {
            list = mapper.readValue(json, ListJuries.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return list;
    }

    public static JuryInfo juryinfo(Context context, String username, String token, String juryId) throws Error{
        String json = retrieve(context, "https://192.168.4.10/www/pfe/webservice.php?q=JYINF&user="+username+"&jury="+juryId+"&token="+token);
        errorHandling(json);
        ObjectMapper mapper = new ObjectMapper();
        JuryInfo jury = null;
        try {
            jury = mapper.readValue(json, JuryInfo.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return jury;
    }

    /**
     * Return a project's poster
     * @param context the android context
     * @param username the username of the user
     * @param token the token from login (see login() )
     * @param proj the number of the project
     * @param style either FULL, THUMB, FLB64, THB64.
     *             By default it returns the full sized image.
     *             An optional parameter style allows a user to choose what format they wish to download the image in,
     *             either PNG both full size and thumbnail or a PNG le encoded in Base64.
     * @return a Bitmap used for ???
     * @throws Error
     */
    public static Bitmap poster(Context context, String username, String token, String proj, String style) {
        InputStream is = retrieveImage(context, "https://192.168.4.10/www/pfe/webservice.php?" +
                "q=POSTR&user="+username+"&proj="+proj+"&style="+style+"&token="+token);

        /*String response = convertStreamToString(is);
            if("{".equals(response.substring(0,0))){
            errorHandling(response);
        }*/
        return BitmapFactory.decodeStream(is);
    }

    public static NoteInfo notes(Context context, String username, String token, String proj) throws Error{
        String json = retrieve(context, "https://192.168.4.10/www/pfe/webservice.php?" +
                "q=NOTES&user="+username+"&proj="+proj+"&token="+token);
        errorHandling(json);
        ObjectMapper mapper = new ObjectMapper();
        NoteInfo note = null;
        try {
            note = mapper.readValue(json, NoteInfo.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return note;
    }

    public static void newNote(Context context, String username, String token, String proj, String student, String note) throws Error{
        String json = retrieve(context, "https://192.168.4.10/www/pfe/webservice.php?" +
                "q=NEWNT&user="+username+"&proj="+proj+"&student="+student+"&note="+note+"&token="+token);
        errorHandling(json);
    }


    public static HashMap<String, Object> porte(Context context, String username, String token, String seed) throws Error{
        String json = retrieve(context, "https://192.168.4.10/www/pfe/webservice.php?" +
                "q=PORTE&user="+username+"&seed="+seed+"&token="+token);
        errorHandling(json);
        ObjectMapper mapper = new ObjectMapper();
        HashMap<String, Object> result = null;
        try {
            result = mapper.readValue(json, HashMap.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    public static HashMap<String, Object> porte(Context context, String username, String token) throws Error{
        String json = retrieve(context, "https://192.168.4.10/www/pfe/webservice.php?" +
                "q=PORTE&user="+username+"&token="+token);
        errorHandling(json);
        ObjectMapper mapper = new ObjectMapper();
        HashMap<String, Object> result = null;
        try {
            result = mapper.readValue(json, HashMap.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    private static void errorHandling(String json) throws Error{
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
    }

}
