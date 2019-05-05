package br.edu.utfpr.cp.emater.midmipsystem.view.base.dto;

import br.edu.utfpr.cp.emater.midmipsystem.domain.base.Farmer;
import br.edu.utfpr.cp.emater.midmipsystem.domain.base.Role;
import br.edu.utfpr.cp.emater.midmipsystem.domain.base.Supervisor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private long id;
    private String login;
    private Role role;
    private Supervisor supervisor;
    private Farmer farmer;
}