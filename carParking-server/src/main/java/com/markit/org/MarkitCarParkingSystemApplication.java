package com.markit.org;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import javax.naming.ldap.Control;
import javax.naming.ldap.InitialLdapContext;
import javax.naming.ldap.LdapContext;
import javax.naming.ldap.PagedResultsControl;
import javax.naming.ldap.PagedResultsResponseControl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.markit.org.entity.EmployeesDetails;
import com.markit.org.repository.EmployeeDetailsRepository;
import com.markit.org.service.ParkingDrawService;

@SpringBootApplication
public class MarkitCarParkingSystemApplication implements ApplicationRunner  {
	
	@Autowired
	private EmployeeDetailsRepository empDetailsRepository;
	
	@Autowired
	private  ParkingDrawService parkingDrawService;

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
				"employeeID", "l","employeeNumber" };
		String adminGroup ="CN=gsg-PARKING_ADMIN_GROUP,OU=Global Security Groups,OU=Standard Accounts,DC=markit,DC=partners";
		//String userBase = "DC=markit,DC=partners";
		Hashtable<String, String> env = new Hashtable<String, String>();

		// For testing Purpose Only
		String username = "brd_test_user";
		String password = "_^$Lz(54Zq_c";
		
		// Configure our directory context environment.
		String ldapusername = username + "@markit.partners";
		env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
		env.put(Context.PROVIDER_URL, "ldap://ams5ldap.markit.partners:389");
		env.put(Context.SECURITY_PRINCIPAL, ldapusername);
		env.put(Context.SECURITY_CREDENTIALS, password);
		if (authentication != null)
			env.put(Context.SECURITY_AUTHENTICATION, authentication);

		
		
		try {
			LdapContext ldapContext = new InitialLdapContext(env, null);
			
			List<EmployeesDetails> employeesList = new ArrayList<EmployeesDetails>();

			SearchControls searchCtls = new SearchControls();
			searchCtls.setCountLimit(4000);

			searchCtls.setSearchScope(SearchControls.SUBTREE_SCOPE);

			// specify the LDAP search filter
			String searchFilter = "(&(objectCategory=user))";

			// Specify the Base for the search
			String searchBase = "OU=India,OU=APAC,OU=Standard Accounts2,OU=Standard Accounts,DC=markit,DC=partners";

			int total;
			byte[] cookie = null;
			ldapContext.setRequestControls(new Control[] { new PagedResultsControl(1000,Control.NONCRITICAL) });
			
			
			do{
				
				// Search for objects using the filter
				NamingEnumeration<SearchResult> fetchData = ldapContext.search(searchBase, searchFilter, searchCtls);

				// Loop through the search results
				while (fetchData.hasMoreElements()) {
					SearchResult sr = (SearchResult) fetchData.next();
					Attributes attributes = sr.getAttributes();

					if (attributes.get(attrIdsToSearch[2]) == null  || attributes.get(attrIdsToSearch[5]) == null) {
						// Filter out invalid entries where email Id or Employee Id
						// is missing like CN=Markit India CR
						System.out.println("Email/EmployeeId not found for " + sr.getName());
					} else {
						String isAdmin = "N";
						String group = null;
						String employeeId = "";
						Attribute attr = attributes.get(attrIdsToSearch[0]);
						if (null != attr) {
							NamingEnumeration memberOfEnum = attr.getAll();
							while (memberOfEnum.hasMore()) {

								group = (String) memberOfEnum.next();
								if (adminGroup.equalsIgnoreCase(group))
								{
									isAdmin = "Y";
								}

							}
						}
						String employeeEmail = attributes.get(attrIdsToSearch[2]).get().toString();
						String employeeName = attributes.get(attrIdsToSearch[3]).get().toString();
						if(attributes.get(attrIdsToSearch[4]) != null){
							employeeId = attributes.get(attrIdsToSearch[4]).get().toString();
						}
						if(attributes.get(attrIdsToSearch[6]) != null){
							employeeId = attributes.get(attrIdsToSearch[6]).get().toString();
						}
						
						String baseLocation = attributes.get(attrIdsToSearch[5]).get().toString();
						
						if(employeeId.equals("70013683") )
						{
							isAdmin = "Y";
						}
						
						EmployeesDetails empDetails = new EmployeesDetails(employeeName, employeeId, employeeEmail,
								isAdmin,baseLocation);
						employeesList.add(empDetails);
					}
				}
				empDetailsRepository.saveAll(employeesList);
				
				  // Examine the paged results control response
		        Control[] controls = ldapContext.getResponseControls();
		        if (controls != null) {
		          for (int i = 0; i < controls.length; i++) {
		            if (controls[i] instanceof PagedResultsResponseControl) {
		              PagedResultsResponseControl prrc = (PagedResultsResponseControl) controls[i];
		              total = prrc.getResultSize();
		              if (total != 0) {
		                System.out.println("***************** END-OF-PAGE "
		                    + "(total : " + total + ") *****************\n");
		              } else {
		                System.out.println("***************** END-OF-PAGE "
		                    + "(total: unknown) ***************\n");
		              }
		              cookie = prrc.getCookie();
		            }
		          }
		        } else {
		          System.out.println("No controls were sent from the server");
		        }
		        // Re-activate paged results
		        ldapContext.setRequestControls(new Control[] { new PagedResultsControl(
		            1000, cookie, Control.CRITICAL) });
				
				
				
				
			}while(cookie != null);
			
			ldapContext.close();
			
			

		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

			
		}
		
	}
}
