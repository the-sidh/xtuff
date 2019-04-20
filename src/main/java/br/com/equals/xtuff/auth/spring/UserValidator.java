package br.com.equals.xtuff.auth.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import br.com.equals.xtuff.domain.entities.Comerciante;
import br.com.equals.xtuff.domain.services.ComercianteService;

@Component
public class UserValidator implements Validator {

    @Autowired
    private ComercianteService service;

    @Override
    public boolean supports(Class<?> aClass) {
        return Comerciante.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Comerciante user = (Comerciante) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "NotEmpty");
        if (user.getEmail().length() < 6 || user.getEmail().length() > 150) {
            errors.rejectValue("email", "Size.userForm.username");
        }
        if (!user.getEmail().contains("@")) {
            errors.rejectValue("email", "Format.userForm.username");
        }
        if (service.findByEmail(user.getEmail()) != null) {
            errors.rejectValue("email", "Duplicate.userForm.username");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "senha", "NotEmpty");
        if (user.getSenha().length() < 8 || user.getSenha().length() > 32) {
            errors.rejectValue("senha", "Size.userForm.password");
        }

        if (!user.getPasswordConfirm().equals(user.getSenha())) {
            errors.rejectValue("passwordConfirm", "Diff.userForm.passwordConfirm");
        }
    }
}
