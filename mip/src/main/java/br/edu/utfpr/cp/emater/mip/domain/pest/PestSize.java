package br.edu.utfpr.cp.emater.mip.domain.pest;

public enum PestSize {
    
    GREATER_15CM ("> 15 cm"),
    SMALLER_15CM ("< 15 cm");
    
    private String name;
    
    PestSize (String name) {
        this.name = name;
    }
    
    public String getName() {
        return this.name;
    }
    
    public void setName (String name) {
        this.name = name;
    }
}
