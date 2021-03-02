package br.eti.deividferreira.legalpecas.app.controllers.security;

import java.util.UUID;

import javax.annotation.PostConstruct;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.eti.deividferreira.legalpecas.app.components.security.Administrator;
import br.eti.deividferreira.legalpecas.domain.dto.UserForm;
import br.eti.deividferreira.legalpecas.domain.dto.validation.Adding;
import br.eti.deividferreira.legalpecas.domain.dto.validation.Editing;
import br.eti.deividferreira.legalpecas.domain.entities.security.User;
import br.eti.deividferreira.legalpecas.domain.repositories.security.UserRepository;
import br.eti.deividferreira.legalpecas.domain.services.UserService;
import br.eti.deividferreira.legalpecas.infra.misc.RestPreconditions;

@RestController
@RequestMapping("/api/users")
public class UsersController {
	
	@Autowired
    private UserService userService;
	@Autowired
    private UserRepository userRepository;
    @Autowired
    private ModelMapper modelMapper;
    
    
    @PostConstruct
    protected void configureMapper() {
        this.modelMapper.createTypeMap(User.class, UserForm.class)
                .addMapping(source -> "", UserForm::setPassword);
    }


    @GetMapping
    @Administrator
    public ResponseEntity<Page<User>> get(@RequestParam String filter, Pageable pageable) {
        return RestPreconditions.checkFound(this.userRepository.findByFilter(filter, pageable));
    }

    @Administrator
    @GetMapping("/{userId}")
    public ResponseEntity<UserForm> getById(@PathVariable UUID userId) {
        return this.userRepository.findById(userId)
                .map(found -> ResponseEntity.ok(this.modelMapper.map(found, UserForm.class)))
                .orElse(ResponseEntity.noContent().build());
    }

    @PostMapping
    @Administrator
    public ResponseEntity<User> save(@RequestBody @Validated(Adding.class) UserForm model) {
        this.userService.save(this.modelMapper.map(model, User.class), model.getAuthorities());
        return ResponseEntity.ok().build();
    }

    @Administrator
    @PutMapping("/{userId}")
    public ResponseEntity<UserForm> update(@PathVariable UUID userId,
                                           @RequestBody @Validated(Editing.class) UserForm model) {
        return this.userRepository.findById(userId)
                .map(found -> {
                    final var user = this.modelMapper.map(model, User.class);
                    final var saved = this.userService.update(user, model.getAuthorities());
                    return ResponseEntity.ok(this.modelMapper.map(saved, UserForm.class));
                })
                .orElse(ResponseEntity.badRequest().build());
    }

    @Administrator
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable UUID userId) {
        return this.userRepository.findById(userId).map(user -> {
            this.userRepository.delete(user);
            return ResponseEntity.ok().build();
        }).orElse(ResponseEntity.badRequest().build());
    }

}
