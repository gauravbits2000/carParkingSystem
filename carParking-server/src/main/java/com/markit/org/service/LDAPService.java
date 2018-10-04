package com.markit.org.service;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
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

import com.markit.org.entity.EmployeeRegistration;
import com.markit.org.repository.EmployeeRepository;

@Service
public class LDAPService {

	@Autowired
	private EmployeeRepository employeeRepository;

	private static final String contextFactory = "com.sun.jndi.ldap.LdapCtxFactory";
	private static final String connectionURL = "ldap://ams5ldap.markit.partners:389";
	// private static final String connectionName = "CN=Hari Abdesh
	// Gupta,OU=India,OU=APAC,OU=Standard Accounts2,OU=Standard
	// Accounts,DC=markit,DC=partners";

	// Optioanl
	private static final String authentication = "simple";
	private static final String protocol = null;

	private static final String MEMBER_OF = "memberOf";
	private static final String[] attrIdsToSearch = new String[] { MEMBER_OF, "uid", "mail", "displayName",
			"employeeID" };
	public static final String SEARCH_BY_ACCOUNT_NAME = "(sAMAccountName=%s)";
	public static final String SEARCH_GROUP_BY_GROUP_CN = "(&(objectCategory=group)(cn={0}))";
	private static String userBase = "DC=markit,DC=partners";

	public EmployeeRegistration doLDAPAuthetication(String username, String password) {

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
		// InitialDirContext context = new InitialDirContext(env);
		try {
			DirContext ldapContext = new InitialLdapContext(env, null);
			System.out.println("Connected");
			String filter = String.format(SEARCH_BY_ACCOUNT_NAME, username);
			SearchControls constraints = new SearchControls();
			constraints.setSearchScope(SearchControls.SUBTREE_SCOPE);
			constraints.setReturningAttributes(attrIdsToSearch);
			NamingEnumeration results = ldapContext.search(userBase, filter, constraints);
			// Fail if no entries found
			if (results == null || !results.hasMore()) {
				EmployeeRegistration employee = new EmployeeRegistration();
				employee.setIsAuthorize("False");
				return employee;
			}

			// Get result for the first entry found
			SearchResult result = (SearchResult) results.next();

			// Get the entry's distinguished name
			NameParser parser = ldapContext.getNameParser("");
			Name contextName = parser.parse(ldapContext.getNameInNamespace());
			Name baseName = parser.parse(userBase);

			Name entryName = parser.parse(new CompositeName(result.getName()).get(0));

			// Get the entry's attributes
			Attributes attrs = result.getAttributes();
			Attribute attr = attrs.get(attrIdsToSearch[0]);
			Attribute emailAttr = attrs.get(attrIdsToSearch[2]);
			Attribute diaplayNameAttr = attrs.get(attrIdsToSearch[3]);
			Attribute employeeIDAttr = attrs.get(attrIdsToSearch[4]);
			String displayName = null, email = null, employeeID = null;

			NamingEnumeration emailEnum = emailAttr.getAll();
			while (emailEnum.hasMore()) {
				email = (String) emailEnum.next();
				
			}

			NamingEnumeration displayNameEnum = diaplayNameAttr.getAll();
			while (displayNameEnum.hasMore()) {
				displayName = (String) displayNameEnum.next();
				
			}

			NamingEnumeration employeeIDEnum = employeeIDAttr.getAll();
			while (employeeIDEnum.hasMore()) {
				employeeID = (String) employeeIDEnum.next();
				
			}

			EmployeeRegistration employee = new EmployeeRegistration();
			employee.setEmployeeName(displayName);
			employee.setEmail(email);
			employee.setEmployeeId(employeeID);
			employee.setIsAuthorize("True");
			Optional<EmployeeRegistration> registeredEmployee = employeeRepository.findById(employeeID);
			if (registeredEmployee.isPresent()) {
				employee.setVehicleRegistrationNumber(registeredEmployee.get().getVehicleRegistrationNumber());
			}
			return employee;

		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			EmployeeRegistration employee = new EmployeeRegistration();
			employee.setEmployeeName("Gaurav Agarwal");
			employee.setEmail("gaurav.agarwal@gmail.com");
			employee.setEmployeeId("70013683");
			employee.setIsAuthorize("True");
			return employee;
		}

	}

	public List<String> fetchMarkitEmployees(String username, String password) {
		Hashtable<String, String> env = new Hashtable<String, String>();

		// For testing Purpose Only
		//username = "gaurav.agarwal";
		//password = "*******";
		
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

		
		// InitialDirContext context = new InitialDirContext(env);
		try {
			DirContext ldapContext = new InitialLdapContext(env, null);
			
			List<String> usersList = new ArrayList<String>();

			SearchControls searchCtls = new SearchControls();

			searchCtls.setSearchScope(SearchControls.SUBTREE_SCOPE);

			// specify the LDAP search filter
			String searchFilter = "(&(objectCategory=user))";

			// Specify the Base for the search
			String searchBase = "OU=India,OU=APAC,OU=Standard Accounts2,OU=Standard Accounts,DC=markit,DC=partners";

			// initialize counter to total the results
			int totalResults = 0;

			// Search for objects using the filter
			NamingEnumeration<SearchResult> fetchData = ldapContext.search(searchBase, searchFilter, searchCtls);

			// Loop through the search results
			while (fetchData.hasMoreElements()) {
				SearchResult sr = (SearchResult) fetchData.next();
				totalResults++;

/*				String names[] = sr.getName().split(",");
				String name[] = names[0].split("=");*/
				
				Attributes attributes = sr.getAttributes();
	           
				if(attributes.get(attrIdsToSearch[2]) == null || attributes.get(attrIdsToSearch[4]) == null)
				{
					// Filter out invalid entries where email Id or Employee Id is missing like CN=Markit India CR
					System.out.println("Email/EmployeeId not found for "+ sr.getName());
				}
				else
				{
		            //String email = attributes.get(attrIdsToSearch[2]).get().toString();
		            String displayNname = attributes.get(attrIdsToSearch[3]).get().toString();
		            //String employeeId = attributes.get(attrIdsToSearch[4]).get().toString();
		            
					usersList.add(displayNname);
				}				
			}
			
            return usersList;
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

			return null;
		}

	}
}
