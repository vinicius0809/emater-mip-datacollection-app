package br.edu.utfpr.cp.emater.midmipsystem.view.base.controller;

import br.edu.utfpr.cp.emater.midmipsystem.domain.base.User;
import br.edu.utfpr.cp.emater.midmipsystem.domain.base.UserRepository;
import br.edu.utfpr.cp.emater.midmipsystem.view.base.dto.UserDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping (value = "/user")
public class UserController {

    private final UserRepository repository;

    private final Environment environment;

    private boolean operationSuccessMessage;

    private void resetOperationSuccessMessage() {
        if (this.operationSuccessMessage)
            this.operationSuccessMessage = false;
    }

    private void setOperationSuccessMessage() {
        this.operationSuccessMessage = true;
    }

    @Autowired
    public UserController(UserRepository repository, Environment environment) {
        this.repository = repository;
        this.environment = environment;
        this.operationSuccessMessage = false;
    }

    @RequestMapping (value = "", method = RequestMethod.GET)
    public String listAll(Model data) {
        data.addAttribute("users", repository.findAll());
        data.addAttribute("success", this.operationSuccessMessage);

        this.resetOperationSuccessMessage();

        data.addAttribute("urlCreate", environment.getProperty("app.view.route.create.field.user"));
        data.addAttribute("urlUpdate", environment.getProperty("app.view.route.update.field.user"));
        data.addAttribute("urlDelete", environment.getProperty("app.view.route.delete.field.user"));

        return environment.getProperty("app.view.route.template.main.field.user");
    }

    @RequestMapping (value = "/create", method = RequestMethod.POST)
    public String create (UserDTO user) {

        User f = new User();
        f.setLogin(user.getLogin());

        repository.save(f);

        this.setOperationSuccessMessage();

        return environment.getProperty("app.view.route.create.success.field.user");
    }

    @RequestMapping (value = "/update", method = RequestMethod.POST)
    public String update (UserDTO user) {

        User mr = repository.findById((long)user.getId()).orElseThrow();
        mr.setLogin(user.getLogin());

        repository.saveAndFlush(mr);

        this.setOperationSuccessMessage();

        return environment.getProperty("app.view.route.update.success.field.user");
    }

    @RequestMapping (value = "/delete", method = RequestMethod.POST)
    public String delete (@RequestParam int id) {
        repository.deleteById((long)id);

        this.setOperationSuccessMessage();

        return environment.getProperty("app.view.route.delete.success.field.user");
    }
}
