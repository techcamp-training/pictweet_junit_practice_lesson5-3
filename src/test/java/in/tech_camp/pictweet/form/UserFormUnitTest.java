package in.tech_camp.pictweet.form;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;

import in.tech_camp.pictweet.validation.ValidGroup1;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

@ActiveProfiles("test")
public class UserFormUnitTest {
    @Test
    public void nicknameが空では登録できない () {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        UserForm userForm = new UserForm();
        userForm.setNickname("");
        userForm.setEmail("test@example.com");
        userForm.setPassword("password");
        userForm.setPasswordConfirmation("password");

        Set<ConstraintViolation<UserForm>> violations = validator.validate(userForm, ValidGroup1.class);

        assertEquals(1, violations.size());
        assertEquals("Nickname can't be blank", violations.iterator().next().getMessage());
    }

    @Test
    public void emailが空では登録できない() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        UserForm userForm = new UserForm();
        userForm.setNickname("techcamp");
        userForm.setEmail("");
        userForm.setPassword("password");
        userForm.setPasswordConfirmation("password");

        Set<ConstraintViolation<UserForm>> violations = validator.validate(userForm, ValidGroup1.class);

        assertEquals(1, violations.size());
        assertEquals("Email can't be blank", violations.iterator().next().getMessage());
    }
}
