package edu.wustl.mir.ctt.directory;

import javax.naming.*;
import javax.naming.directory.*;
import java.util.*;
import java.security.*;

public class ADConnection {
    DirContext ldapContext;
    String baseName = ",dc=nrg,dc=mir";
    String host = "nrg.mir";

    public ADConnection() {
        try {
            Hashtable ldapEnv = new Hashtable(11);
            ldapEnv.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
            ldapEnv.put(Context.PROVIDER_URL, "ldaps://" + host + ":636");
            ldapEnv.put(Context.SECURITY_AUTHENTICATION, "simple");
            ldapEnv.put(Context.SECURITY_PRINCIPAL, "CN=ECP LDAP,OU=Service Accounts,OU=NRG Users,DC=nrg,DC=mir");
//            ldapEnv.put(Context.SECURITY_CREDENTIALS, "Needs a LDAP password");
            ldapEnv.put(Context.SECURITY_PROTOCOL, "ssl");
            ldapContext = new InitialDirContext(ldapEnv);
        }
        catch (Exception e) {
            System.out.println(" bind error: " + e);
            e.printStackTrace();
            System.exit(-1);
        }
    }

//    public void updatePassword(String username, String password) {
//        try {
//            String baseName = ",OU=Users,OU=ECP Registry,DC=nrg,DC=mir";
//            String quotedPassword = "\"" + password + "\"";
//            char unicodePwd[] = quotedPassword.toCharArray();
//            byte pwdArray[] = new byte[unicodePwd.length * 2];
//            for (int i=0; i<unicodePwd.length; i++) {
//                pwdArray[i*2 + 1] = (byte) (unicodePwd[i] >>> 8);
//                pwdArray[i*2 + 0] = (byte) (unicodePwd[i] & 0xff);
//            }
//            ModificationItem[] mods = new ModificationItem[1];
//            mods[0] = new ModificationItem(DirContext.REPLACE_ATTRIBUTE,
//                    new BasicAttribute("UnicodePwd", pwdArray));
//            ldapContext.modifyAttributes("sAMAccountName=" + username + baseName, mods);
//        }
//        catch (Exception e) {
//            System.out.println("update password error: " + e);
//            System.exit(-1);
//        }
//    }

    public void updatePassword(String userDN, String password) {
        try {
            String quotedPassword = "\"" + password + "\"";
            char unicodePwd[] = quotedPassword.toCharArray();
            byte pwdArray[] = new byte[unicodePwd.length * 2];
            for (int i=0; i<unicodePwd.length; i++) {
                pwdArray[i*2 + 1] = (byte) (unicodePwd[i] >>> 8);
                pwdArray[i*2 + 0] = (byte) (unicodePwd[i] & 0xff);
            }
            ModificationItem[] mods = new ModificationItem[1];
            mods[0] = new ModificationItem(DirContext.REPLACE_ATTRIBUTE,
                    new BasicAttribute("UnicodePwd", pwdArray));
            ldapContext.modifyAttributes(userDN, mods);
        }
        catch (Exception e) {
            System.out.println("update password error: " + e);
            System.exit(-1);
        }
    }

    public SearchResult findAccountByAccountName( String accountName) throws NamingException {

        String baseName = "OU=Users,OU=ECP Registry,DC=nrg,DC=mir";
        String searchFilter = "(&(objectClass=user)(sAMAccountName=" + accountName + "))";

        SearchControls searchControls = new SearchControls();
        searchControls.setSearchScope(SearchControls.SUBTREE_SCOPE);

        NamingEnumeration<SearchResult> results = ldapContext.search(baseName, searchFilter, searchControls);

        SearchResult searchResult = null;
        if(results.hasMoreElements()) {
            searchResult = (SearchResult) results.nextElement();

            //make sure there is not another item available, there should be only 1 match
            if(results.hasMoreElements()) {
                System.err.println("Matched multiple users for the accountName: " + accountName);
                return null;
            }
        }

        return searchResult;
    }

    public static void main(String[] args) {
        Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
// the keystore that holds trusted root certificates
//        System.setProperty("javax.net.ssl.trustStore", "c:\\myCaCerts.jks");
        System.setProperty("javax.net.debug", "all");
        ADConnection adc = new ADConnection();

        try {
            SearchResult account = adc.findAccountByAccountName("drm_sc");
            System.out.println(account);
            adc.updatePassword( account.getNameInNamespace(), "fubar123XYZ");
        } catch (NamingException e) {
            e.printStackTrace();
        }

    }
}
