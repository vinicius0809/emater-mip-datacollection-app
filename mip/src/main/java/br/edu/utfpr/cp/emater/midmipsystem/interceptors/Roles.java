/*
package br.edu.utfpr.cp.emater.midmipsystem.interceptors;

import br.edu.utfpr.cp.emater.midmipsystem.domain.base.Role;
import br.edu.utfpr.cp.emater.midmipsystem.domain.base.RoleRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@NoArgsConstructor
public enum Roles {

    ADMIN(),
    SUPERVISOR(),
    FARMER();

//    private Map<String, String> domain_grants = new HashMap<>();
    private List<String> domains = Arrays.asList("city", "region", "macroregion", "field", "harvest", "pest", "supervisor", "users", "farmer", "survey-field", "pest-survey");

    @Autowired
    private RoleRepository roleRepository;

*/
/*
    Roles(List<String> grants) {
        domains = Arrays.asList("city", "region", "macroregion", "field", "harvest", "pest", "supervisor", "users", "farmer", "survey-field", "pest-survey");

        for (var i = 0; i < domains.size(); i++) {
            setGrants(domains.get(i), grants.get(i));
        }
    }
    *//*


    public Role getRole(long id){
        return roleRepository.findById(id).get();
    }

*/
/*    private void setGrants(String name, String value) {
        domain_grants.put(name, value);
    }

    public String getGrants(String domainName){
        return domain_grants.get(domainName.toLowerCase());
    }*//*


    public List<String> getDomains(){
        return domains;
    }
}
*/
