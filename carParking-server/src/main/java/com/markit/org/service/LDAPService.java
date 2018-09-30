package com.markit.org.service;

import java.util.Hashtable;
import java.util.Optional;

import javax.naming.CompositeName;
import javax.naming.Context;
import javax.naming.Name;
import javax.naming.NameParser;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import javax.naming.ldap.InitialLdapContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.markit.org.entity.Employee;
import com.markit.org.entity.EmployeeRegistration;
import com.markit.org.repository.EmployeeRepository;


@Service
public  class LDAPService {
	
	@Autowired
	private  EmployeeRepository employeeRepository;
	
	private static final String contextFactory = "com.sun.jndi.ldap.LdapCtxFactory";
    private static final String connectionURL = "ldap://ams5ldap.markit.partners:389";
  //  private static final String connectionName = "CN=Hari Abdesh Gupta,OU=India,OU=APAC,OU=Standard Accounts2,OU=Standard Accounts,DC=markit,DC=partners";
    

    // Optioanl
    private static final String authentication = "simple";
    private static final String protocol = null;

  

    private static final String MEMBER_OF = "memberOf";
    private static final String[] attrIdsToSearch = new String[] { MEMBER_OF,"uid","mail","displayName","employeeID" };
    public static final String SEARCH_BY_ACCOUNT_NAME = "(sAMAccountName=%s)";
    public static final String SEARCH_GROUP_BY_GROUP_CN = "(&(objectCategory=group)(cn={0}))";
    private static String userBase = "DC=markit,DC=partners";

	public  Employee doLDAPAuthetication(String username, String password) {
		
		Hashtable<String, String> env = new Hashtable<String, String>();

        // Configure our directory context environment.
        String ldapusername = username + "@markit.partners";
        env.put(Context.INITIAL_CONTEXT_FACTORY, contextFactory);
        env.put(Context.PROVIDER_URL, connectionURL);
        env.put(Context.SECURITY_PRINCIPAL, ldapusername);
        env.put(Context.SECURITY_CREDENTIALS, password);
        if (authentication != null)
            env.put(Context.SECURITY_AUTHENTICATION, authentication);
        if (protocol != null)
            env.put(Context.SECURITY_PROTOCOL, protocol);

        System.out.println("Before Connection");
       // InitialDirContext   context = new InitialDirContext(env);
        try {
			DirContext ldapContext = new InitialLdapContext(env, null);
			System.out.println("Connected");
			String              filter  = String.format(SEARCH_BY_ACCOUNT_NAME, username);
			SearchControls      constraints = new SearchControls();
			constraints.setSearchScope(SearchControls.SUBTREE_SCOPE);
			constraints.setReturningAttributes(attrIdsToSearch);
			NamingEnumeration results = ldapContext.search(userBase, filter,constraints);
			// Fail if no entries found
			if (results == null || !results.hasMore()) {
				Employee employee = new Employee();
				employee.setIsAuthorize("False");
				return employee;
			}

			// Get result for the first entry found
			SearchResult result = (SearchResult) results.next();

			// Get the entry's distinguished name
			NameParser parser = ldapContext.getNameParser("");
			Name contextName = parser.parse(ldapContext.getNameInNamespace());
			Name baseName = parser.parse(userBase);

			Name entryName = parser.parse(new CompositeName(result.getName())
			        .get(0));

			// Get the entry's attributes
			Attributes attrs = result.getAttributes();
			Attribute attr = attrs.get(attrIdsToSearch[0]);
			Attribute emailAttr = attrs.get(attrIdsToSearch[2]);
			Attribute diaplayNameAttr = attrs.get(attrIdsToSearch[3]);
			Attribute employeeIDAttr = attrs.get(attrIdsToSearch[4]);
			String displayName = null,email = null,employeeID = null;
			

			NamingEnumeration emailEnum = emailAttr.getAll();
			System.out.println("Email Id  :");
			while (emailEnum.hasMore()) {
			     email = (String) emailEnum.next();
			    System.out.println(email);
			}
			
			NamingEnumeration displayNameEnum = diaplayNameAttr.getAll();
			System.out.println("Display Name  :");
			while (displayNameEnum.hasMore()) {
			     displayName = (String) displayNameEnum.next();
			    System.out.println(displayName);
			}
			
			NamingEnumeration employeeIDEnum = employeeIDAttr.getAll();
			System.out.println("Employee  Id  :");
			while (employeeIDEnum.hasMore()) {
			     employeeID = (String) employeeIDEnum.next();
			    System.out.println(employeeID);
			}
			
			
			Employee employee = new Employee();
			employee.setEmployeeName(displayName);
			employee.setEmail(email);
			employee.setEmployeeId(employeeID);
			employee.setIsAuthorize("True");
			Optional<EmployeeRegistration> registeredEmployee = employeeRepository.findById(employeeID);
			if(registeredEmployee.isPresent()){
				employee.setVehicleRegistrationNumber(registeredEmployee.get().getVehicleRegistrationNumber());
			}
			return employee;
			
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Employee employee = new Employee();
			employee.setIsAuthorize("False");
			return employee;
		}
		
      
		
		
	}

	
	
	

}