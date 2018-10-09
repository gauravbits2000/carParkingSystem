package com.markit.org;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import javax.naming.ldap.InitialLdapContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.markit.org.entity.EmployeesDetails;
import com.markit.org.repository.EmployeeDetailsRepository;

@SpringBootApplication
public class MarkitCarParkingSystemApplication implements ApplicationRunner  {
	
	@Autowired
	private EmployeeDetailsRepository empDetailsRepository;

	public static void main(String[] args) 
	{
		SpringApplication.run(MarkitCarParkingSystemApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		
		final String authentication = "simple";
		final String protocol = null;

		final String MEMBER_OF = "memberOf";
		final String[] attrIdsToSearch = new String[] { MEMBER_OF, "uid", "mail", "displayName",
				"employeeID" };
		final String SEARCH_BY_ACCOUNT_NAME = "(sAMAccountName=%s)";
		final String SEARCH_GROUP_BY_GROUP_CN = "(&(objectCategory=group)(cn={0}))";
		String userBase = "DC=markit,DC=partners";
		Hashtable<String, String> env = new Hashtable<String, String>();

		// For testing Purpose Only
		String username = "nikhil.chitranshi";
		String password = "homeland@123";
		
		// Configure our directory context environment.
		String ldapusername = username + "@markit.partners";
		env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
		env.put(Context.PROVIDER_URL, "ldap://ams5ldap.markit.partners:389");
		env.put(Context.SECURITY_PRINCIPAL, ldapusername);
		env.put(Context.SECURITY_CREDENTIALS, password);
		if (authentication != null)
			env.put(Context.SECURITY_AUTHENTICATION, authentication);
		if (protocol != null)
			env.put(Context.SECURITY_PROTOCOL, protocol);

		
		// InitialDirContext context = new InitialDirContext(env);
		try {
			DirContext ldapContext = new InitialLdapContext(env, null);
			
			List<EmployeesDetails> employeesList = new ArrayList<EmployeesDetails>();

			SearchControls searchCtls = new SearchControls();

			searchCtls.setSearchScope(SearchControls.SUBTREE_SCOPE);

			// specify the LDAP search filter
			String searchFilter = "(&(objectCategory=user))";

			// Specify the Base for the search
			String searchBase = "OU=Noida,OU=APAC,OU=Standard Accounts2,OU=Standard Accounts,DC=markit,DC=partners";

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
		            String employeeEmail = attributes.get(attrIdsToSearch[2]).get().toString();
		            String employeeName = attributes.get(attrIdsToSearch[3]).get().toString();
		            String employeeId = attributes.get(attrIdsToSearch[4]).get().toString();
		            EmployeesDetails empDetails = new EmployeesDetails(employeeName,employeeId,employeeEmail);
		            employeesList.add(empDetails);
				}				
			}
			
			empDetailsRepository.saveAll(employeesList);
			
			
			
           
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

			
		}
		
	}
}
