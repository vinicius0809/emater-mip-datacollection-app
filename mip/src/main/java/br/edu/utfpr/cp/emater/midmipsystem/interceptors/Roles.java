package br.edu.utfpr.cp.emater.midmipsystem.interceptors;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public enum Roles {

    ADMIN(Arrays.asList("C-R-U-D","C-R-U-D","C-R-U-D","C-R-U-D","C-R-U-D","C-R-U-D","C-R-U-D","C-R-U-D")),
    SUPERVISOR(Arrays.asList("x-r-x-x","x-r-x-x","x-r-x-x","c-r-u-d","c-r-u-d","C-R-U-D","x-R-u-x","C-R-U-D")),
    FARMER(Arrays.asList("x-r-x-x","x-r-x-x","x-r-x-x","x-r-x-x","x-r-u-d","x-r-x-x","x-x-x-x","x-r-u-x"));

    private Map<String, String> domain_grants;
    private List<String> domains;

    Roles(List<String> grants) {
        domains = Arrays.asList("City", "Region", "Macroregion", "Field", "Harvest Pest", "Supervisors", "Users");

        for (var i = 0; i < domains.size(); i++) {
            setGrants(domains.get(i), grants.get(i));
        }
    }

    private void setGrants(String name, String value) {
        domain_grants.put(name, value);
    }

    public String getGrants(String domainName){
        return domain_grants.get(domainName);
    }

    public Map<String, String> getDomainGrants(){
        return domain_grants;
    }
}
